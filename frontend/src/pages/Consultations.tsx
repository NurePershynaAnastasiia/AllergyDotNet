import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'

export function Consultations() {
  useProtected()

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

        <div className="block">
          <div className="ph_and_name">
            <img src="/photos/draco.jpg" />
            <p>Прізвище Ім'я По-батькові</p>
          </div>
          <div className="date_and_btn">
            <p>xx.xx.xxxx</p>
            <button className="dis">Скасувати</button>
          </div>
        </div>
      </div>

      <Footer />
    </>
  )
}
