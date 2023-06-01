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

const querySelect = `SELECT user_sub FROM Users WHERE user_id = ?`;
const queryUpdate = `UPDATE Users SET user_sub = 1 WHERE user_id = ? AND user_sub = 0`;

app.post('/changeUserSub', (req, res) => {
    const user_id = req.body.user_id;

    // Check the current user_sub value
    db.get(querySelect, [user_id], function (err, row) {
        if (err) {
            console.error(err);
            return res.status(500).send('Error querying the database');
        }

        if (!row) {
            return res.status(404).json({ message: 'User not found' });
        }

        const userSub = row.user_sub;

        if (userSub === 1) {
            return res.status(200).json({ message: 'Subscription is already active' });
        }

        // Update the user_sub value
        db.run(queryUpdate, [user_id], function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error updating data in the database');
            }

            res.status(200).json({ message: 'User subscription changed successfully!' });
        });
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
