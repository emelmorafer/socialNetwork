import { BrowserRouter, Routes, Route } from 'react-router-dom'
import UserPage from './views/UserPage'
import PostPage from './views/PostPage'
import LoginPage from './views/LoginPage'

export default function AppRouter(){
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<LoginPage />} index />
        <Route path='/adminuser' element={<UserPage />} />
        <Route path='/adminpost' element={<PostPage />} />
      </Routes>
    </BrowserRouter>
  )
}