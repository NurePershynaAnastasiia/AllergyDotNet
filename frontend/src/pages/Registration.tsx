import { FormEvent, useState } from 'react'
import { NavLink, useNavigate } from 'react-router-dom'
import { register } from '../services'

export function Registration() {
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [iban, setIban] = useState('')
  const [file, setFile] = useState<File | undefined>()

  const navigate = useNavigate()

  async function handleSubmit(e: FormEvent) {
    e.preventDefault()

    console.log('hadleSubmit')

    const result = await register(name, email, iban, password, file as Blob)

    if (!result) {
      alert('Incorrect registration data')
      return
    }

    navigate('/login')

    alert(
      'Ви успішно зареєструвалися! Лист про активацію вашого акаунту ми надішлемо на вказану вами пошту.'
    )
  }
  return (
    <>
      <img className="img" src="./photos/авторизация.svg"></img>

      <div className="out">
        <img src="photos/reg.png" className="imgReg" />

        <div className="form">
          <h2 className="reg">Реєстрація</h2>
          <form className="register" onSubmit={handleSubmit}>
            <label htmlFor="name">ПІБ</label>
            <br />
            <input
              value={name}
              onChange={(e) => setName(e.target.value)}
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
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input_text"
              type="email"
              id="email"
              name="email"
              placeholder="Адреса ел. пошти"
            />
            <br />

            <label htmlFor="password">IBAN</label>
            <br />
            <input
              value={iban}
              onChange={(e) => setIban(e.target.value)}
              className="input_text"
              id="iban"
              name="iban"
              placeholder="Введіть пароль"
            />
            <br />
            <label htmlFor="password1">Пароль</label>
            <br />
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input_text"
              type="password"
              id="password"
              name="password"
              placeholder="Введіть пароль ще раз"
            />
            <br />

            <label htmlFor="docs">
              <div>
                <p>Додати документи</p>
              </div>
            </label>
            <br />
            <input
              id="docs"
              type="file"
              name="docs"
              className="docs"
              onChange={(e) => setFile(e.target.files?.[0])}
              accept="application/pdf"
            />
            <br />
            <input className="submit" type="submit" value="Створити акаунт" />
          </form>

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
