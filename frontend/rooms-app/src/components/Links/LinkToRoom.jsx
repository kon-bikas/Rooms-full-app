import React from 'react';
import { Link } from 'react-router-dom';
import { BsFillDoorOpenFill } from 'react-icons/bs';
import './links.css';

const LinkToRoom = ({ roomId, roomName }) => {

      return(

            <Link 
                  to = "/room"
                  state = {{ room_id: roomId }}>
                  <span>
                        <BsFillDoorOpenFill /> { roomName }
                  </span>
            </Link>

      );

}

export default LinkToRoom;