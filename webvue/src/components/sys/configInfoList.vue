<template>
  <div>
  <button type="button" class="btn btn-primary" @click="addConfigInfo()">添加</button>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">名称</th>
          <th scope="col">类型</th>
          <th scope="col">账户</th>
          <th scope="col">密码</th>
          <th scope="col">联系人</th>
          <th scope="col">联系方式</th>
          <th scope="col">相关站点</th>
          <th scope="col">备注</th>
        </tr>
      </thead>
      <tbody>
        <tr class=table-success v-for="item in list">
          <th scope="row">1</th>
          <td v-text='item.name'></td>
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
</template>

<script>
  export default {
    name: 'configInfoList',
    data () {
      return {
        list: [],
        msg: 'Welcome to Your Vue.js App'
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
          
      }
    },
    mounted: function() {
      var me = this;
      this.axios.get('/configInfo/queryPageList').then(function(resp) {
        me.list = resp.data.value.list
      });
    }
  }
</script>

<style scoped>
</style>
