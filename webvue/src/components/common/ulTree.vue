<template>
  <ul>
    <tree-item v-for='model in recursionList' :model="model" :option='mergeOpt' :key='model.id'></tree-item>
  </ul>
</template>

<script>
import treeItem from '@/components/common/treeItem';
  var defaultOption = {
    id: 'defaulttree',
    checktype: 'radio',
    openIcon: 'fa-minus-circle',
    closeIcon: 'fa-plus-circle',
    leafIcon: 'fa-lightbulb-o',
    folderIcon: 'fa-heart'
  };
  var mergeDefaultOpt = {};
  $.extend(true, mergeDefaultOpt, defaultOption);

  export default {
    name: 'ulTree',
    props: {
      plainList: Array,
      option: Object
    },
    data: function(){
      return {
        mergeOpt: mergeDefaultOpt,
        recursionList: []
      }
    },
    methods: {
      clickItem: function(item) {
        if(this.option.beforeClick && this.option.beforeClick(item) === false) {
          return false;
        }
        // 点击操作
        item.selected = true;
        for(var i = 0; i < this.plainList.length; i++) {
          if(this.plainList[i] == item) continue;
          this.plainList[i].selected = false;
        }
        this.option.afterClick && this.option.afterClick(item);
      },
      checkedItem: function(item) {
        this.option.afterCheck && this.option.afterCheck(item);
      },
      handlerData: function() {
        this.recursionList = [];
        this.plainToRecursionList(this.plainList || []);
        var opt = {};
        $.extend(true, opt, mergeDefaultOpt, this.option);
        opt.clickItem = this.clickItem;
        opt.checkedItem = this.checkedItem;
        this.mergeOpt = opt;
      },
      plainToRecursionList: function(plainList) {
        for (var i = 0; i < plainList.length; i++) {
          var parents = plainList.filter(item => item.id == plainList[i].pId);
          plainList[i].children = [];
          if(parents && parents.length) {
            parents[0].children.push(plainList[i]);
          } else {
            this.recursionList.push(plainList[i]);
          }
        }
      }
    },
    mounted: function() {
      this.handlerData();
    },
    watch: {
      list: function(curVal, oldVal){
        this.handlerData();
      }
    },
    components: {treeItem}
  };
</script>
