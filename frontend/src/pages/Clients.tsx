import { NavLink } from 'react-router-dom'
import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'

export function Clients() {
  useProtected()

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

        <div className="last_clients">
          <NavLink to={'/client.html'} className="client">
            Ім'я Прізвище, хх.хх.хх
          </NavLink>
        </div>
      </div>
      <Footer />{' '}
    </>
  )
}
