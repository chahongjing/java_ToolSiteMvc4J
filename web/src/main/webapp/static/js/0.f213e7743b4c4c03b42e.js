webpackJsonp([0],{"1lYN":function(t,s,e){"use strict";(function(t){var a=e("RH64");s.a={name:"appHeader",data:function(){return{user:{userName:"",oldPassword:null,newPassword:null,passwordAgain:null},showMenu:!1,showchangePasswordDialog:!1,hoverMenu:!1,oldPasswordStatus:{v:null,t:""},newPasswordStatus:{v:null,t:""},passwordAgainStatus:{v:null,t:""},modalOpt:{width:"350px"}}},methods:{building:function(){this.$toaster.warning("功能建设中...")},logout:function(){var t=this;t.$axios.get("/user/logout").then(function(s){s.data.status==ResultStatus.OK.key&&(t.$root.clearUser(),t.$router.push({path:"/login"}))})},openChangePasswordDialog:function(){this.user.oldPassword=null,this.user.newPassword=null,this.user.passwordAgain=null,this.oldPasswordStatus.v=null,this.newPasswordStatus.v=null,this.passwordAgainStatus.v=null,this.showchangePasswordDialog=!0},changePassword:function(){var t=this;return null===this.user.oldPassword||void 0===this.user.oldPassword||""===this.user.oldPassword||""===this.user.oldPassword.trim()?(t.oldPasswordStatus.v=2,void(t.oldPasswordStatus.t="原密码不能为空！")):(t.oldPasswordStatus.v=1,t.oldPasswordStatus.t="",null===this.user.newPassword||void 0===this.user.newPassword||""===this.user.newPassword||""===this.user.newPassword.trim()?(t.newPasswordStatus.v=2,void(t.newPasswordStatus.t="新密码不能为空！")):(t.newPasswordStatus.v=1,t.newPasswordStatus.t="",null===this.user.passwordAgain||void 0===this.user.passwordAgain||""===this.user.passwordAgain||""===this.user.passwordAgain.trim()?(t.passwordAgainStatus.v=2,void(t.passwordAgainStatus.t="确认密码不能为空！")):this.user.newPassword!==this.user.passwordAgain?(t.passwordAgainStatus.v=2,void(t.passwordAgainStatus.t="两次密码不一致！")):(t.passwordAgainStatus.v=1,t.passwordAgainStatus.t="",void t.$axios.get("/user/changePassword",{userCode:this.user.userCode,oldPassword:this.user.oldPassword,newPassword:this.user.newPassword}).then(function(s){s.data.status==ResultStatus.OK.key&&(t.$toaster.success("修改密码成功！"),t.showchangePasswordDialog=!1,t.user.oldPassword="",t.user.newPassword="",t.user.passwordAgain="")}))))},getErrorClass:function(t){var s={};return 1==this[t].v?s["info-success"]=!0:2==this[t].v&&(s["info-error"]=!0),s},enterMenu:function(){this.hoverMenu=!0},leaveMenu:function(){var t=this;t.hoverMenu=!1,setTimeout(function(){t.hoverMenu||(t.showMenu=!1)},400)},editInfo:function(){var t=this.$root.getUser();this.$router.push({path:"/user/userEdit",query:{id:t.userId,type:"editSelf"}}),this.showMenu=!1}},computed:{getSubMenuHeight:function(){var s=t(".dropdown-menu-right").children().length;return(this.showMenu?35*s:0)+"px"}},mounted:function(){this.user=this.$root.getUser()},components:{commonModal:a.a}}}).call(s,e("7t+N"))},"5BOO":function(t,s,e){"use strict";var a=e("1lYN"),o={render:function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticClass:"head"},[e("div",{staticClass:"logo",attrs:{title:"首页"}},[e("a",{staticClass:"fl",attrs:{href:"javascript:void(0)"},on:{click:function(s){t.$root.goHome()}}},[e("i",{staticClass:"fa fa-android fa-2x white"})]),t._v(" "),e("a",{staticClass:"fl title pl10",attrs:{href:"javascript:void(0)"},on:{click:function(s){t.$root.goHome()}}},[t._v("\n      首页\n    ")])]),t._v(" "),e("div",{staticClass:"info"},[e("ul",{staticClass:"rightmenu",on:{mouseenter:t.enterMenu,mouseleave:t.leaveMenu}},[e("li",[e("a",{staticClass:"licontent relative submenu pointer",attrs:{href:"javascript:void(0)"}},[e("i",{staticClass:"fa fa-caret-down more text-center",attrs:{title:"更多操作"},on:{click:function(s){t.showMenu=!t.showMenu}}}),t._v(" "),e("ul",{staticClass:"dropdown-menu dropdown-menu-right",style:{height:t.getSubMenuHeight}},[e("li",{staticClass:"dropdown-item",on:{click:function(s){t.editInfo()}}},[e("i",{staticClass:"fa fa-id-card-o fa-fw c06f"}),t._v("修改个人信息\n            ")]),t._v(" "),e("li",{staticClass:"dropdown-item",on:{click:function(s){t.openChangePasswordDialog()}}},[e("i",{staticClass:"fa fa-key fa-fw text-danger"}),t._v("修改密码\n            ")]),t._v(" "),e("li",{staticClass:"dropdown-item bg-danger text-white",on:{click:function(s){t.logout()}}},[e("i",{staticClass:"fa fa-power-off fa-fw"}),t._v("注销\n            ")])])])]),t._v(" "),e("li",[e("a",{staticClass:"licontent pointer",attrs:{title:"消息",href:"javascript:void(0)"},on:{click:function(s){t.building()}}},[e("i",{staticClass:"fa fa-commenting-o icon-animated-vertical c5dd255 mr0"}),t._v(" "),e("span",{staticClass:"badge badge-success"},[t._v("2")])])]),t._v(" "),e("li",[e("a",{staticClass:"licontent pointer",attrs:{title:"公告",href:"javascript:void(0)"},on:{click:function(s){t.building()}}},[e("i",{staticClass:"fa fa-bullhorn icon-animated-horn cf93939 mr0"}),t._v(" "),e("span",{staticClass:"badge badge-danger"},[t._v("4")])])]),t._v(" "),e("li",[e("a",{staticClass:"licontent pointer",attrs:{title:"提醒",href:"javascript:void(0)"},on:{click:function(s){t.building()}}},[e("i",{staticClass:"fa fa-bell-o icon-animated-bell text-warning mr0"}),t._v(" "),e("span",{staticClass:"badge badge-warning"},[t._v("5")])])]),t._v(" "),e("li",{staticClass:"userInfo"},[e("a",{staticClass:"licontent",attrs:{title:t.user.userName+"\r\n"+t.user.userCode}},[e("i",{staticClass:"fa fa-user-o mr0 caf0"}),t._v(" "),e("span",{staticClass:"blank",domProps:{innerHTML:t._s(t.user.userName+"<br>"+t.user.userCode)}}),t._v(" "),e("span",{staticClass:"userName ellipsis",domProps:{textContent:t._s(t.user.userName)}}),t._v(" "),e("span",{staticClass:"userCode ellipsis",domProps:{textContent:t._s(t.user.userCode)}})])])])]),t._v(" "),e("common-modal",{attrs:{"show-modal":t.showchangePasswordDialog,options:t.modalOpt}},[e("div",{staticClass:"modal-header",attrs:{slot:"headerSlot"},slot:"headerSlot"},[e("h5",{staticClass:"modal-title"},[t._v("修改密码")]),t._v(" "),e("button",{staticClass:"close",attrs:{type:"button"},on:{click:function(s){t.showchangePasswordDialog=!1}}},[e("span",{staticClass:"closeicon",attrs:{title:"关闭"}},[t._v("×")])])]),t._v(" "),e("div",{staticClass:"modal-body pr30",attrs:{slot:"bodySlot"},slot:"bodySlot"},[e("form",{staticClass:"myform form-label-w100 block-form-group"},[e("div",{staticClass:"form-group",class:t.getErrorClass("oldPasswordStatus")},[e("label",{staticClass:"form-label req colon"},[t._v("原密码")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.user.oldPassword,expression:"user.oldPassword"},{name:"focus",rawName:"v-focus"}],staticClass:"form-control",attrs:{type:"password",placeholder:"原密码",autofocus:""},domProps:{value:t.user.oldPassword},on:{input:function(s){s.target.composing||t.$set(t.user,"oldPassword",s.target.value)}}})]),t._v(" "),e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa",attrs:{title:t.oldPasswordStatus.t}})]),t._v(" "),e("span",{staticClass:"error-msg",domProps:{textContent:t._s(t.oldPasswordStatus.t)}})]),t._v(" "),e("div",{staticClass:"form-group",class:t.getErrorClass("newPasswordStatus")},[e("label",{staticClass:"form-label req colon"},[t._v("新密码")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.user.newPassword,expression:"user.newPassword"},{name:"focus",rawName:"v-focus"}],staticClass:"form-control",attrs:{type:"password",placeholder:"新密码",autofocus:""},domProps:{value:t.user.newPassword},on:{input:function(s){s.target.composing||t.$set(t.user,"newPassword",s.target.value)}}})]),t._v(" "),e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa",attrs:{title:t.newPasswordStatus.t}})]),t._v(" "),e("span",{staticClass:"error-msg",domProps:{textContent:t._s(t.newPasswordStatus.t)}})]),t._v(" "),e("div",{staticClass:"form-group",class:t.getErrorClass("passwordAgainStatus")},[e("label",{staticClass:"form-label req colon"},[t._v("确认密码")]),t._v(" "),e("div",{staticClass:"form-content"},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.user.passwordAgain,expression:"user.passwordAgain"},{name:"focus",rawName:"v-focus"}],staticClass:"form-control",attrs:{type:"password",placeholder:"确认密码",autofocus:""},domProps:{value:t.user.passwordAgain},on:{input:function(s){s.target.composing||t.$set(t.user,"passwordAgain",s.target.value)}}})]),t._v(" "),e("div",{staticClass:"form-info"},[e("i",{staticClass:"fa",attrs:{title:t.passwordAgainStatus.t}})]),t._v(" "),e("span",{staticClass:"error-msg",domProps:{textContent:t._s(t.passwordAgainStatus.t)}})])])]),t._v(" "),e("div",{staticClass:"modal-footer",attrs:{slot:"footerSlot"},slot:"footerSlot"},[e("button",{staticClass:"btn btn-outline-purple",attrs:{type:"button"},on:{click:function(s){t.showchangePasswordDialog=!1}}},[e("i",{staticClass:"fa fa-times fa-plus-myrotate fa-back-myrotate"}),e("span",[t._v("取消")])]),t._v(" "),e("button",{staticClass:"btn btn-purple mr5",attrs:{type:"button"},on:{click:function(s){t.changePassword()}}},[e("i",{staticClass:"fa fa-check"}),t._v("确定\n      ")])])])],1)},staticRenderFns:[]};var i=function(t){e("Ypk+")},n=e("VU/8")(a.a,o,!1,i,"data-v-223f5988",null);s.a=n.exports},YU1u:function(t,s,e){"use strict";var a=e("Ymiw"),o={name:"appMenu",data:function(){return{list:[]}},mounted:function(){this.getMenuData()},methods:{getMenuData:function(){var t=this.$root.getMenuList();if(t&&t.length>0)this.list=t;else{var s=this;s.$axios.get("/menu/queryMenu").then(function(t){if(t.data.status==ResultStatus.OK.key){for(var e={first:{},second:{}},a=0;a<t.data.value.length;a++){var o=t.data.value[a];o.isSelected=!1,e.first.selected&&e.first.selected.id==o.id&&(e.first.selected=o),e.second.selected&&e.second.selected.id==o.id&&(e.second.selected=o)}var i=t.data.value.filter(function(t){return!t.pId});for(a=0;a<i.length;a++)i[a].children=t.data.value.filter(function(t){return t.pId==i[a].id}),0==a&&(i[a].isSelected=!0,i[a].height=35*(i[a].children.length+1));e.first.selected&&(e.first.selected.isSelected=!0),e.second.selected&&(e.second.selected.isSelected=!0),s.list=i,s.list&&0!=s.list.length||s.setMenuShowOrHide(!1),s.$root.setMenuList(s.list)}})}},clickFirstMenu:function(t){if(t.isSelected)return t.isSelected=!1,t.height=35,void this.$root.setMenuList(this.list);for(var s=0;s<this.list.length;s++){var e=this.list[s];t==e?(e.isSelected=!e.isSelected,e.height=35*(e.children.length+1)):(e.isSelected=!1,e.height=35)}this.$root.setMenuList(this.list)},clickSecondMenu:function(t,s,e){e.stopPropagation(),s.isSelected||this.$root.clearBreadrumb();for(var o=0;o<this.list.length;o++)for(var i=this.list[o],n=0;n<i.children.length;n++){var r=i.children[n];t!=r&&(r.isSelected=!1)}s.isSelected=!0,this.$root.setMenuList(this.list),a.a.clearPagerModel(),this.$router.push({path:s.data.url})},toggleMenu:function(){this.setMenuShowOrHide(!this.$root.getShowMenu())},setMenuShowOrHide:function(t){this.$root.setShowMenu(t)}},computed:{showMenu:function(){return this.$root.getShowMenu()}}},i={render:function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticClass:"menu",class:{hidemenu:!t.showMenu}},[e("div",{staticClass:"slide-menu"},[e("ul",{staticClass:"first-menu"},t._l(t.list,function(s){return e("li",{class:{selected:s.isSelected},style:{height:s.height+"px"},attrs:{title:s.name},on:{click:function(e){t.clickFirstMenu(s)}}},[e("div",[e("i",{class:"fa "+s.data.icon+" fw"}),t._v(" "),e("span",{domProps:{textContent:t._s(s.name)}}),t._v(" "),e("i",{staticClass:"fa fa-angle-down subMenuTog mr10",class:{open:s.isSelected}})]),t._v(" "),e("ul",{staticClass:"sub-menu",class:{show:s.isSelected}},t._l(s.children,function(a){return e("li",{class:{selected:a.isSelected},on:{click:function(e){t.clickSecondMenu(s,a,e)}}},[e("a",{attrs:{href:"javascript:void(0)"}},[e("i",{class:"fa "+a.data.icon+" fw"}),t._v(" "),e("span",{domProps:{textContent:t._s(a.name)}})])])}))])}))]),t._v(" "),e("span",{staticClass:"tog",on:{click:function(s){t.toggleMenu()}}},[e("i",{staticClass:"fa fa-angle-left"})])])},staticRenderFns:[]};var n=e("VU/8")(o,i,!1,function(t){e("jglb")},"data-v-63be3537",null);s.a=n.exports},"Ypk+":function(t,s){},aOcC:function(t,s,e){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var a=e("5BOO"),o=e("YU1u"),i={name:"breadcrumb",data:function(){return{menuList:[]}},mounted:function(){this.setBreadcrumb()},methods:{goPage:function(t){null==t?console.log("返回首页"):this.$router.push({path:t.path,query:t.query,params:t.params})},setBreadcrumb:function(){this.menuList=this.$store.state.breadcrumb.breadcurmbList}},computed:{showGoBack:function(){var t=this.$store&&this.$store.state&&this.$store.state.breadcrumb&&this.$store.state.breadcrumb.breadcurmbList;return t&&t.length>0},showBread:function(){return this.$root.getShowBreadcrumb()}}},n={render:function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{class:{bread:!0,hidebread:!t.showBread}},[e("ul",[e("li",[e("a",{staticClass:"w100p h100p inline-block pl8 homebread",attrs:{href:"javascript:void(0)",title:"首页"}},[e("i",{staticClass:"fa fa-home mr0",on:{click:function(s){t.$root.goHome()}}})])]),t._v(" "),t._l(t.menuList,function(s){return e("li",{staticClass:"bread-item",attrs:{title:s.text}},[e("span",{staticClass:"w100p h100p inline-block",attrs:{title:s.name}},[e("a",{staticClass:"w100p h100p inline-block",attrs:{href:"javascript:void(0)"},domProps:{textContent:t._s(s.name)},on:{click:function(e){t.goPage(s)}}})])])})],2),t._v(" "),t.showGoBack?e("button",{staticClass:"btn btn-outline-purple btn-sm fr mr5 mt4 pt3",attrs:{type:"button",title:"返回"},on:{click:function(s){t.$root.goBack()}}},[e("i",{staticClass:"fa fa-reply mr5"}),t._v("返回\n  ")]):t._e()])},staticRenderFns:[]};var r=e("VU/8")(i,n,!1,function(t){e("ebqu")},"data-v-eadfd6b6",null).exports,l={name:"headerAndMenu",computed:{showMenu:function(){return this.$root.getShowMenu()},showBreadcrumb:function(){return this.$root.getShowBreadcrumb()}},components:{appHeader:a.a,appMenu:o.a,breadcrumb:r}},u={render:function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticClass:"h100p"},[e("app-header"),t._v(" "),e("div",{staticClass:"body"},[e("app-menu"),t._v(" "),e("div",{staticClass:"right-main",class:{fullwin:!t.showMenu,showBreadcrumb:t.showBreadcrumb}},[e("breadcrumb"),t._v(" "),e("div",{staticClass:"right-content"},[e("transition",{attrs:{name:"slide-fade"}},[e("router-view")],1)],1),t._v(" "),e("div",{staticClass:"footer"})],1)],1),t._v(" "),t.$root.showLoadingBox?e("div",{staticClass:"loading"},[e("div",[e("i",{staticClass:"fa fa-spinner fa-spin fa-3x fa-fw"}),t._v(" "),e("p",{domProps:{textContent:t._s(t.$root.loadingText||"处理中，请稍候...")}})])]):t._e()],1)},staticRenderFns:[]};var c=e("VU/8")(l,u,!1,function(t){e("xhIc")},"data-v-736a3dde",null);s.default=c.exports},ebqu:function(t,s){},jglb:function(t,s){},xhIc:function(t,s){}});
//# sourceMappingURL=0.f213e7743b4c4c03b42e.js.map