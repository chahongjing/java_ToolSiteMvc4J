/**
 * Created by jyzeng on 2018/11/2.
 */
import axios from 'axios';
import Qs from 'Qs';
import toaster from '@/common/toaster';

axios.defaults.baseURL = 'http://' + process.env.baseHost + (process.env.basePort ? (':' + process.env.basePort) : '');
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
axios.defaults.withCredentials = true;

axios.defaults.paramsSerializer = function (params) {
  // return Qs.stringify(params);
  return $.param(params);
};
axios.defaults.transformRequest = [function (data) {
  if (data && !(data instanceof FormData)) {
    data = $.param(data);
    // data = Qs.stringify(data);
  }
  return data;
}]
axios.defaults.transformResponse = [function (data) {
  if (data && !(data instanceof Blob)) {
    var data = $.parseJSON(data);
    // var data = Qs.parse(data);
    if (data.status == ResultStatus.UNAUTHENTICATION.key) {
      window.location.hash = "/login";
    }
  }
  return data;
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
  var result = {data:{}};
  // 对响应错误做点什么
  if(!error.response) {
    result.data.status = ResultStatus.ERROR.key;
    result.data.message = '访问服务器失败！';
  } else if (error.response.status == 401) {
    // 用户未授权
    result.data.status = ResultStatus.UNAUTHORIZED.key;
    result.data.message = ResultStatus.UNAUTHORIZED.name;
  } else if (error.response.status == 500) {
    if (error.response.data instanceof Blob) {
      Utility.readBlobAsText(error.response.data, function (data) {
        var res = {};
        res.data = JSON.parse(data);
        toaster.error(res.message);
      });
    }
  } else {
    result.data.status = ResultStatus.ERROR.key;
    result.data.message = ResultStatus.ERROR.name;
    console.error(error);
  }
  // return Promise.resolve(result);
  return Promise.reject(result);
});

var axiosIns = {
  getAjaxUrl: function (path) {
    if (path.indexOf('http') == 0) return path;
    return this.getContext() + process.env.proxyPrefix + path;
  },
  getContext: function () {
    return process.env.context;
  },
  get: function (path, param) {
    return axios.get(this.getAjaxUrl(path), {params: param}).catch(function (resp) {
      if (resp.data.status == ResultStatus.UNAUTHORIZED.key) {
        toaster.error(resp.data.message);
      } else if(resp.data.status == ResultStatus.ERROR.key) {
        toaster.error(resp.data.message);
      }
      return resp;
    });
  },
  post: function (path, param) {
    return axios.post(this.getAjaxUrl(path), param).catch(function (resp) {
      if (resp.data.status == ResultStatus.UNAUTHORIZED.key) {
        toaster.error(resp.data.message);
      } else if(resp.data.status == ResultStatus.ERROR.key) {
        toaster.error(resp.data.message);
      }
      return resp;
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
  postDownload: function (path, param) {
    return axios.post(this.getAjaxUrl(path), param, {responseType: 'blob'}).catch(function (resp) {
      if (resp.status == ResultStatus.UNAUTHORIZED.key) {
        toaster.error(resp.message);
      }
      return resp;
    });
  },
};
export default axiosIns;

// axios.all([
//   axios.get('https://api.github.com/xxx/1'),
//   axios.get('https://api.github.com/xxx/2')
// ]) .then(axios.spread(function (userResp, reposResp) {
//   // 上面两个请求都完成后，才执行这个回调方法
//   console.log('User', userResp.data);
//   console.log('Repositories', reposResp.data);
// }));