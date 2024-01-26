import axios from "axios";

export default {
    getUserById(userId) {
        const token = localStorage.getItem('token');
        return axios.get(`users/${userId}`, { headers: { Authorization: `Bearer ${token}` } }) 
    }
}