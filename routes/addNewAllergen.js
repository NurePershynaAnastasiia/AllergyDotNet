const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

const querySelect = 'SELECT allergen_id FROM Allergens WHERE allergen_name = ?;';
const queryInsertAllergens = `INSERT INTO UserAllergens (user_id, allergen_id)
                              VALUES (?, ?)`;
router.post('/addNewAllergen', (req, res) => {
    const allergen_name = req.body.allergen_name; // Отримання user_id з тіла запиту
    const user_id = req.body.user_id;

    db.get(querySelect, [allergen_name], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        } else if (!row) {
            return res.status(404).json({message: 'Allergen not found'});
        } else {
            res.status(200).json(rows);
            const allergen_id = rows.allergen_id;
            db.run(queryInsertAllergens, [user_id, allergen_id], (err, rows) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error updating data in the database');
                }
                res.status(200).json({message: 'Allergen added successfully!'});
            })
        }
    })
    db.all(query, [allergen_name], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;
