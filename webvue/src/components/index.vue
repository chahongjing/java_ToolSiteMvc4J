<template>
  <div class='main'>
    <div class='box' v-for='group in list'>
      <div class="card border-info">
        <div class="card-header text-center" v-text='group.name'></div>
        <div class="card-body text-info">
          <div class="list-group oya mygroup">
            <a :href="'static/toolhtml/' + item.href" class="list-group-item list-group-item-action"
               v-for='item in group.children' target='_blank'>
              <i class='fa fa-comment fa-fw'></i><span v-text='item.name'></span>
            </a>
          </div>
        </div>
        <div class="card-footer text-muted">
          more <i class="fa fa-angle-double-right" aria-hidden="true"></i>
        </div>
      </div>
    </div>
    <div class='box'>
      <div class="card border-info">
        <div class="card-header text-center">其它</div>
        <div class="card-body text-info">
          <div class="list-group mygroup oya">
            <router-link to="/test/test" class="list-group-item list-group-item-action">
              <i class='fa fa-comment fa-fw'></i><span>测试</span>
            </router-link>
            <div class='mytest'>

              <button type="button inline-block" class="btn btn-outline-purple" @click="confirm1()">
                <i class='fa fa-plus mr5'></i>弹框1
              </button>
              <button type="button inline-block" class="btn btn-outline-purple" @click="confirm2()">
                <i class='fa fa-plus mr5'></i>弹框2
              </button>
              <button type="button inline-block" class="btn btn-outline-purple" @click="confirm3()">
                <i class='fa fa-plus mr5'></i>弹Modal
              </button>

            </div>
          </div>
        </div>
        <div class="card-footer text-muted">
          more <i class="fa fa-angle-double-right" aria-hidden="true"></i>
        </div>
      </div>
    </div>
    <my-modal :title='myDialog.title' :show-dialog.sync='myDialog.showDialog' :btn-list='myDialog.btnList'
              :width='myDialog.width'></my-modal>
  </div>
</template>

<script>
  import appHeader from '@/components/layout/appHeader'
  import appMenu from '@/components/layout/appMenu'
  import myModal from '@/components/other/myModal'

  var list = [];
  // region java
  var first = {name: 'java', children: []};
  list.push(first);
  var second = {name: 'servlet学习', href: 'learnServlet.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'jsp学习', href: 'learnJsp.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'el学习', href: 'learnEL.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'spring aop学习', href: 'learnAop.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'spring Bean学习', href: 'learnBean.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'springMVC学习', href: 'learnSpringMvc.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'cookie学习', href: 'learnCookie.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'session学习', href: 'learnSession.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'java后台学习', href: 'learnJava.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'jdk学习', href: 'learnJDK.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'thread后台学习', href: 'learnThread.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'exception后台学习', href: 'learnException.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'jvm学习', href: 'learnJvm.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'filter学习', href: 'learnFilter.html', iconClass: ''};
  first.children.push(second);
  second = {name: '事务学习', href: 'learnTransaction.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'nio学习', href: 'learnNio.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'shiro学习', href: 'learnShiro.html', iconClass: ''};
  first.children.push(second);
  second = {name: '反射学习', href: 'learnReflect.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'mybatis学习', href: 'learnMybatis.html', iconClass: ''};
  first.children.push(second);
  // endregion

  // region 服务器
  first = {name: '服务器', children: []};
  list.push(first);
  second = {name: 'tomcat学习', href: 'learnTomcat.html', iconClass: ''};
  first.children.push(second);
  // endregion

  // region maven
  first = {name: 'maven', children: []};
  list.push(first);
  second = {name: 'maven学习', href: 'learnMaven.html', iconClass: ''};
  first.children.push(second);
  // endregion

  // region vue
  first = {name: 'vue', children: []};
  list.push(first);
  second = {name: 'vue demo', href: 'vueDemo.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'vue learn', href: 'learnVue.html', iconClass: ''};
  first.children.push(second);
  // endregion

  // region ueditor
  first = {name: 'ueditor', children: []};
  list.push(first);
  second = {name: 'ueditor', href: 'learnUeditor.html', iconClass: ''};
  first.children.push(second);
  // endregion

  // region js
  first = {name: 'js', children: []};
  list.push(first);
  second = {name: 'js学习', href: 'learnJs.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'echarts', href: 'echarts.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'jsplumb', href: 'jsplumb.html', iconClass: ''};
  first.children.push(second);
  // endregion

  // region 其它
  first = {name: '其它', children: []};
  list.push(first);
  second = {name: 'git学习', href: 'learnGit.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'oracle表转类', href: 'tableToObject.html', iconClass: ''};
  first.children.push(second);
  second = {name: 'sql生成器', href: 'generateSql.html', iconClass: ''};
  first.children.push(second);
  // endregion

  export default {
    name: 'index',
    data () {
      return {
        list: list,
        showMenu: 'true',
        myDialog: {
          showDialog: false,
          width: 800,
          title: '选择用户',
          btnList: [{show: true, cls: '', showIcon: true, iconCls: 'fa fa-times', text: '关闭', fn: null},
            {show: true, cls: 'btn-purple', showIcon: true, iconCls: 'fa fa-check', text: '确定', fn: null}
          ]
        }
      }
    },
    components: {appHeader, appMenu, myModal},
    methods: {
      goPage: function () {
        //this.$router.push({path: '/myPage', params:{ id:'1'}});
        //this.$router.push({name: 'myPage', params:{ id:'1'}});
        this.$router.push({path: '/myPage', query: {id: '1'}});
        //this.$router.push({name: 'myPage', query:{ id:'1'}});
      },
      confirm1() {
        var option = {
          title: '提示1',
          message: '确定要退出吗1确定要退出吗1确定要退出吗1确定要退出吗1确定要退出吗1？',
          closeBtn: {fn: null},
          confirmBtn: {
            fn: function () {
              console.log('adsf');
            }
          }
        }
        this.$confirm.confirmCore(option);
      },
      confirm2() {
        var option = {
          title: '提示2',
          message: '确定要退出吗2？',
          closeBtn: {fn: null},
          confirmBtn: {fn: null}
        }
        this.$confirm.confirmCore(option);
      },
      confirm3() {
        var me = this;
        this.myDialog.btnList[0].fn = function () {
          me.myDialog.showDialog = false;
        }
        this.myDialog.btnList[1].fn = function () {
          console.log('确定');
          me.myDialog.showDialog = false;
        }
        this.myDialog.showDialog = true;
      },
    }
  }
</script>

<style scoped>
  .main{padding-top:15px;}
  .box {
    width: 33%;
    float: left;
    padding: 0 15px;
    margin-bottom: 15px;
    min-width:300px;
  }

  .card {
    display: inline-block;
    width: 100%;
  }

  .p15 {
    padding: 15px;
  }

  .card-body {
    padding: 0;
  }

  .card-body .list-group .list-group-item {
    border-left: 0;
    border-right: 0;
  }

  .card-body .list-group .list-group-item:first-child {
    border-top: 0;
  }

  .card-body .list-group .list-group-item:last-child {
    border-bottom: 0;
  }
  .mytest{padding:20px;}
  .mygroup{height:200px;}
</style>
