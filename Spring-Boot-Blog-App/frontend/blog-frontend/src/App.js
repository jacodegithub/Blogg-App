import './App.css';
import { HomePage } from './pages/homepage/homepage';
import { Login } from './pages/login/loginpage';
import { Register } from './pages/registeration/regiser';
import { Route, Routes } from 'react-router-dom' 

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/register' element={<Register />} />
        <Route path='/login' element={<Login />} />
      </Routes>
    </div>
  );
}

export default App;
