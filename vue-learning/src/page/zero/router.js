import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);  

export default new Router({
  routes: [
    {
      path: '/',
      name: 'zero',
      component: () => import('@/page/zero/subpage/zero-list.vue')
    },
    {
      path: '/pick-skin',
      name: 'pick-skin',
      component: () => import('@/page/zero/subpage/pick-skin.vue')
    }
  ]
})
