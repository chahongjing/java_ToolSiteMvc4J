<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<div class="right-header">
    <a href="javascript:void(0)" class="btn m-b-xs w-xs btn-primary" @click="addUser()"><i class="fa fa-plus"></i>&nbsp;添加</a>
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
            <th scope="col">姓名</th>
            <th scope="col">编号</th>
            <th scope="col">性别</th>
            <th scope="col">是否系统用户</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in userList">
            <th scope="row">
                <a href="javascript:void(0)" v-text="item.userName" @click="editUser(item)">
                </a>
            </th>
            <td v-text="item.userCode"></td>
            <td v-text="item.sexName"></td>
            <td v-text="item.isSystem ? '是' : '否'"></td>
            <td class="table-oper">
                <a href="javascript:void(0)" class="fabutton" title="删除用户" @click="deleteUser(item)">
                    <i class="fa fa-times text-danger" v-if="!item.isSystem"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
    <pagination v-bind:pagerInfo="pagerInfo" v-bind:click="testMe"></pagination>
</div>
<div>
    sdf
</div>
<script type="text/template" id="pagination">
    <div class="pager">
        <ul class="pagination">
            <li class="page-item" v-for="item in list" @click="goPage(item)" :class="getClass(item)">
                <a class="page-link" href="#" v-text="item.name"></a>
            </li>
        </ul>
    </div>
</script>
<jsSection>
    <script>
        vueData = {userList:[],searchKey:'',pagerInfo:null};

        vueMethods = {
            addUser: addUser,
            editUser: editUser,
            deleteUser: deleteUser,
            search: search,
            testMe: testMe
        };

        vueMounted = function (){
            search(this);
        };

        function addUser(){
            this.commonSrv.get("/comm/getId").then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    window.location = ctx + '/userinfo/userEdit.do?userGuid=' + resp.data.value;
                } else {
                    alert(resp.data.message);
                }
            });
        }

        function editUser(user){
            window.location = ctx + '/userinfo/userEdit.do?userGuid=' + user.userGuid;
        }

        function deleteUser(user) {
            var me = this;
            if(confirm("是否要删除用户！")) {
                me.commonSrv.get('/userinfo/delete', {userGuid: user.userGuid}).then(function(resp) {
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
            me.commonSrv.get('/userinfo/queryPageList', {userName: me.searchKey}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.userList = resp.data.value.list;
                    me.pagerInfo = resp.data.value;
                } else {
                    alert(resp.data.message);
                }
                me.isButtonDisabled = false;
            });
        }

        function testMe(s) {
            console.log(s);
        }
    </script>
</jsSection>
</body>
</html>
