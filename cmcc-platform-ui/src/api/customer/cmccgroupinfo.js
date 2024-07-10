import request from '@/utils/request'

// 查询楼宇信息维护列表
export function listCmccgroupinfo(query) {
  return request({
    url: '/customer/cmccgroupinfo/list',
    method: 'get',
    params: query
  })
}

// 查询楼宇信息维护列表
export function getAreaList() {
  return request({
    url: '/customer/cmccgroupinfo/getAreaList',
    method: 'get'
  })
}

// 查询楼宇信息维护列表
export function getCmccGroupList(query) {

  let urls = '/customer/cmccgroupinfo/getCmccGroupList?queryDeptId='+query
  if (query === undefined || query == null ) {
    urls = '/customer/cmccgroupinfo/getCmccGroupList'
  }
  return request({
    url: urls,
    method: 'get'
  })
}

// 查询楼宇信息维护详细
export function getCmccgroupinfo(id) {
  return request({
    url: '/customer/cmccgroupinfo/' + id,
    method: 'get'
  })
}

// 新增楼宇信息维护
export function addCmccgroupinfo(data) {
  return request({
    url: '/customer/cmccgroupinfo',
    method: 'post',
    data: data
  })
}

// 修改楼宇信息维护
export function updateCmccgroupinfo(data) {
  return request({
    url: '/customer/cmccgroupinfo',
    method: 'put',
    data: data
  })
}

// 删除楼宇信息维护
export function delCmccgroupinfo(id) {
  return request({
    url: '/customer/cmccgroupinfo/' + id,
    method: 'delete'
  })
}
