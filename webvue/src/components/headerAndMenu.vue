<template>
  <div class='h100p'>
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main">
        <div class="bread"></div>
        <div class="right-content">
          <router-view/>
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
    name: 'headerAndMenu',
    data () {
      return {
      }
    },
    components: {appHeader, appMenu}
  }
</script>

<style scoped>
</style>
