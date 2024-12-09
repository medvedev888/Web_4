import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import axios from 'axios';

// Настройка Axios
axios.interceptors.request.use((config) => {
    const token = localStorage.getItem('authToken');
    if (token) {
        config.headers.Authorization = `${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

const app = createApp(App);
app.use(router);
app.mount('#app');
