import { NavLink } from 'react-router-dom'

export function SideBar() {
  return (
    <div className="panel">
      <div className="buttons">
        <NavLink
          className={({ isActive }) =>
            isActive ? 'active' : 'p_button profile_button'
          }
          to={'/profile'}
        >
          Профіль
        </NavLink>
        <NavLink
          className={({ isActive }) =>
            isActive ? 'active' : 'p_button consults'
          }
          to={'/consultations'}
        >
          Мої консультації
        </NavLink>
        <NavLink
          className={({ isActive }) => (isActive ? 'active' : 'p_button plot')}
          to={'/plate'}
        >
          Графік консультацій
        </NavLink>
        <NavLink
          className={({ isActive }) => (isActive ? 'active' : 'p_button')}
          to={'/clients'}
        >
          Клієнти
        </NavLink>
      </div>

      <div className="d_button">
        <img className="door" src="/photos/door1.png" />
        <button className="exit">Вийти</button>
      </div>
    </div>
  )
}
