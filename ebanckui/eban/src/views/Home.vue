<template>
  <div class="home">
    <mt-button type="default" @click.native="myClick('/auth/login')">登录页面</mt-button>
  </div>
</template>

<script>
export default {
  name: 'Home',
  methods:{
    myClick(url){
      this.$router.push(url);
    }
  }
}
</script>
