const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadPersonalPoints', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту

    const query = 'SELECT a.allergen_name, p.point_photo, p.point_coordinates_latitude, p.point_coordinates_longitude\n' +
            'FROM Users u\n' +
            'JOIN UserAllergens ua ON u.user_id = ua.user_id\n' +
            'JOIN Allergens a ON ua.allergen_id = a.allergen_id\n' +
            'JOIN Points p ON a.allergen_id = p.allergen_id WHERE u.user_id = ? AND p.point_status = \'1\'';


    db.all(query, [user_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving points from the database');
        }
        res.status(200).json(rows);
    });
});

module.exports = router;