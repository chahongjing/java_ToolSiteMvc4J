/**
 * Created by chahongjing on 2017/6/10.
 */
'use strict';
app.service('commonService', ['$http', function ($http) {
    /**
     * 获取ajax url，主要是添加context
     * @param path 路径
     * @returns {*}
     */
    this.getAjaxUrl = function (path) {
        return this.getContext() + path;
    };

    /**
     * 获取当前应用context
     */
    this.getContext = function () {
        return ctx;
    };

    /**
     * get ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    this.get = function (path, param) {
        return $http.get(this.getAjaxUrl(path), {params: param});
    };

    /**
     * post ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    this.post = function (path, param) {
        return $http.post(this.getAjaxUrl(path), param);
    };

    /**
     * 以formdata的形式发起get ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    this.getFormData = function (path, formData) {
        return $http({
            url: this.getAjaxUrl(path),
            method: 'get',
            data: formData,
            headers: {'Content-Type': undefined},
            processData: false,
            transformRequest: angular.identity
        });
    };

    /**
     * 以formdata的形式发起post ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    this.postFormData = function (path, formData) {
        return $http({
            url: this.getAjaxUrl(path),
            method: 'post',
            data: formData,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        });
    };

    /**
     * 以formdata的形式发起post ajax请求
     * @param path 路径
     * @param param 参数，object类型
     * @returns {*}
     */
    this.postDownload = function (path, param) {
        return $http.post(this.getAjaxUrl(path), param, {responseType: 'blob'});
    };

    /**
     * 获取文件名称
     * @param path
     * @returns {*}
     */
    this.getFileName = function (path) {
        if (path.lastIndexOf("\\") == -1) return "";
        var pos = path.lastIndexOf("\\");
        return path.substring(pos + 1);
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @returns {*}
     */
    this.getFileExtension = function (fileName) {
        if (!fileName || fileName.indexOf(".") == -1) return '';
        var start = fileName.lastIndexOf(".");
        var end = fileName.length;
        return fileName.substring(start, end);
    }

    /**
     * 获取分布信息
     * @param data
     * @returns {{}}
     */
    this.getPagerInfo = function (data) {
        var pager = {};
        if (!data) return pager;
        $.extend(pager, data);
        delete pager.list;
        return pager;
    }

    this.showLoading = function (title) {
        if (title) {
            $('.loadingmask .info p').text(title);
        }
        $('.loadingmask').show();
    }

    this.hideLoading = function () {
        $('.loadingmask').hide();
        $('.loadingmask .info p').text('数据处理中，请等待...');
    }

    /**
     * 树操作
     * @type {{getTree: treeUtils.getTree, getNodeById: treeUtils.getNodeById, getNodes: treeUtils.getNodes, checkNode: treeUtils.checkNode, checkNodes: treeUtils.checkNodes, selectNode: treeUtils.selectNode, selectNodes: treeUtils.selectNodes, updateNode: treeUtils.updateNode, updateDiyDom: treeUtils.updateDiyDom, expandNodes: treeUtils.expandNodes}}
     */
    this.treeUtils = {
        /**
         * 获取树
         * @param treeId 树id
         * @returns {*} 返回ztree树对象
         */
        getTree: function (treeId) {
            if (typeof treeId == "object") {
                return treeId;
            } else {
                return $.fn.zTree.getZTreeObj(treeId);
            }
        },
        /**
         * 按id获取节点
         * @param treeId 树id
         * @param nodeId 节点id
         * @returns {*} 返回节点
         */
        getNodeById: function (treeId, nodeId) {
            return this.getTree(treeId).getNodeByParam("id", nodeId);
        },
        /**
         * 获取所有节点
         * @param treeId 树id
         * @returns {*} 返回所有节点
         */
        getNodes: function (treeId) {
            var tree = this.getTree(treeId);
            return tree.transformToArray(tree.getNodes());
        },
        /**
         * 勾选节点
         * @param treeId 树id
         * @param treeNode 要操作的节点
         * @param checked 是否选中
         * @param checkTypeFlag 是否联动(默认为true)
         * @param callbackFlag 是否执行回调(默认为false)
         */
        checkNode: function (treeId, treeNode, checked, checkTypeFlag, callbackFlag) {
            this.checkNodes(treeId, [treeNode], checked, checkTypeFlag, callbackFlag);
        },
        /**
         * 勾选多个节点
         * @param treeId 树id
         * @param treeNodes 要操作的节点
         * @param checked 是否选中
         * @param checkTypeFlag 是否联动(默认为true)
         * @param callbackFlag 是否执行回调(默认为false)
         */
        checkNodes: function (treeId, treeNodes, checked, checkTypeFlag, callbackFlag) {
            if (!(checkTypeFlag === false)) {
                checkTypeFlag = true;
            }
            var tree = this.getTree(treeId);
            treeNodes.forEach(function (treeNode) {
                tree.checkNode(treeNode, checked, checkTypeFlag, callbackFlag);
            });
        },
        /**
         * 选中节点
         * @param treeId 树id
         * @param treeNode 要操作的节点
         * @param addFlag 是否追加选中(默认为false)
         * @param isSilent 是否滚动到操作的节点(默认为false)
         */
        selectNode: function (treeId, treeNode, addFlag, isSilent) {
            this.selectNodes(treeId, [treeNode], addFlag, isSilent);
        },
        /**
         * 选中多个节点
         * @param treeId 树id
         * @param treeNodes 要操作的节点
         * @param addFlag 是否追加选中(默认为false)
         * @param isSilent 是否滚动到操作的节点(默认为false)
         */
        selectNodes: function (treeId, treeNodes, addFlag, isSilent) {
            var tree = this.getTree(treeId);
            treeNodes.forEach(function (treeNode) {
                tree.selectNode(treeNode, addFlag, isSilent);
            });
        },
        /**
         * 更新节点
         * @param treeId 树id
         * @param nodeInfo 树节点
         * @param checkTypeFlag 是否联动更新父子节点
         */
        updateNode: function (treeId, nodeInfo, checkTypeFlag) {
            var tree, node;
            tree = this.getTree(treeId);
            if (typeof nodeInfo == "object") {
                node = nodeInfo;
            } else {
                node = this.getNodeById(tree, nodeInfo);
            }
            if (!node) return;
            tree.updateNode(node, checkTypeFlag);
        },
        /**
         * 更新节点自定义html
         * @param treeId 树id
         * @param nodeInfo 树节点
         */
        updateDiyDom: function (treeId, nodeInfo) {
            var tree, node;
            tree = this.getTree(treeId);
            if (typeof nodeInfo == "object") {
                node = nodeInfo;
            } else {
                node = this.getNodeById(tree, nodeInfo);
            }
            if (!node) return;
            tree.setting.view && tree.setting.view.addDiyDom && tree.setting.view.addDiyDom(treeId, node);
        },
        /**
         * 展开节点
         * @param treeId 树id
         * @param treeNodes 树节点
         * @param expandFlag 是否展开
         * @param sonSign 子节点是否进行相同操作(默认为false)
         * @param focus 是否滚动(默认为false)
         * @param callbackFlags 是否触发回调(默认为false)
         * @returns {Array} 返回实际操作情况true：展开；false：折叠；null：不是父节点
         */
        expandNodes: function (treeId, treeNodes, expandFlag, sonSign, focus, callbackFlags) {
            var obj = this.getTree(treeId);
            var result = [];
            treeNodes.forEach(function (treeNode) {
                result.push(obj.expandNode(treeNode, expandFlag, sonSign, focus, callbackFlags));
            });
            return result.length == 1 ? result[0] : result;
        }
    };
}]);