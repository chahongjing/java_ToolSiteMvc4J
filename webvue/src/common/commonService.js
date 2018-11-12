var commonService = {
	getPagerInfo: function(pager, callback) {
		var pagerInfo = {callback:callback};
		if(pager) {
			pagerInfo.pageNum = pager.pageNum;
			pagerInfo.pageSize = pager.pageSize;
			pagerInfo.pages = pager.pages;
			pagerInfo.total = pager.total;
		}
		return pagerInfo;
	}
};

export default commonService;