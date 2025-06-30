import React, { useEffect, useState } from 'react';
import './login.css';
import axios from 'axios';
import theme from '../Themes.js'; // Adjust the path to your theme.js
import { FaEye, FaEyeSlash } from 'react-icons/fa'; // Import eye icons

function Login({ settings }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false); // State for password visibility

  useEffect(() => {
    // Set CSS custom properties
    document.documentElement.style.setProperty('--primary-color', theme.palette.primary.main);
    document.documentElement.style.setProperty('--primaryDarker-color', theme.palette.primary.darker);
    document.documentElement.style.setProperty('--secondary-color', theme.palette.secondary.main);
    document.documentElement.style.setProperty('--background-color', theme.palette.background.default);
  }, []);

  const handleLogin = async (event) => {
    event.preventDefault();
    console.log(email, password);
    try {
      // /myapi/api/user/login
      const response = await axios.post('/myapi/api/user/login', {
        'userEmail': `${email}`,
        'userPassword': `${password}`
      });
      console.log(response);
      if (response.status === 200) {
        window.location.href = '/dashboard';
      } else {
        setErrorMsg(response.data);
      }
    } catch (error) {
      setErrorMsg(error.response.data);
      console.log(error, '######################');
    }
  };

  return (
    <div className="loginBodyDiv">
      <div className="login-form-container">
        <form className="login-form" onSubmit={(event) => handleLogin(event)}>
          <h2 className="form-title">Login</h2>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input 
              type="email" 
              id="email" 
              name="email" 
              placeholder="Enter your email" 
              required 
              onChange={(e) => setEmail(e.target.value)} 
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <div className="password-container">
              <input 
                type={passwordVisible ? 'text' : 'password'} // Toggle input type based on state
                id="password" 
                name="password" 
                placeholder="Enter your password" 
                required 
                onChange={(e) => setPassword(e.target.value)} 
              />
              <span 
                className="password-toggle" 
                onClick={() => setPasswordVisible(!passwordVisible)}
                style={{
                  cursor: 'pointer',
                  position: 'absolute',
                  right: '7px'

                }}
              >
                {passwordVisible ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>
          </div>
          {errorMsg && <p style={{ color: 'red' }}>{errorMsg}</p>}
          <button type="submit" className="submit-button mt-2">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;
