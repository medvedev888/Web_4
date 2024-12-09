<template>
  <div class="auth_page">
    <Header></Header>
    <div class="container auth_container">
      <form @submit.prevent="handleSubmit">
        <div>
          <label for="login">Login:</label>
          <input id="login" class="input" v-model="login" type="text" required />
        </div>
        <div>
          <label for="password">Password:</label>
          <input id="password" class="input" v-model="password" type="password" required />
        </div>
        <button class="button submit_button" type="submit">Submit</button>
      </form>
      <p style="margin-top: 30px; display: flex; flex-direction: row">
        {{ isRegister ? "Already have an account?" : "Don't have an account?" }}
        <button class="button submit_button" @click="toggleMode">{{ isRegister ? "Authorization" : "Registration" }}</button>
      </p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Header from '@/components/HeaderComponent.vue';

export default {
  components: {
    Header,
  },
  data() {
    return {
      isRegister: false, // Mode: registration (true) or authorization (false)
      login: '',
      password: ''
    };
  },
  methods: {
    toggleMode() {
      this.isRegister = !this.isRegister;
      this.login = '';
      this.password = '';
    },
    async handleSubmit() {
      try {
        const url = this.isRegister ? 'http://localhost:8080/register' : 'http://localhost:8080/login';
        const response = await axios.post(url, {
          login: this.login,
          password: this.password,
        });

        if (response && response.data && response.data.success) {
          if (this.isRegister) {
            alert('Registration successful!');
            this.toggleMode(); // Переключение на авторизацию
          } else {
            const token = response.data.token;
            localStorage.setItem('authToken', token);
            this.$router.push('/main'); // Переход на главную страницу
          }
        } else {
          alert(response.data.message);
        }
      } catch (error) {
        if (error) {
          alert(error.response.data.message);
          console.log()
        } else {
          console.error('Request error:', error);
          alert('An unexpected error occurred. Please try again later.');
        }
      }
    }
  }
};
</script>

<style scoped>
  .auth_page {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .auth_container {
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

  .submit_button {
    width: 100%;
  }

</style>
