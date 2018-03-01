var vm = new Vue({
    el: '#app',
    data() {
        return {
            param:{},
            page:{
                curPage: 1,
                pageSize: 10
            },
            currentPage4: 4,
            tableData: []
        }
    },
    methods: {
        getList(){
            ajaxPost("list",this.page,function (r) {
                vm.tableData = r.data.content;
            })
        }
    },
    created(){
        this.getList();
    }
})