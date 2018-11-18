<template>
  <div class="head">
    <div class="logo">
      <a class='fl' href="#/" title="首页">
        <i class="fa fa-android fa-2 white"></i>
      </a>
      <span class="fl title pl10">首页</span>
    </div>
    <div class="info">
      <ul>
        <li>
          <a class="fr logout licontent" href="javascript:void(0)" @click="logout()">
            <i class="fa fa-power-off"></i>注销
          </a>
        </li>
        <li>
          <span class="licontent" v-text='"姓名：" + user.userName'>
          </span>
        </li>
        <li class="menu">
          <div><i class="fa fa-list"></i></div>
          <ul></ul>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'appHeader',
    data () {
      return {
        user:{userName:''}
      }
    },
    methods: {
      logout: function() {
        var me = this;
        this.axios.get('/userinfo/logout').then(function(resp) {
          if(resp.data.status == ResultStatus.OK.key) {
            me.$store.commit("USER_SIGNOUT");
            me.$router.push({path: '/login'});
          }
        });
      },
    },
    mounted: function() {
      this.user = this.$store.state.user;
    }
  }
</script>

<style scoped>
  .logo a i {
    width: 45px;
    height: 50px;
    display: inline-block;
  }
  .menu{display:none !important;}
</style>
