import request from '@/utils/request'

// 查询巡检记录新列表
export function listTaskinfonew(query) {
  return request({
    url: '/inspection/taskinfonew/list',
    method: 'get',
    params: query
  })
}

// 查询巡检记录新详细
export function getTaskinfonew(id) {
  return request({
    url: '/inspection/taskinfonew/' + id,
    method: 'get'
  })
}

// 新增巡检记录新
export function addTaskinfonew(data) {
  return request({
    url: '/inspection/taskinfonew',
    method: 'post',
    data: data
  })
}

// 修改巡检记录新
export function updateTaskinfonew(data) {
  return request({
    url: '/inspection/taskinfonew',
    method: 'put',
    data: data
  })
}

// 删除巡检记录新
export function delTaskinfonew(id) {
  return request({
    url: '/inspection/taskinfonew/' + id,
    method: 'delete'
  })
}
