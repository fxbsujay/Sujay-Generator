const App = {
    name: 'APP',
    setup() {

        const data = Vue.reactive({
            total: 0,
            list: []
        });

        sourcePage().then((res) => {
            data.list = res.list
        }).catch(() => {});

        let footerName = '页尾'
        const activeIndex = Vue.ref('1')

        const handleSelect = (key = '', keyPath = []) => {
            console.log(key, keyPath)
            ElementPlus.ElMessage.success({
                message: '切换页面'
            });
        }
        var list = Vue.toRaw(data)
        console.log(list)

        const state = Vue.reactive({
            query: 'vue',
            hits: []
        })
        const fetchData = async (query) => {
            const data = await fetch( `https://localhost:8001/search?query=${query}` ).then(rsp => rsp.json())
            state.hits = data.hits
        }
        Vue.onMounted(() => {
            - fetchData(state.query)
            Vue.watchEffect(() => {
                fetchData(state.query)
            })
        })
        return {
            ...Vue.toRefs(data),
            footerName,
            activeIndex,
            handleSelect
        }
    }
}
const app = Vue.createApp(App);
app.use(ElementPlus);
app.mount("#app");
