<template>
  <div>
    <div class="input-group date form_datetime">
      <input class="form-control" size="16" type="text" readonly>
      <div class="input-group-addon input-group-append">
        <span class="input-group-text"><i class='fa fa-calendar mr0'></i></span>
      </div>
    </div>
    {{date}}
  </div>
</template>

<script>
  export default {
    name: 'datetimepicker',
    data () {
      return {
        date: null
      }
    },
    model: {
      prop: 'date',
      event: 'returnBack'
    },
    methods: {
      returnBackFn() {
        this.$emit('returnBack', this.date);
      },
      initDate() {
        var me = this;
        $('.form_datetime').datetimepicker({
          language:  'zh-CN',
          weekStart: 1,
          todayBtn:  1,
          autoclose: 1,
          todayHighlight: 1,
          startView: 2,
          forceParse: 0,
          showMeridian: 1,
          fontAwesome:true,
          initialDate: me.date
        }).on('changeDate', function(e){
          me.date = e.date;
          me.returnBackFn();
        });
      }
    },
    watch: {
      date:{
        handler:function(curVal, oldVal) {
          this.initDate();
        },
        deep:true
      }
    },
    mounted: function(){
      this.initDate();
    }
  }
</script>

<style scoped>
  .input-append.date .add-on i, .input-prepend.date .add-on i, .input-group.date .input-group-addon span{width:auto;height:auto;}
</style>