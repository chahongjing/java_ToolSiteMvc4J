var commonService = {
  getPagerInfo: function (pager, callback) {
    pager.callback = callback;
    return pager;
  },
  getPagerModel:function(pagerId) {
  	var pms = sessionStorage.getItem('pagerModel');
  	var allModel, model;
	if(pms) {
		allModel = JSON.parse(pms);
	} else {
		allModel = {};
		model = {};
		model.pageNum = 1;
		model.pageSize = 10;
		model.filter = {};
		allModel[pagerId] = model;
	}
	return allModel[pagerId];
  },
  setPagerModel: function(pagerId, pagerModel) {
  	var pms = sessionStorage.getItem('pagerModel');
  	var model;
	if(pms) {
		model = JSON.parse(pms);
	} else {
		model = {};
	}
	model[pagerId] = pagerModel;
  	sessionStorage.setItem('pagerModel', JSON.stringify(model));
  },
  clearPagerModel: function(pagerId) {
  	if(pagerId) {
  		var pms = sessionStorage.getItem('pagerModel');
  		if(pms) {
  			var obj = JSON.parse(pms);
  		    delete obj[pagerId];
  		}
  	} else {
  	    sessionStorage.removeItem('pagerModel');	
  	}
  }
};

export default commonService;
