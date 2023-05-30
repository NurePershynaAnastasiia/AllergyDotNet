const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const app = express();
const bodyParser = require('body-parser');
const db = new sqlite3.Database('AllergyDotNet.db');// Підключення до бази даних SQLite
const query = `INSERT INTO Users (user_name, user_email, user_password) VALUES ($user_name, $user_email, $user_password)`;

// Розбір даних у форматі JSON
app.use(express.json());
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());



// Обробник GET-запиту на кореневий шлях
app.get('/', (req, res) => {
    res.sendFile(__dirname + '/index_name.html');
});

app.post('/', (req, res) => {
    fs.readFile(`newUser.json`, 'utf8', async (err, data) => {
        if (err) {
            return res.status(500).json({ error: 'Error reading the file' });
        }

        try {
            const userData = JSON.parse(data);

            db.run(query, { $user_name: userData.user_name, $user_email: userData.user_email, $user_password: userData.user_password }, function (err) {
                if (err) {
                    console.error(err);
                    return res.status(500).send('Error inserting data into the database');
                }

                console.log(`User with ID ${this.lastID} registered`);
                res.status(200).send('Registration successful');
            });

            // Insert the data into the Users table
            /*await Users.create({
                user_name: userData.user_name,
                user_email: userData.user_email,
                user_password: userData.user_password,
            });*/

            res.json({ message: 'Data downloaded and inserted successfully' });
        } catch (error) {
            res.status(400).json({ error: 'Invalid JSON file' });
        }



    });
});



/*db.run(query, { $user_name: "lalala", $user_email: "user_email@gmai.com", $user_password: "user_password" }, function (err) {
    if (err) {
        console.error(err);
    }

    console.log(`Користувач з ID ${this.lastID} зареєстрований`);
});*/
app.get('/register', (req, res) => {
    res.sendFile(__dirname + '/index_name.html');
});

// Обробник POST-запиту на реєстрацію користувача
app.post('/register', (req, res) => {
    const { user_name, user_email, user_password } = req.body;

    // Вставка даних у базу даних

    const query = 'INSERT INTO Users (user_name, user_email, user_password) VALUES ($user_name, $user_email, $user_password)';
    db.run(query, { $user_name: user_name, $user_email: user_email, $user_password: user_password }, function (err) {
        if (err) {
            console.error(err);
            return res.status(500).send('Помилка сервера');
        }

        console.log('Користувач з ID ${this.lastID} зареєстрований');
        res.status(200).send('Реєстрація успішна');
    });
});

// Запуск сервера
app.use(express.static(__dirname));
app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});