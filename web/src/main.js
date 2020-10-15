import Vue from 'vue';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Vuelidate from 'vuelidate';
import App from "./App";
import router from "./router";
import {store} from './vuex';

Vue.use(Vuelidate);

Vue.config.productionTip = false;

new Vue({
    render: h => h(App),
    router,
    store
}
).$mount('#app')

