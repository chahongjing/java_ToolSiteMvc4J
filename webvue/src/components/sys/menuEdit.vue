<template>
  <div class="mypanel" style="width:500px;margin:auto;margin-top:20px;">
    <div class="panel-heading font-bold">菜单信息</div>
    <div class="panel-body">
      <form class='myform form-label-w120 block-form-group'>
        <div class="form-group">
          <label class="form-label">名称：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="名称" autofocus 
            v-model='menu.name'>
          </div>
          <div class='form-info'>
            <i class='fa'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">父级：</label>
          <div class="form-content">
            <select class="form-control" v-model='menu.pId'
            placeholder='请选择类型'>
            <option>--请选择类型--</option>
            <option v-for='item in menuList':value="item.menuId" v-text="item.name"></option>
          </select>
        </div>
        <div class='form-info'>
          <i class='fa fa-question-circle-o'></i>
        </div>
      </div>
      <div class="form-group">
        <label class="form-label">编码：</label>
        <div class="form-content">
          <input type="text" class="form-control" placeholder="编码"
          v-model='menu.code'>
        </div>
        <div class='form-info'>
          <i class='fa fa-question-circle-o'></i>
        </div>
      </div>
      <div class="form-group">
        <label class="form-label">路径：</label>
        <div class="form-content">
          <input type="text" class="form-control" placeholder="路径"
          v-model='menu.url'>
        </div>
        <div class='form-info'>
          <i class='fa fa-question-circle-o'></i>
        </div>
      </div>
      <div class="form-group">
        <label class="form-label">序号：</label>
        <div class="form-content">
          <input type="num" class="form-control" step='1' placeholder="序号"
          v-model='menu.seq'>
        </div>
        <div class='form-info'>
          <i class='fa fa-question-circle-o'></i>
        </div>
      </div>
      <div class="form-group">
        <label class="form-label">图标：</label>
        <div class="form-content">
          <input type="text" class="form-control" placeholder="图标"
          v-model='menu.icon'>
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
    name: 'menuEdit',
    data () {
      return {
        menu:{menuId:null,name:null,code:null,url:null,seq:null,icon:null},
        menuList:[]
        }
      },
      methods: {
        goBack() {
          this.$router.back(-1);
        },
        getDetail: function(id) {
          var me = this;
          this.axios.get('/menu/getDetail', {id:id}).then(function(resp) {
            me.menu = resp.data.value;
          });
        },
        save: function() {
          var me = this;
          this.axios.post('/menu/save', me.menu).then(function(resp) {
            me.goBack();
          });
        },
        getMenuList() {
          var me = this;
          this.axios.post('/menu/queryParentList', me.menu).then(function(resp) {
            me.menuList = resp.data.value;
          });
        }
      },
      mounted: function() {
        this.getDetail(this.$route.query.id);
        this.getMenuList();
      }
    }
  </script>

  <style scoped>
  </style>
