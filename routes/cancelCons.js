const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/cancelCons', (req, res) => {
    const consultation_id = req.body.consultation_id;
    const query = 'UPDATE Consultations SET consultation_status = 1 WHERE consultation_id = ?;';

    db.all(query, [consultation_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving cons from the database');
        }
        res.json({message: 'Consultation canceled successfully'});
    });

});

module.exports = router;