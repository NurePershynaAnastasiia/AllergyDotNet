const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');

// Parse request bodies
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Route to get all notes for a specific user
app.post('/notes/:userId', (req, res) => {
    const userId = req.params.userId;
    const query = 'SELECT * FROM Notes WHERE user_id = ?';

    db.all(query, [userId], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.json(rows);
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});