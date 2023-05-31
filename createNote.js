const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite
const query = `INSERT INTO Notes (note_name, note_text, note_date, user_id)
               VALUES ($note_name, $note_text, $note_date, $user_id)`;

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    fs.readFile(`newNote.json`, 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({error: 'Error reading the file'});
        }
        try {
            const noteData = JSON.parse(data);

                // Insert the data into the Users table
                db.run(query, {
                    $note_name: noteData.note_name,
                    $note_date: noteData.note_date,
                    $note_text: noteData.note_text,
                    $user_id: noteData.user_id
                }, function (err) {
                    if (err) {
                        console.error(err);
                        return res.status(500).send('Error inserting data into the database');
                    }
                });
                res.json({message: 'Data downloaded and inserted successfully'});


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