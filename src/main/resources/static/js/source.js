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
            handleCurrentChange(page) {
                data.getList(page).then(r => {})
            },
            handleSizeChange(val) {
                data.getList(null, null, val).then(r => {})
            }
        })

        const search = () => {
            sourcePage().then((res) => {
                data.list = res.list
            }).catch(() => {});
        }
        Vue.onMounted(() => {
            data.getList(null, null, 10).then(r => {})
        })
        return {
            ...Vue.toRefs(data),
            search,
        }
    }
}
const app = Vue.createApp(Source);
app.use(ElementPlus);
app.mount("#source");
