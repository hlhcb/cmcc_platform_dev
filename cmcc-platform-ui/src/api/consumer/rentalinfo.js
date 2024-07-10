import request from '@/utils/request'

// 查询出租房信息列表
export function listRentalinfo(query) {
  return request({
    url: '/consumer/rentalinfo/list',
    method: 'get',
    params: query
  })
}

// 查询出租房信息详细
export function getRentalinfo(id) {
  return request({
    url: '/consumer/rentalinfo/' + id,
    method: 'get'
  })
}

// 新增出租房信息
export function addRentalinfo(data) {
  return request({
    url: '/consumer/rentalinfo',
    method: 'post',
    data: data
  })
}

// 修改出租房信息
export function updateRentalinfo(data) {
  return request({
    url: '/consumer/rentalinfo',
    method: 'put',
    data: data
  })
}

// 删除出租房信息
export function delRentalinfo(id) {
  return request({
    url: '/consumer/rentalinfo/' + id,
    method: 'delete'
  })
}
