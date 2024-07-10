import request from '@/utils/request'

// 查询联防配置列表
export function listEnterpriseconfig(query) {
  return request({
    url: '/inspection/enterpriseconfig/list',
    method: 'get',
    params: query
  })
}

// 查询联防配置详细
export function getEnterpriseconfig(id) {
  return request({
    url: '/inspection/enterpriseconfig/' + id,
    method: 'get'
  })
}

// 新增联防配置
export function addEnterpriseconfig(data) {
  return request({
    url: '/inspection/enterpriseconfig',
    method: 'post',
    data: data
  })
}

// 修改联防配置
export function updateEnterpriseconfig(data) {
  return request({
    url: '/inspection/enterpriseconfig',
    method: 'put',
    data: data
  })
}

// 删除联防配置
export function delEnterpriseconfig(id) {
  return request({
    url: '/inspection/enterpriseconfig/' + id,
    method: 'delete'
  })
}
