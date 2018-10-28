/**
 * Created by Administrator on 2018/5/24.
 */
Vue.prototype.commonSrv = {
    getAjaxUrl: function (path) {
        return this.getContext() + path;
    },
    getContext: function () {
        return ctx;
    },
    get: function (path, param) {
        return axios.get(this.getAjaxUrl(path), {params: param});
        return $.ajax({
            type: 'get',
            url: this.getAjaxUrl(path),
            data: param
        });
    },
    post: function (path, param) {
        return axios.post(this.getAjaxUrl(path), param);
        return $.ajax({
            type: 'post',
            url: this.getAjaxUrl(path),
            data: param
        });
    },
    /**
     * 以formdata的形式发起get ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    getFormData: function (path, formData) {
        return axios({
            url: this.getAjaxUrl(path),
            method: 'get',
            data: formData,
            transformRequest: [function (data) {
                return $.param(data);
            }],
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    },
    /**
     * 以formdata的形式发起post ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    postFormData: function (path, formData) {
        return axios({
            url: this.getAjaxUrl(path),
            method: 'post',
            data: formData,
            // transformRequest: [function (data) {
            //     return $.param(data);
            // }],
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    },
    /**
     * 以formdata的形式发起post ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    postFormDataWithFile: function (path, formData) {
        var config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        return axios.post(this.getAjaxUrl(path), formData, config);
        return axios({
            url: this.getAjaxUrl(path),
            method: 'post',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
    }
};