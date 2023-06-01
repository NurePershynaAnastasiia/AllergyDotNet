const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadDoctors', (req, res) => {
    const query = 'SELECT doctor_id, doctor_name, doctor_photo, doctor_price, doctor_info FROM Doctors';

    db.all(query,  (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving doctors from the database');
        }
        res.json(rows);
    });

});

module.exports = router;