import Vue from 'vue'
import customModal from '@/components/modal/customModal'

const modal = {
  customModal: function (options) {
    var tempOption = {};
    $.extend(true, tempOption, options);
    var VueComponent = Vue.extend(customModal);
    var vm = new VueComponent().$mount();
    Object.assign(vm, tempOption);
    document.body.appendChild(vm.$el);
    return vm.show();
  }
};

export default modal;
