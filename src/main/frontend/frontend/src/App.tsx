import './App.css'
import {Route, Routes} from "react-router-dom";
import Home from './components/Home.tsx'
import Login from './components/Login.tsx'
import SignUp from './components/SignUp.tsx'
import {useState} from "react";

function App() {

const[user, setUser] = useState(getUserToken());

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/signup" element={<SignUp/>}/>
      </Routes>

    </div>
  )
}

export default App
