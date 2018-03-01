<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#include "../../common/common.ftl">
<div id="app">
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>系统管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <hr style="height:2px;border:none;border-top:2px solid #409EFF;margin-top: 15px;margin-bottom: 10px">
    <el-input v-model="param.userName" placeholder="请输入用户名称"></el-input>
    <el-input v-model="param.loginName" placeholder="请输入登录名称"></el-input>
    <el-button-group>
        <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="param.userName = '';param.loginName = ''">清空</el-button>
        <el-button type="primary" icon="el-icon-plus">新增</el-button>
    </el-button-group>
    <br><br>
    <el-table :data="tableData" border style="width: 100%" size="medium">
        <el-table-column prop="userName" label="用户名称"></el-table-column>
        <el-table-column prop="loginName" label="登录名称"></el-table-column>
        <el-table-column prop="createTime" label="创建日期"></el-table-column>
        <el-table-column prop="updateTime" label="修改日期"></el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button size="mini" @click="update(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="delete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div style="float: right;margin-top: 10px">
        <page :size="param.pageSize" :total="total" @click="query" @change="query"></page>
    </div>
</div>
<script src="/static/js/sys/user/user.js"></script>
</body>
</html>