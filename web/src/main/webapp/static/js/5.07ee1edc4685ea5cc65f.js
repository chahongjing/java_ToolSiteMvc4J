webpackJsonp([5],{"+Nj0":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=s("YiXb"),a={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"main"},[s("div",{staticClass:"login-content"},[t._m(0),t._v(" "),t._m(1),t._v(" "),s("div",{staticClass:"login-form"},[s("form",{attrs:{id:"formLogin",method:"post",beforesubmit:"return false"}},[s("input",{attrs:{type:"hidden",id:"RedirectUrl",name:"RedirectUrl",value:""}}),t._v(" "),t._m(2),t._v(" "),s("div",{staticClass:"form-control"},[s("input",{directives:[{name:"focus",rawName:"v-focus"}],attrs:{type:"text",name:"UserCode",maxlength:"30",placeholder:"请输入账户"}}),s("i",{staticClass:"fa fa-user"})]),t._v(" "),t._m(3),t._v(" "),s("div",{staticClass:"form-button"},[s("button",{staticClass:"pull-right btn btn-sm btn-purple",attrs:{type:"button",id:"btnLogin",disabled:t.allDisabled},on:{click:t.login}},[s("i",{staticClass:"fa fa-key"}),s("span",{staticClass:"bigger-110"},[t._v("登 录")])])])])])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("h1",[e("i",{staticClass:"fa fa-leaf green"}),e("span",{staticClass:"red"},[this._v("后台管理")]),e("span",{staticClass:"grey",attrs:{id:"id-text2"}},[this._v("系统")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("h4",{staticClass:"copy-right blue"},[e("i",{staticClass:"fa fa-copyright"}),this._v(" Zjy Office")])},function(){var t=this.$createElement,e=this._self._c||t;return e("h4",{staticClass:"header blue"},[e("i",{staticClass:"fa fa-coffee green"}),this._v("请输入您的信息")])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-control"},[e("input",{attrs:{type:"password",name:"Password",maxlength:"30",placeholder:"请输入密码"}}),this._v(" "),e("i",{staticClass:"fa fa-lock"})])}]};var r=function(t){s("WCRr")},n=s("VU/8")(i.a,a,!1,r,"data-v-36902520",null);e.default=n.exports},WCRr:function(t,e){},YiXb:function(t,e,s){"use strict";(function(t){s("7+uW");e.a={name:"login",data:function(){return{allDisabled:!0}},mounted:function(){t(".form-control i").click(function(){t(this).siblings("input").focus()}),t("input[name=UserCode], input[name=Password]").bind("keypress",function(e){e&&"13"==e.keyCode&&t("#btnLogin").click()}),this.allDisabled=!1},methods:{login:function(){this.allDisabled=!0;var e=t("input[name=UserCode]"),s=t("input[name=Password]");if(""==t.trim(e.val()))return this.$toaster.warning("请输入用户名!"),this.allDisabled=!1,!1;if(""==t.trim(s.val()))return this.$toaster.warning("请输入密码!"),this.allDisabled=!1,!1;var i=this;this.$axios.post("/user/login",{userCode:t.trim(e.val()),password:t.trim(s.val())}).then(function(t){t.data.status==ResultStatus.OK.key?(i.user=t.data.value,i.$root.setUser(i.user),i.$root.setPermissionList(i.user.permissionList),i.$root.clearMenuList(),i.$root.setShowMenu(!0),i.$root.clearBreadrumb(),window.Utility.initialQuery(),window.Query.redirect?(window.Query.redirect=decodeURIComponent(window.Query.redirect),i.$router.push({path:window.Query.redirect})):i.$router.push({path:"/"})):i.allDisabled=!1})}}}}).call(e,s("7t+N"))}});
//# sourceMappingURL=5.07ee1edc4685ea5cc65f.js.map