const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadDoctorConsultations', (req, res) => {
    const doctor_id = req.body.doctor_id; // Отримання doctor_id з тіла запиту
    const query = 'SELECT Consultations.consultation_date, ConsStatus.consultation_status, Users.user_name FROM Consultations ' +
        'INNER JOIN ConsStatus ON Consultations.consultation_status = ConsStatus.consultation_status_id' +
        'INNER JOIN Users on Consultations.user_id = Users.user_id WHERE Consultations.doctor_id = ? ';

    db.all(query, [doctor_id], (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;
