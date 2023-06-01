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

//
app.post('/loadDoctors', (req, res) => {
    const query = 'SELECT doctor_id, doctor_name, doctor_photo, doctor_price, doctor_info FROM Doctors';

    db.all(query,  (err, rows) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving doctors from the database');
        }
        res.json(rows);
    });

});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});