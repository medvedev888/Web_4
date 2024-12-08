<template>
  <div class="auth_page">
    <Header></Header>
    <div class="container auth_container">
      <form @submit.prevent="handleSubmit">
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
      username: '',
      password: '',
    };
  },
  methods: {
    toggleMode() {
      this.isRegister = !this.isRegister;
      this.username = '';
      this.password = '';
    },
    async handleSubmit() {
      if (this.isRegister) {
        // Registration request
        try {
          await axios.post('http://localhost:8080/api/register', {
            username: this.username,
            password: this.password,
          });
          alert('Registration successful!');
          this.toggleMode();
        } catch (error) {
          console.error('Registration error:', error);
          alert('Error during registration. Please try again.');
        }
      } else {
        // Authorization request
        try {
          const response = await axios.post('http://localhost:8080/api/login', {
            username: this.username,
            password: this.password,
          });
          if (response.data.success) {
            this.$router.push('/main'); // Redirect to the main page
          } else {
            alert('Invalid username or password.');
          }
        } catch (error) {
          console.error('Authorization error:', error);
          alert('Error during authorization. Please try again.');
        }
      }
    },
  },
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
