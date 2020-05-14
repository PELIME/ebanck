<template>
  <div>
    <img class="userimg" src="../assets/image/login-portrait.jpg" :style="imgstyle" />
    <section class="user_input_sec" :style="secstyle">
      <div class="username_box">
        <input
          class="username_input"
          type="text"
          name
          placeholder="邮箱/手机号/帐号"
          v-model="userinfo.username"
        />
      </div>
      <div class="password_box">
        <input
          class="password_input"
          type="password"
          name
          placeholder="请输入密码"
          v-model="userinfo.password"
        />
      </div>
      <div class="submit_box" @click="login()">
        <input
          class="submit_input"
          type="submit"
          name
          value="登录"
          :style="loginbutstyle"
        />
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      scrheight: 0,
      secstyle: "",
      imgstyle: "",
      loginbutstyle: "",
      userinfo: [
        {
          username: ""
        },
        {
          password: ""
        }
      ],
      imgsrc: "/assets/image/login-portrait.jpg"
    };
  },
  methods: {
    login() {
        console.log('login');
        this.$toast({
        message: "登录后台未开放",
        position: "top",
        duration: 5000
      });
    },
    regester() {}
  }
};
</script>
<style>
* {
  padding: 0;
  margin: 0;
  overflow: hidden;
}
body {
  overflow-y: hidden;
  overflow-x: hidden;
}
#app {
  width: 100%;
  background: -webkit-linear-gradient(
    left top,
    #740479,
    #224
  ); /* Safari 5.1 - 6.0 */
}
.user_input_sec {
  width: 100%;
  /*top:250px;*/
  height: 150px;
}
.user_input_sec > .username_box {
  position: absolute;
  top: 230px;
  left: 50%;
  width: 70%;
  margin-left: -35%;
  height: 30px;
  border-radius: 5px;
  background-color: #888;
  border: 1px solid #fff;
  -webkit-box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.5);
  opacity: 0.5;
}
.user_input_sec > .username_box > .username_input {
  width: 100%;
  border: 0;
  height: 30px;
  text-align: center;
}

.user_input_sec > .password_box {
  position: absolute;
  top: 270px;
  left: 50%;
  width: 70%;
  margin-left: -35%;
  height: 30px;
  border-radius: 5px;
  background-color: #888;
  border: 1px solid #fff;
  -webkit-box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.5);
  opacity: 0.5;
}
.user_input_sec > .password_box > .password_input {
  width: 100%;
  border: 0;
  height: 30px;
  text-align: center;
}

.user_input_sec > .submit_box {
  position: absolute;
  top: 320px;
  left: 50%;
  width: 70%;
  margin-left: -35%;
  height: 30px;
  border-radius: 5px;
  border: 1px solid #fff;
}
.user_input_sec > .submit_box > .submit_input {
  width: 100%;
  background-color: #b14955;
  border: 0;
  height: 30px;
  text-align: center;
  color: #fff;
}

.user_input_sec > .submit_box > .submit_input:active {
  width: 100%;
  background-color: #112;
  border: 0;
  height: 30px;
  text-align: center;
  color: #fff;
}
.userimg {
  left: 50%;
  width: 120px;
  border-radius: 50%;
  -webkit-box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.5);
  margin: 20px auto;
}
</style>