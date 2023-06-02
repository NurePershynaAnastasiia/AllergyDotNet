const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

const querySelect = `SELECT user_sub
                     FROM Users
                     WHERE user_id = ?`;
const queryUpdateToPremium = `UPDATE Users
                              SET user_sub = 1
                              WHERE user_id = ?
                                AND user_sub = 0`;
const queryUpdateToFree = `UPDATE Users
                           SET user_sub = 0
                           WHERE user_id = ?
                             AND user_sub = 1`;

router.post('/changeUserSub', (req, res) => {
    const user_id = req.body.user_id;

    // Check the current user_sub value
    db.get(querySelect, [user_id], function (err, row) {
        if (err) {
            console.error(err);
            return res.status(500).send('Error querying the database');
        }

        if (!row) {
            return res.status(404).json({message: 'User not found'});
        }

        const userSub = row.user_sub;

        if (userSub === 1) {
            //return res.status(200).json({ message: 'Subscription is already active' });
            db.run(queryUpdateToFree, [user_id], function (err) {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error updating data in the database');
                }

                res.status(200).json({message: 'User subscription changed to free successfully!'});
            });
        } else if (userSub === 0) {
            // Update the user_sub value
            db.run(queryUpdateToPremium, [user_id], function (err) {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error updating data in the database');
                }

                res.status(200).json({message: 'User subscription changed successfully!'});
            });
        }
    });
});

module.exports = router;

