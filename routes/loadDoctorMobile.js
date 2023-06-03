const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadDoctorMobile', (req, res) => {
    const doctor_id = req.body.doctor_id; // Отримання user_id з тіла запиту
    const query = 'SELECT doctor_name, doctor_photo, doctor_price, doctor_info FROM Doctors WHERE doctor_id = ?';

    db.get(query, [doctor_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);
    });
});

module.exports = router;