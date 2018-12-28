// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'iview/dist/styles/iview.css'

import myUtils  from '@/../static/lib/myUtils';

import Vue from 'vue'
import iView from 'iview'
import axios from 'axios'
import App from './App'
import router from './router'

Vue.config.productionTip = false
Vue.use(iView)

Vue.prototype.$http=axios
Vue.prototype.myUtils = myUtils;

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  next();
});

router.afterEach((to) => {
  iView.LoadingBar.finish();
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  render: h => h(App),
})