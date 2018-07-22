<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增/编辑用户</title>
</head>
<body>
{{userInfo}}
<form>
    <input type="hidden" name="userGuid" v-model="userInfo.userGuid" />
    编号<input type="text" name="userCode" v-model="userInfo.userCode" /><br/>
    姓名<input type="text" name="userName" v-model="userInfo.userName" /><br/>
    密码<input type="password" name="password" v-model="userInfo.password" /><br/>
    性别
    <label class="radio_checkbox" v-for="item in sexList">
        <input type='radio' name="sex" :value="item.key" v-model="userInfo.sex"/>
        <i></i>
        <span v-text="item.name"></span>
    </label><br />
    年龄<input type="text" name="age" v-model="userInfo.age" /><br/>
    生日<input type="text" name="birthday" v-model="userInfo.birthday" /><br/>
    头像<input type="text" name="photo" v-model="userInfo.photo" /><br/>
    是否禁用
    <label class="radio_checkbox" v-for="item in isDisabledList">
        <input type='radio' name="isDisabled" :value="item.key" v-model="userInfo.isDisabled"/>
        <i></i>
        <span v-text="item.name"></span>
    </label>
    <br/><br />
    <input type="button" value="保存" @click="saveUser()" :disabled="isButtonDisabled" />
</form>

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
            this.commonSrv.get('/userinfo/getUserInfo', {userGuid:this.userGuid}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.userInfo = resp.data.value;
                    me.isButtonDisabled = false;
                } else {
                    alert(resp.data.message);
                }
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
