const Template = {
    name: 'Template',
    setup() {
        const form = Vue.ref(null)
        const data = Vue.reactive({
            tableKey: 0,
            listLoading: true,
            drawerVisible: false,
            total: 0,
            dataListSelections: [],
            list: [],
            listQuery: {
                page: 1,
                limit: 10
            },
            dataForm: {
                id: '',
                name: '',
                fileName: '',
                content: '',
                path: '',
                status: 0
            },
            /**
             * 编辑器实例
             */
            editor: null,
            /**
             * 编辑器属性
             */
            options: {
                mode: "text/x-java",
                lineNumbers: true,
                styleActiveLine: true,
                matchBrackets: true,
                theme: 'darcula'
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
                const res = await templatePage(data.listQuery)
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
                data.drawerVisible = true
                if (id) {
                    const res = await templateInfo(id)
                    data.dataForm = {
                        ...data.dataForm,
                        ...res
                    }
                }
                this.$nextTick(() => {
                    if (!isNotBlank(data.editor)) {
                        data.editor = Vue.markRaw(CodeMirror.fromTextArea(document.getElementById('editorArea'),  {
                            mode: "text/x-java",
                            lineNumbers: true,
                            styleActiveLine: true,
                            matchBrackets: true,
                            theme: 'darcula',
                            autoRefresh: true
                        }));
                    }
                    data.editor.setValue(data.dataForm.content)
                    data.editor.on('change', cm => {
                        this.$emit('changed', data.codeMirrorHandle());
                        this.$emit('input', data.codeMirrorHandle());
                    });
                    data.codeMirrorRefresh()
                })
            },
            /**
             * 刷新CodeMirror组件
             */
            codeMirrorRefresh() {
                data.editor && data.editor.refresh();
            },
            /**
             * CodeMirror输入触发方法
             */
            codeMirrorHandle() {
                data.dataForm.content = data.editor.getValue()
                return data.editor.getValue()
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
                        ElementPlus.ElMessageBox.confirm(`Are you confirm to chose  ?`)
                            .then(async () => {
                                if (data.dataForm.id) {
                                    await templateUpdate(data.dataForm)
                                } else {
                                    await templateSave(data.dataForm)
                                }
                                data.drawerVisible = false
                                await data.getList()
                            })
                            .catch(() => {
                                // catch error
                            })
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
                await templateDelete(id ? [id] : this.dataListSelections.map(item => item['id']))
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
            name: [
                {
                    required: true,
                    message: '该项不能为空！',
                    trigger: 'blur',
                }
            ],
            fileName: [
                {
                    required: true,
                    message: '该项不能为空！',
                    trigger: 'blur',
                }
            ],
            path: [
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
const app = Vue.createApp(Template);
app.use(ElementPlus);
app.mount("#template");
