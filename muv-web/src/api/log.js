import request from '@/utils/request'

// 查询角色列表
export function accountLogList(query) {
  return request({
    url: 'log/account',
    method: 'get',
    params: query
  })
}
