import Vue from 'vue';
import Router from 'vue-router';
import Main from './main';

Vue.use(Router);

export default new Router({
    routes: [{
        path: '/',
        component: Main,
        redirect: '/editor1',
    },
    {
        path: '/editor1',
        component: () => import('./subpage/editor1.vue')
    }, {
        path: '/editor2',
        component: () => import('./subpage/editor2.vue')
    }, {
        path: '/editor3',
        component: () => import('./subpage/editor3.vue')
    }, {
        path: '/paper-edit/:paperId',
        component: () => import('./subpage/paper-edit.vue')
    }, {
        path: '/paper-sheet-edit/:paperId',
        component: () => import('./subpage/paper-sheet-edit.vue')
    }, {
        path: '/gallery',
        component: () => import('./subpage/gallery.vue')
    }, {
        path: '/gallery2',
        component: () => import('./subpage/gallery2.vue')
    }, {
        path: '/hello',
        component: () => import('./subpage/hello.vue')
    }, {
        path: '/anchor-group',
        component: () => import('./subpage/anchor-group.vue')
    }, {
        path: '/pdf',
        component: () => import('./subpage/pdf.vue')
    }, {
        path: '/pdf2',
        component: () => import('./subpage/pdf2.vue')
    }, {
        path: '/pdf3',
        component: () => import('./subpage/pdf3.vue')
    }, {
        path: '/video',
        component: () => import('./subpage/video.vue')
    }, {
        path: '/upload',
        component: () => import('./subpage/upload.vue')
    }],
});
