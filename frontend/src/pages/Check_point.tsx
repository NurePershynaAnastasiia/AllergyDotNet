import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'

export function CheckPoint() {
  useProtected()

  return (
    <>
      <Header />
      <SideBar />
      <div className="check_point">
        <div className="search_check_point">
          <input className="b_search" type="text" placeholder="Пошук" />
          <button className="submit" type="submit">
            Знайти
          </button>
        </div>
        <div className="row">
          <img src="/photos/draco.jpg" />
          <div className="info">
            <h1 className="check_point_h1">Назва</h1>
            <p>
              AJDCOIMJFSPIONPCADIGHNVIUHFNGOIUDHNGVIOSUHV DV
              UFHVNOIUHVOIUHVGOIUHVOIUFVHSOIUVHGODSIUH DFVHGODSIUFHVGODSIUFHV
              DVOUFGHVDIUFHGV ILUDFHIUHD FJVBBLD LKDSJBL KSDJHGLSIUDHGDB SDJ{' '}
            </p>
          </div>
        </div>
        <div className="yes_no">
          <button className="yes">Cхвалити</button>
          <button className="no">Відхилити</button>
        </div>
      </div>{' '}
      <Footer />
    </>
  )
}
