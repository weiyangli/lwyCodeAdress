<template>
  <div id="app">
    <!-- 我的理解: 该标签就是引入了router中所有的路由该页面中任何地方使用了路由跳转
    就从这个组件中查找，找到对应的路由就跳转到指定页面 -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
