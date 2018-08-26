<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>功能列表</title>
</head>
<body>
<div class="right-header">
    <a href="javascript:void(0)" class="btn m-b-xs w-xs btn-primary" @click="addPermission()"><i class="fa fa-plus"></i>&nbsp;添加</a>
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
        <tr v-for="item in permissionList">
            <th scope="row">
                <a href="javascript:void(0)" v-text="item.name" @click="editPermission(item)">
                </a>
            </th>
            <td v-text="item.pName"></td>
            <td v-text="item.url"></td>
            <td><i :class="item.icon" v-if="item.icon"></i></td>
            <td v-text="item.seq"></td>
            <td class="table-oper">
                <a href="javascript:void(0)" class="fabutton" title="删除用户" @click="deletePermission(item)">
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
        vueData = {permissionList:[],searchKey:'',pagerInfo:null};

        vueMethods = {
            addPermission: addPermission,
            editPermission: editPermission,
            deletePermission: deletePermission,
            search: search,
            quanXian: quanXian
        };

        vueMounted = function (){
            search(this);
        };

        function addPermission(){
            this.commonSrv.get("/comm/getId").then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    window.location = ctx + '/permission/permissionEdit?permissionId=' + resp.data.value;
                } else {
                    alert(resp.data.message);
                }
            });
        }

        function editPermission(permission){
            window.location = ctx + '/permission/permissionEdit?permissionId=' + permission.permissionId;
        }

        function deletePermission(permission) {
            var me = this;
            if(confirm("是否要删除用户！")) {
                me.commonSrv.get('/permission/delete', {permissionId: permission.permissionId}).then(function(resp) {
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
            me.commonSrv.get('/permission/queryPageList', {permissionName: me.searchKey}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.permissionList = resp.data.value.list;
                    me.pagerInfo = resp.data.value;
                } else {
                    alert(resp.data.message);
                }
                me.isButtonDisabled = false;
            });
        }

        function quanXian(permission) {
            window.location = ctx + '/permission/list?permissionId=' + permission.permissionId;
        }
    </script>
</jsSection>
</body>
</html>
