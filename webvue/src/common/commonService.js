var commonService = {
  getPagerInfo: function (pager, callback) {
    pager.callback = callback;
    //pager.loading = false;
    return pager;
  }
};

export default commonService;
