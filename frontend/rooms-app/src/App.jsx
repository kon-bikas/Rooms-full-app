import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/auth/Login';
import Register from './components/auth/Register';
import Profile from './components/Profile/Profile';
import NavBar from './components/NavBar/NavBar';
import Room from './components/Room/Room';
import Request from './components/Request/Request';
import AllRooms from './components/Room/AllRooms';
import Home from './components/Home/Home';

const App = () => {

      return(

            <>
                  
                  <NavBar />


                  <Routes>

                        <Route path = "/" element = { <Home /> } ></Route>
                        <Route path = "/login" element = { <Login /> }></Route>
                        <Route path = "/profile" element = { <Profile /> } ></Route>
                        <Route path = "/register" element = { <Register /> }></Route>
                        <Route path = "/room" element = { <Room /> } ></Route>
                        <Route path = "/requests" element = { <Request /> } ></Route>
                        <Route path = "/all/rooms" element = { <AllRooms /> } ></Route>

                  </Routes>
            
            </>

      );

}

export default App;
