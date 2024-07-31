import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { attendance } from './service/attendance';
import Swal from 'sweetalert2';

const AdminReport = () => {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await attendance.get('/attendance/all');
                setUsers(response.data);
            } catch (error) {
                Swal.fire({
                    title: "Error",
                    text: error.response?.data.message || error.message || 'Failed to fetch users',
                    icon: "error",
                    confirmButtonText: "OK"
                });
            }
        };

        fetchUsers();
    }, []);

    const handleUserClick = (username) => {
        navigate(`/attendance/report?username=${username}`);
    };

    return (
        <div style={containerStyle}>
            <h1>Admin Report</h1>
            <ul style={listStyle}>
                {users.map((user, index) => (
                    <li key={index} style={listItemStyle} onClick={() => handleUserClick(user.username)}>
                        {user.username}
                    </li>
                ))}
            </ul>
        </div>
    );
};

const containerStyle = {
    padding: '20px',
    boxSizing: 'border-box',
    border: '1px solid #ccc',
    borderRadius: '8px',
    width: '25%',
    margin: '0 auto',
};

const listStyle = {
    listStyleType: 'none',
    padding: 0,
};

const listItemStyle = {
    padding: '10px',
    border: '1px solid #ccc',
    borderRadius: '8px',
    marginBottom: '10px',
    cursor: 'pointer',
    backgroundColor: '#f0f0f0',
};

export default AdminReport;