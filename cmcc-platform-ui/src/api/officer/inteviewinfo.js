import request from '@/utils/request'

// 查询访问记录列表
export function listInteviewinfo(query) {
  return request({
    url: '/officer/inteviewinfo/list',
    method: 'get',
    params: query
  })
}

// 查询访问记录列表
export function appcheckadmin() {
  return request({
    url: '/officer/inteviewinfo/appcheckadmin',
    method: 'get'
  })
}

// 查询访问记录详细
export function getInteviewinfo(id) {
  return request({
    url: '/officer/inteviewinfo/' + id,
    method: 'get'
  })
}

// 新增访问记录
export function addInteviewinfo(data) {
  return request({
    url: '/officer/inteviewinfo',
    method: 'post',
    data: data
  })
}

// 修改访问记录
export function updateInteviewinfo(data) {
  return request({
    url: '/officer/inteviewinfo',
    method: 'put',
    data: data
  })
}

// 删除访问记录
export function delInteviewinfo(id) {
  return request({
    url: '/officer/inteviewinfo/' + id,
    method: 'delete'
  })
}
