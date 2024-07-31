import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { attendance } from './service/attendance';
import Swal from 'sweetalert2';

const AttendanceReport = () => {
    const [attendanceData, setAttendanceData] = useState([]);
    const navigate = useNavigate();
    const location = useLocation();

    const username = new URLSearchParams(location.search).get('username');

    useEffect(() => {
        const fetchAttendance = async () => {
            try {
                const response = await attendance.get('/attendance/report', { params: { username } });
                const report = response.data.report;
                const parsedData = report.split('\n').filter(entry => entry).map(entry => {
                    const [datePart, signInPart, signOutPart] = entry.split(', ');
                    const date = datePart.split(': ')[1];
                    const signInTime = signInPart.split(': ')[1] || 'Absent';
                    const signOutTime = signOutPart.split(': ')[1] || 'Absent';
                    return { date, signInTime, signOutTime };
                });
                setAttendanceData(parsedData);
            } catch (error) {
                Swal.fire({
                    title: "Error",
                    text: error.response?.data.message || error.message || 'Failed to fetch attendance report',
                    icon: "error",
                    confirmButtonText: "OK"
                });
            }
        };

        if (username) {
            fetchAttendance();
        } else {
            navigate('/login');
        }
    }, [username, navigate]);

    return (
        <div style={containerStyle}>
            {attendanceData.length === 0 ? (
                <p>No attendance data available.</p>
            ) : (
                attendanceData.map((entry, index) => (
                    <div key={index} style={entryStyle}>
                        <div style={dateStyle}>{entry.date}</div>
                        <div style={statusStyle}>
                            Sign in - {entry.signInTime !== 'null' ? entry.signInTime : 'Absent'}
                        </div>
                        <div style={statusStyle}>
                            Sign out - {entry.signOutTime !== 'null' ? entry.signOutTime : 'Absent'}
                        </div>
                    </div>
                ))
            )}
        </div>
    );
};

const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '40px',
    boxSizing: 'border-box',
    border: '1px solid #ccc',
    borderRadius: '8px',
    width: '25%',
    margin: '20px auto',
};

const entryStyle = {
    marginBottom: '20px',
    padding: '10px',
    border: '1px solid #ccc',
    borderRadius: '8px',
    width: '100%',
};

const dateStyle = {
    fontSize: '18px',
    fontWeight: 'bold',
    marginBottom: '10px',
};

const statusStyle = {
    fontSize: '16px',
    marginTop: '5px',
};

export default AttendanceReport;
