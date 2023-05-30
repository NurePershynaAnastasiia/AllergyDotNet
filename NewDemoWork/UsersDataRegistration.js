//мобільний додаток

const express = require('express');
const sqlite3 = require('sqlite3').verbose();

// Створення екземпляра додатку Express
const app = express();

// Розбір даних у форматі JSON
app.use(express.json());

// Підключення до бази даних SQLite
const db = new sqlite3.Database('database.db');

// Обробник POST-запиту на реєстрацію користувача
app.post('/register', (req, res) => {
    const { user_name, user_email, user_password } = req.body;

    // Вставка даних у базу даних
    const query = `INSERT INTO Users (user_name, user_email, user_password) VALUES (?, ?, ?)`;
    db.run(query, [user_name, user_email, user_password], function (err) {
        if (err) {
            console.error(err);
            return res.status(500).send('Помилка сервера');
        }

        console.log(`Користувач з ID ${this.lastID} зареєстрований`);
        res.status(200).send('Реєстрація успішна');
    });
});

// Запуск сервера
app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});
