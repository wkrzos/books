import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

// Configure global axios defaults for API calls
// This is where you can set the base URL for all API requests
const apiUrl = process.env.VUE_APP_API_URL || 'http://localhost:8090'
axios.defaults.baseURL = apiUrl

createApp(App)
  .use(router)
  .mount('#app')
