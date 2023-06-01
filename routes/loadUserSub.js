const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadUserSub', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query = 'SELECT user_sub FROM Users WHERE Users.user_id = ?';

    db.get(query, [user_id], (err, row) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(row);
    });
});

module.exports = router;