import { NavLink } from 'react-router-dom'

export function Header() {
  return (
    <header>
      <img src="/photos/logo.svg" className="logo" alt="Логотип" />
      <nav className="header">
        <a href="/">
          <img className="bell" src="/photos/quest.svg" />
        </a>
        <a href="/notifications">
          <img className="bell" src="/photos/bell1.svg" />
        </a>
        <NavLink to={'/profile.html'}>
          <img className="prof_photo" src="/photos/prof_photo.jpg" />
        </NavLink>
      </nav>
    </header>
  )
}
