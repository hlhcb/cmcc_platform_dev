import request from '@/utils/request'

// 查询企业信息列表
export function listCmccenterpriseinfo(query) {
  return request({
    url: '/customer/cmccenterpriseinfo/list',
    method: 'get',
    params: query
  })
}

// 查询企业信息详细
export function getCmccenterpriseinfo(id) {
  return request({
    url: '/customer/cmccenterpriseinfo/' + id,
    method: 'get'
  })
}

// 新增企业信息
export function addCmccenterpriseinfo(data) {
  return request({
    url: '/customer/cmccenterpriseinfo',
    method: 'post',
    data: data
  })
}

// 修改企业信息
export function updateCmccenterpriseinfo(data) {
  return request({
    url: '/customer/cmccenterpriseinfo',
    method: 'put',
    data: data
  })
}

// 删除企业信息
export function delCmccenterpriseinfo(id) {
  return request({
    url: '/customer/cmccenterpriseinfo/' + id,
    method: 'delete'
  })
}
