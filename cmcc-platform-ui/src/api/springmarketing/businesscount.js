import request from '@/utils/request'

// 查询业务办理统计表列表
export function listBusinesscount(query) {
  return request({
    url: '/springmarketing/businesscount/list',
    method: 'get',
    params: query
  })
}

// 查询业务办理统计表详细
export function getBusinesscount(id) {
  return request({
    url: '/springmarketing/businesscount/' + id,
    method: 'get'
  })
}

// 新增业务办理统计表
export function addBusinesscount(data) {
  return request({
    url: '/springmarketing/businesscount',
    method: 'post',
    data: data
  })
}

// 修改业务办理统计表
export function updateBusinesscount(data) {
  return request({
    url: '/springmarketing/businesscount',
    method: 'put',
    data: data
  })
}

// 删除业务办理统计表
export function delBusinesscount(id) {
  return request({
    url: '/springmarketing/businesscount/' + id,
    method: 'delete'
  })
}
