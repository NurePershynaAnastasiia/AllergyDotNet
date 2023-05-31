const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite
const query = `INSERT INTO Points (point_name, point_photo, point_info, point_coordinates_latitude, point_coordinates_longitude)
               VALUES ($point_name, $point_photo, $point_info, $point_coordinates_latitude, $point_coordinates_longitude)`;

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    fs.readFile(`newPoint.json`, 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({error: 'Error reading the file'});
        }
        try {
            const pointData = JSON.parse(data);
                // Insert the data into the Users table
                db.run(query, {
                    $point_name: pointData.point_name,
                    $point_photo: pointData.point_photo,
                    $point_info: pointData.point_info,
                    $point_coordinates_latitude: pointData.point_coordinates_latitude,
                    $point_coordinates_longitude: pointData.point_coordinates_longitude
                }, function (err) {
                    if (err) {
                        console.error(err);
                        return res.status(500).send('Error inserting data into the database');
                    }
                });
                res.json({message: 'Data downloaded and inserted successfully'});


        } catch (error) {
            res.status(400).json({error: 'Invalid JSON file'});
        }
    });
});

// Запуск сервера
app.use(express.static(__dirname));
app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});