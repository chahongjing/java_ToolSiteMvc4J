webpackJsonp([15],{"/dsb":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("Ymiw"),i={name:"permissionList",data:function(){return{allDisabled:!0,searchKey:null,functionId:null,list:[],pager:{pageNum:1,pageSize:10,loading:!0}}},methods:{add:function(){var t=this;this.$axios.get("/comm/getNewId").then(function(e){e.data.status==ResultStatus.OK.key&&t.$router.push({path:"/admin/permissionEdit",query:{id:e.data.value,functionId:t.functionId}})})},edit:function(t){this.$router.push({path:"/admin/permissionEdit",query:{id:t.permissionId}})},search:function(){var t=this;t.pager.loading=!0,t.allDisabled=!0,this.$axios.get("/permission/queryPageList",{name:this.searchKey,functionId:this.functionId,pageNum:this.pager.pageNum,pageSize:this.pager.pageSize}).then(function(e){e.data.status==ResultStatus.OK.key&&(t.list=e.data.value.list,t.pager=a.a.getPagerInfo(e.data.value,t.goPage)),t.allDisabled=!1})},goPage:function(t){this.pager.pageNum=t,this.search()},deleteItem:function(t){var e=this;this.$confirm.confirm("确定要删除权限吗？",function(){e.$axios.get("/permission/delete",{id:t.permissionId}).then(function(t){t.data.status==ResultStatus.OK.key&&(e.$toaster.success("删除成功！"),e.search())})})}},mounted:function(){this.functionId=this.$route.query.functionId,this.search()}},n={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"maincontent listcontent"},[s("div",{staticClass:"list-header-but-group"},[s("button",{directives:[{name:"authcode",rawName:"v-authcode",value:"permissionList_add",expression:'"permissionList_add"'}],staticClass:"btn btn-outline-purple",attrs:{type:"button inline-block"},on:{click:function(e){t.add()}}},[s("i",{staticClass:"fa fa-plus mr5"}),t._v("添加\n    ")])]),t._v(" "),s("div",{staticClass:"searchbar"},[s("form",{staticClass:"myform form-inline form-group-w250 form-label-w80"},[s("div",{staticClass:"form-group"},[s("label",{staticClass:"form-label"},[t._v("名称：")]),t._v(" "),s("div",{staticClass:"form-content"},[s("input",{directives:[{name:"model",rawName:"v-model",value:t.searchKey,expression:"searchKey"}],staticClass:"form-control",attrs:{type:"text",placeholder:"名称",autofocus:""},domProps:{value:t.searchKey},on:{input:function(e){e.target.composing||(t.searchKey=e.target.value)}}})])]),t._v(" "),s("div",{staticClass:"form-group"},[s("button",{staticClass:"btn btn-purple ml20",attrs:{type:"button",disabled:t.allDisabled},on:{click:function(e){t.search()}}},[s("i",{staticClass:"fa fa-search mr5"}),t._v("搜索\n        ")])])])]),t._v(" "),s("div",{staticClass:"table-list"},[s("table",{staticClass:"table table-hover"},[t._m(0),t._v(" "),t.pager.loading?t._e():s("tbody",t._l(t.list,function(e,a){return s("tr",[s("td",{staticClass:"text-center",domProps:{textContent:t._s(a+1)}}),t._v(" "),s("td",[s("a",{staticClass:"block w100p h100p",attrs:{href:"javascript:void(0)"},domProps:{textContent:t._s(e.name)},on:{click:function(s){t.edit(e)}}})]),t._v(" "),s("td",{domProps:{textContent:t._s(e.functionName)}}),t._v(" "),s("td",{directives:[{name:"tooltip",rawName:"v-tooltip",value:e.code,expression:"item.code"}],domProps:{textContent:t._s(e.code)}}),t._v(" "),s("td",{staticClass:"text-center",domProps:{textContent:t._s(e.seq)}}),t._v(" "),s("td",{staticClass:"operate"},[s("a",{directives:[{name:"authcode",rawName:"v-authcode",value:"permissionList_delete",expression:'"permissionList_delete"'}],staticClass:"inline-block mybtn",attrs:{href:"javascript:void(0)",title:"删除"},on:{click:function(s){t.deleteItem(e)}}},[s("i",{staticClass:"fa fa-trash cf05"})])])])}))]),t._v(" "),s("table-list-loading",{attrs:{list:t.list,loading:t.pager.loading}})],1),t._v(" "),s("div",{staticClass:"footer-pager"},[s("pagination",{attrs:{"pager-info":t.pager}})],1)])},staticRenderFns:[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("thead",[s("tr",[s("th",{staticClass:"w50"},[t._v("#")]),t._v(" "),s("th",{},[t._v("名称")]),t._v(" "),s("th",{},[t._v("功能")]),t._v(" "),s("th",{staticClass:"w150"},[t._v("编码")]),t._v(" "),s("th",{staticClass:"w50"},[t._v("序号")]),t._v(" "),s("th",{staticClass:"w100"},[t._v("操作")])])])}]},o=s("VU/8")(i,n,!1,null,null,null);e.default=o.exports}});
//# sourceMappingURL=15.6b467d28143580eeebe7.js.map