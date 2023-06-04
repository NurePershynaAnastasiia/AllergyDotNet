import { useEffect, useState } from 'react'
import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'
import { cancelCons, getDoctorId, loadDoctorConsultations } from '../services'

export function Consultations() {
  useProtected()
  const [consults, setConsults] = useState<
    Array<{
      user_name: string
      consultation_date: string
      user_id: number
      consultation_id: number
      canceled?: boolean
      consultation_status: string
    }>
  >([])

  useEffect(() => {
    ;(async () => {
      const result = await loadDoctorConsultations(getDoctorId())

      if (!result) {
        alert('Не вдалося завантажити інформацію про консультації')
        return
      }

      setConsults(result)
    })()
  }, [])

  async function cancel(cons_id: number) {
    console.log('cancel')
    const result = await cancelCons(cons_id)
    if (result) {
      setConsults(
        consults.map((c) => {
          if (c.consultation_id === cons_id) {
            c.canceled = true
          }
          return c
        })
      )
    }
  }

  return (
    <>
      <Header />

      <SideBar />

      <div className="consultations">
        <div className="search_consultations">
          <input className="b_search" type="text" placeholder="Пошук" />
          <button className="submit" type="submit">
            Знайти
          </button>
        </div>

        {consults.map((c) => (
          <div className="block" key={c.consultation_id}>
            <div className="ph_and_name">
              <img src="/photos/draco.jpg" />
              <p>{c.user_name}</p>
              {console.log(c) as any}
            </div>
            <div className="date_and_btn">
              <p>{c.consultation_date}</p>
              <button
                className={
                  'dis ' + c.consultation_status === 'Завершено'
                    ? 'done-class'
                    : c.consultation_status === 'Скасовано'
                    ? 'cancel-class'
                    : ''
                }
                onClick={() => cancel(c.consultation_id)}
              >
                {c.consultation_status == 'Очікується'
                  ? !c.canceled
                    ? 'Скасувати'
                    : 'Скасовано'
                  : c.consultation_status}
              </button>
            </div>
          </div>
        ))}
      </div>

      <Footer />
    </>
  )
}
