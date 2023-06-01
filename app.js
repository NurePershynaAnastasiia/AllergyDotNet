// app.js

const express = require('express');
const app = express();
const bodyParser = require('body-parser');

// Parse request bodies
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Подключение маршрутов
const changeSub = require('./routes/changeSub');
const createNote = require('./routes/createNote');
const createPoint = require('./routes/createPoint');
const doctorLogIn = require('./routes/doctorLogIn');
const doctorRegistration = require('./routes/doctorRegistration');
const loadAllCheckedPoints = require('./routes/loadAllCheckedPoints');
const loadDoctors = require('./routes/loadDoctors');
const loadNotesName = require('./routes/loadNotesName');
const loadPersonalPoints = require('./routes/loadPersonalPoints');
const loadPersonalRecomendations = require('./routes/loadPersonalRecomendations');
const loadUserAllergens = require('./routes/loadUserAllergens');
const loadUserNameSub = require('./routes/loadUserNameSub');
const loadFullNotes = require('./routes/loadFullNotes');
const userLogin = require('./routes/userLogin');
const userRegistration = require('./routes/userRegistration');

app.use(changeSub);
app.use(createNote);
app.use(createPoint);
app.use(doctorLogIn);
app.use(doctorRegistration);
app.use(loadAllCheckedPoints);
app.use(loadDoctors);
app.use(loadNotesName);
app.use(loadPersonalPoints);
app.use(loadPersonalRecomendations);
app.use(loadUserAllergens);
app.use(loadUserNameSub);
app.use(loadFullNotes);
app.use(userLogin);
app.use(userRegistration);


const app = require('./app');
// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
