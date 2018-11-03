<template>
  <div class='maincontent w100p h100p'>
    <div class='list-header-but-group'>
      <button type="button inline-block" class="btn btn-outline-purple" @click="addUser()">
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
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
          <th class='w50'>#</th>
            <th class='w150'>名称</th>
            <th class='w100'>编码</th>
            <th class='w150'>创建时间</th>
            <th class='w50'>性别</th>
            <th class='w50'>系统用户</th>
            <th class='w50'>是否禁用</th>
            <th class='w100'>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in list">
            <td class="text-center" v-text='index + 1'></td>
            <td>
              <a class='block w100p h100p' href='javascript:void(0)' v-text='item.userName' @click='editUser(item)'></a>
            </td>
            <td v-text='item.userCode'></td>
            <td v-text='$options.filters.formatDate(item.createdOn)'></td>
            <td v-text='item.sexName'></td>
            <td v-text='item.isSystemName'></td>
            <td v-text='item.isDisabledName'></td>
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
    name: 'userList',
    data () {
      return {
        searchKey:null,
        list: [],
        pager: {pageNum:1,pageSize:5}
      }
    },
    methods: {
      addUser() {
        var me = this;
        this.axios.get('/comm/getId').then(function(resp) {
          me.$router.push({path: '/user/userEdit', query: {id: resp.data.value}});
        });
        
      },
      editUser(user) {
        this.$router.push({path: '/user/userEdit', query: {id: user.userId}});
        
      },
      search() {
        var me = this;
        this.axios.get('/userinfo/queryPageList', {userName: this.searchKey,pageNum:this.pager.pageNum,pageSize:this.pager.pageSize}).then(function(resp) {
          me.list = resp.data.value.list;
          me.pager = commonSrv.getPagerInfo(resp.data.value, me.goPage);
        });
      },
      goPage(page) {
        this.pager.pageNum = page;
        this.search();
      },
      deleteItem:function(item) {
        var me = this;
        this.axios.get('/userinfo/delete', {userId: item.userId}).then(function(resp) {
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
