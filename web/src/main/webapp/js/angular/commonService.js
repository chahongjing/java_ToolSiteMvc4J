/**
 * Created by chahongjing on 2017/6/10.
 */
'use strict';
app.service('commonService', ['$http', function ($http) {
    /**
     * get请求
     * @param url 服务地址
     * @param param 参数
     * @returns {*}
     */
    this.get = function (url, param) {
        return $http.get(url, {params: param});
    };

    /**
     * post请求
     * @param url 服务地址
     * @param postParam post参数
     * @param urlParam url参数
     * @returns {*}
     */
    this.post = function (url, postParam, urlParam) {
        return $http.post(url, postParam, {params: urlParam || {}});
    };
}]);