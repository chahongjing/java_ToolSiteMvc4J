<template>
  <div class="bread">
    <ul>
      <li>
        <a class='w100p h100p inline-block pl8 homebread' href='javascript:void(0)' title='首页'>
          <i class='fa fa-home mr0' @click='goHome()'></i></a>
      </li>
      <li class='bread-item' v-for='item in menuList' :title='item.text'>
        <span class='w100p h100p inline-block'>
          <a class='w100p h100p inline-block' href='javascript:void(0)' v-text='item.name'
             @click='goPage(item)'></a>
        </span>
      </li>
    </ul>
    <button type="button" class="btn btn-outline-purple btn-sm fr mr5 mt4 pt3" @click='goBack()'
       v-if='showGoBack'>
      <i class='fa fa-reply mr5'></i>返回
    </button>
  </div>
</template>

<script>
  export default {
    name: 'breadcrumb',
    data () {
      return {
        menuList: []
      }
    },
    mounted: function () {
      this.setBreadcrumb();
    },
    methods: {
      goPage(item) {
        if (item == null) {
          console.log('返回首页');
        } else {
          this.$router.push({path: item.path, query: item.query, params: item.params});
        }
      },
      goHome() {
        this.$root.goHome();
      },
      goBack: function () {
        this.$root.goBack();
      },
      setBreadcrumb: function () {
        this.menuList = this.$store.state.breadcrumb;
      }
    },
    computed: {
      showGoBack: function() {
        var breadcrumb = this.$store && this.$store.state && this.$store.state.breadcrumb;
        return breadcrumb && breadcrumb.length > 0;
      }
    }
  }
</script>

<style scoped>
  .fa-home {
    color: #93c;
    transition:0.1s;
    font-size:1.2rem;
  }
  .homebread:hover .fa-home{transform:scale(1.2);}
</style>
