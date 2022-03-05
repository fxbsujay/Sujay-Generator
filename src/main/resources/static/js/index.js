const App = {
    name: 'APP',
    setup() {
        const data = Vue.reactive({
            menu: {
                index: 1,
                path: 'source.html',
                title: "数据源",
            },
            menuList: [
                {
                    index: 1,
                    path: 'source.html',
                    title: '数据源'
                },
                {
                    index: 2,
                    path: 'main.html',
                    title: 'main'
                }
            ]
        })
        /**
         * 页面切换
         * @param key 下标 index
         * @param keyPath 下标数组
         */
        const handleSelect = (key = 1, keyPath = []) => {
            data.menu = data.menuList.find( item => item.index === key)
        }
        return {
            handleSelect,
            ...Vue.toRefs(data)
        }
    }
}
const app = Vue.createApp(App);
app.use(ElementPlus);
app.mount("#app");
