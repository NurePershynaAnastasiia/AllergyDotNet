import { NavLink } from 'react-router-dom'
import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'
import { getDoctorId, loadDoctorClients } from '../services'
import { useEffect, useState } from 'react'

export function Clients() {
  useProtected()
  const [clients, setClient] = useState<
    Array<{
      user_id: number
      user_name: string
      consultation_date: string
    }>
  >([])

  useEffect(() => {
    ;(async () => {
      const result = await loadDoctorClients(getDoctorId())

      if (!result) {
        alert('Не вдалося завантажити інформацію про консультації')
        return
      }

      setClient(result)
    })()
  }, [])

  return (
    <>
      <Header />
      <SideBar />
      <div className="clients">
        <h1>Мої клієнти</h1>

        {/* <div className="search">
          <input className="b_search" type="text" placeholder="Пошук" />
          <button className="submit" type="submit">
            Знайти
          </button>
        </div> */}
        {clients.map((cl) => (
          <div className="last_clients" key={cl.user_id}>
            <NavLink to={`/client/${cl.user_id}`} className="client">
              {cl.user_name}, {cl.consultation_date}
            </NavLink>
          </div>
        ))}
      </div>
      <Footer />{' '}
    </>
  )
}
