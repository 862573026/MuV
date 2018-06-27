import request from '@/utils/request'

// 查询角色列表
export function apkList(query) {
  return request({
    url: '/manager/apk/list',
    method: 'get',
    params: query
  })
}

// 检查Apk的Md5
export function checkApkMd5(apk) {
  return request({
    url: '/upload/apk/checkFileMd5',
    method: 'post',
    data: apk,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

// 上传Apk
export function uploadApk(apk) {
  return request({
    url: '/manager/apk/upload',
    method: 'post',
    data: apk,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

// 添加Apk
export function addApk(data) {
  return request({
    url: '/manager/apk/add',
    method: 'post',
    data: data
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
