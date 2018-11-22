<template>
  <div class="mypanel" style="width:500px;margin:auto;margin-top:20px;">
    <div class="panel-heading font-bold">用户信息</div>
    <div class="panel-body">
      <form class='myform form-label-w120 block-form-group'>
        <div class="form-group">
          <label class="form-label">编号：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="编号" autofocus 
            v-model='user.userCode' v-focus />
          </div>
          <div class='form-info'>
            <i class='fa'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">姓名：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="姓名"
            v-model='user.userName'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group" v-if='!user.isSave'>
          <label class="form-label">密码：</label>
          <div class="form-content">
            <input type="password" class="form-control" placeholder="密码"
            v-model='user.password'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group" v-if='!user.isSave'>
          <label class="form-label">确认密码：</label>
          <div class="form-content">
            <input type="password" class="form-control" placeholder="确认密码"
            v-model='user.passwordAgain'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">性别：</label>
          <div class="form-content">
            <label class="radio_checkbox" v-for="item in sexList">
              <input type='radio' name="sex" :value="item.key" v-model="user.sex"/>
              <i></i>
              <span v-text="item.name"></span>
            </label>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">生日：</label>
          <div class="form-content">
            <input type="text" class="form-control" placeholder="生日"
            v-model='user.birthday'>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">是否禁用：</label>
          <div class="form-content">
            <label class="radio_checkbox" v-for="item in YesNoList">
              <input type='radio' name="isDisabled" :value="item.key" v-model="user.isDisabled"/>
              <i></i>
              <span v-text="item.name"></span>
            </label>
          </div>
          <div class='form-info'>
            <i class='fa fa-question-circle-o'></i>
          </div>
        </div>
        <div class="form-group">
          <label class="form-label">是否系统用户：</label>
          <div class="form-content">
            <label class="radio_checkbox" v-for="item in YesNoList">
              <input type='radio' name="isSystem" :value="item.key" v-model="user.isSystem"/>
              <i></i>
              <span v-text="item.name"></span>
            </label>
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
    name: 'userEdit',
    data () {
      return {
        user:{userId:null,userName:null,password:null,passwordAgain:null,sex:null,birthday:null,isDisabled:null,isSystem:null,isSave:true},
        sexList:[],
        YesNoList:[]
      }
    },
    methods: {
      goBack() {
        this.$router.back(-1);
      },
      getDetail: function(id) {
        var me = this;
        this.axios.get('/userinfo/getDetail', {id:id}).then(function(resp) {
          if(resp.data.status == ResultStatus.OK.key) {
            me.user = resp.data.value;
          } else if(resp.data.status == ResultStatus.NO.key) {

          }
        });
      },
      save: function() {
        var me = this;
        this.axios.post('/userinfo/save', me.user).then(function(resp) {
          if(resp.data.status == ResultStatus.OK.key) {
            me.$toaster.success('保存成功！');
            me.goBack();
          } else if(resp.data.status == ResultStatus.NO.key) {

          }
        });
      },
      getEnumList() {
        var list = [];
        for(var item in Sex) {
          list.push(Sex[item]);
        }
        this.sexList = list;
        list = [];
        for(var item in YesNo) {
          list.push(YesNo[item]);
        }
        this.YesNoList = list;
      }
    },
    mounted: function() {
      this.getDetail(this.$route.query.id);
      this.getEnumList();
    }
  }
</script>

<style scoped>
</style>
