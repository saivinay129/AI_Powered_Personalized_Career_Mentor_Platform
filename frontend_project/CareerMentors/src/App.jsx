import { useState } from 'react'
import './App.css'
import "bootstrap/dist/css/bootstrap.min.css"
import Chatbot from './components/chatbot'

function App() {

  return (
    <>
      <div className='d-flex justify-content-center align-items-center vh-100'>
        <Chatbot />
      </div>  
    </>
  )
}

export default App
