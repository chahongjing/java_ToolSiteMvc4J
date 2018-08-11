<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<div>
    <input type="button" value="添加" @click="addUser()"/>
</div>
<div>
    <nav class="navbar navbar-light bg-light">
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
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in userList">
            <th scope="row" v-text="item.userName"></th>
            <td v-text="item.userCode"></td>
            <td v-text="item.sex"></td>
            <td v-text="item.isSystem"></td>
        </tr>
        </tbody>
    </table>
</div>
<div>
    sdf
</div>
<jsSection>
    <script>
        vueData = {userList:[],searchKey:''};

        vueMethods = {
            addUser: addUser,
            search: search
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

        function search(that){
            var me = that || this;
            me.isButtonDisabled = true;
            me.commonSrv.get('/userinfo/queryPageList', {userName: me.searchKey}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.userList = resp.data.value.list;
                } else {
                    alert(resp.data.message);
                }
                me.isButtonDisabled = false;
            });
        }
    </script>
</jsSection>
</body>
</html>
