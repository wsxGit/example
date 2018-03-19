function DataTransfer(data) {
    if (!(this instanceof DataTransfer)) {
        return new DataTransfer(data, null, null)
    }
}


DataTransfer.treeToArray = function (data, parent, level, expandedAll) {
    let tmp = []
    Array.from(data).forEach(function (record) {
        if (record._expanded === undefined) {
            Vue.set(record, '_expanded', true)
        }
        if (record.icon === undefined) {
            Vue.set(record, 'icon', true)
        }
        if (parent) {
            Vue.set(record, '_parent', parent)
        }
        let _level = 0
        if (level !== undefined && level !== null) {
            _level = level + 1
        }
        Vue.set(record, '_level', _level)
        tmp.push(record)
        if (record.children && record.children.length > 0) {
            let children = DataTransfer.treeToArray(record.children, record, _level, expandedAll)
            tmp = tmp.concat(children)
        }
    })
    return tmp
}
Vue.component('tree-grid', {
    props:["treeStructure","columns","dataSource","requestUrl","defaultExpandAll"] ,
    template: '<el-table size="medium" :data="data" border  :row-style="showTr" style="width: 100%" @selection-change="handleSelectionChange">  ' +
    '<el-table-column type="selection" ></el-table-column>'+
    '<el-table-column v-for="(column, index) in columns" :key="column.dataIndex"  ' +
    '  :label="column.text">  ' +
    '  <template scope="scope">  ' +
    '    <span v-if="spaceIconShow(index)" v-for="(space, levelIndex) in scope.row._level" class="ms-tree-space"></span>  ' +
    '    <a style="color: #606266;text-decoration:none" class="button is-outlined is-primary is-small" href="javascript:void(0)" v-if="toggleIconShow(index,scope.row)" @click="toggle(scope.$index)">' +
    '     <i v-if="!scope.row.icon" class="el-icon-caret-right" aria-hidden="true"></i>' +
    '      <i v-if="scope.row.icon" class="el-icon-caret-bottom" aria-hidden="true"></i> ' +
    '    </a>  ' +
    '    <span v-else-if="index===0" class="ms-tree-space"></span>  ' +
    '    {{scope.row[column.dataIndex]}}  ' +
    '  </template>  ' +
    '</el-table-column>  ' +
    '  </el-table> ',
    data: function () {
        return {}
    },
    computed: {
        // 格式化数据源
        data: function () {
            let me = this
            if (me.treeStructure) {
                let data = DataTransfer.treeToArray(me.dataSource, null, null, me.defaultExpandAll)
                return data
            }
            return me.dataSource
        }
    },
    created: function () {
    },
    methods: {
        handleSelectionChange:function (val) {
            this.$emit('aa');
        },
        showTr: function (row, index) {
            if (row.row._expanded){
                row.row._show = true;
                return true;
            }else {
                row.row._show = 'display:none;'
                return 'display:none;'
            }
        },
        recursionToggle:function (children,flag) {
            for (var i = 0; i < children.length; i++) {
                var obj = children[i];
                if (!flag){
                    obj._expanded = false;
                }else {
                    obj._expanded = true;
                    obj.icon = true;
                }
                if (obj.children != undefined){
                    this.recursionToggle(obj.children,flag)
                }
            }
        },
        // 展开下级
        toggle: function (trIndex) {
            console.log(this.data)
            let me = this
            let record = me.data[trIndex];
            me.data[trIndex].icon = !me.data[trIndex].icon;
            let children = record.children;
            this.recursionToggle(children,me.data[trIndex].icon)
        },
        // 显示层级关系的空格和图标
        spaceIconShow(index) {
            let me = this
            if (me.treeStructure && index === 0) {
                return true
            }
            return false
        },
        // 点击展开和关闭的时候，图标的切换
        toggleIconShow(index, record) {
            let me = this
            if (me.treeStructure && index === 0 && record.children && record.children.length > 0) {
                return true
            }
            return false
        },
        handleDelete() {
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'error'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })
            })
        }
    }
})