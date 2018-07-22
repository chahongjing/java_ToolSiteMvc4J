/**
 * Created by Administrator on 2018/5/24.
 */
Vue.prototype.commonSrv = {
    getAjaxUrl: function (path) {
        return this.getContext() + path + '.do';
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
    getFormData: function (path, formData) {
        return $.ajax({
            type: 'get',
            url: this.getAjaxUrl(path),
            data: formData,
            processData: false,
            contentType: false
        });
    },
    postFormData: function (path, formData) {
        return $.ajax({
            type: 'post',
            url: this.getAjaxUrl(path),
            data: formData,
            processData: false,
            contentType: false
        });
    }
};