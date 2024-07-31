import axios from 'axios';

export const attendance = axios.create({
    baseURL: 'http://localhost:8080/api/users',
    headers: {
        'Content-Type': 'application/json',
    },
});
