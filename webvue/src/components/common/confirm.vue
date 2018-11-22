<template>
  <common-modal :show-modal='showDialog'>
    <div class="modal-header" slot='headerSlot'>
      <h5 class="modal-title" v-text='title'></h5>
      <button type="button" class="close" @click='defaultClose()'>
        <span class='text-danger closeicon' title="关闭">&times;</span>
      </button>
    </div>
    <div class="modal-body" slot="bodySlot" v-text='message'>
    </div>
    <div class="modal-footer" slot="footerSlot">
      <button type="button" class="btn btn-secondary" :class='getCancelCls' v-if='closeBtn.show' @click='defaultClose()'>
        <i class='fa fa-times' :class='getCancelIconCls'></i><span v-text='closeBtn.text'></span>
      </button>
      <button type="button" class="btn btn-purple" :class='getConfirmCls' v-if='confirmBtn.show' @click='defaultConfirm()'>
        <i class='fa fa-check' :class='getCancelIconCls'></i><span v-text='confirmBtn.text'></span>
      </button>
    </div>
  </common-modal>
</template>

<script>
  import commonModal from './commonModal'

  export default {
    name: 'confirm',
    data () {
      return {
        showDialog: false,
        title: '确认信息',
        message: '确定要退出吗？',
        closeBtn: {show: true, cls:'', showIcon: true, iconCls:'',text: '关闭', fn: null},
        confirmBtn: {show: true, cls:'', showIcon: true, iconCls:'',text: '确定', fn: null}
      }
    },
    methods: {
      show:function() {
        $(this.$el).show();
        this.showDialog = true;
      },
      defaultClose: function() {
        var me = this;
        if(me.closeBtn && me.closeBtn.fn) {
          me.closeBtn && me.closeBtn.fn();
        }
        me.showDialog = false;
      },
      defaultConfirm: function() {
        var me = this;
        if(me.confirmBtn && me.confirmBtn.fn) {
          me.confirmBtn && me.confirmBtn.fn();
        }
        me.showDialog = false;
      }
    },
    computed: {
      getCancelCls: function() {
        var obj = {};
        if(this.closeBtn.cls) {
          obj[this.closeBtn.cls] = true;
        }
        return obj;
      },
      getCancelIconCls: function() {
        var obj = {};
        if(this.closeBtn.iconCls) {
          obj[this.closeBtn.iconCls] = true;
        }
        return obj;
      },
      getConfirmCls: function() {
        var obj = {};
        if(this.confirmBtn.cls) {
          obj[this.confirmBtn.cls] = true;
        }
        return obj;
      },
      getConfirmIconCls: function() {
        var obj = {};
        if(this.confirmBtn.iconCls) {
          obj[this.confirmBtn.iconCls] = true;
        }
        return obj;
      },
    },
    components:{commonModal}
  }
</script>

<style scoped>
  .closeicon{font-size:26px;}
</style>