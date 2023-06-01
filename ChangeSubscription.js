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

const query = `INSERT INTO Users (user_sub) VALUES (?)`;

app.post('/changeUserSub', (req, res) => {
    const user_sub = req.body.user_sub;

    if (user_sub != row.user_sub) {
        db.run(query, [user_sub], function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }
            res.status(200).json({ message: 'User subscription changes sucsesfully!' });
        });
    } else {
        res.status(404).json({ error: 'User already had this type of subscription' });
    }
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
