const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const router = express.Router();
const fs = require('fs');

// я пытаюсь тупо загрузить хоть фотку амброзии!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11

const db = new sqlite3.Database('AllergyDotNet.db');
const query = `UPDATE Allergens SET allergen_photo = ? WHERE allergen_id = 1;`;

router.post('/loadAllergenPhoto', (req, res) => {

        const photo = fs.readFileSync('.AllyrgyDotNet/photos/allergenPhotos/ambroziya.jpg');


        // Insert the data into the Users table
        db.run(query, {photo
        }, function (err) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into the database');
            }
        });
        res.json({ message: 'Data downloaded and inserted successfully' });

});

module.exports = router;