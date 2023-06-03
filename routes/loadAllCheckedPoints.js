const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadAllCheckedPoints', (req, res) => {
    const query = 'SELECT a.allergen_name, p.point_photo, p.point_coordinates_latitude, p.point_coordinates_longitude\n' +
        'FROM Points AS p\n' +
        'INNER JOIN Allergens AS a ON p.allergen_id = a.allergen_id\n' +
        'WHERE p.point_status = 1;\n';

    db.all(query,  (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving checked points from the database');
        }
        res.json(rows);
    });

});

module.exports = router;