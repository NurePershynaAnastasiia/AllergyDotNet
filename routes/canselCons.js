const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/canselCons', (req, res) => {
    const doctor_id = req.body.doctor_id;
    const user_id = req.body.user_id;
    const query = 'UPDATE Consultations SET consultation_status = 1 WHERE doctor_id = ? AND user_id = ?;';

    db.all(query, [doctor_id, user_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving cons from the database');
        }
        res.status(200).json({message: 'Consultation canceled successfully'});
    });

});

module.exports = router;