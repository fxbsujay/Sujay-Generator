const App = {
	name: 'APP',
	setup() {
		let message = 'AAAAAA'
		return {
			message
		}

	}
}
const app = Vue.createApp(App);
app.use(ElementPlus);
app.mount("#app");