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
    fs.readFile(`ListNotes.json`, 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({error: 'Error reading the file'});
        }
        try {
            const notesData = JSON.parse(data);

            const $userId = notesData.userId;
            const query = 'SELECT * FROM Notes WHERE user_id = $userId';

            db.all(query, [$userId], (err, rows) => {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error retrieving notes from the database');
                }
                res.json(rows);
            });

            res.json({message: 'Data viewed'});


        } catch (error) {
            res.status(400).json({error: 'Invalid JSON file'});
        }
    });
});

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});