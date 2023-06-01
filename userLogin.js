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

app.post('/userLogin', (req, res) => {
    const user_email = req.body.user_email;
    const user_password = req.body.user_password;

    db.get('SELECT user_id, user_password FROM Users WHERE user_email = ?', [user_email], (err, row) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving data from the database');
        }

        if (row) {
            if (row.user_password === user_password) {
                res.status(200).json({ user_id: row.user_id });
            } else {
                res.status(404).json({ error: 'Invalid password' });
            }
        } else {
            res.status(404).json({ user_id: false });//there isn`t user with this email
        }
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
