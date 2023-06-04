const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

router.post('/loadUserInfo', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const allergensQuery =
        'SELECT allergen_name, allergen_info, allergen_photo FROM Allergens ' +
        'INNER JOIN UserAllergens ON Allergens.allergen_id = UserAllergens.allergen_id ' +
        'INNER JOIN Users ON UserAllergens.user_id = Users.user_id WHERE Users.user_id = ?;';

    const consultationsQuery =
        'SELECT Consultations.consultation_id, Consultations.consultation_date, ConsStatus.consultation_status, Doctors.doctor_name FROM Consultations ' +
        'INNER JOIN ConsStatus ON Consultations.consultation_status = ConsStatus.consultation_status_id ' +
        'INNER JOIN Doctors ON Consultations.doctor_id = Doctors.doctor_id WHERE Consultations.user_id = ? ';

    const notesQuery =
        'SELECT note_id, note_name, note_text, note_date FROM Notes ' +
        'WHERE user_id = ?;';

    db.all(allergensQuery, [user_id], (err, allergens) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving allergens from the database');
        }

        db.all(consultationsQuery, [user_id], (err, consultations) => {
            if (err) {
                console.error(err);
                return res.status(500).send('Error retrieving consultations from the database');
            }

            db.all(notesQuery, [user_id], (err, notes) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error retrieving notes from the database');
                }

                res.status(200).json({ allergens, consultations, notes });
            });
        });
    });
});

module.exports = router;
