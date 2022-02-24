const App = {
    name: 'APP',
    setup() {
        let message = 'AAAAAA'
        const activeIndex = Vue.ref('1')
        const handleSelect = (key = '', keyPath = []) => {
            console.log(key, keyPath)
            verify({})
        }
        return {
            message,
            activeIndex,
            handleSelect
        }

    }
}
const app = Vue.createApp(App);
app.use(ElementPlus);
app.mount("#app");
