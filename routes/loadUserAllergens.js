const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadUserAllergens', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query = 'SELECT allergen_name FROM Allergens ' +
        'inner join UserAllergens on Allergens.allergen_id = UserAllergens.allergen_id ' +
        'inner join Users on UserAllergens.user_id = Users.user_id WHERE Users.user_id = ?;';

    db.all(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;