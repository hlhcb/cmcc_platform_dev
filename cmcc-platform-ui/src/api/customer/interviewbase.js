import request from '@/utils/request'

// 查询用户走访列表
export function listInterviewbase(query) {
  return request({
    url: '/customer/interviewbase/list',
    method: 'get',
    params: query
  })
}

// 查询用户走访详细
export function getInterviewbase(id) {
  return request({
    url: '/customer/interviewbase/' + id,
    method: 'get'
  })
}

// 新增用户走访
export function addInterviewbase(data) {
  return request({
    url: '/customer/interviewbase',
    method: 'post',
    data: data
  })
}

// 修改用户走访
export function updateInterviewbase(data) {
  return request({
    url: '/customer/interviewbase',
    method: 'put',
    data: data
  })
}

// 删除用户走访
export function delInterviewbase(id) {
  return request({
    url: '/customer/interviewbase/' + id,
    method: 'delete'
  })
}
