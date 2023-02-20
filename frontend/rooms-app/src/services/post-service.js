import axios from 'axios';
import AuthHeaderService from './auth-header';

const API_URL = "http://localhost:8080/posts";

const makePost = (room_id, post_content) => {
      const url = API_URL + `/room/${room_id}/makepost`;

      // return axios.post(url, {
      //       content: post_content
      // }, {
      //       headers: AuthHeaderService.authBodyHeader()
      // });

      return axios.request({
            url: url,
            method: 'post',
            headers: AuthHeaderService.authHeader(),
            body: {
                  content: post_content
            }
      });
}

const deletePost = (post_id) => {
      const url = API_URL + `/${post_id}/delete`;

      return axios.request({
            url: url,
            method: 'delete',
            headers: AuthHeaderService.authHeader()
      });
}

const PostService = {
      makePost,
      deletePost
};

export default PostService;