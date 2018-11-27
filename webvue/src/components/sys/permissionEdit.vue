<template>
  <div class="mypanel" style="width:500px;margin:auto;margin-top:20px;">
    <div class="panel-heading font-bold">权限信息</div>
    <div class="panel-body">
      <form class='myform form-label-w120 block-form-group'>
        <div class="form-group">
          <label class="form-label">名称：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="名称" autofocus
                   v-model='permission.name'>
          </div>
          <div class='form-info'>
            <i class='fa'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">功能：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="功能"
                   v-model='permission.functionName' readonly>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">编码：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="编码"
                   v-model='permission.code'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">序号：</label>
          <div class="form-content">
            <input type="num" class="form-control" step='1' placeholder="序号"
                   v-model='permission.seq'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group text-right mb0">
          <button type="button" class="btn btn-primary mr5" @click="save">保存</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'permissionEdit',
    data () {
      return {
        permission: {permissionId: null, name: null, code: null, seq: null}
      }
    },
    methods: {
      goBack() {
        this.$router.back(-1);
      },
      getDetail: function (id, functionId) {
        var me = this;
        this.axios.get('/permission/getDetail', {id: id, functionId: functionId}).then(function (resp) {
          if (resp.data.status == Constant.AjaxStatus.OK) {
            me.permission = resp.data.value;
          } else {
            alert(resp.data.message);
          }
        });
      },
      save: function () {
        var me = this;
        this.axios.post('/permission/save', me.permission).then(function (resp) {
          if (resp.data.status == Constant.AjaxStatus.OK) {
            me.$toaster.success('保存成功！');
            me.goBack();
          } else {
            alert(resp.data.message);
          }
        });
      }
    },
    mounted: function () {
      this.getDetail(this.$route.query.id, this.$route.query.functionId || '');
    }
  }
</script>

<style scoped>
</style>
