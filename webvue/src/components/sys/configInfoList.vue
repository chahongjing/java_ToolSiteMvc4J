<template>
  <div class='maincontent w100p h100p'>
    <div class='list-header-but-group'>
      <button type="button inline-block" class="btn btn-outline-purple" @click="add()">
        <i class='fa fa-plus mr5'></i>添加
      </button>
    </div>
    <div class='searchbar'>
      <form class='myform form-inline form-group-w250 form-label-w80'>
        <div class="form-group">
          <label class="form-label">名称：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="名称" autofocus v-model='searchKey'>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">名称：</label>
          <div class="form-content">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">&yen;</span>
              </div>
              <input type="text" class="form-control">
              <div class="input-group-append">
                <span class="input-group-text">@qq.com</span>
              </div>
            </div>
          </div>
        </div>
        <div class="form-group">
          <button type="button" class="btn btn-purple ml20" @click='search()'>
            <i class='fa fa-search mr5'></i>搜索
          </button>
        </div>
      </form>
    </div>
    <div class='table-list'>
      <table class="table table-hover">
        <thead>
          <tr>
          <th class='w50'>#</th>
            <th class='w150'>名称</th>
            <th class='w100'>类型</th>
            <th class='w100'>账户</th>
            <th class='w100'>密码</th>
            <th class='w100'>联系人</th>
            <th class='w100'>联系方式</th>
            <th class='w100'>相关站点</th>
            <th class='w100'>备注</th>
            <th class='w100'>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in list">
            <td class="text-center" v-text='index + 1'></td>
            <td>
              <a class='block w100p h100p' href='javascript:void(0)' v-text='item.name' @click='edit(item)'></a>
            </td>
            <td v-text='item.typeName'></td>
            <td v-text='item.account'></td>
            <td v-text='item.password'></td>
            <td v-text='item.contactPerson'></td>
            <td v-text='item.contacts'></td>
            <td v-text='item.relateWebsite'></td>
            <td v-text='item.memo'></td>
            <td><a class='inline-block' href='javascript:void(0)' @click='deleteItem(item)'><i class='fa fa-trash'></i></a></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class='footer-pager'>
      <pagination :pager-info='pager'></pagination>
    </div>
  </div>
</template>

<script>
  import commonSrv from '../../common/commonService'
  import pagination from '../common/pagination'
  export default {
    name: 'configInfoList',
    data () {
      return {
        searchKey:null,
        list: [],
        pager: {pageNum:1,pageSize:10}
      }
    },
    methods: {
      add() {
        var me = this;
        this.axios.get('/comm/getId').then(function(resp) {
          me.$router.push({path: '/sys/configInfoEdit', query: {id: resp.data.value}});
        });
        
      },
      edit(entity) {
        this.$router.push({path: '/sys/configInfoEdit', query: {id: entity.id}});
        
      },
      search() {
        var me = this;
        this.axios.get('/configInfo/queryPageList', {name: this.searchKey,pageNum:this.pager.pageNum,pageSize:this.pager.pageSize}).then(function(resp) {
          me.list = resp.data.value.list;
          me.pager = commonSrv.getPagerInfo(resp.data.value, me.goPage);
        });
      },
      goPage(page) {
        this.pager.pageNum = page;
        this.search();
      },
      deleteItem:function(entity) {
        var me = this;
        this.axios.get('/configInfo/delete', {id: entity.id}).then(function(resp) {
          me.search();
        });
      }
    },
    mounted: function() {
      this.search();
    },
    components:{pagination}
  }
</script>
