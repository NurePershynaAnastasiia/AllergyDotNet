const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db'); // Підключення до бази даних SQLite
const router = express.Router();

const query = `INSERT INTO Points (point_name, point_photo, point_info, point_coordinates_latitude, point_coordinates_longitude, allergen_id)
               VALUES (?, ?, ?, ?, ?, ?)`;

router.post('/', (req, res) => {

        const point_name = req.body.point_name;
        const point_photo = req.body.point_photo;
        const point_info = req.body.point_info;
        const point_coordinates_latitude = req.body.point_coordinates_latitude;
        const point_coordinates_longitude = req.body.point_coordinates_longitude;
        const allergen_id = req.body.allergen_id;

        // Insert the data into the Points table
        db.run(query, [point_name, point_photo, point_info, point_coordinates_latitude, point_coordinates_longitude, allergen_id], function (err){
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }
            res.json({ message: 'Data downloaded and inserted successfully' });
        });

});

module.exports = router;