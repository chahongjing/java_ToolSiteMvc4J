<template>
  <div class='h100p'>
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main">
        <div class="bread"></div>
        <div class="right-content">
          <h1>{{ msg }}</h1>
          <router-link to="/login">
            <button class="btn btn-default">登录</button>
          </router-link>
        </router-link>
        <router-link to="/sys/configInfoList">
          <button class="btn btn-default">配置</button>
        </router-link>
        <router-link to="/user/userList">
          <button class="btn btn-default">用户管理</button>
        </router-link>
        <router-link to="/sys/menuList">
          <button class="btn btn-default">菜单</button>
        </router-link>
      </div>
      <div class="footer"></div>
    </div>
  </div>
</div>
</template>

<script>
  import appHeader from './appHeader'
  import appMenu from './appMenu'
  export default {
    name: 'index',
    data () {
      return {
        msg: 'Welcome to Your Vue index'
      }
    },
    components: {appHeader, appMenu},
    methods: {
      goPage: function () {
    //this.$router.push({path: '/myPage', params:{ id:'1'}});
    //this.$router.push({name: 'myPage', params:{ id:'1'}});
    this.$router.push({path: '/myPage', query: {id: '1'}});
    //this.$router.push({name: 'myPage', query:{ id:'1'}});
  }
}
}
</script>
