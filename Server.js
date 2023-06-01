// app.js

const express = require('express');
const app = express();
const bodyParser = require('body-parser');

// Parse request bodies
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Подключение маршрутов
const loadUserSubRoute = require('./routes/changeSub');
const userLoginRoute = require('./routes/createNote');
const loadUserAllergensRoute = require('./routes/createPoint');
const loadUserSubRoute = require('./routes/doctorLogIn');
const userLoginRoute = require('./routes/doctorRegistration');
const loadUserAllergensRoute = require('./routes/loadAllCheckedPoints');
const loadUserSubRoute = require('./routes/loadDoctors');
const userLoginRoute = require('./Routes/loadNotesName');
const loadUserAllergensRoute = require('./routes/loadPersonalPoints');
const loadUserSubRoute = require('./routes/loadPersonalRecomendations');
const userLoginRoute = require('./routes/loadUserAllergens');
const loadUserAllergensRoute = require('./routes/loadUserNameSub');
const loadUserSubRoute = require('./Routes/loadFullNotes');
const userLoginRoute = require('./routes/userLogin');
const loadUserAllergensRoute = require('./routes/userRegistration');

app.use(loadUserSubRoute);
app.use(userLoginRoute);
app.use(loadUserAllergensRoute);

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
