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

// Route to get all notes for a specific user


app.post('/', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query =
        'SELECT Users.user_name, Users.user_sub, Notes.note_name, Notes.note_text, Allergens.allergen_name\n' +
        'FROM Users\n' +
        'LEFT JOIN Notes ON Users.user_id = Notes.user_id\n' +
        'LEFT JOIN UserAllergens ON Users.user_id = UserAllergens.user_id\n' +
        'LEFT JOIN Allergens ON UserAllergens.allergen_id = Allergens.allergen_id\n' +
        'WHERE Users.user_id = ?;\n';

    db.all(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.json(rows);
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});