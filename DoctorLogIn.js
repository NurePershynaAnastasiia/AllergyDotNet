const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    fs.readFile('doctorLogin.json', 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({ error: 'Error reading the file' });
        }
        try {
            const doctorData = JSON.parse(data);
            db.get('SELECT doctor_id, doctor_password, isAdmin FROM Doctors WHERE doctor_email = ?', [doctorData.doctor_email], (err, row) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error retrieving data from the database');
                }

                if (row) {
                    if (row.doctor_password === doctorData.doctor_password) {
                        if(!doctorData.isAdmin){
                            res.status(200).json({ doctor_id: row.doctor_id, isAdmin: false });

                        }else{
                            res.status(200).json({ doctor_id: row.doctor_id, isAdmin: true });
                        }
                    } else {
                        res.status(400).json({ error: 'Invalid password' });
                    }
                } else {
                    res.status(200).json({ doctor_id: false });
                }
            });
        } catch (error) {
            res.status(400).json({ error: 'Invalid JSON file' });
        }
    });
});

// Запуск сервера
app.use(express.static(__dirname));
app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});