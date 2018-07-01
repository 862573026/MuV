import request from '@/utils/request'
import Qs from 'qs'

// 获取tokenKey
export function getTokenKey() {
  return request({
    url: '/account?tokenKey=get',
    method: 'get'
  })
}

export function loginByPsw(params) {
  return request({
    url: '/account/login',
    method: 'post',
    data: Qs.stringify(params),
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export function getUserRoleCode(uid) {
  return request({
    url: '/role/user/code/' + uid,
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

