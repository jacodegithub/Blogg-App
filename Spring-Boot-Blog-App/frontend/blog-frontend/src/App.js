import './App.css';
import { HomePage } from './pages/homepage';
import { Login } from './pages/login';
import { Register } from './pages/registeration';

function App() {
  return (
    <div className="App">
      <Login />
      <Register />
      <HomePage />
    </div>
  );
}

export default App;
