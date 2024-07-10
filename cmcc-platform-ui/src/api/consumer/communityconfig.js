import request from '@/utils/request'

// 查询归属地管理列表
export function listCommunityconfig(query) {
  return request({
    url: '/consumer/communityconfig/list',
    method: 'get',
    params: query
  })
}

// 查询归属地管理详细
export function getCommunityconfig(id) {
  return request({
    url: '/consumer/communityconfig/' + id,
    method: 'get'
  })
}

// 新增归属地管理
export function addCommunityconfig(data) {
  return request({
    url: '/consumer/communityconfig',
    method: 'post',
    data: data
  })
}

// 修改归属地管理
export function updateCommunityconfig(data) {
  return request({
    url: '/consumer/communityconfig',
    method: 'put',
    data: data
  })
}

// 删除归属地管理
export function delCommunityconfig(id) {
  return request({
    url: '/consumer/communityconfig/' + id,
    method: 'delete'
  })
}
