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