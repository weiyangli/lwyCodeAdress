import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'sample',
      component: () => import('@/page/sample.vue')
    },
    {
      path: '/menu',
      name: 'menu',
      component: () => import('@/page/menu.vue')
    }
  ]
})
