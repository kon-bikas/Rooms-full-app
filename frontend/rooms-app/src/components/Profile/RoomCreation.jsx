import { isValidDateValue } from '@testing-library/user-event/dist/utils';
import axios from 'axios';
import React, { useState, useEffect, useRef } from 'react';
import { AiOutlinePlus, AiOutlineClose } from 'react-icons/ai';
import { BsFillDoorOpenFill } from 'react-icons/bs';
import AuthHeaderService from '../../services/auth-header';
import RoomService from '../../services/room-service';
import UserService from '../../services/user-service';

const user = UserService.getUser();

const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user?.accessToken}`
};

const url = "http://localhost:8080/rooms/create";

const RoomCreation = () => {

      const [ createRoom, setCreateRoom ] = useState(false);

      const [ roomName, setRoomName ] = useState();
      const [ roomDesc, setRoomDesc ] = useState();

      const [ error, setError ] = useState(false);

      const input_ref = useRef();

      const handleRoomCreation = async () => {
            try {
                  
                  const response = await axios.post(url, {
                        name: roomName,
                        description: roomDesc
                  }, {
                        headers: headers
                  });

                  console.log(response.data);
                  window.location.reload();

            } catch (error) {
                  console.log(error);
                  setError(true);
            }

      }

      const handleNameChage = (event) => {
            const { value } = event.target;

            setRoomName(value);
      }

      const handleDescChange = (event) => {
            const { value } = event.target;

            setRoomDesc(value);
      }

      useEffect(() => {

            if (createRoom) {
                  input_ref.current.focus();
            }

      }, [ createRoom ]);

      if (!createRoom) {
            return(
                  <div 
                        className = "add-room"
                        onClick = { () => setCreateRoom(true) }>

                        <AiOutlinePlus className = "add-room-icon" />

                  </div>
            );
      } else {
            return(

                  <div className = "add-room-bg">

                        <div className = "add-room-card">

                              <div className = "add-room-card-top">

                                    <div className = "add-room-card-input">

                                          <BsFillDoorOpenFill 
                                                className = "add-room-door-icon"
                                          />

                                          <input 
                                                ref = { input_ref }
                                                type = "text"
                                                onChange = { handleNameChage } 
                                                placeholder = 'name' />
                                    </div>

                                    <AiOutlineClose 
                                          onClick = { () => setCreateRoom(false) }
                                          className = "add-room-close-icon"
                                    />
                              </div>

                              <textarea 
                                    name = "desc"
                                    onChange = { handleDescChange }
                                    placeholder = 'Description'>
                              </textarea>

                              <button
                                    onClick = { handleRoomCreation }>
                              Create Room</button>

                              { error && (
                                    <h3>An error occured</h3>
                              )}

                        </div>

                  </div>

            );
      }

}

export default RoomCreation;