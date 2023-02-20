import React from 'react';
import { Link } from 'react-router-dom';
import { FaUserAlt } from 'react-icons/fa';
import './links.css';

const LinkToProfile = ({ profileTo }) => {

      return(

            <Link 
                  to = "/profile"
                  state = {{ username: profileTo }}>
                  <span>
                        <FaUserAlt /> { profileTo }
                  </span>
            </Link>

      );

}

export default LinkToProfile;