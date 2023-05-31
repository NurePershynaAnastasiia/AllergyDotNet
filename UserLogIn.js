const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite
/*const query = `SELECT user_email, user_password FROM USERS
                                 WHERE (user_email = $user_email AND user_password = $user_password)`;*/

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    fs.readFile('userLogin.json', 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({ error: 'Error reading the file' });
        }
        try {
            const userData = JSON.parse(data);
            db.get('SELECT user_id, user_password FROM Users WHERE user_email = ?', [userData.user_email], (err, row) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error retrieving data from the database');
                }

                if (row) {
                    if (row.user_password === userData.user_password) {
                        res.status(200).json({ user_id: row.user_id });
                    } else {
                        res.status(400).json({ error: 'Invalid password' });
                    }
                } else {
                    res.status(200).json({ user_id: false });
                }
            });
        } catch (error) {
            res.status(400).json({ error: 'Invalid JSON file' });
        }
    });
});

/*
app.post('/', (req, res) => {
    fs.readFile(`userLogin.json`, 'utf8', async (err, data) => {
        const userData = JSON.parse(data);
        if (err) {
            return res.status(500).json({error: 'Error reading the file'});
        }
        try {
            db.get('SELECT user_id FROM Users WHERE user_email = ? AND user_password = ?', [userData.user_email, userData.user_password], (err, row) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error retrieving data from the database');
                }
                res.

            });

        } catch (error) {
            res.status(400).json({error: 'Invalid JSON file'});
        }
    });
});
*/

// Запуск сервера
app.use(express.static(__dirname));
app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});