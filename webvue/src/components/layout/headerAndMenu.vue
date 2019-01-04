<template>
  <div class='h100p'>
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main" :class='{"fullwin":!showMenuNew}'>
        <breadcrumb></breadcrumb>
        <div class="right-content">
          <transition name="slide-fade">
            <router-view />
          </transition>
        </div>
        <div class="footer"></div>
      </div>
    </div>
  </div>
</template>

<script>
  import appHeader from '@/components/layout/appHeader';
  import appMenu from '@/components/layout/appMenu';
  import breadcrumb from '@/components/layout/breadcrumb';
  export default {
    name: 'headerAndMenu',
    computed: {
      showMenuNew() {
        return this.$root.getShowMenu();
      }
    },
    components:{appHeader, appMenu, breadcrumb}
  }
</script>
<style scoped>
  .slide-fade-enter-active {
    transition-duration: .3s;
  }
  .slide-fade-leave-active {
    transition-duration: 0s;
    opacity: 0;
  }
  .slide-fade-enter, .slide-fade-leave-to{
    transform: translateY(20px);
    opacity: 0;
  }
</style>