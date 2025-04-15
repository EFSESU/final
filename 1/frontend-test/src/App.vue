<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <CustomerTest/>
    <DeliverTest/>
  </div>
</template>

<script>
import CustomerTest from './components/CustomerTest.vue'
import DeliverTest from './components/DeliverTest.vue'

export default {
  name: 'App',
  components: {
    CustomerTest,
    DeliverTest
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
