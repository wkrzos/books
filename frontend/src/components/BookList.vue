<template>
  <div class="books">
    <h2>Books</h2>
    <button @click="showCreateForm = true">Add New Book</button>

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
            <button @click="deleteBook(book.id)">Delete</button>
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
            <label>Title:</label>
            <input v-model="currentBook.title" required />
          </div>
          <div class="form-group">
            <label>Author ID:</label>
            <input v-model="currentBook.authorID" type="number" required />
          </div>
          <div class="form-group">
            <label>Pages:</label>
            <input v-model="currentBook.pages" type="number" />
          </div>
          <div class="form-group">
            <label>Available:</label>
            <input v-model="currentBook.isAvailable" type="checkbox" />
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

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
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
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
  min-width: 300px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input[type="text"],
.form-group input[type="number"] {
  width: 100%;
  padding: 8px;
}

.button-group {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}

.pagination {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  align-items: center;
}

button {
  padding: 5px 10px;
  cursor: pointer;
}
</style>