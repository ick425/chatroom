import request from '@/utils/request'

export function page${entity}(parameter) {
  return request({
    url: '/api/admin/${entity?uncap_first}/page',
    method: 'get',
    params: parameter
  })
}

export function add${entity}(parameter) {
  return request({
    url: '/api/admin/${entity?uncap_first}/add',
    method: 'post',
    data: parameter
  })
}

export function edit${entity}(parameter) {
  return request({
    url: '/api/admin/${entity?uncap_first}/edit',
    method: 'post',
    data: parameter
  })
}

export function delete${entity}(parameter) {
  return request({
    url: '/api/admin/${entity?uncap_first}/' + parameter.id,
    method: 'delete'
  })
}