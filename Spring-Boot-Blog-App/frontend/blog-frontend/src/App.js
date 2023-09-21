import './App.css';
import { HomePage } from './pages/homepage/homepage';
import { Login } from './pages/login/loginpage';
import { UserPostpage } from './pages/mypostpage/myPostpage';
import { PostPage } from './pages/postPage/postPage';
import { Register } from './pages/registeration/regiser';
import { Route, Routes } from 'react-router-dom' 

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/register' element={<Register />} />
        <Route path='/login' element={<Login />} />
        <Route path='/user-posts/:userId' element={<UserPostpage />} />
        <Route path='/post-page/:postId' element={<PostPage />} />
      </Routes>
    </div>
  );
}

export default App;
