<template>
  <common-modal :show-modal='showDialog' :width='width'>
    <div class="modal-header" slot='headerSlot'>
      <h5 class="modal-title" v-text='title'></h5>
      <button type="button" class="close" @click='defaultClose()'>
        <span class='closeicon' title="关闭">&times;</span>
      </button>
    </div>
    <div class="modal-body" slot="bodySlot">
      <button> 测试</button>
    </div>
    <div class="modal-footer" slot="footerSlot">
      <button type="button" class="btn btn-secondary" v-for='btn in btnList'
              :class='getCls(btn)' v-if='btn.show' @click='btnFn(btn)'>
        <i class='fa' :class='getIconCls(btn)'></i><span v-text='btn.text'></span>
      </button>
    </div>
  </common-modal>
</template>

<script>
  import commonModal from '@/components/common/commonModal';

  export default {
    name: 'confirm',
    props: {
      showDialog: null,
      width: null,
      title: null,
      btnList: null
    },
    data () {
      return {}
    },
    methods: {
      defaultClose: function () {
        var me = this;
        if (me.closeBtn && me.closeBtn.fn) {
          me.closeBtn && me.closeBtn.fn();
        }
        me.showDialog = false;
      },
      btnFn: function (btn) {
        btn.fn && btn.fn();
      },
      getCls: function (btn) {
        var obj = {};
        if (btn.cls) {
          obj[btn.cls] = true;
        }
        return obj;
      },
      getIconCls: function (btn) {
        var obj = {};
        if (btn.iconCls) {
          obj[btn.iconCls] = true;
        }
        return obj;
      }
    },
    components: {commonModal}
  }
</script>
