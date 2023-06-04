import { useState } from 'react'
import { NavLink, useNavigate } from 'react-router-dom'
import { logIn } from '../services'

export function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const navigate = useNavigate()

  async function handleSubmit() {
    console.log('hadleSubmit')

    const result = await logIn(email, password)

    if (!result) {
      alert('Incorrect logIn data')
      return
    }

    navigate('/profile')
  }

  return (
    <>
      <img className="img" src="./photos/авторизация.svg"></img>
      <div className="out">
        <img src="photos/reg.png" className="imgReg" />

        <div className="form_a">
          <h2>Авторизація</h2>
          <form className="register" onSubmit={handleSubmit}>
            <label htmlFor="email">Пошта</label>
            <br />
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input_text"
              type="email"
              id="email"
              name="email"
              placeholder="Адреса ел. пошти"
            />
            <br />

            <label htmlFor="password">Пароль</label>
            <br />
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input_text"
              type="password"
              id="password"
              name="password"
              placeholder="Введіть пароль"
            />
            <br />
          </form>

          <input
            className="submit"
            type="submit"
            value="Увійти"
            onClick={handleSubmit}
          />
          <br />
          <div className="create">
            <a href="link" className="continue">
              Продовжити з <b>Google</b>
            </a>
            <NavLink to={'/registration'} className="cr_acc">
              Вперше з нами? <b>Створи новий акаунт</b>
            </NavLink>
          </div>
        </div>
      </div>{' '}
    </>
  )
}
