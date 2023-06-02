const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db'); // Підключення до бази даних SQLite
const router = express.Router();

const querySelect = 'SELECT allergen_id FROM Allergens WHERE allergen_name = ?;';
const query = `INSERT INTO Points (point_photo, point_info, point_coordinates_latitude, point_coordinates_longitude, allergen_id)
               VALUES (?, ?, ?, ?, ?)`;

router.post('/createPoint', (req, res) => {
    const allergen_name = req.body.allergen_name;
    const point_info = req.body.point_info;
    const point_photo = req.body.point_photo;
    const point_coordinates_latitude = req.body.point_coordinates_latitude;
    const point_coordinates_longitude = req.body.point_coordinates_longitude;

        // Insert the data into the Points table
    db.get(querySelect, [allergen_name], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving allergen from the database');
        } else if (!rows) {
            return res.status(404).json({message: 'Allergen not found'});
        } else {
            const allergen_id = rows.allergen_id;
            db.run(query, [point_photo, point_info, point_coordinates_latitude, point_coordinates_longitude, allergen_id], (err) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error updating data in the database');
                }
                res.status(200).json({message: 'Point added successfully!'});
            });
        }
    });

});

module.exports = router;