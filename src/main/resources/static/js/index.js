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
