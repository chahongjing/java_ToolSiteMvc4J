var commonService = {
  getPagerInfo: function (pager, callback) {
    pager.callback = callback;
    return pager;
  }
};

export default commonService;
