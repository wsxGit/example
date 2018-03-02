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
    <div v-show="showList">
        <el-input v-model="param.name" placeholder="请输入名称"></el-input>
        <el-button-group>
            <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
            <el-button type="primary" icon="el-icon-refresh" @click="reset">清空</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="add">新增</el-button>
        </el-button-group>
        <br><br>
        <el-table :data="tableData" border style="width: 100%" size="medium">
            <el-table-column prop="roleId" label="角色ID"></el-table-column>
            <el-table-column prop="roleName" label="角色名称"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="update(scope.$index, scope.row)">修改</el-button>
                    <el-button size="mini" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="float: right;margin-top: 10px">
            <page :size="page.pageSize" :total="total" @click="query" @change="query"></page>
        </div>
    </div>
    <el-row v-show="!showList">
        <el-col :span="8">
            <div style="border: 1px solid #409EFF;border-radius: 4px;">
                <div style="background-color: #409eff;border-radius: 1px;color: #fff;padding-top: 20px;text-align: center;padding-bottom: 20px">
                    {{title}}
                </div>
                <br><br>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="160px" class="demo-ruleForm">
                    <el-form-item label="角色名称" prop="roleName">
                        <el-input v-model="ruleForm.roleName" maxlength="20"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button-group>
                            <el-button type="primary" icon="el-icon-check" @click="submitForm('ruleForm')">提交
                            </el-button>
                            <el-button type="primary" icon="el-icon-back" @click="showList = true">返回</el-button>
                        </el-button-group>
                    </el-form-item>
                </el-form>
            </div>
        </el-col>
    </el-row>
</div>
<script src="/static/js/sys/role/role.js"></script>
</body>
</html>
