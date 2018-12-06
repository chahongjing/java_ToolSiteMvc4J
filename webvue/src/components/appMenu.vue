<template>
  <div class="menu" :class='{"hidemenu":!showMenu}'>
    <div class="slide-menu">
      <ul class="first-menu">
        <li v-for="item in list" :class="{'selected': item.isSelected}" :title="item.name"
            @click="clickFirstMenu(item)">
          <div>
            <i :class="'fa ' + item.data.icon + ' fw'"></i>
            <span v-text="item.name"></span>
            <b :class="{'fa fa-angle-down':item.isSelected,'fa fa-angle-right':!item.isSelected}"></b>
          </div>
          <ul class="sub-menu show" :class="{'sub-menu show':item.isSelected}" :style="{'height':getMenuHeight(item)}">
            <li v-for="sub in item.children" :class="{'selected': sub.isSelected}"
                @click="clickSecondMenu(item, sub, $event)">
              <a href="javascript:void(0)"><i :class="'fa ' + sub.data.icon + ' fw'"></i>
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
    mounted: function () {
      this.getMenuData();
    },
    methods: {
      getMenuData: function () {
        var list = this.$store.state.leftMenu;
        if(list && list.length > 0) {
          this.list = list;
        } else {
          var me = this;
          me.axios.get('/menu/queryMenu').then(function (resp) {
            if (resp.data.status == ResultStatus.OK.key) {
              var menuInfo = {first: {}, second: {}};
              for (var i = 0; i < resp.data.value.length; i++) {
                var menu = resp.data.value[i];
                menu.isSelected = false;
                if (menuInfo.first.selected && menuInfo.first.selected.id == menu.id) {
                  menuInfo.first.selected = menu;
                }
                if (menuInfo.second.selected && menuInfo.second.selected.id == menu.id) {
                  menuInfo.second.selected = menu;
                }
              }
              var parents = resp.data.value.filter(function (item) {
                return !item.pId;
              });
              for (var i = 0; i < parents.length; i++) {
                parents[i].children = resp.data.value.filter(function (item) {
                  return item.pId == parents[i].id;
                });
              }
              if (menuInfo.first.selected) {
                menuInfo.first.selected.isSelected = true;
              }
              if (menuInfo.second.selected) {
                menuInfo.second.selected.isSelected = true;
              }
              me.list = parents;
              me.$store.commit("SET_MENU", me.list);
            }
          });
          this.$nextTick(function () {
            setTimeout(function () {
              $('.sub-menu').css('transition', 'height ease 0.2s');
            }, 200);
          })
        }
      },
      getMenuHeight: function (item) {
        return (item.isSelected ? item.children.length * 36 : 0) + 'px';
      },
      clickFirstMenu: function (item) {
        if (item.isSelected) {
          item.isSelected = false;
          this.$store.commit("SET_MENU", this.list);
          return;
        }
        for (var i = 0; i < this.list.length; i++) {
          var obj = this.list[i];
          if (item == obj) {
            item.isSelected = !item.isSelected;
          } else {
            obj.isSelected = false;
          }
        }
        this.$store.commit("SET_MENU", this.list);
      },
      clickSecondMenu: function (item, sub, $event) {
        $event.stopPropagation();
        if (!sub.isSelected) {
          this.$store.commit("CLEAR_BREADCRUMB");
        }
        for (var i = 0; i < this.list.length; i++) {
          var obj = this.list[i];
          for (var j = 0; j < obj.children.length; j++) {
            var subObj = obj.children[j];
            if (item == subObj) continue;
            subObj.isSelected = false;
          }
        }
        sub.isSelected = true;
        this.$store.commit("SET_MENU", this.list);
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
  .menu {
    position: relative;
  }

  .slide-menu {
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
  }

  .tog {
    position: absolute;
    top: 50%;
    right: -15px;
    margin-top: -15px;
    width: 15px;
    border-radius: 0px 15px 15px 0px;
    height: 30px;
    line-height: 30px;
    cursor: pointer;
    background-color: transparent;
    transition: 0.3s;
  }

  .tog i {
    color: transparent;
    font-size: 18px;
    margin-left: 3px;
    transition: 0.3s;
  }

  .menu:hover .tog,.menu.hidemenu .tog {
    background-color: #71a;
  }

  .menu:hover .tog i,.menu.hidemenu .tog i {
    color: #fff;
  }

  .hidemenu {
    width: 0px;
    border: 0;
  }

  .hidemenu i.fa-angle-left:before {
    content: "\f105";
  }
</style>
