axios.defaults.headers.common["x-requested-with"] = "XMLHttpRequest";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.paramsSerializer = function(params) {
    return $.param(params);
};
axios.defaults.transformResponse = [function (data) {
    return $.parseJSON(data);
}]
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    if (config.method === 'post') {
        if(!(config.data instanceof FormData)) {
            config.data = $.param(config.data);
        }
    }
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

Vue.component('apphead', {
    template: '#appHead',
    methods: {
        // 退出登录
        logout: function() {
            var me = this;
            me.commonSrv.get('/userinfo/logout').then(function (resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    sessionStorage.clear();
                    window.location = me.commonSrv.getContext();
                } else {
                    alert(resp.msg);
                }
            });
        },
        showLoading: function (title) {
            $scope.model.loadingReload++;
            if (title) {
                $scope.model.loadingText = 'title';
            }
            $scope.model.isShowLoading = true;
        },
        hideLoading: function () {
            $scope.model.loadingText = '';
            $scope.model.isShowLoading = false;
        },
        // 返回
        goBack: function () {
            history.go(-1);
        }
    }
});
Vue.component('appmenu', {
    data: function () {
        return {list: []};
    },
    template: '#appMenu',
    methods: {
        getMenuHeight: function(item) {
            return (item.isSelected ? item.children.length * 36 : 0) + 'px';
        },
        afterRender: function() { },
        clickFirstMenu: function(item) {
            if(item.isSelected) {
                item.isSelected = false;
                return;
            }
            for(var i = 0; i < this.list.length; i++) {
                var obj = this.list[i];
                if(item == obj) {
                    item.isSelected = !item.isSelected;
                } else {
                    obj.isSelected = false;
                }
            }
            var menuInfo = JSON.parse(sessionStorage.getItem("menuInfo"));
            menuInfo.first.selected = item;
            sessionStorage.setItem("menuInfo", JSON.stringify(menuInfo));
        },
        clickSecondMenu: function(item, sub, $event) {
            $event.stopPropagation();
            for(var i = 0; i < this.list.length; i++) {
                var obj = this.list[i];
                for(var j = 0; j < obj.children.length; j++) {
                    var subObj = obj.children[j];
                    if(item == subObj) continue;
                    subObj.isSelected = false;
                }
            }
            sub.isSelected = true;
            var menuInfo = JSON.parse(sessionStorage.getItem("menuInfo"));
            menuInfo.second.selected = sub;
            sessionStorage.setItem("menuInfo", JSON.stringify(menuInfo));
            window.location = ctx + sub.data.url;
        },
        test:function() {
            var formData = new FormData();
            formData.append("a", 123)
            formData.append("d", new Date())
            //formData.append("myfile", $('#myfile')[0].files[0]);
            this.commonSrv.postFormData('/learn/testajax', formData)
        }
    },
    computed: {

    },
    mounted: function () {
        var me = this;
        var param = {test: new Date(), test2: (new Date()).format("yyyy-MM-dd HH:mm:ss")};
        param.test3 = 'Sat Nov 25 2017 00:00:00 GMT+0800 (中国标准时间)';
        param.test4 = [1, 2];
        // me.commonSrv.get('/test/getMenu',param)
        // //axios.get(ctx + '/test/getMenu11')
        // .then(function(resp) {
        //     // console.log(resp.data);
        //     // console.log(resp.status);
        //     // console.log(resp.statusText);
        //     // console.log(resp.headers);
        //     // console.log(resp.config);
        // }).catch(function(thrown) {
        //     if (axios.isCancel(thrown)) {
        //         console.log('Request canceled', thrown.message);
        //     } else {
        //         // 处理错误
        //     }
        // });
        me.commonSrv.post('/menu/queryMenu', param).then(function (resp) {
            if (resp.data.status == Constant.AjaxStatus.OK) {
                var menuInfo = sessionStorage.getItem("menuInfo");
                if(!menuInfo) {
                    menuInfo = {first: {}, second: {}};
                } else {
                    menuInfo = JSON.parse(menuInfo);
                }
                for(var i = 0; i < resp.data.value.length; i++) {
                    var menu = resp.data.value[i];
                    menu.isSelected = false;
                    if(menuInfo.first.selected && menuInfo.first.selected.id == menu.id) {
                        menuInfo.first.selected = menu;
                    }
                    if(menuInfo.second.selected && menuInfo.second.selected.id == menu.id) {
                        menuInfo.second.selected = menu;
                    }
                }
                var parents = resp.data.value.filter(function(item) {return !item.pId;});
                for(var i = 0; i < parents.length; i++) {
                    parents[i].children = resp.data.value.filter(function(item) {return item.pId == parents[i].id;});
                }
                // if(!menuInfo.first.selected && parents.length > 0) {
                //     menuInfo.first.selected = parents[0];
                // }
                // if(!menuInfo.second.selected && parents.length > 0 && parents[0].children.length > 0) {
                //     menuInfo.second.selected = parents[0].children[0];
                // }
                if(menuInfo.first.selected) {
                    menuInfo.first.selected.isSelected = true;
                }
                if(menuInfo.second.selected) {
                    menuInfo.second.selected.isSelected = true;
                }
                sessionStorage.setItem("menuInfo", JSON.stringify(menuInfo));
                me.list = parents;

                setTimeout(function() {
                    $('.sub-menu').css('transition', 'height ease 0.2s');
                }, 200);
            } else {
                alert(resp.msg);
            }
        });
    }
});
Vue.component('pagination', {
    props: ['pagerInfo', 'click'],
    data: function () {
        return {list: []};
    },
    template: '#pagination',
    created: function () {
        var pagerInfo = this.$attrs['pagerinfo'];
        var list = this.handData(pagerInfo);
        this.list = list;
    },
    computed: {
        pagerInfo: function () {
            var pagerInfo = this.$attrs['pagerinfo'];
            if(!pagerInfo) {
                pagerInfo = {
                    isFirstPage:false,
                    pageNum:1,
                    lastPage: 10,
                    isLastPage:false
                };
            }
            return pagerInfo;
        },
        getDataabc: function() {
            var list = [];
            var obj = {name: '上一页', value: 1, isDisabled: false};
            list.push(obj);
            return list;
        }
    },
    methods: {
        handData: function (pagerInfo) {return;
            var list = [], start = pagerInfo.pageNum - 2, end = pagerInfo.pageNum + 2,
                pre = pagerInfo.pageNum - 1,
                next = pagerInfo.pageNum + 1;
            if (start < 1) start = 1;
            if (end > pagerInfo.pages) end = pagerInfo.pages;
            if (pre < 1) pre = 1;
            if (next > pagerInfo.pages) next = pagerInfo.pages;
            var obj;
            obj = {name: '上一页', value: pre, isDisabled: false};
            if (obj.value == pagerInfo.pageNum) {
                obj.isDisabled = true;
            }
            list.push(obj);
            for (var i = start; i < end + 1; i++) {
                obj = {name: i, value: i, isDisabled: false};
                if (pagerInfo.pageNum == i) {
                    obj.isCurrent = true;
                    obj.isDisabled = true;
                }
                list.push(obj);
            }
            obj = {name: '下一页', value: next, isDisabled: false};
            if (obj.value == pagerInfo.pageNum) {
                obj.isDisabled = true;
            }
            list.push(obj);
            return list;
        },
        getClass: function (pageItem) {
            return {'isCurrent': pageItem.isCurrent, 'disabled': pageItem.isDisabled || pageItem.isCurrent}
        },
        goPage: function (page) {
            if (page.isDisabled) return;
            this.click && this.click(page);
        }
    }
});

YesNo={
    YES:1,
    NO:0
}