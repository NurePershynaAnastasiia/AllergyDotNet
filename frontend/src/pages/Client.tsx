import { Accordion } from '../components/Accordion'
import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'

export function Client() {
  useProtected()

  return (
    <>
      <Header />

      <SideBar />

      <h1 className="client_h">Ім'я Прізвище</h1>
      <div className="client">
        <div className="wrapper">
          <div className="allergens">
            <h3 className="client_h">Має алергію на:</h3>
            <p className="allergen">Алерген</p>
            <p className="allergen">Алерген</p>
            <p className="allergen">Алерген</p>
            <p className="allergen">Алерген</p>
          </div>

          <div className="last_cons">
            <h3 className="client_h">Останні зустрічі:</h3>
            <p className="con">xx/xx/xxxx</p>
            <p className="con">xx/xx/xxxx</p>
            <p className="con">xx/xx/xxxx</p>
            <p className="con">xx/xx/xxxx</p>
          </div>
        </div>

        <div className="notes">
          <h3 className="client_h">Нотатки:</h3>
          <ol>
            <Accordion
              text={`жфівоажсьіо фісашьдфішо іфсаьдфішсрд фісарфдсідвлсоідлсоідлсос
                віост імсі ам фісшгра рараіср рд`}
              header={'Заголовок'}
            />
          </ol>
        </div>
      </div>

      <Footer />
    </>
  )
}
