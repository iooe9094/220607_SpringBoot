import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/customers/:id",
    name: "edit-customers",
    component: () => import("@/components/EditCustomer"),
  },
  {
    path: "/customers",
    // alias: "/customers",
    name: "customers",
    component: () => import("@/components/CustomerList"),
  },
  {
    path: "/add",
    name: "add-customer",
    component: () => import("@/components/AddCustomer"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
