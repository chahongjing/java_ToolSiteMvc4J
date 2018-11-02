<template>
  <div>
  <form>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="名称"
      v-model='configInfo.name'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">类型</label>
    <div class="col-sm-10">
      <select class="form-control" id="exampleFormControlSelect1" v-model='configInfo.type'
      placeholder='请选择类型'>
          <option>--请选择类型--</option>
          <option v-for='item in configTypeList':value="item.key" v-text="item.name"></option>
        </select>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">账户</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="账户"
      v-model='configInfo.account'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">密码</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="密码"
      v-model='configInfo.password'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">联系人</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="联系人"
      v-model='configInfo.contactPerson'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">联系方式</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="联系方式"
      v-model='configInfo.contacts'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">相关站点</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="相关站点"
      v-model='configInfo.relateWebsite'>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">备注</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" placeholder="备注"
      v-model='configInfo.memo'>
    </div>
  </div>
  <div class="form-group row">
    <div class="col-sm-10">
      <button type="button" class="btn btn-primary" @click="save">保存</button>
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
          contacts:null,relateWebsite:null,memo:null},
        configTypeList:[]
      }
    },
    methods: {
      goBack() {
          this.$router.back(-1);
      },
      getDetail: function(id) {
        var me = this;
        this.axios.get('/configInfo/getConfigInfo', {id:id}).then(function(resp) {
          me.configInfo = resp.data.value;
        });
      },
      save: function() {
        var me = this;
        this.axios.post('/configInfo/saveConfigInfo', me.configInfo).then(function(resp) {
          me.goBack();
        });
      },
      getConfigTypeList() {
        var list = [];
        for(var item in ConfigType) {
          list.push(ConfigType[item]);
        }
        return list;
      }
    },
    mounted: function() {
      this.getDetail(this.$route.query.id);
      this.configTypeList = this.getConfigTypeList();
    }
  }
</script>

<style scoped>
</style>
