import request from '@/utils/request'

// 查询返乡人员明细列表
export function getHomeData() {
  return request({
    url: '/springmarketing/returndetail/getHomeData1',
    method: 'get',
    params: {}
  })
}

// 查询区县统计
export function getCountyHomeData() {
  return request({
    url: '/springmarketing/returndetail/getCountyHomeData',
    method: 'get',
    params: {}
  })
}

// 查询返乡人员明细列表
export function listReturndetail(query) {
  return request({
    url: '/springmarketing/returndetail/list',
    method: 'get',
    params: query
  })
}

// 查询返乡人员明细详细
export function getReturndetail(id) {
  return request({
    url: '/springmarketing/returndetail/' + id,
    method: 'get'
  })
}

// 新增返乡人员明细
export function addReturndetail(data) {
  return request({
    url: '/springmarketing/returndetail',
    method: 'post',
    data: data
  })
}

// 修改返乡人员明细
export function updateReturndetail(data) {
  return request({
    url: '/springmarketing/returndetail',
    method: 'put',
    data: data
  })
}

// 删除返乡人员明细
export function delReturndetail(id) {
  return request({
    url: '/springmarketing/returndetail/' + id,
    method: 'delete'
  })
}
