import request from '@/utils/request'

// 查询接触人数统计列表
export function listContactcount(query) {
  return request({
    url: '/springmarketing/contactcount/list',
    method: 'get',
    params: query
  })
}

// 查询接触人数统计详细
export function getContactcount(id) {
  return request({
    url: '/springmarketing/contactcount/' + id,
    method: 'get'
  })
}

// 新增接触人数统计
export function addContactcount(data) {
  return request({
    url: '/springmarketing/contactcount',
    method: 'post',
    data: data
  })
}

// 修改接触人数统计
export function updateContactcount(data) {
  return request({
    url: '/springmarketing/contactcount',
    method: 'put',
    data: data
  })
}

// 删除接触人数统计
export function delContactcount(id) {
  return request({
    url: '/springmarketing/contactcount/' + id,
    method: 'delete'
  })
}
