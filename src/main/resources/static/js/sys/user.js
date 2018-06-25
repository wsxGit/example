var vm = new Vue({
    el: '#app',
    data() {
        return {
            showList: true,
            title: '',
            param: {
                curPage: 1,
                pageSize: 10
            },
            total: 0,
            tableData: [],
            ruleForm: {},
            rules: {
                userName: [{required: true, message: '请输用户名称', trigger: 'blur'}],
                loginName: [{required: true, message: '请输登录名', trigger: 'blur'}],
                loginPassword: [{required: true, message: '请输密码', trigger: 'blur'}],
            }
        }
    },
    methods: {
        getList(curPage, pageSize) {
            this.param.curPage = curPage;
            this.param.pageSize = pageSize;
            let that = this;
            ajaxPost("list", this.param, function (r) {
                that.tableData = r.data.data.content;
                that.total = r.data.data.totalElements;
            })
        },
        query(page) {
            this.page = page;
            this.getList(page.curPage, page.pageSize)
        },
        reset() {
            this.param = {
                curPage: 1,
                pageSize: 10
            }
        },
        add() {
            this.ruleForm = {};
            this.showList = false;
            this.title = '新增';
        },
        update(index, data) {
            this.title = '修改';
            this.ruleForm = data;
            this.showList = false;
        },
        submitForm(formName) {
            let that = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    ajaxPost("save", this.ruleForm, function (r) {
                        that.$message({showClose: true, message: '操作成功', type: 'success'});
                        that.showList = true;
                        that.getList(1, that.param.pageSize);
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
        remove(index, data) {
            let that = this;
            this.$confirm('确认删除吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                ajaxGet('delete?id=' + data.userId, data, function (r) {
                    that.$message({type: 'success', message: '删除成功!'})
                    that.getList(that.param.curPage, that.param.pageSize);
                })
            });
        }
    },
    created() {
        this.getList(this.param.curPage, this.param.pageSize);
    }
})