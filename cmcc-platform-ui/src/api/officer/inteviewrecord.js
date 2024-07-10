import request from '@/utils/request'

// 查询房东访问列表
export function listInteviewrecord(query) {
  return request({
    url: '/officer/inteviewrecord/list',
    method: 'get',
    params: query
  })
}

// 查询房东访问详细
export function getInteviewrecord(id) {
  return request({
    url: '/officer/inteviewrecord/' + id,
    method: 'get'
  })
}

// 新增房东访问
export function addInteviewrecord(data) {
  return request({
    url: '/officer/inteviewrecord',
    method: 'post',
    data: data
  })
}

// 修改房东访问
export function updateInteviewrecord(data) {
  return request({
    url: '/officer/inteviewrecord',
    method: 'put',
    data: data
  })
}

// 删除房东访问
export function delInteviewrecord(id) {
  return request({
    url: '/officer/inteviewrecord/' + id,
    method: 'delete'
  })
}
