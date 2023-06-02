const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/approveDoctor', (req, res) => {
    const doctor_id = req.body.doctor_id;
    const query = 'UPDATE Doctors SET doctor_status = 1 WHERE doctor_id = ?';

    db.all(query,  [doctor_id],(err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving unchecked points from the database');
        }
        res.json({ message: 'Doctor approved successfully' });
    });

});

module.exports = router;