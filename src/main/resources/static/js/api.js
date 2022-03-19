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
        if (config.method === 'get') {
            config.params = {
                ...config.params,
                ...{ _t: new Date().getTime() }
            }
        }
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
function sourcePage(data) {
    return request({
        url: '/source/page',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 数据源分页查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function sourceList(data) {
    return request({
        url: '/source/list',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 数据源详情查询 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function sourceInfo(id) {
    return request({
        url: '/source/' + id,
        method: 'get'
    })
}


/**
 * <p>Description: 数据源新增 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function sourceSave(data) {
    return request({
        url: '/source',
        method: 'post',
        data
    })
}

/**
 * <p>Description: 数据源修改 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function sourceUpdate(data) {
    return request({
        url: '/source',
        method: 'put',
        data
    })
}

/**
 * <p>Description: 数据源删除 API</p>
 * @author sujay
 * @version 16:09 2022/3/5
 */
function sourceDelete(data) {
    return request({
        url: '/source',
        method: 'delete',
        data
    })
}

/**
 * <p>Description: 数据源连接测试 API</p>
 * @author sujay
 * @version 15:35 2022/3/6
 */
function sourceConnTest(id) {
    return request({
        url: '/source/test/' + id,
        method: 'get'
    })
}

/**
 * <p>Description: 表分页查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function tablePage(data) {
    return request({
        url: '/table/page',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 表查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function tableList(data) {
    return request({
        url: '/table/list',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 表详情查询 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function tableInfo(id) {
    return request({
        url: '/table/' + id,
        method: 'get'
    })
}


/**
 * <p>Description: 表新增 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function tableSave(data) {
    return request({
        url: '/table',
        method: 'post',
        data
    })
}

/**
 * <p>Description: 表修改 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function tableUpdate(data) {
    return request({
        url: '/table',
        method: 'put',
        data
    })
}

/**
 * <p>Description: 表删除 API</p>
 * @author sujay
 * @version 16:09 2022/3/5
 */
function tableDelete(data) {
    return request({
        url: '/table',
        method: 'delete',
        data
    })
}

/**
 * <p>Description: 数据源表导入查询 API</p>
 * @author sujay
 * @version 13::13 2022/3/8
 */
function queryTableListBySourceId(id) {
    return request({
        url: '/table/queryTableListBySourceId/' + id,
        method: 'get'
    })
}

/**
 * <p>Description: 数据源表导入查询 API</p>
 * @author sujay
 * @version 13::13 2022/3/8
 */
function importTable(data) {
    return request({
        url: '/table/importTable',
        method: 'post',
        data
    })
}


/**
 * <p>Description: 字段分页查询 API</p>
 * @author sujay
 * @version 0:43 2022/3/17
 */
function columnPage(data) {
    return request({
        url: '/column/page',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 字段分页查询 API</p>
 * @author sujay
 * @version 0:43 2022/3/17
 */
function columnList(data) {
    return request({
        url: '/column/list',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 字段详情查询 API</p>
 * @author sujay
 * @version 0:43 2022/3/17
 */
function columnInfo(id) {
    return request({
        url: '/column/' + id,
        method: 'get'
    })
}


/**
 * <p>Description: 字段新增 API</p>
 * @author sujay
 * @version 0:43 2022/3/17
 */
function columnSave(data) {
    return request({
        url: '/column',
        method: 'post',
        data
    })
}

/**
 * <p>Description: 字段修改 API</p>
 * @author sujay
 * @version 0:43 2022/3/17
 */
function columnUpdate(data) {
    return request({
        url: '/column',
        method: 'put',
        data
    })
}

/**
 * <p>Description: 字段删除 API</p>
 * @author sujay
 * @version 0:43 2022/3/17
 */
function columnDelete(data) {
    return request({
        url: '/column',
        method: 'delete',
        data
    })
}


/**
 * <p>Description: 数据源分页查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function fieldTypePage(data) {
    return request({
        url: '/field-type/page',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 数据源分页查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function fieldTypeList(data) {
    return request({
        url: '/field-type/list',
        method: 'get',
        params: data
    })
}

/**
 * <p>Description: 数据源详情查询 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function fieldTypeInfo(id) {
    return request({
        url: '/field-type/' + id,
        method: 'get'
    })
}


/**
 * <p>Description: 数据源新增 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function fieldTypeSave(data) {
    return request({
        url: '/field-type',
        method: 'post',
        data
    })
}

/**
 * <p>Description: 数据源修改 API</p>
 * @author sujay
 * @version 23:39 2022/3/3
 */
function fieldTypeUpdate(data) {
    return request({
        url: '/field-type',
        method: 'put',
        data
    })
}

/**
 * <p>Description: 数据源删除 API</p>
 * @author sujay
 * @version 16:09 2022/3/5
 */
function fieldTypeDelete(data) {
    return request({
        url: '/field-type',
        method: 'delete',
        data
    })
}