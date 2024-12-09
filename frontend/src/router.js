import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from './pages/LoginPage.vue';
import MainPage from './pages/MainPage.vue';

const routes = [
    { path: '/', component: LoginPage },
    {
        path: '/main',
        component: MainPage,
        beforeEnter: (to, from, next) => {
            const token = localStorage.getItem('authToken');
            if (token) {
                next();
            } else {
                next('/');
            }
        }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
