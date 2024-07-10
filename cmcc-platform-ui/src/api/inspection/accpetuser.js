import request from '@/utils/request'

// 查询接收工单人员列表
export function listAccpetuser(query) {
  return request({
    url: '/inspection/accpetuser/list',
    method: 'get',
    params: query
  })
}

// 查询接收工单人员详细
export function getAccpetuser(id) {
  return request({
    url: '/inspection/accpetuser/' + id,
    method: 'get'
  })
}

// 新增接收工单人员
export function addAccpetuser(data) {
  return request({
    url: '/inspection/accpetuser',
    method: 'post',
    data: data
  })
}

// 修改接收工单人员
export function updateAccpetuser(data) {
  return request({
    url: '/inspection/accpetuser',
    method: 'put',
    data: data
  })
}

// 删除接收工单人员
export function delAccpetuser(id) {
  return request({
    url: '/inspection/accpetuser/' + id,
    method: 'delete'
  })
}
