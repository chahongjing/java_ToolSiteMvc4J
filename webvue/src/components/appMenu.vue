<template>
  <div class="menu">
    <div class="slide-menu">
      <ul class="first-menu">
        <li v-for="item in list" :class="{'selected': item.isSelected}" :title="item.name"
        @click="clickFirstMenu(item)">
        <div>
          <i :class="'fa ' + item.data.icon"></i>
          <span v-text="item.name"></span>
          <b :class="{'fa fa-angle-down':item.isSelected,'fa fa-angle-right':!item.isSelected}"></b>
        </div>
        <ul class="sub-menu show" :class="{'sub-menu show':item.isSelected}" :style="{'height':getMenuHeight(item)}">
          <li v-for="sub in item.children" :class="{'selected': sub.isSelected}"
          @click="clickSecondMenu(item, sub, $event)">
          <a href="javascript:void(0)"><i :class="'fa ' + sub.data.icon"></i>
            <span v-text="sub.name"></span>
          </a>
        </li>
      </ul>
    </li>
  </ul>
</div>
</div>
</template>

<script>
  export default {
    name: 'appMenu',
    data () {
      return {
        list: []
      }
    },
    mounted:function() {
      var me = this;
      me.axios.get('/menu/queryMenu').then(function (resp) {
        if (resp.data.status == Constant.AjaxStatus.OK) {
            var menuInfo = {first: {}, second: {}};
            for(var i = 0; i < resp.data.value.length; i++) {
                var menu = resp.data.value[i];
                menu.isSelected = false;
                if(menuInfo.first.selected && menuInfo.first.selected.id == menu.id) {
                    menuInfo.first.selected = menu;
                }
                if(menuInfo.second.selected && menuInfo.second.selected.id == menu.id) {
                    menuInfo.second.selected = menu;
                }
            }
            var parents = resp.data.value.filter(function(item) {return !item.pId;});
            for(var i = 0; i < parents.length; i++) {
                parents[i].children = resp.data.value.filter(function(item) {return item.pId == parents[i].id;});
            }
            if(menuInfo.first.selected) {
                menuInfo.first.selected.isSelected = true;
            }
            if(menuInfo.second.selected) {
                menuInfo.second.selected.isSelected = true;
            }
            me.list = parents;

            setTimeout(function() {
                $('.sub-menu').css('transition', 'height ease 0.2s');
            }, 200);
        }
      });
    },
    methods: {
      getMenuHeight: function(item) {
            return (item.isSelected ? item.children.length * 36 : 0) + 'px';
        },
      clickFirstMenu: function(item) {
          if(item.isSelected) {
              item.isSelected = false;
              return;
          }
          for(var i = 0; i < this.list.length; i++) {
              var obj = this.list[i];
              if(item == obj) {
                  item.isSelected = !item.isSelected;
              } else {
                  obj.isSelected = false;
              }
          }
      },
      clickSecondMenu: function(item, sub, $event) {
          $event.stopPropagation();
          for(var i = 0; i < this.list.length; i++) {
              var obj = this.list[i];
              for(var j = 0; j < obj.children.length; j++) {
                  var subObj = obj.children[j];
                  if(item == subObj) continue;
                  subObj.isSelected = false;
              }
          }
          sub.isSelected = true;
      }
    }
  }
</script>

<style scoped>
</style>
