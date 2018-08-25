<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增/编辑功能</title>
    <link href="${ctx}/bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="panel panel-default" style="margin-top:20px;">
                <div class="panel-heading font-bold">功能信息</div>
                <div class="panel-body">
                    <form class="bs-example form-horizontal">
                        <input type="hidden" name="menuId" v-model="menuInfo.menuId" />
                        <div class="form-group">
                            <label class="col-lg-2 col-md-3 col-sm-4 col-sm-4 control-label">名称</label>
                            <div class="col-lg-10 col-md-9 col-sm-8">
                                <input class="form-control" type="text" name="name" v-model="menuInfo.name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 col-md-3 col-sm-4 col-sm-4 control-label">序号</label>
                            <div class="col-lg-10 col-md-9 col-sm-8">
                                <input class="form-control" type="text" name="seq" v-model="menuInfo.seq" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 col-md-3 col-sm-4 col-sm-4 control-label">父级</label>
                            <div class="col-lg-10 col-md-9 col-sm-8">
                                <select class="form-control" v-model="menuInfo.pId">
                                    <option value="">--请选择--</option>
                                    <option v-for="item in parentList" :value="item.menuId" v-text="item.name"></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 col-md-3 col-sm-4 col-sm-4 control-label">页面地址</label>
                            <div class="col-lg-10 col-md-9 col-sm-8">
                                <input class="form-control" type="text" name="url" v-model="menuInfo.url" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 col-md-3 col-sm-4 col-sm-4 control-label">图标</label>
                            <div class="col-lg-10 col-md-9 col-sm-8">
                                <input class="form-control" type="text" name="icon" v-model="menuInfo.icon" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-offset-2 col-md-offset-3 col-sm-offset-4 col-lg-10 col-md-9 col-sm-8">
                                <input class="btn btn-sm btn-info" type="button" value="保存" @click="saveMenu()"
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
    <script src="${ctx}/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script src="${ctx}/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script>
        vueData = {
            menuId: '<c:out value="${menuId}" />',
            menuInfo: {menuId: null},
            sexList: [{key:'Male',name:'男'},{key:'Female',name:'女'}],
            isDisabledList: [{key:'YES',name:'是'},{key:'NO',name:'否'}],
            isSystemList: [{key:'YES',name:'是'},{key:'NO',name:'否'}],
            isButtonDisabled: true,
            parentList:[]
        };

        vueMethods = {
            saveMenu: saveMenu
        };

        vueMounted = function (){
            var me = this;
            me.isButtonDisabled = true;
            this.commonSrv.get('/menu/getMenuInfo', {menuId:this.menuId}).then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.menuInfo = resp.data.value;
                } else {
                    alert(resp.data.message);
                }
                me.isButtonDisabled = false;
            });
            this.commonSrv.get('/menu/queryParentList').then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    me.parentList = resp.data.value;
                } else {
                    alert(resp.data.message);
                }
            });
        };

        function saveMenu(){
            this.commonSrv.post("/menu/saveMenu", this.menuInfo).then(function(resp) {
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
