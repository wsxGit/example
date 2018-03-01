var vm = new Vue({
    el: '#app',
    data() {
        return {
            param:{
                curPage: 1,
                pageSize: 10
            },
            total: 0,
            tableData: []
        }
    },
    methods: {
        getList(){
            let that = this;
            ajaxPost("list",this.param,function (r) {
                that.tableData = r.data.content;
                that.total = r.data.totalElements;
            })
        },
        query(page){
            this.param.curPage = page.curPage;
            this.param.pageSize = page.pageSize;
            this.getList()
        },
        update(index,data){

        },
        delete(index,data){

        }
    },
    created(){
        this.getList();
    }
})