import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from './pages/LoginPage.vue';
import MainPage from './pages/MainPage.vue';

const routes = [
    { path: '/', component: LoginPage },  // Стартовая страница
    { path: '/main', component: MainPage } // Основная страница
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
