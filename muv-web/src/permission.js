import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
import { getToken } from '@/utils/auth' // getToken from cookie
import Cookie from 'js-cookie'

NProgress.configure({ showSpinner: false })// NProgress Configuration

// permission judge function
function hasPermission(roles, permissionRoles) {
  if (roles.indexOf('admin') >= 0) return true // admin permission passed directly
  if (!permissionRoles) return true
  return roles.some(role => permissionRoles.indexOf(role) >= 0)
}

const whiteList = ['/login', '/authredirect', '/404']// no redirect whitelist

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  if (getToken()) { // determine if there has token
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      const user = JSON.parse(Cookie.get('user-info'))
      var uid = user.uid
      console.log('role:' + store.getters.roles + '-> uid:' + uid)
      if (store.getters.roles.length === 0 && uid.length !== 0) { // 判断当前用户是否已拉取完user_info信息
        store.dispatch('ChangeRoles').then(resp => { // 拉取user_info
          // const data = res.data.data // note: roles must be a array! such as: ['editor','develop']
          // var roles = ['admin']
          // roles = data.role
          // console.log('role::::' + data + ' -> ' + roles)
          const role = resp.data.role
          console.log('resp: ' + resp)
          store.dispatch('GenerateRoutes', { role }).then(() => { // 根据roles权限生成可访问的路由表
          //   router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
            next({ ...to, replace: true }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
          })
        }).catch((err) => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || 'Verification failed, please login again')
            next({ path: '/' })
          })
        })
        // getUserRoleCode(uid).then(res => { // 拉取user_info
        //   const data = res.data.data // note: roles must be a array! such as: ['editor','develop']
        //   var roles = ['admin']
        //   console.log('roles:   ' + data.role)
        // })
        // store.dispatch('GetUserInfo').then(res => { // 拉取user_info
        //   const roles = ['admin', 'user']
        //   // const roles = res.data.roles // note: roles must be a array! such as: ['editor','develop']
        //   console.log('roles:   ' + roles)
        //   store.dispatch('GenerateRoutes', { roles }).then(() => { // 根据roles权限生成可访问的路由表
        //     router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
        //     next({ ...to, replace: true }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
        //   })
        // }).catch((err) => {
        //   store.dispatch('FedLogOut').then(() => {
        //     Message.error(err || 'Verification failed, please login again')
        //     next({ path: '/' })
        //   })
        // })
      } else {
        // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
        if (hasPermission(store.getters.roles, to.meta.roles)) {
          next()//
        } else {
          next({ path: '/401', replace: true, query: { noGoBack: true }})
        }
        // 可删 ↑
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next('/login') // 否则全部重定向到登录页
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
