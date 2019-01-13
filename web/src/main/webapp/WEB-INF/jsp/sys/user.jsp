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
            <td v-text="item.isSystem == 'YES' ? '是' : '否'"></td>
            <td class="table-oper">
                <a href="javascript:void(0)" class="fabutton" title="删除用户" @click="deleteUser(item)">
                    <i class="fa fa-times text-danger" v-if="item.isSystem != 'YES'"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>

    <pagination v-bind:pagerInfo="pagerInfo" v-bind:click="testMe"></pagination>
</div>
<script type="text/template" id="pagination">
    <ul class="pagination pagination-sm">
        <li :disabled="pagerInfo.isFirstPage" title="上一页"><a href="javascript:void(0)" @click="goPage(pagerInfo.pageNum - 1)"><i class="fa fa-chevron-left"></i></a></li>
        <li v-if="pagerInfo.pageNum > 2"><a href="javascript:void(0)" v-text="pagerInfo.pageNum - 2" @click="goPage(pagerInfo.pageNum - 2)"></a></li>
        <li v-if="pagerInfo.pageNum > 1"><a href="javascript:void(0)" v-text="pagerInfo.pageNum - 1" @click="goPage(pagerInfo.pageNum - 1)"></a></li>
        <li class='current' disabled="disabled"><a href="javascript:void(0)" v-text="pagerInfo.pageNum"></a></li>
        <li v-if="pagerInfo.pageNum + 1 <= pagerInfo.lastPage"><a href="javascript:void(0)" v-text="pagerInfo.pageNum + 1" @click="goPage(pagerInfo.pageNum + 1)"></a></li>
        <li v-if="pagerInfo.pageNum + 2 <= pagerInfo.lastPage"><a href="javascript:void(0)" v-text="pagerInfo.pageNum + 2" @click="goPage(pagerInfo.pageNum + 2)"></a></li>
        <li :disabled="pagerInfo.isLastPage" title="下一页"><a href="javascript:void(0)"><i class="fa fa-chevron-right" @click="goPage(pagerInfo.pageNum + 1)"></i></a></li>
    </ul>
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
            this.commonSrv.get("/comm/getNewId").then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    window.location = ctx + '/user/userEditPage?id=' + resp.data.value;
                } else {
                    alert(resp.data.message);
                }
            });
        }

        function editUser(user){
            window.location = ctx + '/user/userEditPage?id=' + user.userId;
        }

        function deleteUser(user) {
            var me = this;
            if(confirm("是否要删除用户！")) {
                me.commonSrv.get('/user/delete', {userId: user.userId}).then(function(resp) {
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
            me.commonSrv.get('/user/queryPageList', {userName: me.searchKey}).then(function(resp) {
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
