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

app.post('/loadUserAllergens', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query = 'SELECT allergen_name, FROM Allergens ' +
        'inner join UserAllergens on Allergens.allergen_id = UserAllergens.allergen_id ' +
        'inner join Users on UserAllergens.user_id = Users.user_id WHERE Users.user_id = ?;';

    db.get(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);
        //res.status(200).json(row);// я не знаю, как лучше, надо потестить!!!!!!!!!!!!!!!!!!!!!!!!

    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});