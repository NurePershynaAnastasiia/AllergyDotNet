const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadAllAllergens', (req, res) => {
    const query = 'SELECT allergen_name FROM Allergens;';

    db.all(query, (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;