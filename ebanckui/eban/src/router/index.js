import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Auth from '../views/Auth.vue'
import Login from '../views/Login.vue'
import Regiest from '../views/Regiest.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path:'/auth',
    name:'Auth',
    component:Auth,
    children:[
      {
        path:'login',
        name:'login',
        component:Login
      },
      {
        path:'regiest',
        name:'regiest',
        component:Regiest
      }
    ]

  },
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  //mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
