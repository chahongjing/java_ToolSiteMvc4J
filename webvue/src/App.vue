<template>
  <div class='w100p h100p' id='app'>
    <router-view v-if="isRouterAlive" />
    <div class="loadingmask">
      <div class="info">
        <!-- <img src="static/bootstrap/img/loading.gif"/> -->
        <p class="mt10">数据处理中，请等待...</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {isRouterAlive:true}
    },
    // 提供reload方法
    provide: function () {
      return {
        reload: this.reload
      }
    },
    methods: {
      // 刷新方法
      reload: function () {
        this.isRouterAlive = false;
        // 该方法会在dom更新后执行
        this.$nextTick(function () { this.isRouterAlive = true })
      }
    }
  }
</script>
