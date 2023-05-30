const express = require('express');
const passport = require('passport');
const GoogleStrategy = require('passport-google-oauth20').Strategy;
const sqlite3 = require('sqlite3').verbose();

const app = express();
app.use(express.json());

const db = new sqlite3.Database('database.db');

// Конфігурація паспорта для Google Strategy
passport.use(
    new GoogleStrategy(
        {
            clientID: 'YOUR_GOOGLE_CLIENT_ID',
            clientSecret: 'YOUR_GOOGLE_CLIENT_SECRET',
            callbackURL: '/auth/google/callback',
        },
        (accessToken, refreshToken, profile, done) => {
            // Тут ви можете перевірити або створити користувача у вашій базі даних
            // Викликайте функцію `done` з користувачем, якщо вхід пройшов успішно

            // Приклад створення нового користувача
            const user = {
                id: profile.id,
                name: profile.displayName,
                email: profile.emails[0].value,
                isAdmin: false,
            };

            // Додайте код для збереження користувача у базі даних
            // ...

            done(null, user);
        }
    )
);

// Паспорт серіалізує та десеріалізує користувача
passport.serializeUser((user, done) => {
    done(null, user.id);
});

passport.deserializeUser((id, done) => {
    // Отримайте користувача з бази даних за його ідентифікатором
    // Декодуйте його з бази даних та передайте його в функцію `done`

    // Приклад отримання користувача з бази даних
    const user = {
        id: id,
        name: 'John Doe',
        email: 'john.doe@example.com',
        isAdmin: false,
    };

    done(null, user);
});

app.use(passport.initialize());

// Роут для початку процесу аутентифікації Google
app.get(
    '/auth/google',
    passport.authenticate('google', { scope: ['profile', 'email'] })
);

// Роут для обробки зворотного виклику після аутентифікації Google
app.get(
    '/auth/google/callback',
    passport.authenticate('google', { failureRedirect: '/login' }),
    (req, res) => {
        // Аутентифікація успішна, можна перенаправити користувача на сторінку після входу
        res.redirect('/dashboard');
    }
);

// Захищені маршрути, які вимагають аутентифікації
app.get('/dashboard', (req, res) => {
    // Перевірка, чи користувач аутентифікований
    if (req.isAuthenticated()) {
        // Доступ до захищеної сторінки
        res.send('Welcome to the dashboard!');
    } else {
        // Користувач не аутентифікований, перенаправляємо на сторінку входу
        res.redirect('/login');
    }
});

app.listen(3000, () => {
    console.log('Сервер запущено на порті 3000');
});
