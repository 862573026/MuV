import { getTokenKey, loginByPsw, logout, getUserInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import CryptoJS from 'crypto-js/crypto-js'

const user = {
  state: {
    user: '',
    status: '',
    code: '',
    token: getToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_SETTING: (state, setting) => {
      state.setting = setting
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    // 获取tokenKey
    LoginByPsw({ commit }) {
      return new Promise((resolve, reject) => {
        getTokenKey().then(response => {
          // console.log(response)
          // resolve(response)
          var message = response.data
          var success = message.success
          if (success === true) {
            resolve(response.data)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 用户名登录
    LoginCommit({ commit }, loginInfo) {
      const username = loginInfo.username.trim()
      const message = loginInfo.message
      var tokenKey = message.data.tokenKey
      var password = loginInfo.password.trim()
      console.log('加密:' + tokenKey + ' ' + password)
      tokenKey = CryptoJS.enc.Utf8.parse(tokenKey)
      console.log('加密:' + tokenKey + ' ' + password)
      // AES CBC加密模式
      password = CryptoJS.AES.encrypt(password, tokenKey, { iv: tokenKey, mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 }).toString()
      console.log('加密:' + tokenKey + ' ' + password)
      var params = {
        appId: username,
        password: password,
        timestamp: message.timestamp,
        userKey: message.data.userKey,
        methodName: 'login'
      }
      console.log('Param:' + JSON.stringify(params))
      return new Promise((resolve, reject) => {
        loginByPsw(params).then(response => {
          const data = response.data
          console.log(data)
          // commit('SET_TOKEN', data.token)
          // setToken(response.data.token)
          commit('SET_TOKEN', 'admin')
          setToken('admin')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token).then(response => {
          if (!response.data) { // 由于mockjs 不支持自定义状态码只能这样hack
            reject('error')
          }
          const data = response.data

          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', data.roles)
          } else {
            reject('getInfo: roles must be a non-null array !')
          }

          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 第三方验证登录
    // LoginByThirdparty({ commit, state }, code) {
    //   return new Promise((resolve, reject) => {
    //     commit('SET_CODE', code)
    //     loginByThirdparty(state.status, state.email, state.code).then(response => {
    //       commit('SET_TOKEN', response.data.token)
    //       setToken(response.data.token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

    // 动态修改权限
    ChangeRoles({ commit }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          resolve()
        })
      })
    }
  }
}

export default user
