import { NavLink } from 'react-router-dom'

export function Registration() {
  return (
    <>
      <img className="img" src="./photos/авторизация.svg"></img>

      <div className="out">
        <img src="photos/reg.png" className="imgReg" />

        <div className="form">
          <h2 className="reg">Реєстрація</h2>
          <form className="register">
            <label htmlFor="name">ПІБ</label>
            <br />
            <input
              className="input_text"
              type="text"
              id="name"
              name="name"
              placeholder="Повне ім'я"
            />
            <br />
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
            <label htmlFor="password1">Підтвердження пароля</label>
            <br />
            <input
              className="input_text"
              type="password"
              id="password1"
              name="password1"
              placeholder="Введіть пароль ще раз"
            />
            <br />

            <label htmlFor="docs">
              <div>
                <p>Додати документи</p>
              </div>
            </label>
            <br />
            <input id="docs" type="file" name="docs" className="docs" />
            <br />
          </form>

          <input className="submit" type="submit" value="Створити акаунт" />
          <br />
          <nav className="links">
            <a href="link">
              Продовжити з <b>Google</b>
            </a>
            <NavLink to="/login">
              Вже маєте акаунт? <b>Увійти</b>
            </NavLink>
          </nav>
        </div>
      </div>
    </>
  )
}
