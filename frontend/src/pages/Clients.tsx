import { SideBar } from '../components/SideBar'

export function Clients() {
  return (
    <>
      <header>
        <img src="/photos/logo.svg" className="logo" alt="Логотип" />
        <nav className="header">
          <a href="/">
            <img className="bell" src="/photos/quest.svg" />
          </a>
          <a href="/notifications">
            <img className="bell" src="/photos/bell1.svg" />
          </a>
          <a href="/profile.html">
            <img className="prof_photo" src="/photos/prof_photo.jpg" />
          </a>
        </nav>
      </header>

      <SideBar />
      {/* <div className="panel">
        <div className="buttons">
          <button className="p_button profile_button">Профіль</button>
          <button className="p_button consults">Мої консультації</button>
          <button className="p_button plot">Графік консультацій</button>
          <button className="active">Клієнти</button>
        </div>

        <div className="d_button">
          <img className="door" src="/photos/door1.png" />
          <button className="exit">Вийти</button>
        </div>
      </div> */}
      <div className="clients">
        <h1>Мої клієнти</h1>

        {/* <div className="search">
          <input className="b_search" type="text" placeholder="Пошук" />
          <button className="submit" type="submit">
            Знайти
          </button>
        </div> */}

        <div className="last_clients">
          <a href="/client.html" className="client">
            Ім'я Прізвище, хх.хх.хх
          </a>
        </div>
      </div>
      <footer>
        <p className="contacts">
          <span style={{ fontWeight: 500 }}>Контакти</span>
          <br />
          <br />
          +38 (067) 938 38 38
          <br />
          пн-пт 06:00-21:00
          <br />
          <br />
          allergy.net_info@gmail.com
        </p>
        <p className="copyright">Copyright. All rights reserved</p>
      </footer>{' '}
    </>
  )
}
