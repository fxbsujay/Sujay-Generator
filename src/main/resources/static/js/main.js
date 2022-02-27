const Main = {
    name: 'Main',
    setup() {

        const data = Vue.reactive({
            total: 0,
            list: []
        });
        sourcePage().then((res) => {
            data.list = res.list
        }).catch(() => {});

        return {
            ...Vue.toRefs(data),
        }
    }
}
const app = Vue.createApp(Main);
app.use(ElementPlus);
app.mount("#main");
