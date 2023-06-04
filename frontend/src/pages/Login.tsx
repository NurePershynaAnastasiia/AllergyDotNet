import { NavLink } from 'react-router-dom'

export function Login() {
  return (
    <>
      <img className="img" src="./photos/авторизация.svg"></img>
      <div className="out">
        <img src="photos/reg.png" className="imgReg" />

        <div className="form_a">
          <h2>Авторизація</h2>
          <form className="register">
            <label htmlFor="email">Пошта</label>
            <br />
            <input
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
              className="input_text"
              type="password"
              id="password"
              name="password"
              placeholder="Введіть пароль"
            />
            <br />
          </form>

          <input className="submit" type="submit" value="Увійти" />
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
