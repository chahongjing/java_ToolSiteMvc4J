webpackJsonp([16],{HLI5:function(t,s,e){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var a={name:"permissionEdit",data:function(){return{allDisabled:!0,permission:{permissionId:null,name:null,code:null,seq:null}}},methods:{getDetail:function(t,s){var e=this;e.allDisabled=!0,this.$axios.get("/permission/getDetail",{id:t,functionId:s}).then(function(t){t.data.status==ResultStatus.OK.key&&(e.permission=t.data.value),e.allDisabled=!1})},save:function(){var t=this;t.allDisabled=!0,this.$axios.post("/permission/save",t.permission).then(function(s){s.data.status==ResultStatus.OK.key?(t.$toaster.success("保存成功！"),t.$root.goBack()):t.allDisabled=!1})}},mounted:function(){this.getDetail(this.$route.query.id,this.$route.query.functionId||"")}},i={render:function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticClass:"maincontent"},[e("div",{staticClass:"mypanel",staticStyle:{width:"500px",margin:"auto","margin-top":"20px"}},[e("div",{staticClass:"panel-heading font-bold"},[t._v("权限信息")]),t._v(" "),e("div",{staticClass:"panel-body"},[e("form",{staticClass:"myform form-label-w120 block-form-group"},[e("div",{staticClass:"form-group"},[e("label",{staticClass:"form-label"},[t._v("名称：")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.permission.name,expression:"permission.name"}],staticClass:"form-control",attrs:{type:"text",placeholder:"名称",autofocus:""},domProps:{value:t.permission.name},on:{input:function(s){s.target.composing||t.$set(t.permission,"name",s.target.value)}}})]),t._v(" "),t._m(0)]),t._v(" "),e("div",{staticClass:"form-group"},[e("label",{staticClass:"form-label"},[t._v("功能：")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.permission.functionName,expression:"permission.functionName"}],staticClass:"form-control",attrs:{type:"text",placeholder:"功能",readonly:""},domProps:{value:t.permission.functionName},on:{input:function(s){s.target.composing||t.$set(t.permission,"functionName",s.target.value)}}})]),t._v(" "),t._m(1)]),t._v(" "),e("div",{staticClass:"form-group"},[e("label",{staticClass:"form-label"},[t._v("编码：")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.permission.code,expression:"permission.code"}],staticClass:"form-control",attrs:{type:"text",placeholder:"编码"},domProps:{value:t.permission.code},on:{input:function(s){s.target.composing||t.$set(t.permission,"code",s.target.value)}}})]),t._v(" "),t._m(2)]),t._v(" "),e("div",{staticClass:"form-group"},[e("label",{staticClass:"form-label"},[t._v("序号：")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.permission.seq,expression:"permission.seq"}],staticClass:"form-control",attrs:{type:"num",step:"1",placeholder:"序号"},domProps:{value:t.permission.seq},on:{input:function(s){s.target.composing||t.$set(t.permission,"seq",s.target.value)}}})]),t._v(" "),t._m(3)]),t._v(" "),e("div",{staticClass:"form-group text-right mb0"},[e("button",{staticClass:"btn btn-outline-purple",attrs:{type:"button"},on:{click:function(s){t.$root.goBack()}}},[e("i",{staticClass:"fa fa-arrow-circle-o-left"}),e("span",[t._v("返回")])]),t._v(" "),e("button",{staticClass:"btn btn-purple mr5",attrs:{type:"button",disabled:t.allDisabled},on:{click:t.save}},[e("i",{staticClass:"fa fa-save"}),t._v("保存\n          ")])])])])])])},staticRenderFns:[function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"form-info"},[s("i",{staticClass:"fa"})])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"form-info"},[s("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"form-info"},[s("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"form-info"},[s("i",{staticClass:"fa fa-question-circle-o"})])}]},o=e("VU/8")(a,i,!1,null,null,null);s.default=o.exports}});
//# sourceMappingURL=16.80e2563896745b29d2d8.js.map