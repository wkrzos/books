import { createRouter, createWebHistory } from 'vue-router'
import BookList from '@/components/BookList.vue'
import AuthorList from '@/components/AuthorList.vue'

const routes = [
  {
    path: '/authors',
    name: 'authors',
    component: AuthorList
  },
  {
    path: '/books',
    name: 'books',
    component: BookList
  },
  {
    path: '/',
    redirect: '/authors'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router