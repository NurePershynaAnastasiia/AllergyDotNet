const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/loadClientsConsultations', (req, res) => {
    const user_id = req.body.user_id;
    const doctor_id = req.body.doctor_id;
    const query = 'SELECT consultation_date FROM Consultations WHERE doctor_id = ? AND consultation_status = 3 AND user_id = ?';

    db.all(query, [user_id, doctor_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;
