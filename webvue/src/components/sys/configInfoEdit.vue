<template>
  <div>
  <form>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.name'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">类型</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Password"
      v-model='configInfo.type'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">账户</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.account'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">密码</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.password'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">联系人</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.contactPerson'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">联系方式</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.contacts'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">相关站点</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.relateWebsite'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">备注</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="Email"
      v-model='configInfo.memo'>
    </div>
  </div>
  <div class="form-group row">
    <div class="col-sm-10">
      <button type="button" class="btn btn-primary" @click="save">Sign in</button>
    </div>
  </div>
</form>
    
</div>
</template>

<script>
  export default {
    name: 'configInfoEdit',
    data () {
      return {
        configInfo:{id:null,name:null,type:null,account:null,password:null,contactPersion:null,
        contacts:null,relateWebsite:null,memo:null}
      }
    },
    methods: {
      goBack() {
          this.$router.back(-1);
      },
      save: function() {
      var me = this;
        this.axios.post('/api/configInfo/saveConfigInfo', me.configInfo).then(function(resp) {
          me.configInfo = resp.data.value;
        console.log(resp);
        });
      }
    },
    mounted: function() {
      var me = this;
      this.axios.get('/api/configInfo/getConfigInfo', {params: {id: 1}}).then(function(resp) {
        me.configInfo = resp.data;
      console.log(resp);
      });
    }
  }
</script>

<style scoped>
</style>
