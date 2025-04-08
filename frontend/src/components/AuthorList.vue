<template>
  <div class="authors">
    <h2>Authors</h2>
    <div class="search-box">
      <input v-model="searchName" placeholder="Name" />
      <input v-model="searchSurname" placeholder="Surname" />
      <button @click="searchAuthor">Search</button>
      <button @click="resetSearch">Reset</button>
    </div>

    <button @click="showCreateForm = true" class="add-button">Add New Author</button>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Surname</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="author in authors" :key="author.id">
          <td>{{ author.id }}</td>
          <td>{{ author.name }}</td>
          <td>{{ author.surname }}</td>
          <td>
            <button @click="editAuthor(author)">Edit</button>
            <button @click="deleteAuthor(author.id)" class="delete-btn">Delete</button>
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
    <div v-if="showCreateForm || editingAuthor" class="modal">
      <div class="modal-content">
        <h3>{{ editingAuthor ? 'Edit Author' : 'Create Author' }}</h3>
        <form @submit.prevent="saveAuthor">
          <div class="form-group">
            <label for="name">Name:</label>
            <input v-model="currentAuthor.name" id="name" required />
          </div>
          <div class="form-group">
            <label for="surname">Surname:</label>
            <input v-model="currentAuthor.surname" id="surname" required />
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
import { AuthorService } from '@/services/AuthorService';

export default {
  name: 'AuthorList',
  data() {
    return {
      authors: [],
      page: 0,
      size: 10,
      showCreateForm: false,
      editingAuthor: null,
      currentAuthor: { name: '', surname: '' },
      searchName: '',
      searchSurname: ''
    };
  },
  created() {
    this.loadAuthors();
  },
  methods: {
    async loadAuthors() {
      try {
        const response = await AuthorService.getAll(this.page, this.size);
        this.authors = response.data;
      } catch (error) {
        console.error('Error loading authors:', error);
      }
    },
    async searchAuthor() {
      if (this.searchName && this.searchSurname) {
        try {
          const response = await AuthorService.findByName(this.searchName, this.searchSurname);
          this.authors = [response.data];
        } catch (error) {
          console.error('Error searching author:', error);
        }
      } else {
        this.loadAuthors();
      }
    },
    resetSearch() {
      this.searchName = '';
      this.searchSurname = '';
      this.loadAuthors();
    },
    async deleteAuthor(id) {
      if (confirm('Are you sure you want to delete this author?')) {
        try {
          await AuthorService.delete(id);
          this.loadAuthors();
        } catch (error) {
          console.error('Error deleting author:', error);
        }
      }
    },
    editAuthor(author) {
      this.editingAuthor = author;
      this.currentAuthor = { ...author };
    },
    async saveAuthor() {
      try {
        if (this.editingAuthor) {
          await AuthorService.update(this.editingAuthor.id, this.currentAuthor);
        } else {
          await AuthorService.create(this.currentAuthor);
        }
        this.cancelEdit();
        this.loadAuthors();
      } catch (error) {
        console.error('Error saving author:', error);
      }
    },
    cancelEdit() {
      this.editingAuthor = null;
      this.showCreateForm = false;
      this.currentAuthor = { name: '', surname: '' };
    },
    prevPage() {
      if (this.page > 0) {
        this.page--;
        this.loadAuthors();
      }
    },
    nextPage() {
      this.page++;
      this.loadAuthors();
    }
  }
};
</script>

<style scoped>
.authors {
  padding: 20px;
}

.search-box {
  margin-bottom: 20px;
  display: flex;
  gap: 10px;
}

.search-box input {
  padding: 8px;
  flex-grow: 1;
  max-width: 300px;
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

.form-group input {
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