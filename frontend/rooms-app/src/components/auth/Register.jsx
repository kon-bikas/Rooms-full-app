import React, { useState } from 'react';
import Form from './Form/Form';
import UsernameField from './Form/UsernameField';
import PasswordField from './Form/PasswordField';
import EmailField from './Form/EmailField';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './auth.css'
import UserService from '../../services/user-service';

const Register = () => {

      const [ username, setUsername ] = useState("");
      const [ email, setEmail ] = useState("");
      const [ password, setPassword ] = useState("");

      const [ usernameError, setUsernameError ] = useState();
      const [ emailError, setEmailError ] = useState();
      const [ passwordError, setPasswordError ] = useState();

      const [ formError, setFormError ] = useState();

      const navigate = useNavigate();

      const onRegister = (event) => {
            event.preventDefault();

            if (usernameError !== null && setPassword !== null && emailError !== null) {
                  setFormError("Please fill the form in the corrent way");
            } else {
                  sendLoginRequest();
                  setFormError(null);
            }

      }

      const sendLoginRequest = async () => {

            var config = {
                  method: 'post',
                  url: 'http://localhost:8080/api/auth/singup',
                  headers: { 
                        'Content-Type': 'application/json'
                  },
                  data : {
                        username: `${username}`,
                        email: `${email}`,
                        password: `${password}`
                  }
            };
            
            try {
                  const response = await axios.request(config);
                  console.log(response.data);

                  UserService.setUser(response.data);
                  console.log(response.data.accessToken);
                  navigate("/");

            } catch (error) {
                  console.log(error);
                  if (error.repsonse.status === 400) {
                        setFormError(error.response.data.message);      
                  } else {
                        setFormError("Something went wrong");
                  }
                  console.log(error.response.data.message);
                  
            }
      }

      return(

            <>

                  <Form 
                        title = "register"
                        action = { onRegister }
                        formError = { formError }
                  >

                        <UsernameField 
                              setField = { setUsername } 
                              error = { usernameError }
                              setError = { setUsernameError }
                        />

                        <EmailField 
                              setField = { setEmail } 
                              error = { emailError }
                              setError = { setEmailError }
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

export default Register;