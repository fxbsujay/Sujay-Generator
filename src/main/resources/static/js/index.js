const App = {
    name: 'APP',
    setup() {


        let footerName = '页尾'
        const activeIndex = Vue.ref('1')

        const handleSelect = (key = '', keyPath = []) => {
            console.log(key, keyPath)
            ElementPlus.ElMessage.success({
                message: '切换页面'
            });
        }

        return {
            footerName,
            activeIndex,
            handleSelect
        }
    }
}
const app = Vue.createApp(App);
app.use(ElementPlus);
app.mount("#app");
