Vue.component('page', {
    props: ["size", "total"],
    template: ' ' +
    '<div style="float: right">' +
    '<el-pagination background layout="total,sizes, prev, pager, next, jumper" :total="total" ' +
    ':page-size="page.pageSize" @current-change="handlePageChange" @size-change="handleSizeChange">' +
    '</el-pagination>' +
    '</div>',
    data: function () {
        return {
            page: {
                curPage: 1,
                pageSize: this.size
            }
        }
    },
    methods: {
        handlePageChange(curPage) {
            this.page.curPage = curPage;
            this.$emit("click", this.page)
        },
        handleSizeChange(size){
            this.page.pageSize = size;
            this.$emit("change", this.page)
        }
    }
})

function ajaxGet(url, param, callback) {
    axios({
        method: 'get',
        url: url,
        param: param,
    }).then(function (res) {
        callback(res);
    })
}

function ajaxPost(url, data, callback) {
    axios({
        method: 'post',
        url: url,
        data: data,
        transformRequest: [function (data, headers) {
            var args = ''
            for (var obj in data) {
                if (typeof (data[obj]) == 'object' && data[obj] != null) {
                    var value = '';
                    var array = data[obj];
                    for (var i = 0; i < array.length; i++) {
                        var item = array[i];
                        value += item + ","
                    }
                    value = value.substring(0, value.length - 1);
                    args += obj + "=" + value + "&"
                } else {
                    args += obj + "=" + data[obj] + "&"
                }
            }
            return args;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(function (res) {
        callback(res);
    })
}

function ajaxJson(url, data, callback) {
    axios({
        method: 'post',
        url: url,
        data: data,
    }).then(function (res) {
        callback(res);
    })
}