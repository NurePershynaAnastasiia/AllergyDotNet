const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

const query = `INSERT INTO Users (user_name, user_email, user_password)
               VALUES (?, ?, ?)`;

router.post('/userRegistration', (req, res) => {
    const user_name = req.body.user_name;
    const user_email = req.body.user_email;
    const user_password = req.body.user_password;
    const user_password1 = req.body.user_password1;

    if (user_password === user_password1) {
        db.run(query, [user_name, user_email, user_password], function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }
            res.json({ message: 'Data downloaded and inserted successfully' });
        });
    } else {
        res.status(404).json({ error: 'Passwords don`t match' });
    }

});

module.exports = router;
