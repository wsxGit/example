var vm = new Vue({
    el: '#app',
    data() {
        return {
            showList: true,
            param:{},
            page: {
                curPage: 1,
                pageSize: 10
            },
            total: 0,
            tableData: [],
            ruleForm: {},
            rules: {
                userName: [{ required: true, message: '请输用户名称', trigger: 'blur' }],
                loginName: [{ required: true, message: '请输登录名', trigger: 'blur' }],
                loginPassword: [{ required: true, message: '请输密码', trigger: 'blur' }],
            }
        }
    },
    methods: {
        getList(){
            this.param.curPage = this.page.curPage;
            this.param.pageSize = this.page.pageSize;
            let that = this;
            ajaxPost("list",this.param,function (r) {
                that.tableData = r.data.data.content;
                that.total = r.data.data.totalElements;
            })
        },
        query(page){
            this.page = page;
            this.getList()
        },
        reset(){
            this.param = {};
        },
        submitForm(formName) {
            let that = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    ajaxPost("save",this.ruleForm,function (r) {
                        that.$message({showClose: true,message: '添加成功',type: 'success'});
                        that.showList = true;
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        update(index,data){
            this.ruleForm = data;
            this.showList = false;
        },
        remove(index,data){
            this.$confirm('确认删除吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let that = this;
                ajaxGet('delete?userId='+data.userId,data,function (r) {
                    that.$message({type: 'success',message: '删除成功!'})
                    that.getList();
                })
            });
        }
    },
    created(){
        this.getList();
    }
})