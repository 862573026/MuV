import request from '@/utils/request'

export function roleList(query) {
  return request({
    url: '/role/list',
    method: 'get',
    params: query
  })
}

export function addRole(data) {
  return request({
    url: '/role/add',
    method: 'post',
    params: data
  })
}

