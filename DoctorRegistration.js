const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite
const query = `INSERT INTO Doctors (doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_documents)
               VALUES ($doctor_name, $doctor_email, $doctor_password, $doctor_IBAN, $doctor_documents)`;

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    //fs.readFile(`newDoctor.json`, 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({error: 'Error reading the file'});
        }
        try {
            const doctorData = JSON.parse(data);
            if (doctorData.doctor_password == doctorData.doctor_password1) {//remove
                // Insert the data into the Users table
                db.run(query, {
                    $doctor_name: doctorData.doctor_name,
                    $doctor_email: doctorData.doctor_email,
                    $doctor_password: doctorData.doctor_password,
                    $doctor_IBAN: doctorData.doctor_IBAN,
                    $doctor_documents: doctorData.doctor_documents
                }, function (err) {
                    if (err) {
                        console.error(err);
                        return res.status(500).send('Error inserting data into the database');
                    }
                });
                res.json({message: 'Data downloaded and inserted successfully'});
            } else {
                res.status(400).json({error: 'Invalid password'});
            }

        } catch (error) {
            res.status(400).json({error: 'Invalid JSON file'});
        }
    });
});

// Запуск сервера
app.use(express.static(__dirname));
app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});