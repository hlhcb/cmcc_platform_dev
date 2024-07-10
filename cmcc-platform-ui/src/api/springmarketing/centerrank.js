import request from '@/utils/request'

// 查询网格龙虎榜列表
export function listCenterrank(query) {
  return request({
    url: '/springmarketing/centerrank/list',
    method: 'get',
    params: query
  })
}

// 查询网格龙虎榜详细
export function getCenterrank(id) {
  return request({
    url: '/springmarketing/centerrank/' + id,
    method: 'get'
  })
}

// 新增网格龙虎榜
export function addCenterrank(data) {
  return request({
    url: '/springmarketing/centerrank',
    method: 'post',
    data: data
  })
}

// 修改网格龙虎榜
export function updateCenterrank(data) {
  return request({
    url: '/springmarketing/centerrank',
    method: 'put',
    data: data
  })
}

// 删除网格龙虎榜
export function delCenterrank(id) {
  return request({
    url: '/springmarketing/centerrank/' + id,
    method: 'delete'
  })
}
