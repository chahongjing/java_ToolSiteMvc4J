<template>
  <div>
  <div class='list-header-but-group'>
    <button type="button inline-block" class="btn btn-primary" @click="addConfigInfo()">
      <i class='fa fa-plus mr5'></i>添加
    </button>
  </div>
  <div class='searchbar'>
    <form class="form-inline">
    <div class="form-group mb-2">
      <label class='form-label pr5' for="inputPassword2">关键字</label>
      <input type="text" class="form-control" v-model='searchKey' placeholder="输入关键字">
    </div>
    <button type="button" class="btn btn-primary mb-2" @click='search()'><i class='fa fa-search mr5'></i>搜索</button>
    </form>
  </div>
  <div class='list'>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>名称</th>
          <th>类型</th>
          <th>账户</th>
          <th>密码</th>
          <th>联系人</th>
          <th>联系方式</th>
          <th>相关站点</th>
          <th>备注</th>
        </tr>
      </thead>
      <tbody>
        <tr class=table-success v-for="(item, index) in list">
          <td scope="row" v-text='index + 1'></td>
          <td>
          <a class='block w100p h100p' href='javascript:void(0)' v-text='item.name' @click='editConfigInfo(item)'></a>
          </td>
          <td v-text='item.typeName'></td>
          <td v-text='item.account'></td>
          <td v-text='item.password'></td>
          <td v-text='item.contactPerson'></td>
          <td v-text='item.contacts'></td>
          <td v-text='item.relateWebsite'></td>
          <td v-text='item.memo'></td>
        </tr>
      </tbody>
    </table>
    </div>
    <pagination v-bind:pager-info='pager'></pagination>
</div>
</template>

<script>
  import pagination from '../common/pagination'
  export default {
    name: 'configInfoList',
    data () {
      return {
        searchKey:null,
        list: [],
        pager: null
      }
    },
    methods: {
      addConfigInfo() {
        var me = this;
        this.axios.get('/comm/getId').then(function(resp) {
          me.$router.push({path: '/sys/configInfoEdit', query: {id: resp.data.value}});
        });
          
      },
      editConfigInfo(configInfo) {
        this.$router.push({path: '/sys/configInfoEdit', query: {id: configInfo.id}});
          
      },
      search() {
        var me = this;
        this.axios.get('/configInfo/queryPageList', {name: this.searchKey}).then(function(resp) {
          me.list = resp.data.value.list;
          me.pager = resp.data.value;
        });
      }
    },
    mounted: function() {
      this.search();
    },
    components:{pagination}
  }
</script>

<style scoped>
</style>
