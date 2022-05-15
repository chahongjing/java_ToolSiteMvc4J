webpackJsonp([26],{kS50:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s={name:"menuEdit",data:function(){return{allDisabled:!0,menu:{menuId:null,name:null,code:null,url:null,seq:null,icon:null},menuList:[]}},methods:{getDetail:function(t){var e=this;e.allDisabled=!0,this.$axios.get("/menu/getDetail",{id:t}).then(function(t){t.data.status==ResultStatus.OK.key&&(e.menu=t.data.value),e.allDisabled=!1})},save:function(){var t=this;t.allDisabled=!0,this.$axios.post("/menu/save",t.menu).then(function(e){e.data.status==ResultStatus.OK.key?(t.$toaster.success("保存成功！"),t.$root.goBack()):t.allDisabled=!1})},getMenuList:function(){var t=this;this.$axios.post("/menu/queryParentList",t.menu).then(function(e){e.data.status==ResultStatus.OK.key&&(t.menuList=e.data.value)})}},mounted:function(){this.getDetail(this.$route.query.id),this.getMenuList()}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"maincontent"},[a("div",{staticClass:"mypanel",staticStyle:{width:"500px",margin:"auto","margin-top":"20px"}},[a("div",{staticClass:"panel-heading font-bold"},[t._v("菜单信息")]),t._v(" "),a("div",{staticClass:"panel-body"},[a("form",{staticClass:"myform form-label-w120 block-form-group"},[a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label"},[t._v("名称：")]),t._v(" "),a("div",{staticClass:"form-content"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.menu.name,expression:"menu.name"}],staticClass:"form-control",attrs:{type:"text",placeholder:"名称",autofocus:""},domProps:{value:t.menu.name},on:{input:function(e){e.target.composing||t.$set(t.menu,"name",e.target.value)}}})]),t._v(" "),t._m(0)]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label"},[t._v("父级：")]),t._v(" "),a("div",{staticClass:"form-content"},[a("select",{directives:[{name:"model",rawName:"v-model",value:t.menu.pId,expression:"menu.pId"}],staticClass:"form-control",attrs:{placeholder:"请选择类型"},on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){return"_value"in t?t._value:t.value});t.$set(t.menu,"pId",e.target.multiple?a:a[0])}}},[a("option",[t._v("--请选择类型--")]),t._v(" "),t._l(t.menuList,function(e){return a("option",{domProps:{value:e.menuId,textContent:t._s(e.name)}})})],2)]),t._v(" "),t._m(1)]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label"},[t._v("编码：")]),t._v(" "),a("div",{staticClass:"form-content"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.menu.code,expression:"menu.code"}],staticClass:"form-control",attrs:{type:"text",placeholder:"编码"},domProps:{value:t.menu.code},on:{input:function(e){e.target.composing||t.$set(t.menu,"code",e.target.value)}}})]),t._v(" "),t._m(2)]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label"},[t._v("路径：")]),t._v(" "),a("div",{staticClass:"form-content"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.menu.url,expression:"menu.url"}],staticClass:"form-control",attrs:{type:"text",placeholder:"路径"},domProps:{value:t.menu.url},on:{input:function(e){e.target.composing||t.$set(t.menu,"url",e.target.value)}}})]),t._v(" "),t._m(3)]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label"},[t._v("序号：")]),t._v(" "),a("div",{staticClass:"form-content"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.menu.seq,expression:"menu.seq"}],staticClass:"form-control",attrs:{type:"num",step:"1",placeholder:"序号"},domProps:{value:t.menu.seq},on:{input:function(e){e.target.composing||t.$set(t.menu,"seq",e.target.value)}}})]),t._v(" "),t._m(4)]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",{staticClass:"form-label"},[t._v("图标：")]),t._v(" "),a("div",{staticClass:"form-content"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.menu.icon,expression:"menu.icon"}],staticClass:"form-control",attrs:{type:"text",placeholder:"图标"},domProps:{value:t.menu.icon},on:{input:function(e){e.target.composing||t.$set(t.menu,"icon",e.target.value)}}})]),t._v(" "),t._m(5)]),t._v(" "),a("div",{staticClass:"form-group text-right mb0"},[a("button",{staticClass:"btn btn-outline-purple",attrs:{type:"button"},on:{click:function(e){t.$root.goBack()}}},[a("i",{staticClass:"fa fa-arrow-circle-o-left"}),a("span",[t._v("返回")])]),t._v(" "),a("button",{staticClass:"btn btn-purple mr5",attrs:{type:"button",disabled:t.allDisabled},on:{click:t.save}},[a("i",{staticClass:"fa fa-save"}),t._v("保存\n          ")])])])])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa fa-question-circle-o"})])}]},i=a("VU/8")(s,n,!1,null,null,null);e.default=i.exports}});
//# sourceMappingURL=26.acb5cb9a97cb58adfe04.js.map