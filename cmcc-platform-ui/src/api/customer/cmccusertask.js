import request from '@/utils/request'

// 查询用户任务列表
export function listCmccusertask(query) {
  return request({
    url: '/customer/cmccusertask/list',
    method: 'get',
    params: query
  })
}

// 查询用户任务详细
export function getCmccusertask(id) {
  return request({
    url: '/customer/cmccusertask/' + id,
    method: 'get'
  })
}

// 新增用户任务
export function addCmccusertask(data) {
  return request({
    url: '/customer/cmccusertask',
    method: 'post',
    data: data
  })
}

// 修改用户任务
export function updateCmccusertask(data) {
  return request({
    url: '/customer/cmccusertask',
    method: 'put',
    data: data
  })
}

// 删除用户任务
export function delCmccusertask(id) {
  return request({
    url: '/customer/cmccusertask/' + id,
    method: 'delete'
  })
}
