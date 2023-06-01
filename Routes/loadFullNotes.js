const express = require('express');
const sqlite3 = require('sqlite3').verbose();
//const fs = require('fs');
//const app = express();
//const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');
const router = express.Router();

// Parse request bodies
//app.use(express.json());
//app.use(bodyParser.urlencoded({ extended: false }));
//app.use(bodyParser.json());

router.post('/loadFullNotes', (req, res) => {
    const user_id = req.body.user_id; // Отримання user_id з тіла запиту
    const query = 'SELECT note_name, note_text, note_date FROM Notes WHERE user_id = ? '

    db.all(query, [user_id], (err, rows) => { // Використовуйте db.all замість db.each для отримання всіх рядків
        if (err) {
            console.error(err);
            return res.status(500).send('Error retrieving notes from the database');
        }
        res.status(200).json(rows);

    });
});

module.exports = router;

// Start the server

/*
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});*/