webpackJsonp([9],{JYbA:function(e,s,t){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var i=t("mvHQ"),a=t.n(i),c={name:"userRole",data:function(){return{list:[{id:null,name:null,relativeId:null,isCheck:!1,subList:[]}]}},methods:{goBack:function(){this.$root.goBack()},getUserRole:function(e){var s=this;this.$axios.get("/userRole/queryUserRole",{id:e}).then(function(e){e.data.status==ResultStatus.OK.key&&(s.list=e.data.value)})},save:function(e,s){if(s.isCheck){for(var t=!0,i=0;i<e.subList.length;i++)if(!e.subList[i].isCheck){t=!1;break}e.isCheck=t}else e.isCheck=!1;var c=[s];this.$axios.post("/userRole/saveUserRole",{listStr:a()(c)}).then(function(e){e.data.status,ResultStatus.OK.key})},saveGroup:function(e){if(e.subList&&0!=e.subList.length){for(var s=[],t=0;t<e.subList.length;t++)e.subList[t].isCheck!==e.isCheck&&(e.subList[t].isCheck=e.isCheck,s.push(e.subList[t]));0!=s.length&&this.$axios.post("/userRole/saveUserRole",{listStr:a()(s)}).then(function(e){e.data.status,ResultStatus.OK.key})}}},mounted:function(){this.getUserRole(this.$route.query.id)}},n={render:function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("div",{staticClass:"w100p h100p oxh oya"},e._l(e.list,function(s){return t("div",{staticClass:"mypanel"},[t("div",{staticClass:"panel-heading font-bold",class:{noboderbottom:0==s.subList.length||!s.showDetail}},[t("label",{staticClass:"radio_checkbox"},[t("input",{attrs:{type:"checkbox"}}),e._v(" "),t("i",{staticStyle:{display:"none"}}),e._v(" "),t("span",{staticStyle:{"margin-left":"5px"},domProps:{textContent:e._s(s.name)}})]),e._v(" "),t("div",{staticClass:"inline-block fr"},[t("label",{staticClass:"radio_checkbox checkall"},[t("input",{directives:[{name:"model",rawName:"v-model",value:s.isCheck,expression:"root.isCheck"}],attrs:{type:"checkbox"},domProps:{checked:Array.isArray(s.isCheck)?e._i(s.isCheck,null)>-1:s.isCheck},on:{change:[function(t){var i=s.isCheck,a=t.target,c=!!a.checked;if(Array.isArray(i)){var n=e._i(i,null);a.checked?n<0&&e.$set(s,"isCheck",i.concat([null])):n>-1&&e.$set(s,"isCheck",i.slice(0,n).concat(i.slice(n+1)))}else e.$set(s,"isCheck",c)},function(t){e.saveGroup(s)}]}}),e._v(" "),t("i"),e._v(" "),t("span",[e._v("全选")])]),e._v(" "),t("i",{staticClass:"fa showdetailarray",class:{"fa-chevron-up":s.showDetail,"fa-chevron-down":!s.showDetail},on:{click:function(e){s.showDetail=!s.showDetail}}})])]),e._v(" "),s.subList.length>0?t("div",{staticClass:"panel-body",class:{hidedetail:!s.showDetail}},e._l(s.subList,function(i){return t("label",{staticClass:"radio_checkbox"},[t("input",{directives:[{name:"model",rawName:"v-model",value:i.isCheck,expression:"role.isCheck"}],attrs:{type:"checkbox"},domProps:{checked:Array.isArray(i.isCheck)?e._i(i.isCheck,null)>-1:i.isCheck},on:{change:[function(s){var t=i.isCheck,a=s.target,c=!!a.checked;if(Array.isArray(t)){var n=e._i(t,null);a.checked?n<0&&e.$set(i,"isCheck",t.concat([null])):n>-1&&e.$set(i,"isCheck",t.slice(0,n).concat(t.slice(n+1)))}else e.$set(i,"isCheck",c)},function(t){e.save(s,i)}]}}),e._v(" "),t("i"),e._v(" "),t("span",{domProps:{textContent:e._s(i.name)}})])})):e._e()])}))},staticRenderFns:[]};var l=t("VU/8")(c,n,!1,function(e){t("azsN")},"data-v-74d8a680",null);s.default=l.exports},azsN:function(e,s){}});
//# sourceMappingURL=9.bb4890c522c6b3b8c6ad.js.map