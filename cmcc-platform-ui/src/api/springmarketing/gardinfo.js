import request from '@/utils/request'

// 查询网格信息列表
export function listGardinfo(query) {
  return request({
    url: '/springmarketing/gardinfo/list',
    method: 'get',
    params: query
  })
}

export function getAreaCodeList(query) {
  return request({
    url: '/springmarketing/gardinfo/getAreaCodeList',
    method: 'get',
    params: query
  })
}

// 查询网格信息详细
export function getGardinfo(id) {
  return request({
    url: '/springmarketing/gardinfo/' + id,
    method: 'get'
  })
}

// 新增网格信息
export function addGardinfo(data) {
  return request({
    url: '/springmarketing/gardinfo',
    method: 'post',
    data: data
  })
}

// 修改网格信息
export function updateGardinfo(data) {
  return request({
    url: '/springmarketing/gardinfo',
    method: 'put',
    data: data
  })
}

// 删除网格信息
export function delGardinfo(id) {
  return request({
    url: '/springmarketing/gardinfo/' + id,
    method: 'delete'
  })
}
