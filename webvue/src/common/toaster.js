import Toastr from 'vue-toastr';

const toaster = {
	success: function(msg) {
		Toastr.s(msg);
		alert(msg);
	},
	warning: function(msg) {
		alert(msg);
	},
	info: function(msg) {
		alert(msg);
	},
	error: function(msg) {
		alert(msg);
	}
};

export default toaster;