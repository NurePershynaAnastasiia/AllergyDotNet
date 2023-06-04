import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

// import { Navbar } from './components/Navbar'
import { Clients } from './pages/Clients'

const App: React.FC = () => {
  return (
    <BrowserRouter>
      {/* <Navbar /> */}
      {/* <div className="container"> */}
        <Routes>
          <Route path="/clients" element={<Clients />} />
        </Routes>
      {/* </div> */}
    </BrowserRouter>
  )
}

export default App
