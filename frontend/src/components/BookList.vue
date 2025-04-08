<template>
  <div class="books">
    <h2>Books</h2>
    <button @click="showCreateForm = true" class="add-button">Add New Book</button>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Author ID</th>
          <th>Pages</th>
          <th>Available</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.authorID }}</td>
          <td>{{ book.pages }}</td>
          <td>{{ book.isAvailable ? 'Yes' : 'No' }}</td>
          <td>
            <button @click="editBook(book)">Edit</button>
            <button @click="deleteBook(book.id)" class="delete-btn">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button @click="prevPage" :disabled="page === 0">Previous</button>
      <span>Page {{ page + 1 }}</span>
      <button @click="nextPage">Next</button>
    </div>

    <!-- Create/Edit Modal -->
    <div v-if="showCreateForm || editingBook" class="modal">
      <div class="modal-content">
        <h3>{{ editingBook ? 'Edit Book' : 'Create Book' }}</h3>
        <form @submit.prevent="saveBook">
          <div class="form-group">
            <label for="title">Title:</label>
            <input v-model="currentBook.title" id="title" required />
          </div>
          <div class="form-group">
            <label for="authorId">Author ID:</label>
            <input v-model="currentBook.authorID" id="authorId" type="number" required />
          </div>
          <div class="form-group">
            <label for="pages">Pages:</label>
            <input v-model="currentBook.pages" id="pages" type="number" />
          </div>
          <div class="form-group">
            <label for="available">Available:</label>
            <input v-model="currentBook.isAvailable" id="available" type="checkbox" />
          </div>
          <div class="button-group">
            <button type="submit">Save</button>
            <button type="button" @click="cancelEdit">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { BookService } from '@/services/BookService';

export default {
  name: 'BookList',
  data() {
    return {
      books: [],
      page: 0,
      size: 10,
      showCreateForm: false,
      editingBook: null,
      currentBook: {
        title: '',
        authorID: null,
        pages: null,
        isAvailable: true
      }
    };
  },
  created() {
    this.loadBooks();
  },
  methods: {
    async loadBooks() {
      try {
        const response = await BookService.getAll(this.page, this.size);
        this.books = response.data;
      } catch (error) {
        console.error('Error loading books:', error);
        alert('Error loading books');
      }
    },
    async deleteBook(id) {
      if (confirm('Are you sure you want to delete this book?')) {
        try {
          await BookService.delete(id);
          this.loadBooks();
        } catch (error) {
          console.error('Error deleting book:', error);
          alert('Error deleting book');
        }
      }
    },
    editBook(book) {
      this.editingBook = book;
      this.currentBook = { ...book };
      this.showCreateForm = true;
    },
    async saveBook() {
      try {
        if (this.editingBook) {
          await BookService.update(this.editingBook.id, this.currentBook);
        } else {
          await BookService.create(this.currentBook);
        }
        this.cancelEdit();
        this.loadBooks();
      } catch (error) {
        console.error('Error saving book:', error);
        alert('Error saving book');
      }
    },
    cancelEdit() {
      this.editingBook = null;
      this.showCreateForm = false;
      this.currentBook = {
        title: '',
        authorID: null,
        pages: null,
        isAvailable: true
      };
    },
    prevPage() {
      if (this.page > 0) {
        this.page--;
        this.loadBooks();
      }
    },
    nextPage() {
      this.page++;
      this.loadBooks();
    }
  }
};
</script>

<style scoped>
.books {
  padding: 20px;
}

.add-button {
  margin-bottom: 15px;
  background-color: #4CAF50;
  color: white;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal-content {
  background: white;
  padding: 25px;
  border-radius: 5px;
  min-width: 350px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input[type="text"],
.form-group input[type="number"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

button {
  padding: 8px 15px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
  background-color: #2196F3;
  color: white;
}

button:hover {
  opacity: 0.9;
}

button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.delete-btn {
  background-color: #f44336;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  gap: 10px;
}
</style>