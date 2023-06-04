import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'

export function Profile() {
  return (
    <>
      <Header /> <SideBar />{' '}
      <div className="profile">
        <h1 className="profile_h">Прізвище Ім'я По-батькові</h1>

        <div className="prof_info">
          <div className="ph_and_info">
            <img src="/photos/draco.jpg" className="photo" />

            <div className="inputs">
              <input type="text" className="who" placeholder="Алерголог" />
              <textarea
                className="notes"
                placeholder="Коротко запишіть інформацію про себе"
              ></textarea>
              <div className="price1">
                {' '}
                <input
                  type="number"
                  className="price"
                  min="100"
                  max="10000"
                  step="100"
                />
                <p style={{ fontSize: '16px', fontWeight: 800 }}>ГРН</p>{' '}
              </div>
            </div>
          </div>
          <div>
            <h3 className="profile_h">Документи та сертифікати</h3>
            <img src="/photos/" className="sert" />
          </div>
        </div>
      </div>{' '}
      <Footer />
    </>
  )
}
