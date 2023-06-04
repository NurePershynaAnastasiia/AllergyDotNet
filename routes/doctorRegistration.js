const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const db = new sqlite3.Database('AllergyDotNet.db');

const query = `INSERT INTO Doctors (doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_documents)
               VALUES (?, ?, ?, ?, ?)`;

router.post('/doctorRegistration', (req, res) => {
    const doctor_name = req.body.doctor_name;
    const doctor_email = req.body.doctor_email;
    const doctor_password = req.body.doctor_password;
    const doctor_IBAN = req.body.doctor_IBAN;
    const doctor_documents = req.body.doctor_documents;


        db.run(query, [doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_documents], function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }
            res.status(200).json({ message: 'Data downloaded and inserted successfully' });
        });
});

module.exports = router;