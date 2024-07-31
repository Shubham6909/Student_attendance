import React, { useState } from 'react';
import { attendance } from '../components/service/attendance';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

const RegisterPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const registrationData = {
        username,
        password,
        email,
        phone
      };

      const response = await attendance.post("/attendance/register", registrationData);
      console.log(response.data);
      
      // Display success notification
      Swal.fire({
        title: "Success!",
        text: "Registration successful",
        icon: "success",
        confirmButtonText: "OK"
      }).then(() => {
        navigate('/login');
      });
    } catch (error) {
      console.error('Registration failed:', error);
      
      // Display error notification
      Swal.fire({
        title: "Error",
        text: error.response?.data || "Registration failed. Please try again.",
        icon: "error",
        confirmButtonText: "OK"
      });
    }
  };

  return (
    <div style={containerStyle}>
      <div style={boxStyle}>
        <h1 style={headingStyle}>Register</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="USERNAME"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            style={inputStyle}
          />
          <input
            type="password"
            placeholder="PASSWORD"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={inputStyle}
          />
          <input
            type="email"
            placeholder="EMAIL ID"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            style={inputStyle}
          />
          <input
            type="tel"
            placeholder="PHONE NO."
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            style={inputStyle}
          />
          <div style={buttonContainerStyle}>
            <button type="submit" style={buttonStyle}>Register</button>
            <button type="button" onClick={() => navigate('/login')} style={buttonStyle}>Login</button>
          </div>
        </form>
      </div>
    </div>
  );
};

const containerStyle = {
  maxWidth: '300px',
  height: '500px',
  margin: '0 auto',
  padding: '20px',
  textAlign: 'center',
};

const boxStyle = {
  border: '1px solid #ccc',
  borderRadius: '8px',
  padding: '20px',
};

const headingStyle = {
  fontSize: '24px',
  marginBottom: '20px',
};

const inputStyle = {
  width: '100%',
  padding: '10px',
  marginBottom: '10px',
  boxSizing: 'border-box',
};

const buttonContainerStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  marginTop: '20px',
};

const buttonStyle = {
  width: '48%',
  padding: '12px',
  backgroundColor: '#6e726e',
  color: '#fff',
  border: 'none',
  borderRadius: '4px',
  cursor: 'pointer',
};

export default RegisterPage;