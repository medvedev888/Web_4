<template>
  <div class="login-page">
    <h1>Стартовая страница</h1>
    <form @submit.prevent="login">
      <div>
        <label for="username">Логин:</label>
        <input id="username" v-model="username" type="text" required />
      </div>
      <div>
        <label for="password">Пароль:</label>
        <input id="password" v-model="password" type="password" required />
      </div>
      <button type="submit">Войти</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    async login() {
      try {
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
.login-page {
  text-align: center;
}
</style>
