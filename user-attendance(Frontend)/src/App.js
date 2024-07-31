import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import RegisterPage from './components/RegisterPage';
import Login from './components/Login';
import Protectedroute from './Protectedroute';
import SignIn from './components/SignIN';
import SignOut from './components/SignOut';
import AttendanceReport from './components/AttendanceReport';
import AdminReport from './components/AdminReport';



function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
           <Route path='/signup' element={<RegisterPage/>}/>
           <Route path="/login" element={<Login/>}/>
           <Route path="/" element={<Protectedroute/>}>
           {/* <Route path="/admin" element={<Admin/>}/> */}
           <Route path="/signin" element={<SignIn/>}/>
           <Route path="/signout" element={<SignOut/>}/>
           <Route path="admin/report" element={<AdminReport/>}/>
           <Route path="/attendance/report" element={<AttendanceReport />} />
          </Route>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
