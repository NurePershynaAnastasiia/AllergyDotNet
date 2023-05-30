const express = require('express');
const sqlite3 = require('sqlite3').verbose();

const app = express();
app.use(express.json());

const db = new sqlite3.Database('database.db');

// Обробник POST-запиту для додавання нових записів
app.post('/notes', (req, res) => {
    const { note_name, note_text, user_id } = req.body;

    const query = `INSERT INTO Notes (note_name, note_text, user_id) VALUES (?, ?, ?)`;
    db.run(query, [note_name, note_text, user_id], function (err) {
        if (err) {
            console.error(err);
            return res.status(500).send('Помилка сервера');
        }

        console.log(`Запис з ID ${this.lastID} додано`);
        res.status(200).send('Запис додано');
    });
});

app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});
