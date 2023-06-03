export const ADRESS = "http://localhost:3000";


export async function login(login, password) {
    try {
        const response = await fetch(`${ADRESS}/doctorLogin`, {
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

        return null
    } catch {
        return null;
    }
}

export async function register(login, password) {
    try {
        const response = await fetch(`${ADRESS}/doctorLogin`, {
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

        return null
    } catch {
        return null;
    }
}
