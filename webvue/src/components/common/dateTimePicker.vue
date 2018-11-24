<template>
  <div class="input-group date">
    <input class="form-control" size="16" type="text" readonly v-model='dataText' />
    <div class="input-group-addon input-group-append">
      <span class="input-group-text">
        <i class='fa mr0' :class='{"fa-calendar": type == 1,"fa-clock-o": type == 2}'></i>
      </span>
    </div>
  </div>
</template>

<script>
  var defaultOption = {
    todayBtn:  true,
    autoclose: true,
    todayHighlight: true,
    // showMeridian: 1,
    fontAwesome:true,
    language:  'zh-CN',
    weekStart: 1,
    //format: 'yyyy-mm-dd hh:ii:ss',
    minView:2, //如果是到时分秒，则去掉这一项，或值改成0
    format:'yyyy-mm-dd',
  };
  var mergeDefaultOpt = {};
  $.extend(true, mergeDefaultOpt, defaultOption);

  export default {
    name: 'dateTimePicker',
    props: {option:null,mydate:null},
    model: {
      prop: 'mydate',
      event: 'returnBack'
    },
    data(){
      return {
        dataValue:null,
        dataText: null,
        mergeOption: mergeDefaultOpt,
        // 1日历，2时间
        type:1
      }
    },
    methods: {
      returnBackFn() {
        this.$emit('returnBack', this.dataValue);
      },
      initDate() {
        var me = this;
        var opt = {};
        $.extend(true, opt, defaultOption, this.option);
        this.mergeOption = opt;
        if(this.dataValue) {
          opt.initialDate = this.formatDate(this.dataValue, opt.format);
          this.dataText = opt.initialDate;
        }
        if(opt.format && (opt.format.indexOf('hh') > -1 || opt.format.indexOf('HH') > -1)) {
          this.type = 2;
        }

        $(this.$el).datetimepicker(this.mergeOption)
        .on('changeDate', function(e){
          me.dataValue = e.date;
          me.returnBackFn();
        });
      },
      formatDate:function(date, format) {
        if(!date || !format) return date;
        var format = format.replace('mm', 'MM').replace('hh', 'HH').replace('ii', 'mm');
        return date.format(format);
      }
    },
    watch: {
      mydate:{
        handler:function(curVal, oldVal) {
          this.dataValue = curVal;
          this.dataText = this.formatDate(this.dataValue, this.mergeOption.format);
          this.initDate();
        },
        deep:true
      }
    },
    mounted: function(){
      this.dataValue = this.mydate;
      this.initDate();
    }
  }
</script>

<style scoped>
  .input-group.date .input-group-addon span{width:auto;height:auto;}
</style>