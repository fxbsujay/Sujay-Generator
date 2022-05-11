const Source = {
    name: 'Source',
    setup() {
        const form = Vue.ref(null)
        const data = Vue.reactive({
            tableKey: 0,
            listLoading: true,
            dialogVisible: false,
            total: 0,
            dataListSelections: [],
            list: [],
            listQuery: {
                page: 1,
                limit: 10,
                columnType: ''
            },
            dataForm: {
                id: '',
                columnType: '',
                attrType: '',
                project: '',
                number: null
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
                const res = await fieldTypePage(data.listQuery)
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
             * @param id  数据源id
             * @returns {Promise<void>}
             */
            async init(id) {
                data.dialogVisible = true
                if (id) {
                    const res = await fieldTypeInfo(id)
                    data.dataForm = {
                        ...data.dataForm,
                        ...res
                    }
                }
            },
            /**
             * 清空表单
             */
            resetForm() {
                form.value.resetFields()
                data.dataForm.id = ''
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
                        if (data.dataForm.id) {
                            await fieldTypeUpdate(data.dataForm)
                        } else {
                            await fieldTypeSave(data.dataForm)
                        }
                        data.dialogVisible = false
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
                await fieldTypeDelete(id ? [id] : this.dataListSelections.map(item => item['id']))
                await data.getList()
            },
            /**
             * 判空
             * @param str
             * @returns {*}
             */
            isNotBlank (str = '') {
                return isNotBlank(str)
            },
        })

        const rules = Vue.ref({
            columnType: [
                {
                    required: true,
                    message: '该项不能为空！',
                    trigger: 'blur',
                }
            ],
            attrType: [
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
            rules,
            ...Vue.toRefs(data)

        }
    }
}
const app = Vue.createApp(Source);
app.use(ElementPlus);
app.mount("#fieldType");
