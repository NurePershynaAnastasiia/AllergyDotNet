const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();


router.post('/loadFullNotes', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query = 'SELECT note_name, note_text, note_date FROM Notes WHERE user_id = ? '

    db.all(query, [user_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;
