<template>
  <div class="authors">
    <h2>Authors</h2>
    <div class="search-box">
      <input v-model="searchName" placeholder="Name" />
      <input v-model="searchSurname" placeholder="Surname" />
      <button @click="searchAuthor">Search</button>
    </div>

    <button @click="showCreateForm = true">Add New Author</button>

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
            <button @click="deleteAuthor(author.id)">Delete</button>
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
        <input v-model="currentAuthor.name" placeholder="Name" />
        <input v-model="currentAuthor.surname" placeholder="Surname" />
        <button @click="saveAuthor">Save</button>
        <button @click="cancelEdit">Cancel</button>
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

.search-box {
  margin-bottom: 20px;
}

.search-box input {
  margin-right: 10px;
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
}

.pagination {
  margin-top: 20px;
}

button {
  margin: 0 5px;
  padding: 5px 10px;
}
</style>