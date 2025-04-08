<template>
  <div id="app">
    <AuthorList />
  </div>
</template>

<script>
import AuthorList from './components/AuthorList.vue'

export default {
  name: 'App',
  components: {
    AuthorList
  }
}
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin: 0;
  padding: 20px;
}
</style>
