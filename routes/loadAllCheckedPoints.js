const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadAllCheckedPoints', (req, res) => {
    const query = 'SELECT point_name, point_photo, point_coordinates_latitude, point_coordinates_longitude FROM Points WHERE point_status = 1';

    db.all(query,  (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving doctors from the database');
        }
        res.json(rows);
    });

});

module.exports = router;