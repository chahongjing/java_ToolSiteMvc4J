import Vue from 'vue';
import Toastr from 'vue-toastr';
import 'vue-toastr/dist/vue-toastr.css'

const VueComponent = Vue.extend(Toastr);
const vm = new VueComponent().$mount();
vm.defaultProgressBar = false;
let init = false;


if (!init) {
	document.body.appendChild(vm.$el);
	init = true;
}

const toaster = {
	success: function(msg) {
		vm.s(msg);
	},
	warning: function(msg) {
		vm.w(msg);
	},
	info: function(msg) {
		vm.i(msg);
	},
	error: function(msg) {
		vm.e(msg);
	}
};

export default toaster;