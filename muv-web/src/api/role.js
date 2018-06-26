import request from '@/utils/request'

// 查询角色列表
export function roleList(query) {
  return request({
    url: '/role/list',
    method: 'get',
    params: query
  })
}

// 添加角色
export function addRole(data) {
  return request({
    url: '/role/add',
    method: 'post',
    data: data
  })
}

// 更新角色
export function updateRole(data) {
  return request({
    url: '/role/update',
    method: 'put',
    data: data
  })
}

// 删除角色
export function deleteRole(rid) {
  return request({
    url: '/role/delete/' + rid,
    method: 'delete'
  })
}
