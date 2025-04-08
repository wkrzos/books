<template>
  <div class="rentals">
    <h2>Book Rental Management</h2>
    
    <div class="rental-form">
      <h3>Rent a Book</h3>
      <div class="form-group">
        <label for="bookId">Book ID:</label>
        <input v-model="rentForm.bookId" type="number" id="bookId" required />
      </div>
      <div class="form-group">
        <label for="username">Username:</label>
        <input v-model="rentForm.username" type="text" id="username" required />
      </div>
      <button @click="rentBook" :disabled="isRentFormInvalid">Rent Book</button>
    </div>

    <div class="return-form">
      <h3>Return a Book</h3>
      <div class="form-group">
        <label for="rentalId">Rental ID:</label>
        <input v-model="returnForm.rentalId" type="number" id="rentalId" required />
      </div>
      <button @click="returnBook" :disabled="!returnForm.rentalId">Return Book</button>
    </div>

    <div v-if="currentRental" class="rental-details">
      <h3>Last Rental Operation Details</h3>
      <div class="detail-card">
        <div><strong>Rental ID:</strong> {{ currentRental.id }}</div>
        <div><strong>Book:</strong> {{ currentRental.bookDTO?.title }}</div>
        <div><strong>User:</strong> {{ currentRental.userDTO?.username }}</div>
        <div><strong>Rental Date:</strong> {{ formatDate(currentRental.rentalDate) }}</div>
        <div v-if="currentRental.returnDate">
          <strong>Return Date:</strong> {{ formatDate(currentRental.returnDate) }}
        </div>
        <div v-else class="status active">Currently rented</div>
      </div>
    </div>

    <div v-if="error" class="error-message">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { RentalService } from '@/services/RentalService';

export default {
  name: 'RentalList',
  data() {
    return {
      rentForm: {
        bookId: null,
        username: ''
      },
      returnForm: {
        rentalId: null
      },
      currentRental: null,
      error: null
    };
  },
  computed: {
    isRentFormInvalid() {
      return !this.rentForm.bookId || !this.rentForm.username;
    }
  },
  methods: {
    async rentBook() {
      try {
        this.error = null;
        const response = await RentalService.rentBook(
          this.rentForm.bookId, 
          this.rentForm.username
        );
        this.currentRental = response.data;
        this.resetRentForm();
      } catch (error) {
        console.error('Error renting book:', error);
        this.error = error.response?.data || 'Error renting book';
      }
    },
    async returnBook() {
      try {
        this.error = null;
        const response = await RentalService.returnBook(this.returnForm.rentalId);
        this.currentRental = response.data;
        this.resetReturnForm();
      } catch (error) {
        console.error('Error returning book:', error);
        this.error = error.response?.data || 'Error returning book';
      }
    },
    resetRentForm() {
      this.rentForm = {
        bookId: null,
        username: ''
      };
    },
    resetReturnForm() {
      this.returnForm = {
        rentalId: null
      };
    },
    formatDate(dateString) {
      if (!dateString) return '';
      const date = new Date(dateString);
      return date.toLocaleString();
    }
  }
};
</script>

<style scoped>
.rentals {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.rental-form, .return-form {
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 5px;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.rental-details {
  margin-top: 30px;
}

.detail-card {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  padding: 15px;
  border-radius: 5px;
}

.detail-card div {
  margin-bottom: 8px;
}

.status.active {
  color: #4CAF50;
  font-weight: bold;
}

.error-message {
  color: #f44336;
  padding: 10px;
  background-color: #ffebee;
  border-radius: 4px;
  margin-top: 20px;
}
</style>