import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'

export function CheckDocs() {
  useProtected()

  return (
    <>
      <Header /> <SideBar />{' '}
      <div className="check_docs">
        <div className="search_check_docs">
          <input className="b_search" type="text" placeholder="Пошук" />
          <button className="submit" type="submit">
            Знайти
          </button>
        </div>
        <div className="info">
          <div className="text_info">
            <h1 className="check_docs_h">Прізвище Ім'я По-батькові</h1>
            <p>ID: 123456</p>
            <p>G-mail: 12345632@gmail.com</p>
          </div>

          <div className="serts">
            <h2 className="check_docs_h">Документи та сартифікати</h2>
            <a href=".pdf">файл</a>
          </div>

          <div className="yes_no">
            <button className="yes">Cхвалити</button>
            <button className="no">Відхилити</button>
          </div>
        </div>
      </div>{' '}
      <Footer />
    </>
  )
}
