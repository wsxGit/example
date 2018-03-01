<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<style>
    .el-header {
        background-color: #409EFF;
        color: #fff;
        text-align: center;
        line-height: 60px;
    }

    .el-footer {
        background-color: #fff;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        line-height: 200px;
        background-color: #E9EEF3;
    }

    .el-main {
        background-color: #fff;
        color: #333;
        text-align: center;
        /*line-height: 160px;*/
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
<div id="app">
    <el-container>
        <el-header>Header</el-header>
        <el-container>
            <el-aside>
                <el-row>
                    <el-col :span="24">
                        <el-menu background-color="#E9EEF3" default-active="/user/index" class="el-menu-vertical-demo"
                                @select="route" @open="handleOpen" @close="handleClose">
                            <el-submenu index="1">
                                <template slot="title">
                                    <i class="el-icon-menu"></i>
                                    <span>系统管理</span>
                                </template>
                                <el-menu-item-group>
                                    <el-menu-item index="/user/index">用户管理</el-menu-item>
                                    <el-menu-item index="1-2">选项2</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group>
                                    <el-menu-item index="1-3">选项3</el-menu-item>
                                </el-menu-item-group>
                                <el-submenu index="1-4">
                                    <template slot="title">选项4</template>
                                    <el-menu-item index="1-4-1">选项1</el-menu-item>
                                </el-submenu>
                            </el-submenu>
                            <el-menu-item index="2">
                                <i class="el-icon-menu"></i>
                                <span slot="title">导航二</span>
                            </el-menu-item>
                            <el-menu-item index="3">
                                <i class="el-icon-setting"></i>
                                <span slot="title">导航三</span>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>
            <el-container>
                <el-main>
                    <iframe scrolling="yes" frameborder="0" style="width:100%;min-height: 720px;" :src="main"></iframe>
                </el-main>
                <el-footer>Footer</el-footer>
            </el-container>
        </el-container>
    </el-container>
</div>
<link rel="stylesheet" href="/static/lib/css/elementui.css">
<script src="/static/lib/js/vue.min.js"></script>
<script src="/static/lib/js/elementui.js"></script>
<script src="/static/js/index.js"></script>
</body>
</html>