const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/rejectDoctor', (req, res) => {
    const doctor_id = req.body.doctor_id;
    const query = 'DELETE FROM Doctors WHERE doctor_id = ?';

    db.all(query,  [doctor_id],(err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving unchecked points from the database');
        }
        res.status(200).json({ message: 'Doctor rejected successfully' });
    });

});

module.exports = router;