const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/addCons', (req, res) => {
    const doctor_id = req.body.doctor_id;
    const user_id = req.body.user_id;
    const consultation_date = req.body.consultation_date;

    const query = 'INSERT INTO Consultations (doctor_id, user_id, consultation_date, consultation_status)\n' +
        'VALUES (?, ?, ?, 2);';

    db.all(query, [doctor_id, user_id, consultation_date], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving cons from the database');
        }
        res.json({message: 'Consultation added successfully'});
    });

});

module.exports = router;