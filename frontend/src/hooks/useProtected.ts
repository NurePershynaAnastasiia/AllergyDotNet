import { useNavigate } from 'react-router-dom'
import { getDoctorId } from '../services'
import { useEffect } from 'react'

export function useProtected() {
  const navigate = useNavigate()

  useEffect(() => {
    const doctorId = getDoctorId()

    if (!doctorId || Number.isNaN(doctorId)) {
      navigate('/login')
    }
  })
}
