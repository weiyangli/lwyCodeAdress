// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'iview/dist/styles/iview.css'

import Vue from 'vue'
import iView from 'iview'
import axios from 'axios'
import App from './App'
import router from './router'

import DemoDao from './../static/js/dao/DemoDao'
import Ahri from './../static/js/Ahri'
import jQuery from './../static/lib/jquery.min'

Vue.config.productionTip = false
Vue.use(iView)

Vue.prototype.$DemoDao = DemoDao
Vue.prototype.$Ahri = Ahri
Vue.prototype.$jQuery = jQuery
Vue.prototype.$http=axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
