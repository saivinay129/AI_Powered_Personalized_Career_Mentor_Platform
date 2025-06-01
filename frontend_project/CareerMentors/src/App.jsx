import { useState } from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css'
import "bootstrap/dist/css/bootstrap.min.css"
import Chatbot from './components/chatbot'
import Login from './components/Login'
import Signup from './components/Signup1'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/chat" element={<Chatbot />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
