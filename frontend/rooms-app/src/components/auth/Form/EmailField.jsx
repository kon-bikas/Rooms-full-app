import React, { useState } from 'react';
import * as EmailValidator from 'email-validator';

const EmailField = ({ setField, error, setError }) => {

      const handleChange = (event) => {
            const { value } = event.target;

            setField(value);

            if (!EmailValidator.validate(value)) {
                        setError("Please give a valid email");
            } else {
                  setError(null);
            }

      }

      return(

            <div className = "input-field">
            
                  <input 
                        id = "email-field"
                        type = "text" 
                        placeholder = "Email" 
                        onChange = { handleChange }
                  />

                  { error && (
                        <div>{ error }</div>
                  )}

            </div>

      );

}

export default EmailField;