import axios from 'axios';
import React, { useState, useEffect, useRef } from 'react';
import { AiOutlinePlus, AiOutlineClose } from 'react-icons/ai';
import AuthHeaderService from '../../services/auth-header';
import PostService from '../../services/post-service';
import UserService from '../../services/user-service';

const NewPost = ({ room_id }) => {

      const [ newPost, setNewPost ] = useState(false);
      const [ postContent, setPostContent ] = useState("");

      const [ error, setError ] = useState(false);

      const textarea_ref = useRef();

      const user = UserService.getUser();
      
      const handlePost = async () => {

            var url = `http://localhost:8080/posts/room/${room_id}/makepost`;

            const headers = {
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${user?.accessToken}`
            };

            try {
                  const response = await axios.post(url, {
                        content: postContent
                  }, {
                        headers: headers
                  });

                  console.log(response.data)
                  window.location.reload();

            } catch (error) {
                  setError(true);
                  console.log(error);
            }

      }

      useEffect(() => {

            if (newPost) {
                  textarea_ref.current.focus();
            } 

      }, [ newPost ]);

      if (!newPost) {

            return(
      
                  <div className = "new-post">
      
                        <p>Make your own post? </p> 
                        
                        <AiOutlinePlus 
                              className = "plus-icon" 
                              onClick = { () => setNewPost(prevValue => !prevValue) }
                        />
      
                  </div>
      
            );

      } else {

            return(

                  <div className = "new-post-card">
                  
                        <div className = "new-card-top">

                              <p>@{ user?.username }</p>

                              <AiOutlineClose 
                                    className = "close-button"
                                    onClick = { () => setNewPost(prevValue => !prevValue) }
                              />

                        </div>
                  
                        <div className = "create-post-part">

                              <textarea 
                                    ref = { textarea_ref }
                                    onChange = { (e) => setPostContent(e.target.value) }
                                    name = "new post">
                              </textarea>

                              <button
                                    onClick = { handlePost }
                              >Post</button>

                        </div>

                        { error && (
                              <h3>An error occured.</h3>
                        )}

                  </div>


            );

      }


}

export default NewPost;