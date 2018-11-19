import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);  

export default new Router({
  routes: [
    {
      path: '/',
      name: 'prize-list',
      component: () => import('@/page/prize/subpage/prize-list.vue')
    },
  ]
})
