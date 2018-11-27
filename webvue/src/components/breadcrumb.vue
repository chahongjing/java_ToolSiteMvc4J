<template>
  <div class="bread">
    <ul>
      <li>
        <a class='w100p h100p inline-block pl8' href='javascript:void(0)' title='首页'>
          <i class='fa fa-home mr0' @click='goHomePage()'></i></a>
      </li>
      <li class='bread-item' v-for='item in menuList' :title='item.text'>
        <span class='w100p h100p inline-block'>
          <a class='w100p h100p inline-block' href='javascript:void(0)' v-text='item.name'
             @click='goPage(item)'></a>
        </span>
      </li>
    </ul>
    <button type="button" class="btn btn-outline-purple btn-sm fr mr5 mt4" @click='goBack()'>
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
      this.setBread();
    },
    methods: {
      goPage(item) {
        if (item == null) {
          console.log('返回首页');
        } else {
          this.$router.push({path: item.path, query: item.query, params: item.params});
        }
      },
      goHomePage() {
        this.$store.commit("CLEAR_BREAD");
        this.$router.push({path: '/'});
      },
      goBack: function () {
        var bread = this.$store.state.bread;
        if (bread.length == 1) {
          this.$store.commit("CLEAR_BREAD");
          this.$router.push({path: '/'});
          return;
        }
        bread.pop();
        var item = bread[bread.length - 1];
        if (item != null) {
          this.$router.push({path: item.path, query: item.query, params: item.params});
        } else {
          this.$router.push({path: '/'});
        }
      },
      setBread: function () {
        this.menuList = this.$store.state.bread;
      }
    }
  }
</script>

<style scoped>
  .fa-home {
    color: #93c;
  }
</style>
