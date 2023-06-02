const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

const querySelect = 'SELECT allergen_id FROM Allergens WHERE allergen_name = ?;';
const queryInsertAllergens = `INSERT INTO UserAllergens (user_id, allergen_id)
                              VALUES (?, ?)`;
router.post('/addNewAllergen', (req, res) => {
    const allergen_name = req.body.allergen_name;
    const user_id = req.body.user_id;

    // Перевірка, чи існує вже така пара значень в таблиці UserAllergens
    const queryCheckExists = `SELECT *
                              FROM UserAllergens
                              WHERE user_id = ?
                                AND allergen_id =
                                    (SELECT allergen_id FROM Allergens WHERE allergen_name = ?);`;
    db.get(queryCheckExists, [user_id, allergen_name], (err, row) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving data from the database');
        }
        if (row) {
            return res.status(400).json({message: 'This user already has this allergy'});
        } else {
            // Запис нової алергії в таблицю UserAllergens
            db.get(querySelect, [allergen_name], (err, rows) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error retrieving allergen from the database');
                } else if (!rows) {
                    return res.status(404).json({message: 'Allergen not found'});
                } else {
                    const allergen_id = rows.allergen_id;
                    db.run(queryInsertAllergens, [user_id, allergen_id], (err) => {
                        if (err) {
                            console.error(err);
                            return res.status(500).send('Error updating data in the database');
                        }
                        res.status(200).json({message: 'Allergen added successfully!'});
                    });
                }
            });
        }
    });
});

module.exports = router;
