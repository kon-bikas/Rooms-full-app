import React, { useState } from 'react';

const PasswordField = ({ setField, error, setError }) => {

      const handleChange = (event) => {
            const { value } = event.target;

            setField(value);

            if (value.length < 5 || value.length > 30) {
                        setError("Password should be 5 to 30 characters");
            } else {
                  setError(null);
            }

      }

      return(

            <div className = "input-field">
            
                  <input 
                        id = "password-field"
                        type = "text" 
                        placeholder = "password" 
                        onChange = { handleChange }
                  />

                  { error && (
                        <div>{ error }</div>
                  )}

            </div>

      );

}

export default PasswordField;