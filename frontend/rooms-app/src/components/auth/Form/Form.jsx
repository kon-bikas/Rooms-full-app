import React from 'react';
import { Link } from 'react-router-dom';

const Form = ({ children, title, action, formError }) => {

      return(

            <div className = "form-container">


                  <form onSubmit = { action }>
                  
                        <h1>{ title }</h1>

                        { children }
                  
                        <button type = "submit">{ title }</button>

                        { formError && (
                              <p>{ formError }</p>
                        )}

                        { title === "log in" ? (
                              <div className = "not-a-member">

                                    <p>Not a member?</p>
                                    <Link to = "/register">register</Link>
      
                              </div>
                        ) : (
                              <div className = "not-a-member">

                                    <p>Already a member?</p>
                                    <Link to = "/login">login</Link>
      
                              </div>
                        )}

                  </form>

            </div>


      );

}

export default Form;
