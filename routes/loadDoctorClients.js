const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadDoctorClients', (req, res) => {
    const doctor_id = req.body.doctor_id;
    const query = 'SELECT Users.user_name, Consultations.consultation_date FROM Users ' +
        'INNER JOIN Consultations ON Users.user_id = Consultations.user_id ' +
        'WHERE Consultations.doctor_id = ? AND Consultations.consultation_status = 3';

    db.all(query, [doctor_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;
