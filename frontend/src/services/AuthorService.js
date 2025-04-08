import axios from 'axios';

const API_URL = '/api/v1/authors';

export const AuthorService = {
    getAll(page = 0, size = 10) {
        return axios.get(`${API_URL}?page=${page}&size=${size}`);
    },

    getById(id) {
        return axios.get(`${API_URL}/${id}`);
    },

    findByName(name, surname) {
        return axios.get(`${API_URL}/find?name=${name}&surname=${surname}`);
    },

    create(author) {
        return axios.post(API_URL, author);
    },

    update(id, author) {
        return axios.post(`${API_URL}/${id}`, author);
    },

    delete(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
};