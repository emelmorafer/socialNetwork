import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './css/styles.css'
import AppRouter from './router'
import { UserSessionProvider } from './context/UserSessionContext';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <UserSessionProvider>
      <AppRouter />
    </UserSessionProvider>
  </StrictMode>,
)
