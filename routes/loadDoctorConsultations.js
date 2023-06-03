const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadDoctorConsultations', (req, res) => {
    const doctor_id = req.body.doctor_id; // Отримання user_id з тіла запиту
    const query = 'SELECT Consultations.consultation_date, ConsStatus.consultation_status FROM Consultations ' +
        'INNER JOIN ConsStatus ON Consultations.consultation_status = ConsStatus.consultation_status_id WHERE Consultations.doctor_id = ? ';

    db.all(query, [doctor_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;