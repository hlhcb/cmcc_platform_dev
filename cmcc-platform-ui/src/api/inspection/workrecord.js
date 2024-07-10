import request from '@/utils/request'

// 查询工单记录列表
export function listWorkrecord(query) {
  return request({
    url: '/inspection/workrecord/list',
    method: 'get',
    params: query
  })
}

// 查询工单记录详细
export function getWorkrecord(id) {
  return request({
    url: '/inspection/workrecord/' + id,
    method: 'get'
  })
}

// 新增工单记录
export function addWorkrecord(data) {
  return request({
    url: '/inspection/workrecord',
    method: 'post',
    data: data
  })
}

// 修改工单记录
export function updateWorkrecord(data) {
  return request({
    url: '/inspection/workrecord',
    method: 'put',
    data: data
  })
}

// 删除工单记录
export function delWorkrecord(id) {
  return request({
    url: '/inspection/workrecord/' + id,
    method: 'delete'
  })
}
