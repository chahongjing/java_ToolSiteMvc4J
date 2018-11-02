<template>
  <div class="head">
    <div class="logo">
      <a href="${ctx}" title="首页">
        <i class="fa fa-android fa-2 white"></i>
      </a>
      <span class="fl title pl10">首页</span>
    </div>
    <div class="info">
      <ul>
        <li>
          <a class="fr logout licontent" href="javascript:void(0)" @click="logout()">
            <i class="fa fa-power-off"></i>&nbsp;注销</a>
        </li>
        <li>
          <a class="fr logout licontent" href="javascript:void(0)" @click="goBack()">
            <i class="fa fa-reply"></i>&nbsp;返回</a>
        </li>
        <li>
            <span class="licontent">
                姓名：
            </span>
        </li>
        <li class="menu" data-ng-hide="">
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
      }
    }
  }
</script>

<style scoped>
  .logo a i {
    width: 50px;
    height: 50px;
    display: inline-block;
  }
</style>
