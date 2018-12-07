<template>
  <div class="mypanel" style="width:500px;margin:auto;margin-top:20px;">
    <div class="panel-heading font-bold">角色信息</div>
    <div class="panel-body">
      <form class='myform form-label-w120 block-form-group'>
        <div class="form-group">
          <label class="form-label">名称：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="名称" autofocus
                   v-model='role.name'>
          </div>
          <div class='form-info'>
            <i class='fa'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">编码：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="编码"
                   v-model='role.code'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">序号：</label>
          <div class="form-content">
            <input type="num" class="form-control" step='1' placeholder="序号"
                   v-model='role.seq'>
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
        role: {roleId: null, name: null, code: null, seq: null}
      }
    },
    methods: {
      goBack() {
        this.$router.back(-1);
      },
      getDetail: function (id) {
        var me = this;
        this.axios.get('/role/getDetail', {id: id}).then(function (resp) {
          if (resp.data.status == ResultStatus.OK.key) {
            me.role = resp.data.value;
          } else if (resp.data.status == ResultStatus.NO.key){
            me.$toaster.warning(resp.data.message);
          }
        });
      },
      save: function () {
        var me = this;
        this.axios.post('/role/save', me.role).then(function (resp) {
          if (resp.data.status == ResultStatus.OK.key) {
            me.$toaster.success('保存成功！');
            me.goBack();
          } else if (resp.data.status == ResultStatus.NO.key){
            me.$toaster.warning(resp.data.message);
          }
        });
      }
    },
    mounted: function () {
      this.getDetail(this.$route.query.id);
    }
  }
</script>
