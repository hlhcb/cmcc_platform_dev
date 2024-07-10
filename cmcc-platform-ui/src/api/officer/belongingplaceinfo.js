import request from '@/utils/request'

// 查询归属地管理列表
export function listBelongingplaceinfo(query) {
  return request({
    url: '/officer/belongingplaceinfo/list',
    method: 'get',
    params: query
  })
}

export function getBelongPlaceCodeList(query) {
  return request({
    url: '/officer/belongingplaceinfo/getBelongPlaceCodeList',
    method: 'get',
    params: query
  })
}

// 查询归属地管理详细
export function getBelongingplaceinfo(id) {
  return request({
    url: '/officer/belongingplaceinfo/' + id,
    method: 'get'
  })
}

// 新增归属地管理
export function addBelongingplaceinfo(data) {
  return request({
    url: '/officer/belongingplaceinfo',
    method: 'post',
    data: data
  })
}

// 修改归属地管理
export function updateBelongingplaceinfo(data) {
  return request({
    url: '/officer/belongingplaceinfo',
    method: 'put',
    data: data
  })
}

// 删除归属地管理
export function delBelongingplaceinfo(id) {
  return request({
    url: '/officer/belongingplaceinfo/' + id,
    method: 'delete'
  })
}
