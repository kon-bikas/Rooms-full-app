import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { BsFillDoorClosedFill } from 'react-icons/bs';
import { v4 as uuidv4 } from 'uuid';
import Post from './Post';
import NewPost from './NewPost';
import './room.css';
import RoomService from '../../services/room-service';
import UserService from '../../services/user-service';

const Room = () => {

      const location = useLocation();
      const { room_id } = location.state;

      const navigate = useNavigate();

      const [ room, setRoom ] = useState({});

      useEffect(() => {

            const getRoom = async () => {

                  try {
                        const response = await RoomService.getRoom(room_id);
                        setRoom(response.data);
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

            getRoom();

      }, []);

      return(
      
            <div className = "room-container">
      
                  <div className = "room-info-section">

                        <div className = "room-name">

                              <BsFillDoorClosedFill 
                                    className = "door-icon"
                              />

                              <div className = "room-name-role">

                                    <p>{ room?.name }</p>
                                    <p><span>•</span> { room?.userRole }</p>

                              </div>

                        </div>

                        <div className = "room-descriprion">

                              <h1>Description</h1>

                              <p>{ room?.description }</p>

                        </div>

                  </div>

                  <NewPost 
                        room_id = { room?.id }
                  />

                  { room?.posts?.length <= 0 ? (
                        <h1
                              style = {{ textAlign: "center" }}
                        >No Posts Yet.</h1>
                  ) : (
                        <div className = "room-posts">

                              { room?.posts?.reverse().map((cur_post) => 
                                    <Post 
                                          key = { uuidv4() }
                                          post = { cur_post }
                                          role = { room.userRole }
                                          room_id = { room.id }
                                    />
                              )}

                        </div>
                  )}


            </div>
      
      );

}

export default Room;