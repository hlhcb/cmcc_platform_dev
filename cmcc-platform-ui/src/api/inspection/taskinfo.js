import request from '@/utils/request'

// 查询巡检记录列表
export function listTaskinfo(query) {
  return request({
    url: '/inspection/taskinfo/list',
    method: 'get',
    params: query
  })
}

// 查询巡检记录详细
export function getTaskinfo(id) {
  return request({
    url: '/inspection/taskinfo/' + id,
    method: 'get'
  })
}

// 新增巡检记录
export function addTaskinfo(data) {
  return request({
    url: '/inspection/taskinfo',
    method: 'post',
    data: data
  })
}

// 修改巡检记录
export function updateTaskinfo(data) {
  return request({
    url: '/inspection/taskinfo',
    method: 'put',
    data: data
  })
}

// 删除巡检记录
export function delTaskinfo(id) {
  return request({
    url: '/inspection/taskinfo/' + id,
    method: 'delete'
  })
}
