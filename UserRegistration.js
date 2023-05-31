const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite
const query = `INSERT INTO Users (user_name, user_email, user_password)
               VALUES ($user_name, $user_email, $user_password)`;

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    fs.readFile(`newUser.json`, 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({error: 'Error reading the file'});
        }
        try {
            const userData = JSON.parse(data);
            if (userData.user_password == userData.user_password1) {//pass comp
                // Insert the data into the Users table
                db.run(query, {
                    $user_name: userData.user_name,
                    $user_email: userData.user_email,
                    $user_password: userData.user_password
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