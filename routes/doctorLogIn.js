const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite

router.post('/doctorLogin', (req, res) => {
    const doctor_email = req.body.doctor_email;
    const doctor_password = req.body.doctor_email;

    db.get('SELECT doctor_id, doctor_password, isAdmin FROM Doctors WHERE doctor_email = ?', [doctor_email], (err, row) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving data from the database');
        }
        if (row) {
            if (row.doctor_password === doctor_password) {
                if (row.isAdmin) {
                    res.status(200).json({doctor_id: row.doctor_id, isAdmin: true});//доктор

                } else {
                    res.status(200).json({doctor_id: row.doctor_id, isAdmin: false});//адмін
                }
            } else {
                res.status(404).json({error: 'Invalid password'});//якщо пароль невірний
            }
        } else {
            res.status(200).json({doctor_id: false});//людини з таким емайлом нема
        }
    });
});

module.exports = router;