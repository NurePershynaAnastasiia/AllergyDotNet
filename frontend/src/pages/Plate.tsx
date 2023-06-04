import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'

export function Plate() {
  useProtected()

  return (
    <>
      <Header /> <SideBar />{' '}
      <div className="plate">
        <h1>Графік консультацій</h1>
        <div className="row">
          <table>
            <tr>
              <td> </td>
              <td>xx.xx</td>
              <td>xx.xx</td>
              <td>xx.xx</td>
              <td>xx.xx</td>
              <td>xx.xx</td>
              <td>xx.xx</td>
              <td>xx.xx</td>
            </tr>
            <tr>
              <td>10:00</td>
              <td>
                <img className="" />
              </td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>11:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>12:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>13:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>14:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>15:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>16:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>17:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>18:00</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </table>
          <div className="instruction">
            <h3>Правила використання інтерактивного календаря</h3>
            <p>
              Наведіть курсор на перетин потрібного дня тижня і часу. При кліку
              з’язляється умовне позначення робочого часу. При повторному кліку
              умовне позначення змінюється на порожнє місце, що означає
              неробочій час. Також на калердарі відмічені існуючі консульації,
              їх змінювати неможливо.{' '}
            </p>
            <h4>Позначення на календарі</h4>
            <div className="work">
              <img className="free" />
              <p>Робочий час</p>
            </div>
            <div className="work">
              <img className="will" />
              <p>Консультація</p>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  )
}
