var commonService = {
	getPagerInfo: function(pager, callback) {
		var pagerInfo = {callback:callback};
		if(pager) {
			pagerInfo.pageNum = pager.pageNum;
			pagerInfo.pages = pager.pages;
		}
		return pagerInfo;
	}
};

export default commonService;