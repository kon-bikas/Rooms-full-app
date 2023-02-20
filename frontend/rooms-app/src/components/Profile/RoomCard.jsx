import React, { useState } from 'react';
import { createRoutesFromElements } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { BsFillDoorClosedFill, BsFillDoorOpenFill } from 'react-icons/bs';
import { AiOutlineArrowRight } from 'react-icons/ai';
import LinkToRoom from '../Links/LinkToRoom';
import axios from 'axios';
import RequestService from '../../services/request-service';

const RoomCard = ({ card }) => {

      const [ requestSent, setRequestSent ] = useState(false);
      const [ error, setError ] = useState(false);

      const handleRequest = async () => {

            setRequestSent(true);

            try {
                  const response = await RequestService.sendRequest(card.id);
                  
                  console.log(response.data);

            } catch (error) {
                  setError(true);
                  console.log(error);
            }

      }

      return(

            <div className = "room-card">
            
                  <div className = "room-card-main-part">
                        <span>
                              { !card.role ? (
                                    <BsFillDoorClosedFill /> 
                              ) : (
                                    <BsFillDoorOpenFill />
                              )}
                              { card.name }
                        </span>
                  
                        <p>{ card.description }</p>
                  
                  </div>

                  { !card.role ? (

                        <div className = "room-card-req">
                              { !requestSent ? (<>

                                    <p>Want to join this room? Send a request</p>
            
                                    <button onClick = { handleRequest }>Send Request</button>

                              </>) : (<>
                              

                                    { error ? (
                                          <p>An error occured.</p>
                                    ) : (
                                          <p>Request sent succesfully!</p>
                                    )}

                              </>)}

                        </div>

                  ) : (

                        <div className = "part-of-room-section">
                              { card.role === "requested" ? (
                                    <p>You have made a request to this room.</p>
                              ) : (<>
                                    
                                    <p>You are { card.role } of this room.</p>

                                    <Link 
                                          to = "/room"
                                          state = {{ room_id: card.id }} >
                                          <span>
                                                Visit room <AiOutlineArrowRight />
                                          </span>
                                    </Link>
                              </>)}

                        </div>

                  )}

                  

            </div>

      );

}

export default RoomCard;