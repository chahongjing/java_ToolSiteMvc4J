<template>
  <div class='maincontent w100p h100p'>
    <div class='list-header-but-group'>
      <button type="button inline-block" class="btn btn-outline-purple" @click="add()" v-authcode='"functionList_add"'>
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
            <th class=''>名称</th>
            <th class='w150'>编码</th>
            <th class='w120'>菜单</th>
            <th class='w150'>路径</th>
            <th class='w50'>序号</th>
            <th class='w100'>操作</th>
          </tr>
        </thead>
        <tbody v-if='!pager.loading'>
          <tr v-for="(item, index) in list">
            <td class="text-center" v-text='((pager.pageNum - 1) * pager.pageSize) + index + 1'></td>
            <td>
              <a class='block w100p h100p' href='javascript:void(0)' v-text='item.name' @click='edit(item)'></a>
            </td>
            <td v-text='item.code'></td>
            <td v-text='item.menuName'></td>
            <td v-text='item.path' v-tooltip='item.path'></td>
            <td class="text-center" v-text='item.seq'></td>
            <td class="operate">
              <a class='inline-block mybtn' href='javascript:void(0)' @click='permissionList(item)' v-authcode='"functionList_grant"'>
                <i class='fa fa-id-badge'></i>
              </a>
              <a class='inline-block mybtn' href='javascript:void(0)' @click='deleteItem(item)' v-authcode='"functionList_delete"'>
                <i class='fa fa-trash cf05'></i>
              </a>
            </td>
          </tr>
        </tbody>
      </table>
      <table-list-loading :list='list' :loading='pager.loading'></table-list-loading>
    </div>
    <div class='footer-pager'>
      <pagination :pager-info='pager'></pagination>
    </div>
  </div>
</template>

<script>
  import commonSrv from '@/common/commonService'
  export default {
    name: 'functionList',
    data () {
      return {
        searchKey: null,
        list: [],
        pager: {pageNum: 1, pageSize: 10, loading: true}
      }
    },
    methods: {
      add() {
        var me = this;
        this.axios.get('/comm/getNewId').then(function (resp) {
          me.$router.push({path: '/admin/functionEdit', query: {id: resp.data.value}});
        });

      },
      edit(entity) {
        this.$router.push({path: '/admin/functionEdit', query: {id: entity.functionId}});

      },
      search() {
        this.goPage(1);
      },
      queryList() {
        var me = this;
        me.pager.loading = true;
        this.axios.get('/function/queryPageList', {
          name: this.searchKey,
          pageNum: this.pager.pageNum,
          pageSize: this.pager.pageSize
        }).then(function (resp) {
          if (resp.data.status == ResultStatus.OK.key) {
            me.list = resp.data.value.list;
            me.pager = commonSrv.getPagerInfo(resp.data.value, me.goPage);
          } else if (resp.data.status == ResultStatus.NO.key){
            me.$toaster.warning(resp.data.message);
          }
        });
      },
      goPage(page) {
        this.pager.pageNum = page;
        this.queryList();
      },
      deleteItem: function (entity) {
        var me = this;
        this.$confirm.confirm('确定要删除功能吗？', function () {
          me.axios.get('/function/delete', {id: entity.functionId}).then(function (resp) {
            if (resp.data.status == ResultStatus.OK.key) {
              me.$toaster.success('删除成功！');
              me.queryList();
            } else if (resp.data.status == ResultStatus.NO.key){
              me.$toaster.warning(resp.data.message);
            }
          });
        });
      },
      permissionList: function (entity) {
        this.$router.push({path: '/admin/permissionList', query: {functionId: entity.functionId}});
      }
    },
    mounted: function () {
      this.search();
    }
  }
</script>
