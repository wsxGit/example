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
                    text: '姓名',
                    dataIndex: 'name'
                },
                {
                    text: '年龄',
                    dataIndex: 'age'
                },
                {
                    text: '性别',
                    dataIndex: 'sex'
                }
            ],
            dataSource: [
                {
                    id: 1,
                    parentId: 0,
                    name: '测试1',
                    age: 18,
                    sex: '男',
                    children: [
                        {
                            id: 2,
                            parentId: 1,
                            name: '测试2',
                            age: 22,
                            sex: '男'
                        }
                    ]
                },
                {
                    id: 3,
                    parentId: 0,
                    name: '测试3',
                    age: 23,
                    sex: '女',
                    children: [
                        {
                            id: 4,
                            parentId: 3,
                            name: '测试4',
                            age: 22,
                            sex: '男'
                        },
                        {
                            id: 5,
                            parentId: 3,
                            name: '测试5',
                            age: 25,
                            sex: '男'
                        },
                        {
                            id: 6,
                            parentId: 3,
                            name: '测试6',
                            age: 26,
                            sex: '女',
                            children: [
                                {
                                    id: 7,
                                    parentId: 6,
                                    name: '测试7',
                                    age: 27,
                                    sex: '男'
                                }
                            ]
                        }
                    ]
                },
                {
                    id: 18,
                    parentId: 0,
                    name: '测试8',
                    age: 18,
                    sex: '男'
                }
            ]
        }
    },
    methods: {
        getList() {
            this.param.curPage = this.page.curPage;
            this.param.pageSize = this.page.pageSize;
            let that = this;
            ajaxPost("list", this.param, function (r) {
                that.tableData = r.data.data.content;
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
                ajaxGet('delete?id=' + data.id, data, function (r) {
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
