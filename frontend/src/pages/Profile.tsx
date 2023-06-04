import { useEffect, useState } from 'react'
import { Footer } from '../components/Footer'
import { Header } from '../components/Header'
import { SideBar } from '../components/SideBar'
import { useProtected } from '../hooks/useProtected'
import { getDoctorId, loadDoctorInfo } from '../services'

export function Profile() {
  useProtected()

  const [name, setName] = useState('')

  const [note, setNote] = useState('')
  const [price, setPrice] = useState(100)
  const [documentBlob, setDocumentBlob] = useState<null | Blob>(null)
  const [photoBlob, setPhotoBlob] = useState<null | Blob>(null)

  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    setTimeout(async () => {
      const result = await loadDoctorInfo(getDoctorId())

      if (!result) {
        alert('Не вдалося завантажити інформацію про лікаря')
        return
      }

      setName(result.doctor_name)
      setNote(result.doctor_info)
      const document = new Blob([
        new Uint8Array(result.doctor_documents.data as any),
      ])
      setDocumentBlob(document)
      setPhotoBlob(new Blob([new Uint8Array(result.doctor_photo.data as any)]))
      setIsLoading(false)
    }, 2000)
    // ;(async () => {

    // })()
  }, [])

  return (
    <>
      <Header /> <SideBar />{' '}
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <div className="profile">
          <h1 className="profile_h">{name}</h1>

          <div className="prof_info">
            <div className="ph_and_info">
              {photoBlob && (
                <img src={URL.createObjectURL(photoBlob)} className="photo" />
              )}

              <div className="inputs">
                <input
                  type="text"
                  className="who"
                  placeholder="Алерголог"
                  disabled
                />
                <textarea
                  value={note}
                  onChange={(e) => setNote(e.target.value)}
                  className="notes"
                  placeholder="Коротко запишіть інформацію про себе"
                ></textarea>
                <div className="price1">
                  {' '}
                  <input
                    value={price}
                    onChange={(e) => setPrice(e.target.valueAsNumber)}
                    type="number"
                    className="price"
                    min="100"
                    max="10000"
                    step="100"
                  />
                  <p style={{ fontSize: '16px', fontWeight: 800 }}>ГРН</p>{' '}
                </div>
              </div>
            </div>
            <div>
              {documentBlob && (
                <a
                  href={URL.createObjectURL(documentBlob)}
                  download={'document.pdf'}
                >
                  <h3 className="profile_h">Документи та сертифікати</h3>
                </a>
              )}
            </div>
          </div>
        </div>
      )}{' '}
      <Footer />
    </>
  )
}
