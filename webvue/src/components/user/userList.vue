<template>
  <div class='maincontent w100p h100p'>
    <div class='list-header-but-group'>
      <button type="button inline-block" class="btn btn-outline-purple" @click="add()" v-authcode='"userList_add"'>
        <i class='fa fa-plus mr5'></i>添加
      </button>
    </div>
    <div class='searchbar'>
      <form class='myform form-inline form-group-w280 form-label-w80'>
        <div class="form-group">
          <label class="form-label colon">名称</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="名称" autofocus v-model='searchKey'>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label colon">性别</label>
          <div class="form-content">
            <select class='form-control' v-model="sexValue">
              <option value="">--全部--</option>
              <option v-for="item in sexList" :value="item.value" v-text="item.name"></option>
            </select>
          </div>
        </div>
        <div class="form-group btn100">
          <button type="button" class="btn btn-purple ml20" @click='search()' :disabled='allDisabled'>
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
          <th class='sortheader' :class='getOrderByClass' @click='setOrderByClass()'>名称</th>
          <th class='w100'>编码</th>
          <th class='w155'>创建时间</th>
          <th class='w70'>性别</th>
          <th class='w70'>系统用户</th>
          <th class='w70 text-center'>是否禁用</th>
          <th class='w100'>操作</th>
        </tr>
        </thead>
        <tbody v-if='!pager.loading'>
        <tr v-for="(item, index) in list">
          <td class="text-center" v-text='((pager.pageNum - 1) * pager.pageSize) + index + 1'></td>
          <td>
            <a class='block w100p h100p' href='javascript:void(0)' v-text='item.userName' @click='edit(item)'></a>
          </td>
          <td v-text='item.userCode'></td>
          <td class='text-center' v-text='$options.filters.formatDate(item.createdOn)'></td>
          <td class='text-center' v-text='$options.filters.enumNameFilter(item.sex, "Sex")'></td>
          <td class='text-center' v-text='$options.filters.enumNameFilter(item.isSystem, "YesNo")'></td>
          <td class='text-center' v-text='$options.filters.enumNameFilter(item.isDisabled, "YesNo")'></td>
          <td class='operate'>
            <a class='inline-block mybtn' v-authcode='"userList_grant"' href='javascript:void(0)' @click='grant(item)' title='授权'>
              <i class='fa fa-id-badge c66c'></i>
            </a>
            <a class='inline-block mybtn' v-authcode='"userList_resetPassword"' href='javascript:void(0)' @click='setPassword(item)' title='修改密码'>
              <i class='fa fa-key c393'></i>
            </a>
            <a class='inline-block mybtn' v-authcode='"userList_delete"' href='javascript:void(0)' @click='deleteItem(item)' title='删除'>
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
    <common-modal :show-modal='showchangePasswordDialog' :width='width'>
      <div class="modal-header" slot='headerSlot'>
        <h5 class="modal-title">修改密码</h5>
        <button type="button" class="close" @click='showchangePasswordDialog = false'>
          <span class='closeicon' title="关闭">&times;</span>
        </button>
      </div>
      <div class="modal-body pr20" slot="bodySlot">
        <form class='myform form-label-w100 block-form-group'>
          <div class="form-group">
            <label class="form-label req colon">新密码</label>
            <div class="form-content">
              <input type="password" class="form-control" placeholder="新密码" autofocus
                     v-model='password' v-focus/>
            </div>
            <div class='form-info'>
              <i class='fa'></i>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer" slot="footerSlot">
        <button type="button" class="btn btn-secondary" @click='showchangePasswordDialog = false'>
          <i class='fa fa-times'></i><span>取消</span>
        </button>
        <button type="button" class="btn btn-purple" @click='changePassword()'>
          <i class='fa fa-check'></i><span>确定</span>
        </button>
      </div>
    </common-modal>
  </div>
</template>

<script>
  import commonSrv from '@/common/commonService'
  import commonModal from '@/components/common/commonModal'

  export default {
    name: 'userList',
    data () {
      return {
        allDisabled: true,
        searchKey: null,
        list: [],
        sexValue: '',
        sexList: [],
        orderBy: 'asc',
        pager: {pageNum: 1, pageSize: 5, loading: true},
        width: 350,
        showchangePasswordDialog: false,
        userCode: null,
        password: null
      }
    },
    methods: {
      add() {
        var me = this;
        this.$axios.get('/comm/getNewId').then(function (resp) {
          if(resp.data.status == ResultStatus.OK.key) {
            me.$router.push({path: '/user/userEdit', query: {id: resp.data.value}});
          }
        });

      },
      edit(entity) {
        this.$router.push({path: '/user/userEdit', query: {id: entity.userId}});

      },
      search() {
        this.goPage(1);
      },
      queryList() {
        var me = this;
        me.allDisabled = true;
        me.pager.loading = true;
        this.$axios.get('/user/queryPageList', {
          userName: this.searchKey,
          pageNum: this.pager.pageNum,
          pageSize: this.pager.pageSize,
          orderBy: this.orderBy
        }).then(function (resp) {
          if(resp.data.status == ResultStatus.OK.key) {
            me.list = resp.data.value.list;
            me.pager = commonSrv.getPagerInfo(resp.data.value, me.goPage);
          }
          me.allDisabled = false;
        });
      },
      goPage(page) {
        this.pager.pageNum = page;
        this.queryList();
      },
      deleteItem: function (entity) {
        var me = this;
        this.$confirm.confirm('确定要删除用户吗？', function () {
          me.$axios.get('/user/delete', {id: entity.userId}).then(function (resp) {
            if(resp.data.status == ResultStatus.OK.key) {
              me.$toaster.success('删除成功！');
              me.queryList();
            }
          });
        });
      },
      grant(entity) {
        this.$router.push({path: '/user/userRole', query: {id: entity.userId}});
      },
      getEnumList() {
        var list = [];
        for (var item in Sex) {
          list.push(Sex[item]);
        }
        this.sexList = list;
      },
      setOrderByClass() {
        if(this.orderBy == 'asc') {
          this.orderBy = 'desc';
        } else {
          this.orderBy = 'asc';
        }
        this.queryList();
      },
      setPassword(entity) {
        this.userCode = entity.userCode;
        this.password = '';
        this.showchangePasswordDialog = true;
      },
      changePassword() {
        var me = this;
        if (this.userCode === null || this.userCode === undefined
          || this.userCode === '' || this.userCode.trim() === '') {
          me.$toaster.warning('原密码不能为空！');
          return;
        }
        if (this.password === null || this.password === undefined
          || this.password === '' || this.password.trim() === '') {
          me.$toaster.warning('原密码不能为空！');
          return;
        }
        me.$axios.get('/user/resetPassword', {
          userCode: this.userCode,
          password: this.password
        }).then(function (resp) {
          if (resp.data.status == ResultStatus.OK.key) {
            me.$toaster.success('修改密码成功！');
            me.showchangePasswordDialog = false;
          }
        });
      }
    },
    computed: {
      getOrderByClass: function() {
        var res = {};
        res[this.orderBy] = true;
        return res;
      }
    },
    mounted: function () {
      this.search();
      this.getEnumList();
    },
    components: {commonModal}
  }
</script>
