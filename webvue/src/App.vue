<template>
  <div id="app">
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main">
        <div class="bread"></div>
        <div class="right-content">
          <router-view/>
          <div class="loadingmask">
            <div class="info">
              <img src="${ctx}/bootstrap/images/loading.gif"/>
              <p class="mt10">数据处理中，请等待...</p>
            </div>
          </div>
        </div>
        <div class="footer"></div>
      </div>
    </div>
  </div>
</template>

<script>
  import appHeader from './components/appHeader'
  import appMenu from './components/appMenu'
  export default {
    name: 'App',
    components: {appHeader, appMenu}
  }
</script>


<style>
  #app {
    width: 100%;
    height: 100%;
  }
</style>
