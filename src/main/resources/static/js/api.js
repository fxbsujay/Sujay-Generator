const request = axios.create({
    baseURL: 'http://localhost' // 基础路径
})

// 请求拦截器
request.interceptors.request.use(
    config => {
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器
request.interceptors.response.use(
    response => {
        return response
    },
    err => {
        return Promise.reject(err)
    }
)


function verify(data) {
    return request({
        url: '/auth/verify',
        method: 'post',
        data
    })
}