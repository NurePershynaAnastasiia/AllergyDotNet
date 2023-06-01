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

const query = `INSERT INTO Users (user_name, user_email, user_password)
               VALUES (?, ?, ?)`;

app.post('/', (req, res) => {
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
        res.status(404).json({ error: 'Invalid password' });
    }
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
