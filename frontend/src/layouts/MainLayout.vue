<template>
  <div class="main-layout">
    <header>
      <div class="logo">
        <img src="@/assets/logo.svg" alt="Book Management System" />
        <h1>Book Management System</h1>
      </div>
      <nav>
        <router-link to="/">Home</router-link>
        <router-link to="/authors">Authors</router-link>
        <router-link to="/books">Books</router-link>
        <router-link to="/users">Users</router-link>
        <router-link to="/rentals">Rentals</router-link>
      </nav>
      <div class="user-info">
        <span>Welcome, User</span>
      </div>
    </header>
    
    <main>
      <router-view></router-view>
    </main>
    
    <footer>
      <p>&copy; 2023 Book Management System</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'MainLayout'
}
</script>

<style scoped>
.main-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

header {
  background-color: #2c3e50;
  color: white;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo {
  display: flex;
  align-items: center;
}

.logo img {
  height: 40px;
  margin-right: 10px;
}

.logo h1 {
  font-size: 1.5rem;
  margin: 0;
}

nav {
  display: flex;
  gap: 20px;
}

nav a {
  color: white;
  text-decoration: none;
  padding: 20px 10px;
  transition: background-color 0.3s;
}

nav a:hover {
  background-color: #34495e;
}

nav a.router-link-active {
  border-bottom: 3px solid #42b983;
}

.user-info {
  font-size: 0.9rem;
}

main {
  flex: 1;
  padding: 20px;
  background-color: #f5f7fa;
}

footer {
  background-color: #2c3e50;
  color: white;
  text-align: center;
  padding: 15px 0;
  font-size: 0.9rem;
}
</style>