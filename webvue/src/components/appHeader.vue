<template>
  <div class="head">
    <div class="logo">
      <a class='fl' href="#/" title="首页">
        <i class="fa fa-android fa-2 white"></i>
      </a>
      <span class="fl title pl10">首页</span>
    </div>
    <div class="info">
      <ul class='rightmenu' @mouseenter='enterMenu' @mouseleave='leaveMenu'>
        <li>
          <span class="licontent relative submenu pointer">
            <i class='fa fa-caret-down more text-center' @click='showMenu = !showMenu'>
            </i>
            <ul class="dropdown-menu dropdown-menu-right" :class='{"slidedown": showMenu}'>
              <li class='dropdown-item' @click='openChangePasswordDialog()'><i class="fa fa-key text-danger"></i>修改密码</li>
              <li class='dropdown-item bg-danger text-white' @click="logout()"><i class="fa fa-power-off"></i>注销</li>
            </ul>
          </span>
        </li>
        <li>
          <span class="licontent pointer">
            <i class='fa fa-commenting text-success'></i>
          </span>
        </li>
        <li>
          <span class="licontent pointer">
            <i class='fa fa-bell-o text-warning'></i>
          </span>
        </li>
        <li>
          <span class="licontent" v-text='"姓名：" + user.userName'>
          </span>
        </li>
      </ul>
    </div>
    <common-modal :show-modal='showchangePasswordDialog' :width='width'>
      <div class="modal-header" slot='headerSlot'>
        <h5 class="modal-title">修改密码</h5>
        <button type="button" class="close" @click='showchangePasswordDialog = false'>
          <span class='closeicon' title="关闭">&times;</span>
        </button>
      </div>
      <div class="modal-body" slot="bodySlot">
        <form class='myform form-label-w100 block-form-group'>
          <div class="form-group">
            <label class="form-label">原密码：</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="原密码" autofocus 
              v-model='user.oldPassword' v-focus />
            </div>
            <div class='form-info'>
              <i class='fa'></i>
            </div>
          </div>
          <div class="form-group">
            <label class="form-label">新密码：</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="新密码" autofocus 
              v-model='user.newPassword' v-focus />
            </div>
            <div class='form-info'>
              <i class='fa'></i>
            </div>
          </div>
          <div class="form-group">
            <label class="form-label">确认密码：</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="确认密码" autofocus 
              v-model='user.passwordAgain' v-focus />
            </div>
            <div class='form-info'>
              <i class='fa'></i>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer" slot="footerSlot">
        <button type="button" class="btn btn-secondary" @click='showchangePasswordDialog = false'>
          <i class='fa fa-times'></i><span>取消</span>
        </button>
        <button type="button" class="btn btn-purple" @click='changePassword()'>
          <i class='fa fa-check'></i><span>确定</span>
        </button>
      </div>
    </common-modal>
  </div>
</template>

<script>
  import commonModal from '@/components/common/commonModal'
  export default {
    name: 'appHeader',
    data () {
      return {
        user:{userName:'', oldPassword:null, newPassword:null, passwordAgain:null},
        showMenu: false,
        width:350,
        showchangePasswordDialog: false,
        hoverMenu:false
      }
    },
    methods: {
      logout: function() {
        var me = this;
        this.$confirm.confirm('确定要退出系统吗？', function() {
          me.axios.get('/userinfo/logout').then(function(resp) {
            if(resp.data.status == ResultStatus.OK.key) {
              me.$store.commit("USER_SIGNOUT");
              me.$router.push({path: '/login'});
            }
          });
        });
      },
      openChangePasswordDialog: function() {
        this.showchangePasswordDialog = true;
      },
      changePassword:function(){
        var me = this;
        if(this.user.oldPassword === null || this.user.oldPassword === undefined
          || this.user.oldPassword === '' || this.user.oldPassword.trim() === '') {
          me.$toaster.warning('原密码不能为空！');
          return;
        }
        if(this.user.newPassword === null || this.user.newPassword === undefined
          || this.user.newPassword === '' || this.user.newPassword.trim() === '') {
          me.$toaster.warning('新密码不能为空！');
          return;
        }
        if(this.user.newPassword !== this.user.passwordAgain) {
          me.$toaster.warning('两次密码不一致！');
          return;
        }
        me.axios.get('/userinfo/changePassword', {oldPassword: this.user.oldPassword, newPassword: this.user.newPassword}).then(function(resp) {
          if(resp.data.status == ResultStatus.OK.key) {
             me.$toaster.success('修改密码成功！');
             me.showchangePasswordDialog = false;
             me.user.oldPassword = '';
             me.user.newPassword = '';
             me.user.passwordAgain = '';
          } else {
            me.$toaster.warning(resp.data.message);
          }
        });
      },
      enterMenu:function() {
        this.hoverMenu = true;
      },
      leaveMenu:function() {
        var me = this;
        me.hoverMenu = false;
        setTimeout(function() {
          if(!me.hoverMenu) {
            me.showMenu = false;
          }
        }, 400);
      }
    },
    mounted: function() {
      this.user = this.$store.state.user;
    },
    components:{commonModal}
  }
</script>

<style scoped>
  .logo a i {
    width: 45px;
    height: 50px;
    display: inline-block;
  }
  .head .info .licontent.submenu{padding-right:0px;}
  .head .info .licontent.submenu > i{padding-right:10px;}
  .form-group:last-child{margin-bottom:0;}
</style>
