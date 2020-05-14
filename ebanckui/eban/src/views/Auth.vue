<template>
  <div id="auth">
    <div id="nav">
      <router-link to="/auth/login">登录</router-link> |
      <router-link to="/auth/regiest">注册</router-link>
    </div>
    <router-view/>
  </div>
</template>
<script>
export default {
  name: 'Auth'
}
</script>

<style>
#auth {
  padding: 30px;
  height: 100vh;
  margin: 0 auto;
  background-image: url('../assets/image/bg1.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  overflow: auto;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>