<template>
  <div class="home-page">
    <section class="hero">
      <h1>Welcome to Book Management System</h1>
      <p>A comprehensive solution for libraries and bookstores</p>
    </section>

    <section class="stats">
      <div class="stat-card">
        <h3>Books</h3>
        <div class="stat-value">{{ stats.books }}</div>
        <router-link to="/books" class="stat-link">Manage Books</router-link>
      </div>
      
      <div class="stat-card">
        <h3>Authors</h3>
        <div class="stat-value">{{ stats.authors }}</div>
        <router-link to="/authors" class="stat-link">Manage Authors</router-link>
      </div>
      
      <div class="stat-card">
        <h3>Users</h3>
        <div class="stat-value">{{ stats.users }}</div>
        <router-link to="/users" class="stat-link">Manage Users</router-link>
      </div>
      
      <div class="stat-card">
        <h3>Active Rentals</h3>
        <div class="stat-value">{{ stats.rentals }}</div>
        <router-link to="/rentals" class="stat-link">Manage Rentals</router-link>
      </div>
    </section>

    <section class="features">
      <h2>Key Features</h2>
      <div class="feature-cards">
        <div class="feature-card">
          <h3>Book Management</h3>
          <p>Add, edit, and delete books from your collection</p>
        </div>
        
        <div class="feature-card">
          <h3>Author Tracking</h3>
          <p>Maintain an organized database of authors</p>
        </div>
        
        <div class="feature-card">
          <h3>User Administration</h3>
          <p>Manage library members and their accounts</p>
        </div>
        
        <div class="feature-card">
          <h3>Rental System</h3>
          <p>Keep track of book rentals and returns</p>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      stats: {
        books: 0,
        authors: 0,
        users: 0,
        rentals: 0
      }
    }
  },
  created() {
    // Simulate loading statistics
    // In a real application, this would fetch from APIs
    setTimeout(() => {
      this.stats = {
        books: 187,
        authors: 42,
        users: 63,
        rentals: 15
      }
    }, 500)
  }
}
</script>

<style scoped>
.home-page {
  max-width: 1200px;
  margin: 0 auto;
}

.hero {
  text-align: center;
  padding: 40px 20px;
  background-color: #e3f2fd;
  border-radius: 8px;
  margin-bottom: 30px;
}

.hero h1 {
  font-size: 2.5rem;
  color: #2c3e50;
  margin-bottom: 10px;
}

.hero p {
  font-size: 1.2rem;
  color: #546e7a;
}

.stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
}

.stat-card {
  background-color: white;
  border-radius: 8px;
  padding: 20px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-card h3 {
  color: #546e7a;
  margin-bottom: 10px;
}

.stat-value {
  font-size: 2.5rem;
  font-weight: bold;
  color: #2196f3;
  margin-bottom: 15px;
}

.stat-link {
  display: inline-block;
  text-decoration: none;
  color: white;
  background-color: #2196f3;
  padding: 8px 16px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.stat-link:hover {
  background-color: #1976d2;
}

.features {
  margin-top: 50px;
}

.features h2 {
  text-align: center;
  margin-bottom: 30px;
  color: #2c3e50;
}

.feature-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.feature-card {
  background-color: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.feature-card:hover {
  transform: translateY(-5px);
}

.feature-card h3 {
  color: #2c3e50;
  margin-bottom: 10px;
}

.feature-card p {
  color: #546e7a;
}
</style>