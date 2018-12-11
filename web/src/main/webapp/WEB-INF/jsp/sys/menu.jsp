<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>功能列表</title>
</head>
<body>
<div class="right-header">
    <a href="javascript:void(0)" class="btn m-b-xs w-xs btn-primary" @click="addMenu()"><i class="fa fa-plus"></i>&nbsp;添加</a>
</div>
<div>
    <nav class="navbar navbar-light bg-light" style="margin-top:10px;">
        <form class="form-inline">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" v-model="searchKey">
            <input type="button" class="btn btn-outline-success my-2 my-sm-0" @click="search()" value="Search"/>
        </form>
    </nav>
</div>
<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">名称</th>
            <th scope="col">父级</th>
            <th scope="col">地址</th>
            <th scope="col">图标</th>
            <th scope="col">序号</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in menuList">
            <th scope="row">
                <a href="javascript:void(0)" v-text="item.name" @click="editMenu(item)">
                </a>
            </th>
            <td v-text="item.pName"></td>
            <td v-text="item.url"></td>
            <td><i :class="item.icon" v-if="item.icon"></i></td>
            <td v-text="item.seq"></td>
            <td class="table-oper">
                <a href="javascript:void(0)" class="fabutton" title="删除用户" @click="deleteMenu(item)">
                    <i class="fa fa-times text-danger"></i>
                </a>
                <a href="javascript:void(0)" class="fabutton" title="权限" @click="quanXian(item)">
                    <i class="fa fa-list"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsSection>
    <script>
        vueData = {menuList:[],searchKey:'',pagerInfo:null};

        vueMethods = {
            addMenu: addMenu,
            editMenu: editMenu,
            deleteMenu: deleteMenu,
            search: search,
            quanXian: quanXian
        };

        vueMounted = function (){
            search(this);
        };

        function addMenu(){
            this.commonSrv.get("/comm/getNewId").then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    window.location = ctx + '/menu/menuEdit?menuId=' + resp.data.value;
                } else {
                    alert(resp.data.message);
                }
            });
        }

        function editMenu(menu){
            window.location = ctx + '/menu/menuEdit?menuId=' + menu.menuId;
        }

        function deleteMenu(menu) {
            var me = this;
            if(confirm("是否要删除用户！")) {
                me.commonSrv.get('/menu/delete', {menuId: menu.menuId}).then(function(resp) {
                    if (resp.data.status == Constant.AjaxStatus.OK) {
                        search(me);
                    } else {
                        alert(resp.data.message);
                    }
                });
            }
        }

        function search(that){
            var me = that || this;
            me.isButtonDisabled = true;
            me.commonSrv.get('/menu/queryPageList', {menuName: me.searchKey}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.menuList = resp.data.value.list;
                    me.pagerInfo = resp.data.value;
                } else {
                    alert(resp.data.message);
                }
                me.isButtonDisabled = false;
            });
        }

        function quanXian(menu) {
            window.location = ctx + '/permission/list?menuId=' + menu.menuId;
        }
    </script>
</jsSection>
</body>
</html>
