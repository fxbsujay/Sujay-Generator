const Table = {
    name: 'Table',
    setup() {
        const form = Vue.ref(null)
        const fullscreenLoading = Vue.ref(false)
        const data = Vue.reactive({
            tableKey: 0,
            listLoading: true,
            columnListLoading: true,
            importDialogVisible: false,
            updateDialogVisible: false,
            columnDrawerVisible: false,
            total: 0,
            dataListSelections: [],
            list: [],
            /**
             * 数据源数组
             */
            sourceList: [],
            /**
             * 数据源下的所有表
             */
            tableList: [],
            /**
             * 字段信息数组
             */
            columnList: [],
            fieldType: [],
            listQuery: {
                page: 1,
                limit: 10
            },
            dataForm: {
                id: '',
                tableName: '',
                sourceId: '',
                tableComment: '',
                moduleName: '',
                subModuleName: '',
                packageName: '',
                backendPath: '',
                baseClassId: ''
            },

            /**
             *  查询列表
             * @param page 页码
             * @param total 总条数
             * @param limit 条数
             * @returns {Promise<void>}
             */
            async getList(page = null, total = null, limit = null) {
                if (page) {
                    data.listQuery.page = page
                }
                if (limit) {
                    data.listQuery.limit = limit
                }
                data.listLoading = true
                const res = await tablePage(data.listQuery)
                data.list = res.list
                data.total = res.total
                setTimeout(() => {
                    data.listLoading = false
                }, 0.5 * 1000)
            },
            /**
             * 分页器方法
             * @param page 页码
             */
            handleCurrentChange(page) {
                data.getList(page).then(r => {})
            },
            /**
             * 分页器方法
             * @param val 条数
             */
            handleSizeChange(val) {
                data.getList(null, null, val).then(r => {})
            },
            /**
             * 查询按钮
             * */
            search(){
                data.listQuery.page = 1
                data.getList().then(r => {})
            },
            /**
             * 表单初始化
             * @param id  表id
             * @returns {Promise<void>}
             */
            async init(id) {
                if (id) {
                    const res = await tableInfo(id)
                    data.dataForm = {
                        ...data.dataForm,
                        ...res
                    }
                    data.updateDialogVisible = true
                }else {
                    sourceList({ status: 0 }).then( res => {
                        data.sourceList = res
                    })
                    queryTableListBySourceId(data.dataForm.sourceId ? data.dataForm.sourceId : 0).then( res => {
                        data.tableList = res
                    })
                    data.importDialogVisible = true
                }
            },
            /**
             * 清空表单
             */
            resetForm() {
                form.value.resetFields()
            },
            /**
             * 多选
             * @param rows 行
             */
            handleSelectionChange(rows) {
                data.dataListSelections = rows
            },
            /**
             * 表单提交
             */
            submitHandle: debounce(function (){
                if (!form) return
                form.value.validate(async (valid) => {
                    if (valid) {
                        fullscreenLoading.value = true
                        if (data.dataForm.id) {
                            await tableUpdate(data.dataForm)
                            fullscreenLoading.value = false
                            data.updateDialogVisible = false
                        } else {
                            await importTable(data.dataForm)
                            fullscreenLoading.value = false
                            data.importDialogVisible = false
                        }
                        await data.getList()
                    } else {
                        return false
                    }
                })
            },1000,true),
            /**
             * 删除
             */
            async deleteHandle(id) {
                if (!id && data.dataListSelections.length <= 0) {
                    ElementPlus.ElMessage({
                        message: '请选择删除项！',
                        type: 'warning',
                    })
                }
                await tableDelete(id ? [id] : this.dataListSelections.map(item => item['id']))
                await data.getList()
            },
            /**
             * 字段信息抽屉初始化
             * @param id table_id
             */
            async columnDrawerInit(id) {
                data.columnList = await columnList({tableId: id})
                data.columnDrawerVisible = true
                data.columnListLoading = false
                data.fieldType = await fieldTypeList({})
            },
            /**
             * 字段信息抽屉提交
             */
            columnDrawerSubmit: debounce(function (){
                 ElementPlus.ElMessageBox.confirm(`你确定保存并关闭窗口吗  ?`)
                    .then(() => {
                        data.columnDrawerVisible = false
                    })
                    .catch(() => {
                        // catch error
                    })
            },1000,true),
            /**
             * 数据表导出模板
             */
            exportTable(id) {
                location.href = `http://localhost:8001/table/exportTable/${id}`
                ElementPlus.ElNotification({
                    title: '导出成功',
                    message: '请在游览器的下载内容种查看下载文件',
                    type: 'success'
                });
            },
            /**
             * 判空
             * @param str
             * @returns {*}
             */
            isNotBlank (str = '') {
                return isNotBlank(str)
            }
        })

        const rules = Vue.ref({
            sourceId: [
                {
                    required: true,
                    message: '该项不能为空！',
                    trigger: 'blur',
                }
            ],
            tableName: [
                {
                    required: true,
                    message: '该项不能为空！',
                    trigger: 'blur',
                }
            ]
        })

        Vue.onMounted(() => {
            data.getList(null, null, 10).then(r => {})
        })
        return {
            form,
            fullscreenLoading,
            rules,
            ...Vue.toRefs(data)

        }
    }
}
const app = Vue.createApp(Table);
app.use(ElementPlus);
app.mount("#table");
