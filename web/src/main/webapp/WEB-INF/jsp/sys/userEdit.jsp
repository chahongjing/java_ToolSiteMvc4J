<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增/编辑用户</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="panel panel-default" style="margin-top:20px;">
                <div class="panel-heading font-bold">Horizontal form</div>
                <div class="panel-body">
                    <form class="bs-example form-horizontal">
                        <input type="hidden" name="userGuid" v-model="userInfo.userGuid" />
                        <div class="form-group">
                            <label class="col-lg-2 control-label">编号</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="userCode" v-model="userInfo.userCode" />
                                <span class="help-block m-b-none">Example block-level help text here.</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">姓名</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="userName" v-model="userInfo.userName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">密码</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="password" name="password" v-model="userInfo.password" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">性别</label>
                            <div class="col-lg-10">
                                <label class="radio_checkbox" v-for="item in sexList">
                                    <input type='radio' name="sex" :value="item.key" v-model="userInfo.sex"/>
                                    <i></i>
                                    <span v-text="item.name"></span>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">年龄</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="age" v-model="userInfo.age" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">生日</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="birthday" v-model="userInfo.birthday" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">头像</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="photo" v-model="userInfo.photo" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">是否禁用</label>
                            <div class="col-lg-10">
                                <label class="radio_checkbox" v-for="item in isDisabledList">
                                    <input type='radio' name="isDisabled" :value="item.key" v-model="userInfo.isDisabled"/>
                                    <i></i>
                                    <span v-text="item.name"></span>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <input class="btn btn-sm btn-info" type="button" value="保存" @click="saveUser()"
                                       :disabled="isButtonDisabled" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsSection>
    <script>
        vueData = {
            userGuid: '<c:out value="${userGuid}" />',
            userInfo: {userGuid: null},
            sexList: [{key:'Male',name:'男'},{key:'Female',name:'女'}],
            isDisabledList: [{key:1,name:'是'},{key:0,name:'否'}],
            isButtonDisabled: true
        };

        vueMethods = {
            saveUser: saveUser
        };

        vueMounted = function (){
            var me = this;
            me.isButtonDisabled = true;
            this.commonSrv.get('/userinfo/getUserInfo', {userGuid:this.userGuid}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.userInfo = resp.data.value;
                } else {
                    alert(resp.data.message);
                }
                me.isButtonDisabled = false;
            });
        };

        function saveUser(){
            this.commonSrv.post("/userinfo/saveUser", this.userInfo).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    window.location.reload();
                } else {
                    alert(resp.data.message);
                }
            });
        }
    </script>
</jsSection>
</body>
</html>
