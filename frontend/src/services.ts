export const ADRESS = 'http://localhost:3001'

const DOCTOR_ID = 'doctor_ID'

export function getDoctorId() {
  return Number.parseInt(sessionStorage.getItem(DOCTOR_ID) ?? '')
}

export async function logIn(
  login: string,
  password: string
): Promise<{ doctor_id: number } | null> {
  try {
    const response = await fetch(`${ADRESS}/doctorLogin`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ doctor_email: login, doctor_password: password }),
    })
    if (response.ok) {
      const result = await response.json()

      const doctorId = result.doctor_id

      sessionStorage.setItem(DOCTOR_ID, doctorId)

      return result
    }

    return null
  } catch {
    return null
  }
}

export async function register(
  doctor_name: string,
  doctor_email: string,
  doctor_password: string,
  doctor_IBAN: string,
  doctor_documents: Blob
): Promise<boolean> {
  try {
    const response = await fetch(`${ADRESS}/doctorRegistration`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({
        doctor_name: doctor_name,
        doctor_email: doctor_email,
        doctor_password: doctor_password,
        doctor_IBAN: doctor_IBAN,
        doctor_documents: doctor_documents,
      }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}

export async function approveDoctor(doctor_id: number) {
  try {
    const response = await fetch(`${ADRESS}/approveDoctor`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ doctor_id: doctor_id }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}

export async function approvePoint(point_id: number) {
  try {
    const response = await fetch(`${ADRESS}/approvePoint`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ point_id: point_id }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}

export async function rejectDoctor(doctor_id: number) {
  try {
    const response = await fetch(`${ADRESS}/rejectDoctor`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ doctor_id: doctor_id }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}

export async function rejectPoint(point_id: number) {
  try {
    const response = await fetch(`${ADRESS}/rejectPoint`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ point_id: point_id }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}

export async function loadDoctorClients(
  doctor_id: number
): Promise<Array<{
  user_id: number
  user_name: string
  consultation_date: string
}> | null> {
  try {
    const response = await fetch(`${ADRESS}/loadDoctorClients`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ doctor_id: doctor_id }),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function loadDoctorConsultations(
  doctor_id: number
): Promise<Array<{
  user_name: string
  consultation_date: string
  user_id: number
}> | null> {
  try {
    const response = await fetch(`${ADRESS}/loadDoctorConsultations`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ doctor_id: doctor_id }),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function loadUnCheckedDoctors(): Promise<Array<{
  doctor_id: number
  doctor_email: string
  doctor_info: string
  doctor_documents: Blob
}> | null> {
  try {
    const response = await fetch(`${ADRESS}/loadUnCheckedDoctors`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({}),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function loadUnCheckedPoints(): Promise<Array<{
  allergen_name: string
  point_photo: Blob
  point_info: string
  point_id: string
}> | null> {
  try {
    const response = await fetch(`${ADRESS}/loadUnCheckedPoints`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({}),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function loadUserInfo(
  user_id: number
): Promise<{
  allergens: Array<{
    allergen_name: string
    allergen_info: string
    allergen_photo: string
  }>
  consultations: Array<{
    consultation_id: number
    consultation_date: string
    consultation_status: string
    doctor_name: string
  }>
  notes: Array<{
    note_id: number
    note_name: string
    note_text: string
    note_date: string
  }>
} | null> {
  try {
    const response = await fetch(`${ADRESS}/loadUserInfo`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ user_id }),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function loadDoctorInfo(
  doctor_id: number
): Promise<{
  doctor_name: string
  doctor_email: string
  doctor_IBAN: string
  doctor_photo: Blob
} | null> {
  try {
    const response = await fetch(`${ADRESS}/loadDoctorInfo`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ doctor_id: doctor_id }),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function loadFullNotes(
  user_id: number
): Promise<Array<{
  note_name: string
  note_text: string
}> | null> {
  try {
    const response = await fetch(`${ADRESS}/loadFullNotes`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ user_id: user_id }),
    })
    if (response.ok) {
      const result = await response.json()
      return result
    }

    return null
  } catch {
    return null
  }
}

export async function cancelCons(consultation_id: number): Promise<boolean> {
  try {
    const response = await fetch(`${ADRESS}/cancelCons`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({ consultation_id: consultation_id }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}

export async function changeDoctorInfo(
  doctor_id: number,
  doctor_photo: Blob,
  doctor_price: number,
  doctor_info: string
) {
  try {
    const response = await fetch(`${ADRESS}/changeDoctorInfo`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({
        doctor_id: doctor_id,
        doctor_photo: doctor_photo,
        doctor_price: doctor_price,
        doctor_info: doctor_info,
      }),
    })
    if (response.ok) {
      return true
    }

    return false
  } catch {
    return false
  }
}
