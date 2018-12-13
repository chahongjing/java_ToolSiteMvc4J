<template>
  <li class='tree-item' :class='{"folder":isFolder}'>
    <div class='tree-item-content'>
      <span class='tree-item-icon' @click.stop="toggleItem(model)">
        <i class="fa fw mr0" v-if="isFolder" :class='getFolderIcon'></i>
      </span>
      <span class='tree-item-text' :class='{"selected":model.selected}' @click.stop='option.clickItem(model)'>
       <label class="radio_checkbox" @click.stop='stopEvent()'>
          <input :type='option.checktype' :name='"treeitem_" + option.id' v-model="option.checkedResult" :value='model' @click.stop='stopEvent()' @change.stop='option.checkedItem(model)' />
          <i></i>
          <span>&nbsp;</span>
        </label>
       <span class='tree-item-text-icon'>
         <i class="fa fw mr0" :class='getItemIcon'></i>
       </span>
       <span v-html='model.name'></span>
     </span>
   </div>
   <ul v-show="model.isOpen" v-if="isFolder">
    <tree-item v-for="model in model.children" :model="model" :option='option' :key="model.id">
    </tree-item>
  </ul>
</li>
</template>

<script>
  export default {
    name: 'treeItem',
    props: {
      model: Object,
      option:Object
    },
    data: function () {
      return {
        open: false
      }
    },
    computed: {
      isFolder: function () {
        return this.model.children && this.model.children.length
      },
      getFolderIcon: function() {
        var obj = {};
        if(this.model.isOpen && this.option.openIcon) {
          obj[this.option.openIcon] = true;
        } else if(this.option.closeIcon) {
          obj[this.option.closeIcon] = true;
        }
        return obj;
      },
      getItemIcon: function(item) {
        var obj = {};
        if(item.model.icon) {
          obj[item.model.icon] = true;
        } else if(this.isFolder && this.option.folderIcon) {
          obj[this.option.folderIcon] = true;
        } else if(this.option.leafIcon) {
          obj[this.option.leafIcon] = true;
        }
        return obj;
      }
    },
    methods: {
      toggleItem: function (item) {
        if (this.isFolder) {
          if(this.option.beforeOpenClose && this.option.beforeOpenClose(item) === false) {
            return;
          }
          this.model.isOpen = !this.model.isOpen
          this.option.afterOpenClose && this.option.afterOpenClose(item);
        }
      },
      stopEvent:function() {}
    }
  };
</script>

<style scoped>
  .tree-item {
    cursor: pointer;
  }
  .tree-item-content{
    font-size:0;
  }
  .tree-item-content span{
    font-size:14px;
    display:inline-block;
    height: 100%;
    padding-top:1px;
    padding-bottom:1px;
  }
  span.tree-item-text{font-size:0;padding:0 3px;}
  span.tree-item-text.selected{background-color: #83b5f3;border-radius:2px;}
  .tree-item-icon{
    width:20px;
    text-align: center;
  }
  .tree-item-text-icon{width:20px;text-align:center;}

  ul {
    padding-left: 1em;
  }
  /** 水平方向连线 */
  li{position:relative;}
  li:before,li:after{content:' ';position:absolute;border:0 dotted #999;}
  ul ul > li:after{top:12px;left:-5px;width:20px;border-top-width:1px;}
  ul ul > li.folder:after{width:5px;}
  /** 垂直方向连线 */
  ul > li:before{content:' ';position:absolute;top:0;left:-5px;height:100%;border-left-width:1px;}
  .mytree > li:before{border-left-width:0;}
  ul > li:last-child:before{content:' ';position:absolute;top:0;left:-5px;height:13px;border-left-width:1px;}
  .mytree > li:last-child:before{border-left-width:0;}
  .radio_checkbox span{padding:0;margin-left:0;height:0;line-height:inherit;}
  .radio_checkbox input + i{left:0;top:3px;}
  .radio_checkbox input + i:after{left:2;}
</style>
