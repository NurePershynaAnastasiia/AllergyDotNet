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
const loadUnCheckedPoints = require('./routes/loadUnCheckedPoints');
const loadDoctors = require('./routes/loadDoctors');
const loadNotesName = require('./routes/loadNotesName');
const loadPersonalPoints = require('./routes/loadPersonalPoints');
const loadPersonalRecomendations = require('./routes/loadPersonalRecomendations');
const loadUserAllergens = require('./routes/loadUserAllergens');
const loadUserNameSub = require('./routes/loadUserNameSub');
const loadFullNotes = require('./routes/loadFullNotes');
const userLogin = require('./routes/userLogin');
const userRegistration = require('./routes/userRegistration');
const loadUserConsultations = require('./routes/loadUserConsultations');
const loadDoctorConsultations = require('./routes/loadDoctorConsultations');
const loadUnCheckedDoctors = require('./routes/loadUnCheckedDoctors');
const loadDoctorClients = require('./routes/loadDoctorClients');
const addNewAllergen = require('./routes/addNewAllergen');
const approvePoint = require('./routes/approvePoint');
const rejectPoint = require('./routes/rejectPoint');
const approveDoctor = require('./routes/approveDoctor');
const rejectDoctor = require('./routes/rejectDoctor');
const loadAllAllergens = require('./routes/loadAllAllergens');
const loadClientConsultations = require('./routes/loadClientConsultations');
const canselCons = require('./routes/canselCons');
const addCons = require('./routes/addCons');

app.use('/', changeSub);
app.use('/', createNote);
app.use('/', createPoint);
app.use('/', doctorLogIn);
app.use('/', doctorRegistration);
app.use('/', loadAllCheckedPoints);
app.use('/', loadUnCheckedPoints);
app.use('/', loadDoctors);
app.use('/', loadNotesName);
app.use('/', loadPersonalPoints);
app.use('/', loadPersonalRecomendations);
app.use('/', loadUserAllergens);
app.use('/', loadUserNameSub);
app.use('/', loadFullNotes);
app.use('/', userLogin);
app.use('/', userRegistration);
app.use('/', loadUserConsultations);
app.use('/', loadDoctorConsultations);
app.use('/', loadUnCheckedDoctors);
app.use('/', loadDoctorClients);
app.use('/', addNewAllergen);
app.use('/', loadAllAllergens);
app.use('/', loadClientConsultations);
app.use('/', approvePoint);
app.use('/', approveDoctor);
app.use('/', rejectPoint);
app.use('/', rejectDoctor);
app.use('/', canselCons);
app.use('/', addCons);

// Start the server
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});