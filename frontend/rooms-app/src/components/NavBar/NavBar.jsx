import React from 'react';
import NavList from './NavList';
import { Link } from 'react-router-dom';
import './navbar.css';

const NavBar = () => {

      return(

            <>

                  <header>

                        <div className = "nav-container">

                              <div className = "nav-logo">

                                    <Link
                                          to = "/"
                                    >
                                          <h2>Rooms.</h2> 
                                    </Link>

                              </div>

                              <div className = "nav-items">

                                    <NavList /> 

                              </div>

                        </div>

                  </header>
            
            </>

      );

}

export default NavBar;