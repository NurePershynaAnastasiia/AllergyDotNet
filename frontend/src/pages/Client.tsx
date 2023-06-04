import { useParams } from 'react-router-dom'
import { Accordion } from '../components/Accordion'
import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'
import { useEffect, useState } from 'react'
import { loadUserInfo } from '../services'

export function Client() {
  useProtected()

  const { id } = useParams()

  const [allergens, loadAllergens] = useState<
    Array<{
      allergen_id: number
      allergen_name: string
      allergen_info: string
      allergen_photo: string
    }>
  >([])

  const [consults, loadConsults] = useState<
    Array<{
      consultation_id: number
      consultation_date: string
    }>
  >([])

  const [notes, loadNotes] = useState<
    Array<{
      note_id: number
      note_name: string
      note_text: string
    }>
  >([])

  useEffect(() => {
    ;(async () => {
      const result = await loadUserInfo(Number.parseInt(id ?? '') ?? 0)

      if (!result) {
        alert('Не вдалося завантажити інформацію про консультації')
        return
      }

      loadAllergens(result.allergens)
      loadConsults(result.consultations)
      loadNotes(result.notes)
    })()
  }, [])

  return (
    <>
      <Header />

      <SideBar />

      <h1 className="client_h">Ім'я Прізвище</h1>
      <div className="client">
        <div className="wrapper">
          <div className="allergens">
            <h3 className="client_h">Має алергію на:</h3>
            {allergens.map((al) => (
              <p className="allergen" key={al.allergen_id}>
                {al.allergen_name}
              </p>
            ))}
          </div>

          <div className="last_cons">
            <h3 className="client_h">Останні зустрічі:</h3>
            {consults.map((cons) => (
              <p className="con" key={cons.consultation_id}>
                {cons.consultation_date}
              </p>
            ))}
          </div>
        </div>

        <div className="notes">
          <h3 className="client_h">Нотатки:</h3>
          <ol>
            {notes.map((n) => (
              <Accordion
                key={n.note_id}
                text={n.note_text}
                header={n.note_name}
              />
            ))}
          </ol>
        </div>
      </div>

      <Footer />
    </>
  )
}
