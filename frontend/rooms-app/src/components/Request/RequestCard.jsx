import React, { useState } from 'react';
import { FaUserAlt } from 'react-icons/fa';
import { BsFillDoorClosedFill } from 'react-icons/bs';
import { Link } from 'react-router-dom';
import LinkToProfile from '../Links/LinkToProfile';
import LinkToRoom from '../Links/LinkToRoom';
import axios from 'axios';
import RequestService from '../../services/request-service';
import UserService from '../../services/user-service';

const user = UserService.getUser();

const RequestCard = ({ req_card }) => {

      const [ reqMessage, setReqMessage ] = useState();

      const handleAccept = async () => {

            try {
                  const response = await axios.request({
                        url: `http://localhost:8080/requests/${req_card.requestId}/acceptrequest`,
                        method: 'put',
                        headers: {
                              'Authorization': `Bearer ${user?.accessToken}`
                        }
                  });
                  
                  setReqMessage("Request Accepted!");
                  console.log(response.data);

            } catch (error) {
                  console.log(error);
            }

      }

      const handleDecline = async () => {

            
            try {
                  const response = await axios.request({
                        url: `http://localhost:8080/requests/${req_card.requestId}/declinerequest`,
                        method: 'delete',
                        headers: {
                              'Authorization': `Bearer ${user.accessToken}`
                        }
                  });
                  
                  setReqMessage("Request Declined!");
                  console.log(response.data);

            } catch (error) {
                  console.log(error);
            }

      }

      return(

            <>
            
                  <div className = "request-card">

                        <div className = "req-card-top">

                              <LinkToProfile 
                                    profileTo = { req_card.sender }
                              />

                              <p>wants to join</p>


                              <LinkToRoom 
                                    roomName = { req_card.roomName }
                              />

                        </div>

                        { !reqMessage ? (

                              <div className = "request-answer-part">

                                    <button 
                                          onClick = { handleAccept }>
                                    Accept</button>

                                    <button 
                                          onClick = { handleDecline }>
                                    Decine</button>

                              </div>
                        ) : (
                              <p>{ reqMessage }</p>
                        )}


                  </div>
            
            </>

      );

}

export default RequestCard;