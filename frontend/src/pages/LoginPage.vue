<template>
  <div class="login_page">
    <Header></Header>
    <div class="container login_container">
      <form @submit.prevent="login">
        <div>
          <label for="username">Login:</label>
          <input id="username" class="input" v-model="username" type="text" required />
        </div>
        <div>
          <label for="password">Password:</label>
          <input id="password" class="input" v-model="password" type="password" required />
        </div>
        <button class="button submit_button" type="submit">Submit</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Header from '@/components/HeaderComponent.vue'

export default {
  components: {
    Header
  },
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    async login() {
      try {
        // TODO: need to change the url
        const response = await axios.post('http://localhost:8080/api/login', {
          username: this.username,
          password: this.password,
        });
        if (response.data.success) {
          this.$router.push('/main');
        } else {
          alert('Неверные логин или пароль');
        }
      } catch (error) {
        console.error('Ошибка авторизации:', error);
        alert('Ошибка на сервере');
      }
    },
  },
};
</script>

<style scoped>
  .login_page {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .login_container {
    font-size: 18px;
    background: rgba(255, 255, 255, 0.45);
    margin-top: 40px;
    margin-left: 35%;
    margin-right: 35%;
    width: auto;
    height: auto;
    position: relative;
    padding: 20px;
  }

  .input {
    margin-top: 5px;
    margin-bottom: 20px;
    width: 100%;
    position: relative;
  }
</style>
