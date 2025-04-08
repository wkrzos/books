import axios from 'axios';

const API_URL = '/api/v1/users';

export const UserService = {
    getAll(page = 0, size = 10) {
        return axios.get(`${API_URL}?page=${page}&size=${size}`);
    },

    getById(id) {
        return axios.get(`${API_URL}/${id}`);
    },

    getByUsername(username) {
        return axios.get(`${API_URL}/find?username=${username}`);
    },

    create(user) {
        return axios.post(API_URL, user);
    },

    update(id, user) {
        return axios.post(`${API_URL}/${id}`, user);
    },

    delete(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
};