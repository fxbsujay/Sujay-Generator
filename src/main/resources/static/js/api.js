const request = axios.create({
    baseURL: 'http://localhost:8001' // 基础路径
})

/**
 * <p>Description: 请求拦截器</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
request.interceptors.request.use(
    config => {
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

/**
 * <p>Description: 响应拦截器</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
request.interceptors.response.use(
    response => {
        const res = response.data
        if (res.code !== 200) {
            ElementPlus.ElMessage.error({
                message: res.msg
            });
            return Promise.reject(new Error(res.msg || 'Error'))
        } else {
            return res.data
        }

    },
    err => {
        ElementPlus.ElMessage.error({
            message: '操作失败'
        });
        return Promise.reject(err)
    }
)


/**
 * <p>Description: 数据源分页查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function sourcePage() {
    return request({
        url: '/source/page',
        method: 'get'
    })
}