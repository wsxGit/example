new Vue({
    el: '#app',
    data() {
        return {
            main: "/main"
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        route(key, keyPath) {
            this.main = key;
        }
    }
})