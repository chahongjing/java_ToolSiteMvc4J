export default {
  focus: {
    // 当绑定元素插入到 DOM 中。

    inserted: function (el) {
      // 聚焦元素
      el.focus()
    }
    //也可以用update,update就是当页面有操作的时候就触发，比如点击按钮，输入框输入都算。
    //有一个要注意的就是，就是你页面有几个input,比如一个input绑定了v-focus,一个没有绑定。你在没绑定v-focus的input输入时，绑定了v-focus的input就是自动获取焦点，这是个bug.
    //update: function (el) {
    //el.focus()
    //}
  },
  tooltip: {
    update: function (el, binding, vnode) {
      // if (binding.oldValue === binding.value) return;
      var msg = binding.value;
      if (msg === null || msg === undefined) {
        msg = '';
      }
      var $el = $(el);
      try{
        $el.tooltip('dispose');
      } catch(e){}
      $el.tooltip({html: true, title: msg});
    }
  }
}
