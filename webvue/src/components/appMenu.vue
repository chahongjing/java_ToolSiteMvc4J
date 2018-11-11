<template>
  <div class="menu" :class='{"hidemenu":!showMenu}'>
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
    <span class='tog' @click='toggleMenu()'>
      <i class='fa fa-angle-left'></i>
    </span>
  </div>
</template>

<script>
  export default {
    name: 'appMenu',
    data () {
      return {
        showMenu: true,
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
          this.$router.push({path: sub.data.url});
      },
      toggleMenu() {
        var temp = !this.showMenu;
        this.$parent.showMenu = temp;
        this.showMenu = temp;
      }
    }
  }
</script>

<style scoped>
.menu{position:relative;}
.slide-menu{width:100%;height:100%;overflow-x: hidden;overflow-y: auto;}
.tog{position:absolute;top:50%;right:-10px;margin-top:-25px;width: 10px;border-radius: 0px 5px 5px 0px;
    height: 50px;line-height: 50px;cursor:pointer;background-color: transparent;transition: 0.3s;}
.tog i{color:transparent;font-size:18px;margin-left:1px;transition: 0.3s;}
.menu:hover .tog{background-color: #3e72d2;}
.menu:hover .tog i{color:#fff;}
.hidemenu{width:0px;}
.hidemenu i.fa-angle-left:before{content:"\f105";}
</style>
