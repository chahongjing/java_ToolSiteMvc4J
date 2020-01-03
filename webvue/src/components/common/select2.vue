<template>
  <div>
    <select ref='myselect' style='width:100%;'>
    </select>
  </div>
</template>

<script>
  export default {
    name: "select2",
    props: ['list', 'multiple', 'disabled', 'value'],
    computed: {},
    methods: {
      init: function () {
        var me = this;
        var $obj = $(this.$refs.myselect);
        $obj.select2(
          {
            language: "zh-CN",
            multiple: !!me.multiple,
            disabled: !!me.disabled,
            placeholder: {
              id: '-1',
              text: 'Select an option'
            },
            allowClear: true,
            data: me.list
          }
        ).val(this.value).trigger('change');
        $obj.on('change', function() {
          var val;
          if(this.multiple) {
            val = [];
            $obj.find('option:selected').each(function(index, item) {
              val.push(item.value);
            });
          } else {
            val = $(this).val();
          }
          me.$emit('input', val);
          me.$emit('change', val);
        })
      },
      destoryCom: function() {
        $(this.$refs.myselect).select2("destroy").off('change');
      }
    },
    mounted: function () {
      this.init();
    },
    watch: {
      list: function() {
        this.destoryCom();
        this.init();
      },
      'list.length': function() {
        this.destoryCom();
        this.init();
      },
      'disabled': function() {
        $(this.$refs.myselect).prop('disabled', !!this.disabled);
      }
    },
    beforeDestroy: function () {
      this.destoryCom();
    }
  }
</script>

<style scoped>
</style>
