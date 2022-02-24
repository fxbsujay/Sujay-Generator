const App = {
    name: 'APP',
    setup() {

        let list = []
        sourcePage().then((res) => {
            console.log(res)
        }).catch(() => {});

        let message = 'AAAAAA'
        const activeIndex = Vue.ref('1')
        const handleSelect = (key = '', keyPath = []) => {
            console.log(key, keyPath)
            ElementPlus.ElMessage.success({
                message: '已取消'
            });
        }
        return {
            list,
            message,
            activeIndex,
            handleSelect
        }

    }
}
const app = Vue.createApp(App);
app.use(ElementPlus);
app.mount("#app");
