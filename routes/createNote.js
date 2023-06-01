const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db'); // Підключення до бази даних SQLite

const query = `INSERT INTO Notes (note_name, note_text, note_date, user_id)
               VALUES ($note_name, $note_text, $note_date, $user_id)`;

router.post('/', (req, res) => {
    try {
        const noteData = req.body;

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
        res.json({ message: 'Data downloaded and inserted successfully' });
    } catch (error) {
        res.status(400).json({ error: 'Invalid JSON data' });
    }
});

module.exports = router;