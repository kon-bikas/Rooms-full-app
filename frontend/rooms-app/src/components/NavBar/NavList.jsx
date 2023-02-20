import React, { useEffect, useState } from 'react';
import { NavLink, Link, useNavigate } from 'react-router-dom';
import UserService from '../../services/user-service';

const NavList = () => {

      const navigate = useNavigate();

      const user = UserService.getUser();

      const handleLogOut = () => {
            UserService.removeUser();
            navigate("/login");
      }

      if (user) {
            return(

                  <div className = "nav-list">

                        <ul>

                              <li>
                                    <NavLink 
                                          to = "/requests"
                                          style = { ({ isActive }) => {
                                                return { 
                                                      backgroundColor: isActive 
                                                      && "rgb(121, 175, 245)" 
                                                }
                                          }}>
                                    Requests</NavLink>
                              </li>

                              <li>
                                    <NavLink 
                                          to = "/all/rooms"
                                          style = { ({ isActive }) => {
                                                return {
                                                      backgroundColor: isActive && 
                                                      "rgb(121, 175, 245)" 
                                                }
                                          }}>
                                    Rooms</NavLink>
                              </li>

                              <li>
                                    <Link 
                                          to = "/profile"
                                          state = {{ 
                                                username: user?.username
                                          }}>
                                                
                                    My Profile</Link>
                              </li>

                              <li 
                                    onClick = { handleLogOut }
                                    style = {{ cursor: "pointer" }}>
                                    
                                    Log out
                              </li>

                        </ul>

                  </div>
            );
      } else {
            return(
                  <div className = "nav-list">
                        <ul>
                              <li>
                                    <Link 
                                          to = "/login">

                                    Log in</Link>
                              </li>
                        </ul>
                  </div>
            );
      }
            
}

export default NavList;