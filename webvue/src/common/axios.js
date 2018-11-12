/**
 * Created by jyzeng on 2018/11/2.
 */
import axios from 'axios';

axios.defaults.baseURL = process.env.baseUrl;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.headers.common["x-requested-with"] = "XMLHttpRequest";
axios.defaults.withCredentials = true;

axios.defaults.paramsSerializer = function (params) {
  return $.param(params);
};
axios.defaults.transformRequest  = [function (data) {
  if (data && !(data instanceof FormData)) {
    data = $.param(data);
  }
  return data;
}]
axios.defaults.transformResponse = [function (data) {
  var resp = $.parseJSON(data);
  if(resp.status == window.Constant.AjaxStatus.UNAUTHENTICATION){
    window.location.hash = "/login";
  }
  return resp;
}]
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  return response;
}, function (error) {
  // 对响应错误做点什么
  console.error(error);
  return Promise.resolve({data: {status: window.Constant.AjaxStatus.ERROR, message: 'error reuqest!'}});
  //return Promise.reject(error);
});

var axiosIns = {
  getAjaxUrl: function (path) {
    if(path.indexOf('http') == 0) return path;
    return this.getContext() + process.env.proxyPrefix + path;
  },
  getContext: function () {
    return '';
  },
  get: function (path, param) {
    return axios.get(this.getAjaxUrl(path), {params: param});
  },
  post: function (path, param) {
    return axios.post(this.getAjaxUrl(path), param);
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
  }
};
export default axiosIns;