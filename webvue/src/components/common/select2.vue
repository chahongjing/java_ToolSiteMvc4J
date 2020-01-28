<template>
  <div class="text-left">
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
    data: function () {
      return {
        isInit: false
      }
    },
    methods: {
      // 初始化
      init: function () {
        // 处理id和text
        this.preprocessData();
        // 绑定数据和dom
        this.handleDataAndDom();
        // 初始化完毕
        this.isInit = true;
      },
      // 预处理数据
      preprocessData: function () {
        if (this.list && this.list.length > 0 && (this.idField || this.textField)) {
          for (var i = 0; i < this.list.length; i++) {
            var obj = this.list[i];
            obj.id = obj.id || obj[this.idField];
            obj.text = obj.text || obj[this.textField];
            if (obj.id === null || obj.id === undefined) {
              console.log('select2绑定id值错误!' + JSON.stringify(obj));
            }
          }
        }
      },
      // 绑定数据和dom
      handleDataAndDom: function () {
        var me = this;
        var $obj = $(this.$refs.myselect);
        $obj.select2({
          language: "zh-CN",
          multiple: !!me.multiple,
          closeOnSelect: !me.multiple,
          disabled: !!me.disabled,
          placeholder: '--请选择--',
          allowClear: true,
          data: this.list
        }).val(this.value).trigger('change').on('select2:select', function (e) {
          // var data = e.params.data;
          // console.log(data);
          me.setCstValue(me.getCstValue(), true);
        });
        $obj.closest('div').find('.select2-search__field').css({width: '100%'});
      },
      // 取值
      getCstValue: function () {
        return $(this.$refs.myselect).select2('val');
      },
      // 设置值
      setCstValue: function (value, innerChange) {
        var $obj = $(this.$refs.myselect);
        // 内部发生变化
        if (innerChange) {
          // dom值发生变化时同步js对象的值
          this.$emit('input', value);
          this.$emit('change', value);
        } else {
          $obj.val(value).trigger('change');
        }
      },
      // 判断是否联动引起的变化
      isCycleChange: function (value) {
        var oldValue = this.getCstValue();
        if (value == oldValue) {
          return true;
        }
        if ($.isArray(value) && $.isArray(oldValue) && Array.intersect(value, oldValue).length == 0) {
          return true;
        }
        return false;
      },
      // 销毁控件
      destoryCom: function () {
        var $obj = $(this.$refs.myselect);
        $obj.off('change').empty();
        if (this.isInit) {
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
        // immediate: true
      },
      disabled: function () {
        $(this.$refs.myselect).prop('disabled', !!this.disabled);
      },
      // 监听值是否发生变化
      value: function (curVal, oldVal) {
        if (this.isCycleChange(curVal)) {
          return;
        }
        this.setCstValue(curVal);
      }
    },
    beforeDestroy: function () {
      this.destoryCom();
    }
  }
</script>

<style src="../../../static/plugins/select2/css/select2.css"></style>
<style scoped>
</style>
