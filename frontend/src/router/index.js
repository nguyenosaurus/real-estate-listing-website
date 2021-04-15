import Vue from 'vue'
import Router from 'vue-router'

// Containers
const TheContainer = () => import('@/containers/TheContainer')

// Views
const Dashboard = () => import('@/views/Dashboard')
const Posts = () => import('@/views/Posts')
const Post = () => import('@/views/Post')

Vue.use(Router)

export default new Router({
  mode: 'hash', // https://router.vuejs.org/api/#mode
  linkActiveClass: 'active',
  scrollBehavior: () => ({ y: 0 }),
  routes: configRoutes()
})

function configRoutes () {
  return [
    {
      path: '/',
      redirect: '/dashboard',
      name: 'Home',
      component: TheContainer,
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          component: Dashboard
        },
        // {
        //   path: 'posts',
        //   name: 'Posts',
        //   component: Posts
        // },
        {
          path: 'posts',
          meta: {
            label: 'Posts'
          },
          component: {
            render(c) {
              return c('router-view')
            }
          },
          children: [
            {
              path: '',
              name: 'Posts',
              component: Posts
            },
            {
              path: ':id',
              meta: {
                label: 'Post Details'
              },
              name: 'Post',
              component: Post
            }
          ]
        }
      ]
    }
  ]
}

