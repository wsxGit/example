<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#include "../common/common.ftl">
<div id="app">
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>首页</el-breadcrumb-item>
        <el-breadcrumb-item>系统管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <hr style="height:2px;border:none;border-top:2px solid #409EFF;margin-top: 15px;margin-bottom: 10px">
    <div v-show="showList">
        <el-input v-model="param.userName" placeholder="请输入用户名称" size="small"></el-input>
        <el-input v-model="param.loginName" placeholder="请输入登录名称" size="small"></el-input>
        <el-button-group>
            <el-button type="primary" icon="el-icon-search" @click="getList(1,page.pageSize)" size="small">查询
            </el-button>
            <el-button type="primary" icon="el-icon-refresh" @click="reset" size="small">清空</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="add" size="small">新增</el-button>
        </el-button-group>
        <br><br>
        <el-table :data="tableData" border style="width: 100%" size="small">
            <el-table-column prop="userName" label="用户名称"></el-table-column>
            <el-table-column prop="loginName" label="登录名称"></el-table-column>
            <el-table-column prop="createTime" label="创建日期"></el-table-column>
            <el-table-column prop="updateTime" label="修改日期"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="update(scope.$index, scope.row)">修改</el-button>
                    <el-button size="mini" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="float: right;margin-top: 10px">
            <page :size="param.pageSize" :cur-page.sync="param.curPage" :total="total" @click="query"
                  @change="query"></page>
        </div>
    </div>
    <el-row v-show="!showList">
        <el-col>
            <div style="border: 1px solid #409EFF;border-radius: 4px;">
                <div style="background-color: #409eff;border-radius: 1px;color: #fff;padding-top: 5px;text-align: center;padding-bottom: 5px">
                    {{title}}
                </div>
                <br><br>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="20%" class="demo-ruleForm"
                         size="small">
                    <el-form-item label="用户名称" prop="userName">
                        <el-input v-model="ruleForm.userName" maxlength="20"></el-input>
                    </el-form-item>
                    <el-form-item label="登录名" prop="loginName">
                        <el-input v-model="ruleForm.loginName" maxlength="20"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="loginPassword">
                        <el-input v-model="ruleForm.loginPassword" maxlength="20"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-check" @click="submitForm('ruleForm')">提交
                        </el-button>
                        <el-button type="warning" icon="el-icon-back" @click="showList = true">返回</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-col>
    </el-row>
</div>
<script src="/static/js/sys/user.js"></script>
</body>
</html>