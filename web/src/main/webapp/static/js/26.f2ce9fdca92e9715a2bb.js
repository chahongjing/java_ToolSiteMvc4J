webpackJsonp([26],{fVKg:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={name:"functionEdit",data:function(){return{allDisabled:!0,functionInfo:{functionId:null,menuId:null,name:null,code:null,path:null,seq:null},menuList:[]}},methods:{getDetail:function(t){var e=this;e.allDisabled=!0,this.$axios.get("/function/getDetail",{id:t}).then(function(t){t.data.status==ResultStatus.OK.key&&(e.functionInfo=t.data.value),e.allDisabled=!1})},save:function(){var t=this;t.allDisabled=!0,this.$axios.post("/function/save",t.functionInfo).then(function(e){e.data.status==ResultStatus.OK.key?(t.$toaster.success("保存成功！"),t.$root.goBack()):t.allDisabled=!1})},getMenuList:function(){var t=this;this.$axios.post("/menu/queryPageMenuList",t.menu).then(function(e){e.data.status==ResultStatus.OK.key&&(t.menuList=e.data.value)})}},mounted:function(){this.getDetail(this.$route.query.id),this.getMenuList()}},s={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"maincontent"},[n("div",{staticClass:"mypanel",staticStyle:{width:"500px",margin:"auto","margin-top":"20px"}},[n("div",{staticClass:"panel-heading font-bold"},[t._v("功能信息")]),t._v(" "),n("div",{staticClass:"panel-body"},[n("form",{staticClass:"myform form-label-w120 block-form-group"},[n("div",{staticClass:"form-group"},[n("label",{staticClass:"form-label"},[t._v("名称：")]),t._v(" "),n("div",{staticClass:"form-content"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.functionInfo.name,expression:"functionInfo.name"}],staticClass:"form-control",attrs:{type:"text",placeholder:"名称",autofocus:""},domProps:{value:t.functionInfo.name},on:{input:function(e){e.target.composing||t.$set(t.functionInfo,"name",e.target.value)}}})]),t._v(" "),t._m(0)]),t._v(" "),n("div",{staticClass:"form-group"},[n("label",{staticClass:"form-label"},[t._v("编码：")]),t._v(" "),n("div",{staticClass:"form-content"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.functionInfo.code,expression:"functionInfo.code"}],staticClass:"form-control",attrs:{type:"text",placeholder:"编码"},domProps:{value:t.functionInfo.code},on:{input:function(e){e.target.composing||t.$set(t.functionInfo,"code",e.target.value)}}})]),t._v(" "),t._m(1)]),t._v(" "),n("div",{staticClass:"form-group"},[n("label",{staticClass:"form-label"},[t._v("菜单：")]),t._v(" "),n("div",{staticClass:"form-content"},[n("select",{directives:[{name:"model",rawName:"v-model",value:t.functionInfo.menuId,expression:"functionInfo.menuId"}],staticClass:"form-control",attrs:{placeholder:"请选择类型"},on:{change:function(e){var n=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){return"_value"in t?t._value:t.value});t.$set(t.functionInfo,"menuId",e.target.multiple?n:n[0])}}},[n("option",[t._v("--请选择类型--")]),t._v(" "),t._l(t.menuList,function(e){return n("option",{domProps:{value:e.menuId,textContent:t._s(e.name)}})})],2)]),t._v(" "),t._m(2)]),t._v(" "),n("div",{staticClass:"form-group"},[n("label",{staticClass:"form-label"},[t._v("路径：")]),t._v(" "),n("div",{staticClass:"form-content"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.functionInfo.path,expression:"functionInfo.path"}],staticClass:"form-control",attrs:{type:"text",placeholder:"路径"},domProps:{value:t.functionInfo.path},on:{input:function(e){e.target.composing||t.$set(t.functionInfo,"path",e.target.value)}}})]),t._v(" "),t._m(3)]),t._v(" "),n("div",{staticClass:"form-group"},[n("label",{staticClass:"form-label"},[t._v("序号：")]),t._v(" "),n("div",{staticClass:"form-content"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.functionInfo.seq,expression:"functionInfo.seq"}],staticClass:"form-control",attrs:{type:"num",step:"1",placeholder:"序号"},domProps:{value:t.functionInfo.seq},on:{input:function(e){e.target.composing||t.$set(t.functionInfo,"seq",e.target.value)}}})]),t._v(" "),t._m(4)]),t._v(" "),n("div",{staticClass:"form-group text-right mb0"},[n("button",{staticClass:"btn btn-outline-purple",attrs:{type:"button"},on:{click:function(e){t.$root.goBack()}}},[n("i",{staticClass:"fa fa-arrow-circle-o-left"}),n("span",[t._v("返回")])]),t._v(" "),n("button",{staticClass:"btn btn-purple mr5",attrs:{type:"button",disabled:t.allDisabled},on:{click:t.save}},[n("i",{staticClass:"fa fa-save"}),t._v("保存\n          ")])])])])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])}]},o=n("VU/8")(a,s,!1,null,null,null);e.default=o.exports}});
//# sourceMappingURL=26.f2ce9fdca92e9715a2bb.js.map