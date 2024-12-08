import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from './components/LoginPage.vue';
import MainPage from './components/MainPage.vue';

const routes = [
    { path: '/', component: LoginPage },  // Стартовая страница
    { path: '/main', component: MainPage } // Основная страница
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
