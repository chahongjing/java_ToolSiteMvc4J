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
          <a class="licontent relative submenu pointer">
            <i class='fa fa-caret-down more text-center' title='更多操作' @click='showMenu = !showMenu'>
            </i>
            <ul class="dropdown-menu dropdown-menu-right" :class='{"slidedown": showMenu}'>
              <li class='dropdown-item' @click='editInfo()'>
                <i class="fa fa-id-card-o fa-fw"></i>修改个人信息
              </li>
              <li class='dropdown-item' @click='openChangePasswordDialog()'>
                <i class="fa fa-key fa-fw text-danger"></i>修改密码
              </li>
              <li class='dropdown-item bg-danger text-white' @click="logout()">
                <i class="fa fa-power-off fa-fw"></i>注销
              </li>
            </ul>
          </a>
        </li>
        <li>
          <a class="licontent pointer" title='消息'>
            <i class='fa fa-commenting icon-animated-vertical text-success mr0'></i>
            <span class="badge badge-success">5</span>
          </a>
        </li>
        <li>
          <a class="licontent pointer" title='提醒'>
            <i class='fa fa-bell-o icon-animated-bell text-warning mr0'></i>
            <span class="badge badge-warning">5</span>
          </a>
        </li>
        <li>
          <a class="licontent" :title='user.userName'>
            <i class="fa fa-user-o mr0 caf0"></i>
            <span v-text='user.userName'></span>
          </a>
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
      <div class="modal-body pr20" slot="bodySlot">
        <form class='myform form-label-w100 block-form-group'>
          <div class="form-group">
            <label class="form-label">原密码：</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="原密码" autofocus
                     v-model='user.oldPassword' v-focus/>
            </div>
            <div class='form-info'>
              <i class='fa'></i>
            </div>
          </div>
          <div class="form-group">
            <label class="form-label">新密码：</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="新密码" autofocus
                     v-model='user.newPassword' v-focus/>
            </div>
            <div class='form-info'>
              <i class='fa'></i>
            </div>
          </div>
          <div class="form-group">
            <label class="form-label">确认密码：</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="确认密码" autofocus
                     v-model='user.passwordAgain' v-focus/>
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
        user: {userName: '', oldPassword: null, newPassword: null, passwordAgain: null},
        showMenu: false,
        width: 350,
        showchangePasswordDialog: false,
        hoverMenu: false
      }
    },
    methods: {
      logout: function () {
        var me = this;
        this.$confirm.confirm('确定要退出系统吗？', function () {
          me.axios.get('/userinfo/logout').then(function (resp) {
            if (resp.data.status == ResultStatus.OK.key) {
              me.$root.clearUser();
              me.$router.push({path: '/login'});
            }
          });
        });
      },
      openChangePasswordDialog: function () {
        this.showchangePasswordDialog = true;
      },
      changePassword: function () {
        var me = this;
        if (this.user.oldPassword === null || this.user.oldPassword === undefined
          || this.user.oldPassword === '' || this.user.oldPassword.trim() === '') {
          me.$toaster.warning('原密码不能为空！');
          return;
        }
        if (this.user.newPassword === null || this.user.newPassword === undefined
          || this.user.newPassword === '' || this.user.newPassword.trim() === '') {
          me.$toaster.warning('新密码不能为空！');
          return;
        }
        if (this.user.newPassword !== this.user.passwordAgain) {
          me.$toaster.warning('两次密码不一致！');
          return;
        }
        me.axios.get('/userinfo/changePassword', {
          oldPassword: this.user.oldPassword,
          newPassword: this.user.newPassword
        }).then(function (resp) {
          if (resp.data.status == ResultStatus.OK.key) {
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
      enterMenu: function () {
        this.hoverMenu = true;
      },
      leaveMenu: function () {
        var me = this;
        me.hoverMenu = false;
        setTimeout(function () {
          if (!me.hoverMenu) {
            me.showMenu = false;
          }
        }, 400);
      },
      editInfo() {
        var user = this.$root.getUser();
        this.$router.push({path: '/user/userEdit', query: {id: user.userId, type: 'editSelf'}});
        this.showMenu = false;
      }
    },
    mounted: function () {
      this.user = this.$root.getUser();
    },
    components: {commonModal}
  }
</script>

<style scoped>
  .logo a i {
    width: 45px;
    height: 50px;
    display: inline-block;
  }

  .head .info .licontent.submenu {
    padding: 0px;
  }

  .head .info .licontent.submenu > i {
    padding-left: 5px;
    padding-right: 7px;
    transition: 0.3s;
  }

  .form-group:last-child {
    margin-bottom: 0;
  }
  .icon-animated-vertical{display:inline-block;animation:vertical 2s infinite ease 2s;}
  @keyframes vertical{0%{transform:translate(0,-3px)}4%{transform:translate(0,3px)}8%{transform:translate(0,-3px)}12%{transform:translate(0,3px)}16%{transform:translate(0,-3px)}20%{transform:translate(0,3px)}22%{transform:translate(0,0)}}
  .icon-animated-bell{display:inline-block;animation:ringing 2s infinite ease 1s;transform-origin:50% 0}
  @keyframes ringing{0%{transform:rotate(-15deg)}2%{transform:rotate(15deg)}4%{transform:rotate(-18deg)}6%{transform:rotate(18deg)}8%{transform:rotate(-22deg)}10%{transform:rotate(22deg)}12%{transform:rotate(-18deg)}14%{transform:rotate(18deg)}16%{transform:rotate(-12deg)}18%{transform:rotate(12deg)}20%{transform:rotate(0deg)}}
</style>
