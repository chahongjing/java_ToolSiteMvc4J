<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<title>sql生成器</title>
<link href="${ctx}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link href="${ctx}/bootstrap/css/font-awesome.css" rel="stylesheet"/>
<link href="${ctx}/bootstrap/css/main.css" rel="stylesheet"/>
<style>
    .mt20 {
        margin-top: 20px;
    }

    .ml15 {
        margin-left: 15px;
    }

    .mr15 {
        margin-right: 15px;
    }
    .w100p{width:100%;}

    .form-group > div,.form-group > label{
        margin-top:10px;
    }
    .background-readonly{background-color:#eaeaea;}
</style>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div class='right-content' data-ng-controller="sqlGenerate" data-ng-init="init()">
    <div class='content mt20'>
        <div class="row ml15 mr15">
            <div class="row no-margin-right no-padding-right  ml15 mr15">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control">
                            数据库类型
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4 miniGroup">
                            <span>
                                <select class="form-control" data-ng-options="item.value as item.name for item in model.dbTypes"
                                        data-ng-model="model.selectedDbType">
                                </select>
                            </span>
                        </div>
                        <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control">
                            操作类型
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4 miniGroup">
                            <span>
                                <select class="form-control" data-ng-options="item.value as item.name for item in model.operatorTypes"
                                        data-ng-model="model.operatorType">
                                </select>
                            </span>
                        </div>
                        <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control">
                            对象类型
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4 miniGroup">
                            <span>
                                <select class="form-control" data-ng-options="item.value as item.name for item in model.subjects"
                                        data-ng-model="model.selectedSubject">
                                </select>
                            </span>
                        </div>
                        <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control" data-ng-bind="model.subjectName">
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4 miniGroup">
                            <span>
                                <input class="form-control" type="text" data-ng-model="model.firstObj" onfocus="this.select();"
                                       placeholder="{{model.subjectName}}"/>
                            </span>
                        </div>
                        <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control">
                            字段类型
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4 miniGroup">
                            <span>
                                <select class="form-control" data-ng-options="item.value as item.name for item in model.fieldTypes"
                                        data-ng-model="model.selectedFieldType">
                                </select>
                            </span>
                        </div>
                         <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control">
                             字段名
                         </label>
                         <div class="col-md-4 col-sm-4 col-xs-4 miniGroup" >
                            <span>
                                <input class="form-control" type="text" data-ng-model="model.secondObj" onfocus="this.select();"
                                       placeholder="字段名"/>
                            </span>
                        </div>
                        <label class="col-md-2 col-sm-2 col-xs-2 control-label no-padding-right" class="form-control">
                            备注
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4 miniGroup">
                            <span>
                                <input class="form-control" type="text" data-ng-model="model.remark" onfocus="this.select();"
                                       placeholder=""/>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="btn-group">
                            <button type="button" class="btn btn-info" data-ng-click="getResult()">
                                <i class="fa fa-hand-o-down"></i>获取Sql
                            </button>
                        </div>
                        <div class="col-md-4 col-sm-4 col-xs-4">
                        </div>
                    </div>
                </form>
            </div>

            <div class="space-12">
            </div>
            <div class="row mt20">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <textarea class="txt w100p background-readonly" rows="20" cols="150" data-ng-model="model.result"
                              style="overflow-x: hidden" placeholder="输入信息后得出结果" readonly="readonly"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<script src="/ToolSiteMvc4J/js/angular/angular.js"></script>
<script src="/ToolSiteMvc4J/js/angular/angular_main.js"></script>
<script src="/ToolSiteMvc4J/js/angular/directives/ng_repeat_finish.js"></script>
<script>
    app.controller('sqlGenerate', ['$scope', '$http', '$timeout', '$q', '$templateCache',
        function ($scope, $http, $timeout, $q, $templateCache) {
            $scope.model = {
                author: 'jyzeng',
                selectedDbType: 'oracle',
                operatorType: 'add',
                selectedSubject: 'field',
                selectedFieldType: 'VARCHAR(XXX)',
                subjectName: '表名',
                firstObj: '',
                secondObj: '',
                remark: '',
                result: ''
            };

            $scope.init = function() {
                $scope.model.dbTypes = getAllDbType();
                $scope.model.operatorTypes = getAllOperatorTypes();
                $scope.model.subjects = getAllSubject();
                $scope.model.fieldTypes = getSqlServerFieldTypes();
            }

            $scope.getResult = function() {
                var result;
                switch($scope.model.selectedSubject) {
                    case 'table':
                        result = getTableSql();
                        break;
                    case 'field':
                        result = getFieldSql();
                        break;
                    case 'proc':
                        result = getProcSql();
                        break;
                    case 'function':
                        result = getFunctionSql();
                        break;
                    case 'view':
                        result = getViewSql();
                        break;
                    case 'trigger':
                        result = getTriggerSql();
                        break;
                    case 'seq':
                        result = getSeqSql();
                        break;
                    default:
                        result = '';
                        break;
                }

                result = result || '';
                result = result.replace(/\{author\}/g, $scope.model.author).replace(/\{datatime\}/g, new Date().format('yyyy-MM-dd HH:mm:ss'))
                        .replace(/\{firstName\}/g, $scope.model.firstObj).replace(/\{secondName\}/g, $scope.model.secondObj)
                        .replace(/\{remark\}/g, $scope.model.remark || '请填写备注说明');
                $scope.model.result = result.trim();
            }

            function getAllDbType() {
                var dbTypes = [];
                dbTypes.push({value: 'oracle', name: 'Oracle'});
                dbTypes.push({value: 'mysql', name: 'mysql'});
                dbTypes.push({value: 'sqlserver', name: 'Sql Server'});
                return dbTypes;
            }

            function getAllOperatorTypes() {
                var operatorTypes = [];
                operatorTypes.push({value: 'add', name: '新增'});
                operatorTypes.push({value: 'alter', name: '修改'});
                operatorTypes.push({value: 'delete', name: '删除'});
                return operatorTypes;
            }

            function getAllSubject() {
                var subjects = [];
                subjects.push({value: 'table', name: '表'});
                subjects.push({value: 'field', name: '字段'});
                subjects.push({value: 'proc', name: '存储过程'});
                subjects.push({value: 'function', name: '函数'});
                subjects.push({value: 'view', name: '视图'});
                subjects.push({value: 'trigger', name: '触发器'});
                subjects.push({value: 'seq', name: '序列'});
                return subjects;
            }

            function getSqlServerFieldTypes() {
                var fieldTypes = [];
                fieldTypes.push({value:'BIGINT(XXX)',name:'BIGINT'});
                fieldTypes.push({value:'BINARY(XXX)',name:'BINARY'});
                fieldTypes.push({value:'BIT(XXX)',name:'BIT'});
                fieldTypes.push({value:'CHAR(XXX)',name:'CHAR'});
                fieldTypes.push({value:'DATE(XXX)',name:'DATE'});
                fieldTypes.push({value:'DATETIME(XXX)',name:'DATETIME'});
                fieldTypes.push({value:'DATETIME2(XXX)',name:'DATETIME2'});
                fieldTypes.push({value:'DATETIMEOFFSET(XXX)',name:'DATETIMEOFFSET'});
                fieldTypes.push({value:'DECIMAL(XXX)',name:'DECIMAL'});
                fieldTypes.push({value:'FLOAT(XXX)',name:'FLOAT'});
                fieldTypes.push({value:'IMAGE(XXX)',name:'IMAGE'});
                fieldTypes.push({value:'INT(XXX)',name:'INT'});
                fieldTypes.push({value:'MONEY(XXX)',name:'MONEY'});
                fieldTypes.push({value:'NCHAR(XXX)',name:'NCHAR'});
                fieldTypes.push({value:'NTEXT(XXX)',name:'NTEXT'});
                fieldTypes.push({value:'NUMERIC(XXX)',name:'NUMERIC'});
                fieldTypes.push({value:'NVARCHAR(XXX)',name:'NVARCHAR'});
                fieldTypes.push({value:'REAL(XXX)',name:'REAL'});
                fieldTypes.push({value:'ROWVERSION(XXX)',name:'ROWVERSION'});
                fieldTypes.push({value:'SMALLDATETIME(XXX)',name:'SMALLDATETIME'});
                fieldTypes.push({value:'SMALLINT(XXX)',name:'SMALLINT'});
                fieldTypes.push({value:'SMALLMONEY(XXX)',name:'SMALLMONEY'});
                fieldTypes.push({value:'TEXT(XXX)',name:'TEXT'});
                fieldTypes.push({value:'TIME(XXX)',name:'TIME'});
                fieldTypes.push({value:'TIMESTAMP(XXX)',name:'TIMESTAMP'});
                fieldTypes.push({value:'TINYINT(XXX)',name:'TINYINT'});
                fieldTypes.push({value:'UNIQUEIDENTIFIER(XXX)',name:'UNIQUEIDENTIFIER'});
                fieldTypes.push({value:'VARBINARY(XXX)',name:'VARBINARY'});
                fieldTypes.push({value:'VARCHAR(XXX)',name:'VARCHAR'});
                fieldTypes.push({value:'XML(XXX)',name:'XML'});
                fieldTypes.push({value:'SQL_VARIANT(XXX)',name:'SQL_VARIANT'});
                return fieldTypes;
            }

            function getTableSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleTableSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverTableSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleTableSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('oracleAddTable.html');
                    result = result.replace(/\{functionName\}/g, '创建表 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                    throw new Error('unimplement error！');
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getSqlserverTableSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('sqlserverAddTable.html');
                    result = result.replace(/\{functionName\}/g, '创建表 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                    throw new Error('unimplement error！');
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getFieldSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleFieldSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverFieldSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleFieldSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('oracleAddField.html');
                    result = result.replace(/\{functionName\}/g, '添加字段 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj + '.' + $scope.model.secondObj);
                } else if($scope.model.operatorType == 'alter') {
                    result = $templateCache.get('oracleAlterField.html');
                    result = result.replace(/\{functionName\}/g, '修改字段 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj + '.' + $scope.model.secondObj);
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getSqlserverFieldSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('sqlserverAddField.html');
                    result = result.replace(/\{functionName\}/g, '添加字段 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj + '.' + $scope.model.secondObj);
                } else if($scope.model.operatorType == 'alter') {
                    result = $templateCache.get('sqlserverAlterField.html');
                    result = result.replace(/\{functionName\}/g, "修改字段 " + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj + '.' + $scope.model.secondObj);
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getProcSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleProcSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverProcSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleProcSql() {
                var result;
                if($scope.model.operatorType == 'add' || $scope.model.operatorType == 'alter') {
                    result = $templateCache.get('oracleAddProc.html');
                    result = result.replace(/\{functionName\}/g, '创建/修改存储过程 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj).
                    replace(/\{secondName\}/g, $scope.model.secondObj);
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getSqlserverProcSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('sqlserverAddProc.html');
                    result = result.replace(/\{functionName\}/g, '创建存储过程 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                    throw new Error('unimplement error！');
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getFunctionSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleFunctionSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverFunctionSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleFunctionSql() {
                throw new Error('unimplement error！');
            }

            function getSqlserverFunctionSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('sqlserverAddFunction.html');
                    result = result.replace(/\{functionName\}/g, '创建函数 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getViewSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleViewSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverViewSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleViewSql() {
                throw new Error('unimplement error！');
            }

            function getSqlserverViewSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('sqlserverAddView.html');
                    result = result.replace(/\{functionName\}/g, '创建视图 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getTriggerSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleTriggerSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverTriggerSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleTriggerSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('oracleAddTrigger.html');
                    result = result.replace(/\{functionName\}/g, '创建触发器 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getSqlserverTriggerSql() {
                throw new Error('unimplement error！');
            }

            function getSeqSql() {
                if($scope.model.selectedDbType == 'oracle') {
                    return getOracleSeqSql();
                } else if($scope.model.selectedDbType == 'sqlserver') {
                    return getSqlserverSeqSql();
                } else if($scope.model.selectedDbType == 'mysql') {
                    throw new Error('unimplement error！');
                }
            }

            function getOracleSeqSql() {
                var result;
                if($scope.model.operatorType == 'add') {
                    result = $templateCache.get('oracleAddSeq.html');
                    result = result.replace(/\{functionName\}/g, '创建序列 ' + ($scope.model.remark || '请填写备注说明') + '：' + $scope.model.firstObj);
                } else if($scope.model.operatorType == 'alter') {
                    throw new Error('unimplement error！');
                } else if($scope.model.operatorType == 'delete') {
                    throw new Error('unimplement error！');
                } else {
                    throw new Error('unimplement error！');
                }
                return result;
            }

            function getSqlserverSeqSql() {
                throw new Error('unimplement error！');
            }
        }
    ]);
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>

<script type="text/ng-template" id="oracleAddTable.html">
-- {author} {datatime} {functionName}
DECLARE
  num NUMBER;
  tableName VARCHAR2(100);
BEGIN
  tableName := '{firstName}';
  SELECT COUNT(1) INTO num FROM USER_TABLES WHERE UPPER(TABLE_NAME) = UPPER(tableName) ;
  IF num = 0 THEN
    EXECUTE IMMEDIATE '
    CREATE TABLE {firstName}(
      id INT PRIMARY KEY
    )
    ';
  END IF;
END;
/
COMMENT ON TABLE {firstName} IS {remark};
COMMENT ON COLUMN {firstName}.id is '主键';
/


-- {author} {datatime} {functionName}
DECLARE
  num NUMBER;
  tableName VARCHAR2(100);
BEGIN
  tableName := '{firstName}';
  SELECT COUNT(1) INTO num FROM USER_TABLES WHERE UPPER(TABLE_NAME) = UPPER(tableName) ;
  IF num > 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE ' || tableName;
  END IF;
END;
/
CREATE TABLE {firstName}
(
  id INT PRIMARY KEY
);
/
COMMENT ON TABLE {firstName} IS '{remark}';
COMMENT ON COLUMN {firstName}.id is '主键';
/
</script>
<script type="text/ng-template" id="sqlserverAddTable.html">
-- {author} {datatime} {functionName}
IF(OBJECT_ID('{firstName}', 'U')IS NULL)
BEGIN
CREATE TABLE [DBO].[{firstName}]
(
    ID INT IDENTITY(1, 1) PRIMARY KEY,
    FieldName UNIQUEIDENTIFIER NOT NULL,
    FieldName NVARCHAR(50),
    FieldName INT,
    FieldName DECIMAL(18, 2),
    FieldName DATETIME,
    FieldName MONEY
);
EXEC sys.sp_addextendedproperty @name = N'MS_Description', @value = '{remark}', @level0type = N'SCHEMA', @level0name = N'dbo',
@level1type = N'TABLE', @level1name = '{firstName}'

EXEC sys.sp_addextendedproperty @name = N'MS_Description', @value = '主键', @level0type = N'SCHEMA', @level0name = N'dbo',
@level1type = N'TABLE', @level1name = '{firstName}', @level2type = N'COLUMN', @level2name = 'ID'
END
GO
</script>
<script type="text/ng-template" id="oracleAddProc.html">
    -- {author} {datatime} {functionName}
    CREATE OR REPLACE PROCEDURE {secondName}
    (
    ret OUT SYS_REFCURSOR,

    biaoMing in varchar2,
    lieMing in varchar2,
    zhuTi in varchar2,
    XuHao out int
    )AS
    TYPE ref_cursor_type IS REF CURSOR;
    myrow SYS_USER%rowType;
    --cursor ret is select * from SYS_USER;
    mc ref_cursor_type;
    msql varchar2(200) := 'select * from SYS_USER where rownum < 2';

    newXuHao int;
    BEGIN
    OPEN ret FOR SELECT * FROM sys_user WHERE ROWNUM < 5;
    FETCH ret INTO myrow;
    WHILE(ret%found)
    LOOP
    DBMS_OUTPUT.PUT_LINE(myrow.username);
    FETCH ret INTO myrow ;
    END LOOP;
    CLOSE ret;
    DBMS_OUTPUT.PUT_LINE('--------');
    OPEN mc FOR msql;
    FETCH mc INTO myrow;
    WHILE(mc%found)
    LOOP
    DBMS_OUTPUT.PUT_LINE(myrow.username);
    FETCH mc INTO myrow ;
    END LOOP;
    CLOSE mc;
    EXECUTE IMMEDIATE msql
    INTO myrow;
    DBMS_OUTPUT.PUT_LINE('--------');
    DBMS_OUTPUT.PUT_LINE(myrow.username);


    SELECT XuHao INTO newXuHao FROM Xt_XuHao WHERE BiaoMing = biaoMing AND LieMing = lieMing AND XuHaoZhuTi = zhuTi;
    DBMS_OUTPUT.PUT_LINE('----newXuHao----');
    DBMS_OUTPUT.PUT_LINE(newXuHao);
    IF(newXuHao IS NULL)THEN
    newXuHao := 1;
    INSERT INTO Xt_XuHao(BiaoMing, LieMing, XuHaoZhuTi, XuHao) VALUES (biaoMing, lieMing, zhuTi, newXuHao);
    ELSE
    newXuHao := newXuHao + 1;
    UPDATE Xt_XuHao SET XuHao = newXuHao WHERE BiaoMing = biaoMing AND LieMing = lieMing AND XuHaoZhuTi = zhuTi;
    END IF;
    XuHao := newXuHao;
    DBMS_OUTPUT.PUT_LINE('----newXuHao----');
    DBMS_OUTPUT.PUT_LINE(newXuHao);
    END;
    /
    SET SERVEROUTPUT ON;
    DECLARE
    ret SYS_REFCURSOR;
    BEGIN
    {secondName}(ret);
    END;
</script>
<script type="text/ng-template" id="sqlserverAddProc.html">
-- {author} {datatime} {functionName}
IF(OBJECT_ID('{firstName}', 'P')IS NOT NULL)
BEGIN
  DROP PROC [DBO].[{firstName}];
END
GO
CREATE PROC [DBO].[{firstName}]
AS
BEGIN

END
-- EXEC [DBO].[{firstName}]
GO
</script>
<script type="text/ng-template" id="sqlserverAddFunction.html">
-- {author} {datatime} {functionName}
IF(OBJECT_ID('{firstName}')IS NOT NULL)
BEGIN
  DROP FUNCTION [DBO].[{firstName}];
END
GO
CREATE FUNCTION [DBO].[{firstName}]
()
RETURNS TABLE
AS
BEGIN

END
-- SELECT * FROM [DBO].[{firstName}]
GO
</script>
<script type="text/ng-template" id="sqlserverAddView.html">
-- {author} {datatime} {functionName}
IF(OBJECT_ID('{firstName}', 'V')IS NOT NULL)
BEGIN
  DROP VIEW [DBO].[{firstName}];
END
GO
CREATE VIEW [DBO].[{firstName}]
AS

-- SELECT * FROM [DBO].[{firstName}];
GO
</script>
<script type="text/ng-template" id="oracleAddField.html">
-- {author} {datatime} {functionName}
DECLARE
  num NUMBER;
  tableName VARCHAR2(100);
  fieldName VARCHAR2(100);
BEGIN
  tableName := '{firstName}';
  fieldName := '{secondName}';
  SELECT COUNT(1) INTO num FROM COLS
   WHERE UPPER(TABLE_NAME) = UPPER(tableName)
     AND UPPER(COLUMN_NAME) = UPPER(fieldName);
  IF num = 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE {firstName} ADD({secondName} VARCHAR2(100))';
  END IF;
END;
/
COMMENT ON COLUMN {firstName}.{secondName} is '{remark}';


-- {author} {datatime} {functionName}
DECLARE
  num NUMBER;
  tableName VARCHAR2(100);
  fieldName VARCHAR2(100);
BEGIN
  tableName := '{firstName}';
  fieldName := '{secondName}';
  SELECT COUNT(1) INTO num FROM COLS
   WHERE UPPER(TABLE_NAME) = UPPER(tableName)
    AND UPPER(COLUMN_NAME) = UPPER(fieldName);
  IF num > 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE ' || tableName || ' DROP COLUMN ' || fieldName;
  END IF;
END;
/
ALTER TABLE {firstName} ADD({secondName} VARCHAR2(100));
COMMENT ON COLUMN {firstName}.{secondName} is '{remark}';
/
</script>
<script type="text/ng-template" id="oracleAlterField.html">
    -- {author} {datatime} {functionName}
DECLARE
  num NUMBER;
  tableName VARCHAR2(100);
  fieldName VARCHAR2(100);
BEGIN
  tableName := 'aaaaa';
  fieldName := 'ccccc';
  SELECT COUNT(1) INTO num FROM COLS
   WHERE UPPER(TABLE_NAME) = UPPER(tableName)
    AND UPPER(COLUMN_NAME) = UPPER(fieldName);
  IF num > 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE ' || tableName || ' MODIFY(' || fieldName || ' VARCHAR2(XXX))';
    -- EXECUTE IMMEDIATE 'ALTER TABLE ' || tableName || ' DROP(' || fieldName || ')';
  END IF;
END;
/
</script>
<script type="text/ng-template" id="oracleDropField.html">
</script>
<script type="text/ng-template" id="sqlserverAddField.html">
-- {author} {datatime} {functionName}
IF(COL_LENGTH('{firstName}', '{secondName}') IS NULL)
BEGIN
  ALTER TABLE [DBO].[{firstName}]
    ADD [{secondName}] NVARCHAR(XXX);
  EXEC sys.sp_addextendedproperty @name = N'MS_Description', @value = '{remark}', @level0type = N'SCHEMA', @level0name = N'dbo',
  @level1type = N'TABLE', @level1name = '{firstName}', @level2type = N'COLUMN', @level2name = '{secondName}'
END
GO
</script>
<script type="text/ng-template" id="sqlserverAlterField.html">
-- {author} {datatime} {functionName}
IF(COL_LENGTH('{firstName}', '{secondName}') IS NOT NULL)
BEGIN
  ALTER TABLE [DBO].[{firstName}]
  ALTER COLUMN [{secondName}] MONEY;
  EXEC sys.sp_updateextendedproperty @name = N'MS_Description', @value = @Reference, @level0type = N'SCHEMA', @level0name = N'dbo',
  @level1type = N'TABLE', @level1name = '{firstName}', @level2type = N'COLUMN', @level2name = '{secondName}'

-- {author} {datatime} {functionName}
ALTER TABLE [DBO].[{firstName}]
DROP COLUMN [{secondName}];
END
GO
</script>
<script type="text/ng-template" id="sqlserverDropField.html">
</script>
<script type="text/ng-template" id="oracleAddSeq.html">
-- {author} {datatime} {functionName}
DECLARE
  num NUMBER;
  seqName VARCHAR2(100);
BEGIN
  seqName := '{firstName}';
  SELECT COUNT(1) INTO num FROM USER_SEQUENCES WHERE UPPER(SEQUENCE_NAME) = UPPER(seqName) ;
  IF num = 0 THEN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE {firstName}
    MINVALUE 1
    NOMAXVALUE
    START WITH 1
    INCREMENT BY 1
    NOCYCLE   -- 一直累加，不循环
    --NOCACHE -- 不缓存
    CACHE 10  -- 缓存10条
    ';
  END IF;
END;
/
</script>
<script type="text/ng-template" id="oracleAddTrigger.html">
-- {author} {datatime} {functionName}
CREATE OR REPLACE TRIGGER {firstName}
BEFORE INSERT ON {secondName}
FOR EACH ROW
BEGIN
  SELECT 序列名称.NEXTVAL INTO:NEW.字段名 FROM SYS.DUAL;
END;
</script>