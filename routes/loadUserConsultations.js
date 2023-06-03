const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadUserConsultations', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query = 'SELECT Consultations.consultation_date, ConsStatus.consultation_status, Doctors.doctor_name FROM Consultations ' +
        'INNER JOIN ConsStatus ON Consultations.consultation_status = ConsStatus.consultation_status_id ' +
        'INNER JOIN Doctors ON Consultations.doctor_id = Doctors.doctor_id WHERE Consultations.user_id = ? ';


    db.all(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving consultations from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;
