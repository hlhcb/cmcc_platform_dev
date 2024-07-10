import request from '@/utils/request'

// 查询值班配置列表
export function listSchedulinginfo(query) {
  return request({
    url: '/inspection/schedulinginfo/list',
    method: 'get',
    params: query
  })
}

// 查询值班配置详细
export function getSchedulinginfo(id) {
  return request({
    url: '/inspection/schedulinginfo/' + id,
    method: 'get'
  })
}

// 新增值班配置
export function addSchedulinginfo(data) {
  return request({
    url: '/inspection/schedulinginfo',
    method: 'post',
    data: data
  })
}

// 修改值班配置
export function updateSchedulinginfo(data) {
  return request({
    url: '/inspection/schedulinginfo',
    method: 'put',
    data: data
  })
}

// 删除值班配置
export function delSchedulinginfo(id) {
  return request({
    url: '/inspection/schedulinginfo/' + id,
    method: 'delete'
  })
}
