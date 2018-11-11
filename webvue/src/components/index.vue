<template>
  <div class='h100p'>
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main" :class='{"fullwin":!showMenu}'>
        <div class="bread">
          <ul>
            <li>
              <a class='w100p h100p inline-block' href='javascript:void(0)' title='首页'><i class='fa fa-home mr0' @click='goHomePage()'></i></a>
            </li>
          </ul>
        </div>
        <div class="right-content">
          <router-link to="/login">
            <button class="btn btn-default">登录</button>
          </router-link>
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
        showMenu: 'true'
      }
    },
    components: {appHeader, appMenu},
    methods: {
      goPage: function () {
    //this.$router.push({path: '/myPage', params:{ id:'1'}});
    //this.$router.push({name: 'myPage', params:{ id:'1'}});
    this.$router.push({path: '/myPage', query: {id: '1'}});
    //this.$router.push({name: 'myPage', query:{ id:'1'}});
  },
      goHomePage() {
      }
}
}
</script>
