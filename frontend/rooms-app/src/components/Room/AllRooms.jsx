import React, { useEffect, useState } from 'react';
import RoomCardsGrid from './RoomCardsGrid';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import RoomService from '../../services/room-service';
import UserService from '../../services/user-service';

const user = UserService.getUser();

const headers = {
      'Authorization': `Bearer ${user?.accessToken}`
};

const url = "http://localhost:8080/rooms/roomcards";

const AllRooms = () => {

      const navigate = useNavigate();

      const [ roomCards, setRoomCards ] = useState([]);

      useEffect(() => {
            
            const getAllRooms = async () => {
                  try {
                  
                        const response = await RoomService.getAllRoomCards();
                        setRoomCards(response.data);
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

            getAllRooms();

      }, [])

      return(

            <div className = "all-room-cards-container">

                  <h1>All Rooms</h1>

                  <RoomCardsGrid
                        cards = { roomCards }
                  />
            
            </div>

      );

}

export default AllRooms;