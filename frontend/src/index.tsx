import { createRoot } from 'react-dom/client'
import { Provider } from 'react-redux'
import store from './store'

import './styles/clients.css'
import './styles/check_docs.css'
import './styles/check_point.css'
import './styles/client.css'
import './styles/consultations.css'
import './styles/footer.css'
import './styles/header.css'
import './styles/panel.css'
import './styles/plate.css'
import './styles/profile.css'
import './styles/style.css'

import App from './App'

const container = document.getElementById('root') as HTMLDivElement
const root = createRoot(container!)

root.render(
  <Provider store={store}>
    <App />
  </Provider>
)
