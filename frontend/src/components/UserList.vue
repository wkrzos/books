<template>
  <div class="users">
    <h2>Users</h2>
    
    <div class="search-box">
      <input v-model="searchUsername" placeholder="Search by username" />
      <button @click="searchUser">Search</button>
      <button @click="resetSearch">Reset</button>
    </div>

    <button @click="showCreateForm = true" class="add-button">Add New User</button>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Email</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>
            <button @click="editUser(user)">Edit</button>
            <button @click="deleteUser(user.id)" class="delete-btn">Delete</button>
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
    <div v-if="showCreateForm || editingUser" class="modal">
      <div class="modal-content">
        <h3>{{ editingUser ? 'Edit User' : 'Create User' }}</h3>
        <form @submit.prevent="saveUser">
          <div class="form-group">
            <label for="username">Username:</label>
            <input v-model="currentUser.username" id="username" required />
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input v-model="currentUser.email" id="email" type="email" required />
          </div>
          <div class="form-group" v-if="!editingUser">
            <label for="password">Password:</label>
            <input v-model="currentUser.password" id="password" type="password" :required="!editingUser" />
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
import { UserService } from '@/services/UserService';

export default {
  name: 'UserList',
  data() {
    return {
      users: [],
      page: 0,
      size: 10,
      showCreateForm: false,
      editingUser: null,
      currentUser: {
        username: '',
        email: '',
        password: ''
      },
      searchUsername: ''
    };
  },
  created() {
    this.loadUsers();
  },
  methods: {
    async loadUsers() {
      try {
        const response = await UserService.getAll(this.page, this.size);
        this.users = response.data;
      } catch (error) {
        console.error('Error loading users:', error);
        alert('Error loading users');
      }
    },
    async searchUser() {
      if (this.searchUsername) {
        try {
          const response = await UserService.getByUsername(this.searchUsername);
          this.users = [response.data];
        } catch (error) {
          console.error('Error searching user:', error);
          alert('User not found');
        }
      } else {
        this.loadUsers();
      }
    },
    resetSearch() {
      this.searchUsername = '';
      this.loadUsers();
    },
    async deleteUser(id) {
      if (confirm('Are you sure you want to delete this user?')) {
        try {
          await UserService.delete(id);
          this.loadUsers();
        } catch (error) {
          console.error('Error deleting user:', error);
          alert('Error deleting user');
        }
      }
    },
    editUser(user) {
      this.editingUser = user;
      this.currentUser = { ...user, password: '' }; // Don't include password when editing
      this.showCreateForm = true;
    },
    async saveUser() {
      try {
        if (this.editingUser) {
          await UserService.update(this.editingUser.id, this.currentUser);
        } else {
          await UserService.create(this.currentUser);
        }
        this.cancelEdit();
        this.loadUsers();
      } catch (error) {
        console.error('Error saving user:', error);
        alert('Error saving user: ' + (error.response?.data || error.message));
      }
    },
    cancelEdit() {
      this.editingUser = null;
      this.showCreateForm = false;
      this.currentUser = {
        username: '',
        email: '',
        password: ''
      };
    },
    prevPage() {
      if (this.page > 0) {
        this.page--;
        this.loadUsers();
      }
    },
    nextPage() {
      this.page++;
      this.loadUsers();
    }
  }
};
</script>

<style scoped>
.users {
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