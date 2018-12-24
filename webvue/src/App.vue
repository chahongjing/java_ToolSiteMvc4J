<template>
  <div class='w100p h100p' id='app'>
    <router-view/>
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
    name: 'App'
  }
</script>
