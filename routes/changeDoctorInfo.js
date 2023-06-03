const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

router.post('/changeDoctorInfo', (req, res) => {
    const doctor_id = req.body.doctor_id;
    const doctor_photo = req.body.doctor_photo;
    const doctor_price = req.body.doctor_price;
    const doctor_info = req.body.doctor_info;

    if (doctor_price === null || doctor_info === null) {
        return res.status(400).send('doctor_price and doctor_info cannot be null');
    }

    if (doctor_price < 0) {
        return res.status(400).send('doctor_price cannot be a negative number');
    }

    const query = `
        UPDATE Doctors
        SET 
            doctor_photo = ?,
            doctor_price = CASE WHEN ? IS NULL OR TRIM(?) = '' THEN doctor_price ELSE ? END,
            doctor_info = CASE WHEN ? IS NULL OR TRIM(?) = '' THEN doctor_info ELSE ? END
        WHERE 
            doctor_id = ? AND isAdmin = 0 AND doctor_status = 1;
    `;

    db.run(query, [doctor_photo, doctor_price, doctor_price, doctor_price, doctor_info, doctor_info, doctor_info, doctor_id], (err) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error updating doctors in the database');
        }
        res.status(200).send('Doctor info updated successfully');
    });
});

module.exports = router;
