import axios from 'axios';
import AuthHeaderService from './auth-header';

const API_URL = "http://localhost:8080/users";

const getUser = () => {
      return JSON.parse(window.localStorage.getItem("user"));
}

const setUser = (data) => {
      window.localStorage.setItem("user", JSON.stringify(data));
}

const removeUser = () => {
      window.localStorage.removeItem("user");
}

const getUserProfile = (username) => {
      const url = encodeURI(API_URL + `/profile/${username}`);

      return axios.get(url, { headers: AuthHeaderService.authHeader() });
}

const UserService = {
      getUser,
      setUser,
      removeUser,
      getUserProfile
};

export default UserService;