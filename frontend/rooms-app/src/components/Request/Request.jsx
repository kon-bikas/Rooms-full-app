import React, { useState, useEffect } from 'react';
import { v4 as uuidv4 } from 'uuid';
import { useNavigate } from 'react-router-dom';
import RequestCard from './RequestCard';
import './request.css';
import axios from 'axios';
import UserService from '../../services/user-service';

const Request = () => {

      const [ requests, setRequests ] = useState({});

      const navigate = useNavigate();

      const user = JSON.parse(window.localStorage.getItem("user"));

      useEffect(() => {

            var url = `http://localhost:8080/requests/all/user/requests`;
      
            var config = {
                  headers: { 
                        'Authorization': `Bearer ${user?.accessToken}` 
                  }
            };

            const getRequests = async () => {

                  try {
                        const response = await axios.get(url, config);
                        console.log(response.data);
                        setRequests(response.data);

                  } catch (error) {
                        console.log(error);
                        if (error.response.status === 401) {
                              console.log("code is 401");
                              UserService.removeUser();
                              navigate("/");
                        }
                  }

            }

            getRequests();

      }, []);

      if (requests?.length > 0) {
            
            return(
      
                  <div className = "requests-container">
                  
                        <h1>Your Requests</h1>
      
                        { requests?.map((cur_req) => 
                              <RequestCard 
                                    key = { uuidv4() }
                                    req_card = { cur_req }
                              />
                        )}
      
      
                  
                  </div>
      
            );

      } else {
            return(

                  <div className = "requests-container">
                        <h1>No Requests.</h1>
                  </div>

            );
      }


}

export default Request;