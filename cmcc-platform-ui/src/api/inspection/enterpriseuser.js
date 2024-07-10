import request from '@/utils/request'

// 查询联防员信息列表
export function listEnterpriseuser(query) {
  return request({
    url: '/inspection/enterpriseuser/list',
    method: 'get',
    params: query
  })
}

// 查询联防员信息详细
export function getEnterpriseuser(id) {
  return request({
    url: '/inspection/enterpriseuser/' + id,
    method: 'get'
  })
}

// 新增联防员信息
export function addEnterpriseuser(data) {
  return request({
    url: '/inspection/enterpriseuser',
    method: 'post',
    data: data
  })
}

// 修改联防员信息
export function updateEnterpriseuser(data) {
  return request({
    url: '/inspection/enterpriseuser',
    method: 'put',
    data: data
  })
}

// 删除联防员信息
export function delEnterpriseuser(id) {
  return request({
    url: '/inspection/enterpriseuser/' + id,
    method: 'delete'
  })
}
