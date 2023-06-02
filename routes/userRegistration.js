const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

const queryInsert = `INSERT INTO Users (user_name, user_email, user_password)
               VALUES (?, ?, ?)`;
const querySelect = `SELECT user_id FROM Users WHERE user_email = ? AND user_name = ?`;

router.post('/userRegistration', (req, res) => {
    const user_name = req.body.user_name;
    const user_email = req.body.user_email;
    const user_password = req.body.user_password;
    const user_password1 = req.body.user_password1;

        db.run(queryInsert, [user_name, user_email, user_password], function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }else{
                db.get(querySelect, [user_email, user_name], function (err) {
                    if(err){
                        return res.status(500).send('Error inserting data into the database');
                    }else{
                        res.status(200).json({user_id: row.user_id });
                    }
                })
            }
        });

});

module.exports = router;
