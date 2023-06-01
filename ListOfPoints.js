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

app.post('/listOfPoints', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту

    const query = 'SELECT a.allergen_name, a.allergen_photo, p.point_coordinates_latitude, p.point_coordinates_longitude\n' +
            'FROM Users u\n' +
            'JOIN UserAllergens ua ON u.user_id = ua.user_id\n' +
            'JOIN Allergens a ON ua.allergen_id = a.allergen_id\n' +
            'JOIN Points p ON a.allergen_id = p.allergen_id WHERE u.user_id = ? AND p.point_status = \'1\'';


    //это работает или нет? просто тут ДБ ОЛЛ это странно
    db.all(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving points from the database');
        }
        res.status(200).json(rows);
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});