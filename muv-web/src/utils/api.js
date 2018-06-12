import service from '@/utils/request'

export const getRequest = (url) => {
  return service({
    method: 'get',
    url: `${url}`
  })
}
