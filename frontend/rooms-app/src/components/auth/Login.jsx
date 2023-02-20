import React, { useState, useContext } from 'react'
import { AuthContext } from '../../App';
import Form from './Form/Form';
import UsernameField from './Form/UsernameField';
import PasswordField from './Form/PasswordField';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './auth.css';
import UserService from '../../services/user-service';

const Login = () => {

      const [ username, setUsername ] = useState("");
      const [ password, setPassword ] = useState("");

      const [ usernameError, setUsernameError ] = useState();
      const [ passwordError, setPasswordError ] = useState();

      const [ formError, setFormError ] = useState();

      const navigate = useNavigate();

      const onLogin = (event) => {
            event.preventDefault();
            
            if (usernameError !== null && setPassword !== null) {
                  setFormError("Please fill the form in the corrent way");
            } else {
                  sendLoginRequest();
                  setFormError(null);
            }
      }

      const sendLoginRequest = async () => {
            
            var config = {
                  method: 'post',
                  url: 'http://localhost:8080/api/auth/signin',
                  headers: { 
                        'Content-Type': 'application/json'
                  },
                  data: {
                        username: `${username}`,
                        password: `${password}`
                  }
            };
            
            try {
                  const response = await axios.request(config);

                  UserService.setUser(response.data);
                  
                  console.log(response.data);

                  navigate("/");
            } catch (error) {
                  console.log(error);
                  if (error.response.status === 401) {
                        setFormError("Wrong Credentials");
                  }
            }

      }

      return(

            <>
            
                  <Form 
                        title = "log in"
                        action = { onLogin }
                        formError = { formError }
                  >

                        <UsernameField 
                              setField = { setUsername } 
                              error = { usernameError }
                              setError = { setUsernameError }
                        />

                        <PasswordField 
                              setField = { setPassword }
                              error = { passwordError }
                              setError = { setPasswordError } 

                        />

                  </Form>


            </>

      );

}

export default Login;