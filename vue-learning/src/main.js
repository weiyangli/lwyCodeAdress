// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'iview/dist/styles/iview.css'
import Vue from 'vue'
import iView from 'iview'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false
Vue.use(iView)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
