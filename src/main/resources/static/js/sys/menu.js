var vm = new Vue({
    el: '#app',
    data() {
        return {
            showList: true,
            title: '',
            param: {},
            page: {
                curPage: 1,
                pageSize: 10
            },
            total: 0,
            tableData: [],
            ruleForm: {},
            rules: {
                menuIcon: [{required: true, message: '请输入内容', trigger: 'blur'}],
                menuSort: [{required: true, message: '请输入内容', trigger: 'blur'}],
                menuName: [{required: true, message: '请输入内容', trigger: 'blur'}],
                menuUrl: [{required: true, message: '请输入内容', trigger: 'blur'}],
                parentId: [{required: true, message: '请输入内容', trigger: 'blur'}],
            },
            columns: [
                {
                    text: '菜单名称',
                    dataIndex: 'menuName'
                },
                {
                    text: '菜单url',
                    dataIndex: 'menuUrl'
                },
                {
                    text: '排序号',
                    dataIndex: 'menuSort'
                }
            ],
            dataSource: []
        }
    },
    methods: {
        getList() {
            this.param.curPage = this.page.curPage;
            this.param.pageSize = this.page.pageSize;
            let that = this;
            ajaxPost("list", this.param, function (r) {
                that.dataSource = r.data.data.content;
                that.total = r.data.data.totalElements;
            })
        },
        query(page) {
            this.page = page;
            this.getList()
        },
        reset() {
            this.param = {};
        },
        add() {
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
                        that.getList();
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
            this.$confirm('确认删除吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let that = this;
                ajaxGet('delete?id=' + data.menuId, data, function (r) {
                    that.$message({type: 'success', message: '删除成功!'})
                    that.getList();
                })
            });
        }
    },
    created() {
        this.getList();
    }
})
