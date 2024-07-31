import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { attendance } from './service/attendance';
import Swal from 'sweetalert2';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const logindata = { username, password };

        try {
            let res;
            if (username.startsWith('admin')) {
                res = await attendance.post("/admins/login", logindata);
                sessionStorage.setItem("isAdmin", true);
                sessionStorage.setItem("user", logindata.username);
                Swal.fire({
                    title: "Success!",
                    text: "Admin login successful",
                    icon: "success",
                    confirmButtonText: "OK"
                });
                navigate("/admin/report");
            } else {
                res = await attendance.post("/attendance/login", logindata);
                sessionStorage.setItem("islogin", true);
                sessionStorage.setItem("user", logindata.username);
                Swal.fire({
                    title: "Success!",
                    text: "Successful login",
                    icon: "success",
                    confirmButtonText: "OK"
                });
                navigate("/signin");
            }
            console.log(res.data);
        } catch (err) {
            console.log(err);
            Swal.fire({
                title: "Error",
                text: err.response?.data || "User is not registered. Please register.",
                icon: "error",
                confirmButtonText: "OK"
            });
        }
    };

    return (
        <div style={containerStyle}>
            <div style={boxStyle}>
                <h1 style={headingStyle}>Login</h1>
                <form onSubmit={handleSubmit}>
                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        style={inputStyle}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={inputStyle}
                    />
                    <div style={buttonContainerStyle}>
                        <button type="button" onClick={() => navigate('/signup')} style={buttonStyle}>Register</button>
                        <button type="submit" style={buttonStyle}>Login</button>
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
    marginBottom: '12px',
};

const inputStyle = {
    width: '100%',
    padding: '10px',
    marginTop: '20px',
    boxSizing: 'border-box',
    marginBottom: '10px',
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

export default Login;




