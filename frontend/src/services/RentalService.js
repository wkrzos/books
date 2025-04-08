import axios from 'axios';

const API_URL = '/api/v1/rentals';

export const RentalService = {
    rentBook(bookId, username) {
        return axios.post(`${API_URL}/${bookId}/rent?username=${username}`);
    },
    
    returnBook(rentalId) {
        return axios.post(`${API_URL}/${rentalId}/return`);
    }
};