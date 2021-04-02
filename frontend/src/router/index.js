import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Province.vue"),
  },
  {
    path: "/:province",
    name: "Province",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Province.vue"),
  },
  {
    path: "/:province/:district",
    name: "District",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Province.vue"),
  },
  {
    path: "/:province/:district/:ward",
    name: "Ward",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Province.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
