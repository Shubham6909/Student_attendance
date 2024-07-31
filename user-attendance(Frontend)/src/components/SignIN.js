import React, { useState, useEffect } from 'react';
import { attendance } from './service/attendance';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';

const SignIn = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState(sessionStorage.getItem('user') || '');

    const formatDate = (date) => {
        return new Intl.DateTimeFormat('en-GB', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric'
        }).format(date).replace(/\//g, '-');
    };

    const [currentDate, setCurrentDate] = useState(formatDate(new Date()));
    const [currentTime, setCurrentTime] = useState(new Date().toLocaleTimeString('en-US', { hour12: true }));
    const [status, setStatus] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            // try {
            //     const statusResponse = await attendance.get('/attendance/status', { params: { username } });
            //     setStatus(statusResponse.data.status);
            // } catch (error) {
            //     console.error('Error fetching attendance status', error);
            // }
        };

        fetchData();
        
        const timer = setInterval(() => {
            setCurrentTime(new Date().toLocaleTimeString('en-US', { hour12: true }));
        }, 1000);

        return () => clearInterval(timer);
    }, [username]);

    const handleSignIn = async () => {
        try {
            if (!username) {
                throw new Error('Username not found. Please log in again.');
            }
            const user = { username };
            const response = await attendance.post('/attendance/signin', user);
            Swal.fire({
                title: "Success!",
                text: response.data.message,
                icon: "success",
                confirmButtonText: "OK"
            });
            setStatus(true);
        } catch (error) {
            Swal.fire({
                title: "Error",
                text: error.response?.data.message || 'Sign in failed',
                icon: "error",
                confirmButtonText: "OK"
            });
        }
    };

    const handleSignOut = async () => {
        try {
            if (!username) {
                throw new Error('Username not found. Please log in again.');
            }
            const user = { username };
            const response = await attendance.post('/attendance/signout', user);
            Swal.fire({
                title: "Success!",
                text: response.data.message,
                icon: "success",
                confirmButtonText: "OK"
            });
            setStatus(false);
        } catch (error) {
            Swal.fire({
                title: "Error",
                text: error.response?.data.message || 'Sign out failed',
                icon: "error",
                confirmButtonText: "OK"
            });
        }
    };

    const handleViewReport = () => {
        navigate(`/attendance/report?username=${username}`);
    };

    if (!username) {
        navigate('/login');
        return null;
    }

    return (
        <div style={containerStyle}>
            <div style={dateTimeContainerStyle}>
                <div style={dateStyle}>{currentDate}</div>
                <div style={timeStyle}>{currentTime}</div>
            </div>
            {!status ? (
                <button style={signInButtonStyle} onClick={handleSignIn}>SIGN IN</button>
            ) : (
                <button style={signOutButtonStyle} onClick={handleSignOut}>SIGN OUT</button>
            )}
            <button style={viewReportButtonStyle} onClick={handleViewReport}>View Report</button>
        </div>
    );
};
const containerStyle = {
    marginTop: '1px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: '85vh',
    padding: '20px',
    boxSizing: 'border-box',
    border: '1px solid #ccc',
    borderRadius: '8px',
    maxWidth: '300px',
    margin: '25px auto',
    textAlign: 'center',
};

const dateTimeContainerStyle = {
    marginBottom: '20px',
};

const dateStyle = {
    fontSize: '18px',
    fontWeight: 'bold',
    marginTop: '-60px',
};

const timeStyle = {
    fontSize: '18px',
    fontWeight: 'bold',
    // marginTop: '0px',
};

const signInButtonStyle = {
    padding: '10px 20px',
    fontSize: '16px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    backgroundColor: '#6e726e',
    color: '#fff',
    marginBottom: '10px',
    width: '80%',
};

const signOutButtonStyle = {
    padding: '10px 20px',
    fontSize: '16px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    backgroundColor: '#6e726e',
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
    backgroundColor: '#6e726e',
    color: '#fff',
    width: '80%',
    margin: '40% auto',
};

export default SignIn;


