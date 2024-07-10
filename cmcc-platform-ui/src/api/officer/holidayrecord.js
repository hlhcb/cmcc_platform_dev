import request from '@/utils/request'

// 查询休假记录列表
export function listHolidayrecord(query) {
  return request({
    url: '/officer/holidayrecord/list',
    method: 'get',
    params: query
  })
}

// 查询用户列表
export function listUserInfo(query) {
  return request({
    url: '/officer/holidayrecord/listUserInfo',
    method: 'get',
    params: query
  })
}

// 查询休假记录详细
export function getHolidayrecord(id) {
  return request({
    url: '/officer/holidayrecord/' + id,
    method: 'get'
  })
}

// 新增休假记录
export function addHolidayrecord(data) {
  return request({
    url: '/officer/holidayrecord',
    method: 'post',
    data: data
  })
}

// 修改休假记录
export function updateHolidayrecord(data) {
  return request({
    url: '/officer/holidayrecord',
    method: 'put',
    data: data
  })
}

// 删除休假记录
export function delHolidayrecord(id) {
  return request({
    url: '/officer/holidayrecord/' + id,
    method: 'delete'
  })
}
