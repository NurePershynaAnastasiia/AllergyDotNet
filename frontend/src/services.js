export const ADRESS = "http://localhost:3000";


export async function logIn(login, password) {
    try {
        const response = await fetch(`${ADRESS}/doctorLogIn`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_email: login, doctor_password: password}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function register(doctor_name, doctor_email, doctor_password, doctor_IBAN, doctor_documents) {
    try {
        const response = await fetch(`${ADRESS}/doctorRegistration`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_name: doctor_name, doctor_email: doctor_email, doctor_password: doctor_password, doctor_IBAN: doctor_IBAN, doctor_documents: doctor_documents}),
        });
        if (response.ok) {
            return true;
        }

        return false;
    } catch {
        return null;
    }
}

export async function approveDoctor(doctor_id) {
    try {
        const response = await fetch(`${ADRESS}/approveDoctor`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_id:doctor_id}),
        });
        if (response.ok) {
            
            return true;
        } 

        return false;
    } catch {
        return null;
    }
}

export async function approvePoint(point_id) {
    try {
        const response = await fetch(`${ADRESS}/approvePoint`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({point_id: point_id}),
        });
        if (response.ok) {
            
            return true;
        } 

        return false;
    } catch {
        return null;
    }
}

export async function rejectDoctor(doctor_id) {
    try {
        const response = await fetch(`${ADRESS}/rejectDoctor`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_id:doctor_id}),
        });
        if (response.ok) {
            
            return true;
        } 

        return false;
    } catch {
        return null;
    }
}

export async function rejectPoint(point_id) {
    try {
        const response = await fetch(`${ADRESS}/rejectPoint`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({point_id: point_id}),
        });
        if (response.ok) {
            
            return true;
        } 

        return false;
    } catch {
        return null;
    }
}

export async function loadDoctorClients(doctor_id) {
    try {
        const response = await fetch(`${ADRESS}/loadDoctorClients`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_id:doctor_id}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadDoctorConsultations(doctor_id) {
    try {
        const response = await fetch(`${ADRESS}/loadDoctorConsultations`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_id:doctor_id}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadUnCheckedDoctors() {
    try {
        const response = await fetch(`${ADRESS}/loadUnCheckedDoctors`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadUnCheckedPoints() {
    try {
        const response = await fetch(`${ADRESS}/loadUnCheckedPoints`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadUserInfo(user_id) {
    try {
        const response = await fetch(`${ADRESS}/loadUserInfo`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({user_id: user_id}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadDoctorInfo(doctor_id) {
    try {
        const response = await fetch(`${ADRESS}/loadDoctorInfo`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_id: doctor_id}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadNotesName(user_id) {
    try {
        const response = await fetch(`${ADRESS}/loadNotesName`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({user_id: user_id}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function loadFullNotes(user_id) {
    try {
        const response = await fetch(`${ADRESS}/loadFullNotes`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({user_id: user_id}),
        });
        if (response.ok) {
            const result = await response.json();
            return result;
        } 

        return null;
    } catch {
        return null;
    }
}

export async function cancelCons(consultation_id) {
    try {
        const response = await fetch(`${ADRESS}/cancelCons`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({consultation_id: consultation_id}),
        });
        if (response.ok) {
            
            return true;
        } 

        return false;
    } catch {
        return null;
    }
}

export async function changeDoctorInfo(doctor_id, doctor_photo, doctor_price, doctor_info) {
    try {
        const response = await fetch(`${ADRESS}/changeDoctorInfo`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({doctor_id: doctor_id, doctor_photo: doctor_photo, doctor_price:doctor_price, doctor_info:doctor_info}),
        });
        if (response.ok) {
            return true;
        }

        return false;
    } catch {
        return null;
    }
}


