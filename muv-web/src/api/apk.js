import request from '@/utils/request'

// 查询角色列表
export function apkList(query) {
  return request({
    url: '/manager/apk/list',
    method: 'get',
    params: query
  })
}

// 添加Apk
export function addApk(apk) {
  return request({
    url: '/manager/apk/add',
    method: 'post',
    data: apk
  })
}

// 更新角色
export function updateApk(data) {
  return request({
    url: '/manager/apk/update',
    method: 'put',
    data: data
  })
}

// 删除角色
export function deleteApk(rid) {
  return request({
    url: '/manager/apk/delete/' + rid,
    method: 'delete'
  })
}
