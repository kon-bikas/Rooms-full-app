import axios from 'axios';
import AuthHeaderService from './auth-header';

const API_ULR = "http://localhost:8080/requests";

const sendRequest = (room_id) => {
      const url = API_ULR + `/send/room/${room_id}`;

      return axios.request({
            url: url,
            method: 'post',
            headers: AuthHeaderService.authHeader()
      });
}

const acceptRequest = (request_id) => {
      const url = API_ULR + `${request_id}/acceptrequest`;

      return axios.request({
            url: url,
            method: 'put',
            headers: AuthHeaderService.authHeader()
      });
}

const declineRequest = (request_id) => {
      const url = API_ULR + `${request_id}/declinerequest`

      return axios.request({
            url: url,
            method: 'delete',
            headers: AuthHeaderService.authHeader()
      });
}

const RequestService = {
      sendRequest,
      acceptRequest,
      declineRequest
};

export default RequestService;