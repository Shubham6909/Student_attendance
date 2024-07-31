import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';

const SignOut = () => {
    const [username, setUsername] = useState(sessionStorage.getItem('user'));
    const [currentDate, setCurrentDate] = useState(new Date().toLocaleDateString('en-GB'));
    const [currentTime, setCurrentTime] = useState(new Date().toLocaleTimeString('en-US', { hour12: true }));

    useEffect(() => {
        const timer = setInterval(() => {
            setCurrentTime(new Date().toLocaleTimeString('en-US', { hour12: true }));
        }, 1000);

        return () => clearInterval(timer);
    }, []);

    const handleSignOut = async () => {
        try {
            const response = await axios.post('http://localhost:8080/attendance/signout', { username });
            Swal.fire({
                title: "Success!",
                text: response.data,
                icon: "success",
                confirmButtonText: "OK"
            });
            sessionStorage.removeItem('user');
            sessionStorage.removeItem('islogin');
            window.location.href = '/login'; // redirect to login page
        } catch (error) {
            Swal.fire({
                title: "Error",
                text: error.response ? error.response.data : 'Sign out failed',
                icon: "error",
                confirmButtonText: "OK"
            });
        }
    };

    return (
        <div style={containerStyle}>
            <div style={dateTimeContainerStyle}>
                <div style={dateStyle}>{currentDate}</div>
                <div style={timeStyle}>{currentTime}</div>
            </div>
            <button style={signOutButtonStyle} onClick={handleSignOut}>SIGN OUT</button>
            <button style={viewReportButtonStyle}>View Report</button>
        </div>
    );
};

const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: '100vh',
    padding: '20px',
    boxSizing: 'border-box',
    border: '1px solid #ccc',
    borderRadius: '8px',
    maxWidth: '300px',
    margin: '0 auto',
    textAlign: 'center',
};

const dateTimeContainerStyle = {
    marginBottom: '20px',
};

const dateStyle = {
    fontSize: '18px',
    fontWeight: 'bold',
};

const timeStyle = {
    fontSize: '18px',
    fontWeight: 'bold',
    marginTop: '10px',
};

const signOutButtonStyle = {
    padding: '10px 20px',
    fontSize: '16px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    backgroundColor: '#f44336',
    color: '#fff',
    marginBottom: '20px',
    width: '80%',
};

const viewReportButtonStyle = {
    padding: '10px 20px',
    fontSize: '16px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    backgroundColor: '#2196F3',
    color: '#fff',
    width: '80%',
};

export default SignOut;
