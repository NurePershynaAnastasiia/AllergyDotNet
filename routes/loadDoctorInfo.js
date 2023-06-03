const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadDoctorInfo', (req, res) => {
    const doctor_id = req.body.doctor_id; // Отримання user_id з тіла запиту
    const query = 'SELECT doctor_id, doctor_name, doctor_email, doctor_IBAN, doctor_photo, ' +
        'doctor_documents, doctor_status,  doctor_price, doctor_info FROM Doctors';

    db.all(query, [doctor_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);
    });
});

module.exports = router;