<template>
  <div>
  <button type="button" class="btn btn-primary" @click="addConfigInfo()">添加</button>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">First</th>
          <th scope="col">Last</th>
          <th scope="col">Handle</th>
        </tr>
      </thead>
      <tbody>
        <tr class=table-success>
          <th scope="row">1</th>
          <td>Mark</td>
          <td>Otto</td>
          <td>@mdo</td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>Jacob</td>
          <td>Thornton</td>
          <td>@fat</td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td colspan="2">Larry the Bird</td>
          <td>@twitter</td>
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
        this.axios.get('/api/comm/getId').then(function(resp) {
        me.$router.push({path: '/sys/configInfoEdit', query: {id: resp.data.value}});
        });
          
      },
      editConfigInfo(configInfo) {
        this.$router.push({path: '/sys/configInfoEdit', query: {id: configInfo.id}});
          
      }
    },
    mounted: function() {
      this.axios.get('/api/configInfo/queryPageList').then(function(resp) {
      console.log(resp);
      });
    }
  }
</script>

<style scoped>
</style>
