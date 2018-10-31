<template>
  <div class="menu">
    <div class="slide-menu">
      <ul class="first-menu">
        <li v-for="item in list" v-bind:class="{'selected': item.isSelected}" :title="item.name"
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
        list: [],
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<style scoped>
</style>
