import axios from 'axios';

const API_URL = '/api/v1/books';

export const BookService = {
    getAll(page = 0, size = 10) {
        return axios.get(`${API_URL}?page=${page}&size=${size}`);
    },

    getById(id) {
        return axios.get(`${API_URL}/${id}`);
    },

    create(book) {
        return axios.post(API_URL, book);
    },

    update(id, book) {
        return axios.post(`${API_URL}/${id}`, book);
    },

    delete(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
};