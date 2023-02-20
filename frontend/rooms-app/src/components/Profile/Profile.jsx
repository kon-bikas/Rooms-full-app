import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { FaUserAlt } from 'react-icons/fa';
import './profile.css';
import RoomCardsGrid from '../Room/RoomCardsGrid';
import RoomCreation from './RoomCreation';
import UserService from '../../services/user-service';

const Profile = () => {

      const location = useLocation();
      const { username } = location.state;

      const navigate = useNavigate();

      const [ userProf, setUserProf ] = useState({});

      useEffect(() => {

            const getUserProf = async () => {

                  try {
                        const response = await UserService.getUserProfile(username);
                        setUserProf(response.data);
                        console.log(response.data);
                        
                  } catch (error) {
                        console.log(error);
                        if (error.response.status === 401) {
                              console.log("code is 401");
                              UserService.removeUser();
                              navigate("/");
                        }
                  }

            }

            getUserProf();

      }, [ username ]);

      return(

            <div className = "user-profile">

                  <div className = "user-card">

                        <FaUserAlt className = "user-icon" />

                        <div className = "user-info">

                              <p>{ userProf?.username }</p>
                              <p>{ userProf?.email }</p>

                        </div>

                  </div>

                  <div className = "room-info">


                        { userProf?.roomCards?.length > 0 ? (
                              <h1>Rooms</h1>

                        ) : (
                              <h1>No Rooms</h1>
                        )}

                        <RoomCardsGrid 
                              cards = { userProf?.roomCards }
                        >
                                    { username === UserService.getUser().username && (
                                          <RoomCreation />
                                    )}

                        </RoomCardsGrid>

                  </div>

            </div>
            
      );

}

export default Profile;