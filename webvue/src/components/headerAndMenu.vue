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
            <li class='bread-item' v-for='item in menuList' :title='item.text'>
              <span class='split'>&gt;</span>
              <a class='w100p h100p inline-block' href='javascript:void(0)' v-text='item.text' @click='goPage(item)'></a>
            </li>
          </ul>
          <button type="button" class="btn btn-outline-purple btn-sm fr mr5 mt4" @click='goBack()'>
            <i class='fa fa-reply mr5'></i>返回
          </button>
        </div>
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
        showMenu:true,
        menuList:[]
      }
    },
    mounted:function() {
      var list = [];
      list.push({path:'/',text:'一级模块'});
      list.push({path:'/',text:'二级模块'});
      list.push({path:'/',text:'三级模块'});
      this.menuList = list;
    },
    methods: {
      goPage(item) {
        if(item == null) {
          console.log('返回首页');
        } else {
          console.log(item.text);
        }
      },
      goHomePage() {
        this.$router.push({path: '/'});
      },
      goBack:function() {
        this.$router.back(-1);
      }
    },
    components: {appHeader, appMenu}
  }
</script>

<style scoped>
.right-main.fullwin{width:calc(100% - 1px);}
</style>
