// app.js

const express = require('express');
const app = express();
const bodyParser = require('body-parser');

// Parse request bodies
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Подключение маршрутов
const loadUserSubRoute = require('./routes/loadUserSub');
const userLoginRoute = require('./routes/userLogin');
const loadUserAllergensRoute = require('./routes/loadUserAllergens');

app.use(loadUserSubRoute);
app.use(userLoginRoute);
app.use(loadUserAllergensRoute);

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
