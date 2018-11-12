<template>
  <div class='w100p h100p oxh oya'>
    <div class="mypanel" v-for='firstMenu in list'>
      <div class="panel-heading font-bold" :class='{"noboderbottom":firstMenu.subList.length == 0 || !firstMenu.showDetail}'>
        <label class="radio_checkbox">
          <input type='checkbox' v-model='firstMenu.isCheck' />
          <i></i>
          <span v-text='firstMenu.name'></span>
        </label>
        <div class='inline-block fr' v-if='firstMenu.subList.length > 0'>
          <label class="radio_checkbox checkall">
            <input type='checkbox' />
            <i></i>
            <span>全选</span>
          </label>
          <i class="fa showdetailarray" :class='{"fa-chevron-up":firstMenu.showDetail,"fa-chevron-down":!firstMenu.showDetail}' @click='firstMenu.showDetail = !firstMenu.showDetail'></i>
        </div>
      </div>
      <div class="panel-body" :class='{"hidedetail":!firstMenu.showDetail}' v-if='firstMenu.subList.length > 0'>
        <div class="mypanel subpanel" v-for='secondMenu in firstMenu.subList'>
          <div class="panel-heading font-bold" :class='{"noboderbottom":secondMenu.subList.length == 0 || !secondMenu.showDetail}'>
            <label class="radio_checkbox">
              <input type='checkbox' v-model='secondMenu.isCheck' />
              <i></i>
              <span v-text='secondMenu.name'></span>
            </label>
            <div class='inline-block fr' v-if='secondMenu.subList.length > 0'>
              <label class="radio_checkbox checkall">
                <input type='checkbox' />
                <i></i>
                <span>全选</span>
              </label>
              <i class="fa showdetailarray" :class='{"fa-chevron-up":secondMenu.showDetail,"fa-chevron-down":!secondMenu.showDetail}' @click='secondMenu.showDetail = !secondMenu.showDetail'></i>
            </div>
          </div>
          <div class="panel-body" :class='{"hidedetail":!secondMenu.showDetail}' v-if='secondMenu.subList.length > 0'>
            <div class="mypanel subpanel" v-for='functionItem in secondMenu.subList'>
              <div class="panel-heading font-bold" :class='{"noboderbottom":functionItem.subList.length == 0 || !functionItem.showDetail}'>
                <label class="radio_checkbox">
                  <input type='checkbox' v-model='functionItem.isCheck' />
                  <i></i>
                  <span v-text='functionItem.name'></span>
                </label>
                <div class='inline-block fr' v-if='functionItem.subList.length > 0'>
                  <label class="radio_checkbox checkall">
                    <input type='checkbox' />
                    <i></i>
                    <span>全选</span>
                  </label>
                  <i class="fa showdetailarray" :class='{"fa-chevron-up":functionItem.showDetail,"fa-chevron-down":!functionItem.showDetail}' @click='functionItem.showDetail = !functionItem.showDetail'></i>
                </div>
              </div>
              <div class="panel-body" :class='{"hidedetail":!functionItem.showDetail}' v-if='functionItem.subList.length > 0'>
                <label class="radio_checkbox" v-for="permission in functionItem.subList">
                  <input type='checkbox' v-model="permission.isCheck"/>
                  <i></i>
                  <span v-text="permission.name"></span>
                </label>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'roleGrantPermission',
    data () {
      return {
        list:[{id:null,name:null,relativeId:null,isCheck:false,
          subList:[{id:null,name:null,relativeId:null,isCheck:false,subList:[{id:null,name:null,relativeId:null,isCheck:false,subList:[{id:null,name:null,relativeId:null,isCheck:false,subList:[]}]}]}]}]
        }
      },
      methods: {
        goBack() {
          this.$router.back(-1);
        },
        getRolePermission: function(id) {
          var me = this;
          this.axios.get('/role/getRolePermission', {id:id}).then(function(resp) {
            if(resp.data.status == Constant.AjaxStatus.OK) {
              me.list = resp.data.value;
            } else {
              alert(resp.data.message);
            }
          });
        },
        save: function() {
          var me = this;
          this.axios.post('/role/save', me.role).then(function(resp) {
            if(resp.data.status == Constant.AjaxStatus.OK) {
              me.goBack();
            } else {
              alert(resp.data.message);
            }
          });
        }
      },
      mounted: function() {
        this.getRolePermission(this.$route.query.id);
      }
    }
</script>

<style scoped>
.mypanel{margin:auto; width:calc(100% - 30px);margin-top:15px;}
.panel-body{overflow: hidden;transition:0.3s;padding:8px;}
.subpanel{width:100%;margin-bottom:10px;margin-top:0;}
.subpanel:last-child{margin-bottom:0;}
.radio_checkbox{margin-bottom:0;}
.panel-heading{padding:8px;}
.showdetailarray{display: inline-block;width: 30px;height: 29px;line-height: 28px;margin-right:0;text-align:center;cursor:pointer}
.checkall{margin-right:0;}
.hidedetail{height:0;padding:0;}
.noboderbottom{border-bottom-width:0;}
</style>
