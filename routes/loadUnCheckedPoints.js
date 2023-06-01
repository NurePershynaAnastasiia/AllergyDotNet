const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadUnCheckedPoints', (req, res) => {
    const query = 'SELECT Points.point_photo, Points.point_id, Users.user_id, Points.point_info, Allergens.allergen_name\n' +
        'FROM Points\n' +
        'JOIN Allergens ON Points.allergen_id = Allergens.allergen_id\n' +
        'JOIN Users ON Points.user_id = Users.user_id\n' +
        'WHERE Points.point_status = 0;\n';

    db.all(query,  (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving unchecked points from the database');
        }
        res.json(rows);
    });

});

module.exports = router;