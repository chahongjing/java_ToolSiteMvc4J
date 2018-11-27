<template>
  <div class='h100p'>
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main" :class='{"fullwin":!showMenu}'>
        <breadcrumb></breadcrumb>
        <div class="right-content">
          <router-view/>
        </div>
        <div class="footer"></div>
      </div>
    </div>
  </div>
</template>

<script>
  import appHeader from '@/components/appHeader'
  import appMenu from '@/components/appMenu'
  import breadcrumb from '@/components/breadcrumb'
  export default {
    name: 'headerAndMenu',
    data () {
      return {
        showMenu: true
      }
    },
    mounted: function () {
    },
    methods: {
    },
    components:{appHeader, appMenu, breadcrumb}
  }
</script>

<style scoped>
  .fa-home {
    color: #93c;
  }
</style>
