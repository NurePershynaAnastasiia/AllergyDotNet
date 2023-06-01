const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');

// Parse request bodies
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const query = `INSERT INTO Doctors (doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_documents)
               VALUES (?, ?, ?, ?, ?)`;

app.post('/', (req, res) => {
    const doctor_name = req.body.doctor_name;
    if (!doctor_name) {
        return res.status(400).json({ error: 'Invalid data. Missing doctor_name' });
    }

    const doctor_email = req.body.doctor_email;
    const doctor_password = req.body.doctor_password;
    const doctor_IBAN = req.body.doctor_IBAN;
    const doctor_documents = req.body.doctor_documents;
    const doctor_password1 = req.body.doctor_password1;

    if (doctor_password === doctor_password1) {
        db.run(query, [doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_documents], function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }
            res.json({ message: 'Data downloaded and inserted successfully' });
        });
    } else {
        res.status(400).json({ error: 'Invalid password' });
    }
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
