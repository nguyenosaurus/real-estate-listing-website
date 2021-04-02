import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import VueCompositionAPI from '@vue/composition-api'
import * as VueGoogleMaps from "vue2-google-maps";

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyBOVZw1-d23tUfXj2gqTz622E-utHwT5Nk",
    libraries: "places"
  }
});

Vue.use(VueCompositionAPI)

Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App)
}).$mount("#app");
