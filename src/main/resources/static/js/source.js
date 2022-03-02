const Source = {
    name: 'Source',
    setup() {

        const data = Vue.reactive({
            tableKey: 0,
            listLoading: true,
            total: 0,
            list: [],
            listQuery: {
                page: 1,
                limit: 10
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
                const res = await sourcePage(data.listQuery)
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
            }
        })

        /**
         * 查询按钮
         */
        const search = () => {
            data.listQuery.page = 1
            data.getList().then(r => {})
        }

        const dialogVisible = Vue.ref(false)

        const handleClose = (done) => {
            ElementPlus.ElMessageBox.confirm('Are you sure to close this dialog?')
                .then(() => {
                    done()
                })
                .catch(() => {
                    // TODO catch error
                })
        }
        Vue.onMounted(() => {
            data.getList(null, null, 10).then(r => {})
        })
        return {
            ...Vue.toRefs(data),
            search,
            dialogVisible,
            handleClose
        }
    }
}
const app = Vue.createApp(Source);
app.use(ElementPlus);
app.mount("#source");
