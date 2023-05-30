const express = require('express');
const sqlite3 = require('sqlite3').verbose();

// Створення екземпляра додатку Express
const app = express();

// Розбір даних у форматі JSON
app.use(express.json());

const bodyParser = require('body-parser');
// Підключення модуля body-parser
app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Підключення до бази даних SQLite
const db = new sqlite3.Database('AllergyDotNet.db');

// Обробник GET-запиту на кореневий шлях
app.get('/', (req, res) => {
    res.sendFile(__dirname + '/index_name.html');
});

const query = `INSERT INTO Users (user_name, user_email, user_password) VALUES ($user_name, $user_email, $user_password)`;
db.run(query, { $user_name: "lalala", $user_email: "user_email@gmai.com", $user_password: "user_password" }, function (err) {
    if (err) {
        console.error(err);
    }

    console.log(`Користувач з ID ${this.lastID} зареєстрований`);
});
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