import axios from 'axios';
import AuthHeaderService from './auth-header';

const API_URL = "http://localhost:8080/rooms";

const getRoom = (room_id) => {
      const url = API_URL + `/${room_id}`;

      return axios.get(url, { 
            headers: AuthHeaderService.authHeader() 
      });
}

const getAllRoomCards = () => {
      const url = API_URL + "/roomcards";

      return axios.get(url, { 
            headers: AuthHeaderService.authHeader() 
      });
}

const createRoom = (room_name, room_description) => {
      const url = API_URL + "/create";

      return axios.post(url, {
            name: room_name,
            description: room_description
      }, {
            headers: AuthHeaderService.authBodyHeader()
      });
}

const kickMember = (room_id, member_username) => {
      const url = API_URL + `/${room_id}/kick`;

      return axios.patch(url, {
            username: member_username
      }, {
            headers: AuthHeaderService.authBodyHeader()
      });
}

const RoomService = {
      getRoom,
      getAllRoomCards,
      createRoom,
      kickMember
};

export default RoomService;