import 'iview/dist/styles/iview.css';
import Vue from 'vue'
import iView from 'iview'
import App    from './index.vue';
import router from './router';
import store  from './store';

import DemoDao from '../../../static/js/dao/DemoDao'

Vue.prototype.$DemoDao = DemoDao;

Vue.use(iView);
Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    next();
});

router.afterEach((to) => {
    iView.LoadingBar.finish();
});

