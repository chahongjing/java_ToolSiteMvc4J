<template>
  <div>
    <select ref='myselect' style='width:100%;'>
    </select>
  </div>
</template>

<script>
  import "../../../static/plugins/select2/js/select2.js";
  import "../../../static/plugins/select2/js/i18n/zh-CN.js";
  export default {
    name: "select2",
    props: ['list', 'multiple', 'disabled', 'value', 'idField', 'textField'],
    computed: {},
    data: function() {
      return {
        isInit: false,
        isValueChange: false
      }
    },
    methods: {
      init: function () {
        var me = this;
        // 处理id和text
        if(this.list && this.list.length > 0 && (this.idField || this.textField)) {
          for(var i = 0; i < this.list.length; i++) {
            var obj = me.list[i];
            obj.id = obj.id || obj[me.idField];
            obj.text = obj.text || obj[me.textField];
          }
        }

        var $obj = $(this.$refs.myselect);
        $obj.select2(
          {
            language: "zh-CN",
            multiple: !!me.multiple,
            disabled: !!me.disabled,
            placeholder: '--请选择--',
            allowClear: true,
            data: this.list
          }
        ).val(this.value).trigger('change');
        $obj.closest('div').find('.select2-search__field').css({width:'100%'});
        $obj.on('change', function() {
          var val;
          if(me.isValueChange) {
            val = me.value;
          } else {
            if(this.multiple) {
              val = [];
              $obj.find('option:selected').each(function(index, item) {
                val.push(item.value);
              });
            } else {
              val = $(this).val();
            }
          }
          me.$emit('input', val);
          me.$emit('change', val);
          me.isValueChange = false;
        });
        this.isInit = true;
      },
      getOriginValue: function() {
        for(var i = 0; i < this.list.length; i++) {
          if(this.list[i].id == this.value) {
            return this.value;
          }
        }
        return '';
      },
      destoryCom: function() {
        var $obj = $(this.$refs.myselect);
        $obj.off('change').empty();
        if(this.isInit) {
          $obj.select2("destroy");
        }
        this.isInit = false;
      }
    },
    mounted: function () {
      this.init();
    },
    watch: {
      list: {
        handler: function (curVal, oldVal) {
          this.destoryCom();
          this.init();
        },
        deep: true
      },
      'disabled': function() {
        $(this.$refs.myselect).prop('disabled', !!this.disabled);
      },
      value: function(curVal, oldVal) {
        var $obj = $(this.$refs.myselect);
        $obj.val(curVal);
        if(this.isInit) {
          this.isValueChange = true;
          $obj.trigger('change');
        }
      }
    },
    beforeDestroy: function () {
      this.destoryCom();
    }
  }
</script>

<style src="../../../static/plugins/select2/css/select2.css"></style>
<style scoped>
  /*@import "../../../static/plugins/select2/css/select2.css";*/
</style>
