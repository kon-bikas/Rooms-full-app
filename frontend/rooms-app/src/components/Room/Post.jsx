import React, { useState } from 'react';
import { AiFillLike } from 'react-icons/ai';
import { BsFillTrashFill } from 'react-icons/bs';
import { FaUserAlt } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import axios from 'axios';
import PostService from '../../services/post-service';
import RoomService from '../../services/room-service';

const user = JSON.parse(window.localStorage.getItem("user"));

const Post = ({ post, role, room_id }) => {

      const [ postLiked, setPostLiked ] = useState(false);

      const [ postDeleted, setPostDeleted ] = useState(false);
      const [ userKicked, setUserKicked ] = useState();

      const handlePostDelete = async () => {

            try {
                  const response = await PostService.deletePost(post.id);
                  
                  setPostDeleted(true);
                  console.log(response.data);

            } catch (error) {
                  console.log(error);
            }

      }

      const handleUserKick = async () => {
            
            var url = `http://localhost:8080/rooms/${room_id}/kick`;

            var config = {
                  headers: { 
                        'Authorization': `Bearer ${user.accessToken}`
                  }
            };

            try { 
                  const response = await axios.patch(url, {
                        username: post.username
                  }, config);
                  
                  setUserKicked("User kicked from room");


            } catch (error) {
                  if (error.response.data.status === 400) {
                        setUserKicked(error.response.data.message);
                  }
                  console.log("hellow");
            }

            


      }

      return(

            <div className = "post">
            
                  <div className = "post-upper-part">

                        {/* <h2><FaUserAlt /> { post.username }</h2> */}
                        <div className = "post-user-tag">

                              <Link 
                                    to = "/profile"
                                    state = {{ username: post.username }}>
                                    <FaUserAlt /> { post.username }
                              </Link>

                              { (role === "admin" && !userKicked) && (
                                    <button 
                                          onClick = { handleUserKick }>
                                    kick user</button>
                              )}

                              { userKicked && (<p><i>{ userKicked }</i></p>)}

                        </div>

                        { (role === "admin" && !postDeleted) && (
                              <BsFillTrashFill 
                                    className = "delete-icon" 
                                    onClick = { handlePostDelete }
                              />
                        )}

                  </div>
            
                  { postDeleted ? (
                        <p><i>Post Deleted.</i></p>
                  ) : (
                        <p className = "post-content">{ post.content }</p>
                  )}

            </div>

      );

}

export default Post;