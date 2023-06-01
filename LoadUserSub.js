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

app.post('/loadUserSub', (req, res) => {
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

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});