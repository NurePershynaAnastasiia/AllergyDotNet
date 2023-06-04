import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

// import { Navbar } from './components/Navbar'
import { Clients } from './pages/Clients'
import { Login } from './pages/Login'
import { Registration } from './pages/Registration'
import { Client } from './pages/Client'
import { CheckPoint } from './pages/Check_point'
import { CheckDocs } from './pages/CheckDocs'
import { Profile } from './pages/Profile'
import { Consultations } from './pages/Consultations'
import { Plate } from './pages/Plate'


const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/clients" element={<Clients />} />
        <Route path="/client/:id" element={<Client />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registration" element={<Registration />} />
        <Route path="/check_point" element={<CheckPoint />} />
        <Route path="/check_docs" element={<CheckDocs />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/consultations" element={<Consultations />} />
        <Route path="/plate" element={<Plate />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
