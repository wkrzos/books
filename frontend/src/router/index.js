import { createRouter, createWebHistory } from 'vue-router'
import BookList from '@/components/BookList.vue'
import AuthorList from '@/components/AuthorList.vue'
import RentalList from '@/components/RentalList.vue'
import UserList from '@/components/UserList.vue'
import HomePage from '@/views/HomePage.vue'
import MainLayout from '@/layouts/MainLayout.vue'

const routes = [
  {
    path: '/',
    component: MainLayout,
    children: [
      {
        path: '',
        name: 'home',
        component: HomePage
      },
      {
        path: 'authors',
        name: 'authors',
        component: AuthorList
      },
      {
        path: 'books',
        name: 'books',
        component: BookList
      },
      {
        path: 'rentals',
        name: 'rentals',
        component: RentalList
      },
      {
        path: 'users',
        name: 'users',
        component: UserList
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router