<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="app">
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>活动管理</el-breadcrumb-item>
        <el-breadcrumb-item>活动列表</el-breadcrumb-item>
        <el-breadcrumb-item>活动详情</el-breadcrumb-item>
    </el-breadcrumb>
    <br>
    <hr style="height:2px;border:none;border-top:2px solid #409EFF;">
    <br>
    <el-input v-model="input" placeholder="请输入内容"></el-input>
    <el-input v-model="input" placeholder="请输入内容"></el-input>
    <el-button type="primary">查询</el-button>
    <br>
    <br>
    <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="userName" label="用户名称" width="180"></el-table-column>
        <el-table-column prop="loginName" label="登录名称" width="180"></el-table-column>
        <el-table-column prop="createTime" label="创建日期"></el-table-column>
        <el-table-column prop="updateTime" label="修改日期"></el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div style="float: right;margin-top: 10px">
        <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.curPage"
                :page-sizes="[100, 200, 300, 400]"
                :page-size="100"
                layout="total, sizes, prev, pager, next, jumper"
                :total="400">
        </el-pagination>
    </div>

</div>
<link rel="stylesheet" href="/static/lib/css/elementui.css">
<script src="/static/lib/js/vue.min.js"></script>
<script src="/static/lib/js/elementui.js"></script>
<script src="/static/lib/js/axios.js"></script>
<script src="/static/js/common.js"></script>
<script src="/static/js/sys/user/user.js"></script>
</body>
</html>