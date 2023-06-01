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
    //const query1 = 'SELECT note_id, note_name, note_text, note_date FROM Notes WHERE user_id = ?';

    const query = 'SELECT allergen_name, allergen_photo FROM Allergens ' +
        'inner join UsersAllergens on Allergens.allergen_id = UsersAllergens.allergen_id ' +
        'where UsersAllergens.user_id = ?';


    //это работает или нет? просто тут ДБ ОЛЛ это странно
    db.all(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});