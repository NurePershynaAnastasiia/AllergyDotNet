import { NavLink } from 'react-router-dom'

export function SideBar() {
  return (
    <div className="panel">
      <div className="buttons">
        <NavLink className="p_button profile_button" to={'/profile'}>
          Профіль
        </NavLink>
        <NavLink className="p_button consults" to={'/'}>
          Мої консультації
        </NavLink>
        <NavLink className="p_button plot" to={'/'}>
          Графік консультацій
        </NavLink>
        <NavLink className="active" to={'/'}>
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
