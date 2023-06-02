const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadUnCheckedDoctors', (req, res) => {
    const query = 'SELECT doctor_id, doctor_name, doctor_email, doctor_info, doctor_documents  FROM Doctors WHERE doctor_status = 0';

    db.all(query,  (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving unchecked points from the database');
        }
        res.json(rows);
    });

});

module.exports = router;