const App = {
    name: 'APP',
    setup() {
        const data = Vue.reactive({
            menu: {
                index: 1,
                path: 'main.html',
                title: '首页'
            },
            menuList: [
                {
                    index: 1,
                    path: 'main.html',
                    title: '首页'
                },
                {
                    index: 2,
                    path: 'source.html',
                    title: '数据源'
                },
                {
                    index: 3,
                    path: 'table.html',
                    title: '数据表'
                },
                {
                    index: 4,
                    path: 'fieldType.html',
                    title: '字段管理'
                },
                {
                    index: 5,
                    path: 'template.html',
                    title: '模板管理'
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
