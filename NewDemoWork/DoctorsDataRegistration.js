const express = require('express');
const sqlite3 = require('sqlite3').verbose();

const app = express();
app.use(express.json());

const db = new sqlite3.Database('database.db', sqlite3.OPEN_READWRITE, (err) => {
    if (err) {
        console.error(err.message);
    } else {
        console.log('Підключено до бази даних SQLite');
    }
});

// Обробник POST-запиту для реєстрації лікаря
app.post('/register/doctor', (req, res) => {
    const { doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_photo, doctor_documents, doctor_status } = req.body;

    const query = `INSERT INTO Doctors (doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_photo, doctor_documents, doctor_status) VALUES (?, ?, ?, ?, ?, ?, ?)`;
    db.run(query, [doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_photo, doctor_documents, doctor_status], function (err) {
        if (err) {
            console.error(err);
            return res.status(500).send('Помилка сервера');
        }

        console.log(`Лікар з ID ${this.lastID} зареєстрований`);
        res.status(200).send('Реєстрація успішна');
    });
});

app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});
