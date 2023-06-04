const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/approvePoint', (req, res) => {
    const point_id = req.body.point_id;
    const query = 'UPDATE Points SET point_status = 1 WHERE point_id = ?';

    db.all(query, [point_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving unchecked points from the database');
        }
        res.status(200).json({message: 'Point approved successfully'});
    });

});

module.exports = router;