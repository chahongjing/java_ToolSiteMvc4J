<template>
  <div>
    <form class='myform form-label-w120 block-form-group form-group-w300 w300 mt20'>
      <div class="form-group">
        <label class="form-label">文件：</label>
        <div class="form-content">
          <input type="file" id='testFile' class="form-control" multiple placeholder="文件" autofocus v-focus/>
        </div>
        <div class='form-info'>
          <i class='fa'></i>
        </div>
      </div>
      <div class="form-group text-right mb0">
        <button type="button" class="btn btn-purple mr5" @click="ajaxUploadFile">上传</button>
        <button type="button" class="btn btn-purple mr5" @click="ajaxDownload">下载</button>
      </div>
    </form>
    <div class='trcon'>
      <table id='mainRongQi' class='w100p'>
        <thead>
          <tr>
            <th>
              内容
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for='rongQi in list'>
            <td :data-id='rongQi.id' class='sortable a'>
              <span v-for='item in rongQi.dataList' :data-id='item.id' :data-pid='rongQi.id'>
                <span v-text='item.name + "" + item.xuhao'></span>
                <i class='fa fa-arrows pointer'></i>
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class='trcon'>
      <table id='mainRongQi2' class='w100p'>
        <thead>
          <tr>
            <th>
              内容
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for='rongQi in list2'>
            <td :data-id='rongQi.id' class='sortable b'>
              <span v-for='item in rongQi.dataList' :data-id='item.id' :data-pid='rongQi.id'>
                <span v-text='item.name + "" + item.xuhao'></span>
                <i class='fa fa-arrows pointer'></i>
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <label v-tooltip='html'>提示</label>

    <div class='mt20'>
      <ultree class='w300 mytree' :plainList="treeData" :option='treeoption'></ultree>
    </div>
    <hr>
    {{this.treeoption && this.treeoption.checkedResult && this.treeoption.checkedResult.name|| this.treeoption.checkedResult.map(item => item.name)}}

    <div class='aa'>
    </div>
  </div>
</template>

<script>
// demo data
var treeDataList = [
{id:1, name: '语文', pId: null, selected:false,isOpen:false,isLeaf:false},
{id:2, name: '数学', pId: null, selected:false,isOpen:false,isLeaf:false},
{id:3, name: '英语<b style="color:red;">我的html</b>', pId: null, selected:true,isOpen:false,isLeaf:false},
{id:4, name: '注音', pId:1, selected:false,isOpen:false,isLeaf:false},
{id:5, name: '成语错字识别', pId:1,selected:false,isOpen:false,isLeaf:false},
{id:6, name: '正确的词', pId:1,selected:false,isOpen:false,isLeaf:false},
{id:7, name: '四字成语', pId:5,selected:false,isOpen:false,isLeaf:false},
{id:8, name: '七言绝句', pId:5,selected:false,isOpen:false,isLeaf:false},
{id:9, name: '三角函数', pId:2,selected:true,isOpen:false,isLeaf:false},
{id:10, name: '立体几何', pId:2,selected:false,isOpen:false,isLeaf:false},
{id:11, name: '诗词', pId:8,selected:false,isOpen:false,isLeaf:false}
];
var treeoption = {
  id:'abc',
  openLevel: 2,
  checktype: 'checkbox',
  checkedResult: [treeDataList[0], treeDataList[6]],
  // checktype: 'radio',
  // checkedResult: treeDataList[0],
  beforeOpenClose:function(item){console.log('beforeOpenClose');console.log(item);},
  afterOpenClose:function(item){console.log('afterOpenClose');console.log(item);},
  beforeClick:function(item){console.log('beforeClick');console.log(item);},
  afterCheck: function(item){console.log('afterCheck');console.log(item);}
}
treeoption.afterClick = function(item){
  console.log('afterClick');console.log(item);
  if(treeoption.checktype == 'radio') {
  treeoption.checkedResult = item;
  }
};

export default {
  name: 'test',
  data () {
    return {
      list: [],
      list2: [],
      html: "这是<b style=\"color:red\">html</b>提示",
      treeData: treeDataList,
      treeoption:treeoption
    }
  },
  methods: {
    ajaxUploadFile() {
      var me = this;
      var formData = new FormData();
      formData.append('userCode', 'zjy');
      formData.append('userName', '曾军毅');
      formData.append('birthday', new Date());
      var files = $('#testFile')[0].files;
      if (files && files.length > 0) {
        for (var i = 0; i < files.length; i++) {
          formData.append('myfile', files[i]);
        }
      }
      this.$axios.post('/learn/testPostWithFile', formData).then(function (resp) {
        if (resp.data.status == ResultStatus.OK.key) {
          console.log(resp.data.value);
          me.$toaster.success('上传成功！');
        }
      });
    },
    ajaxDownload() {
      var me = this;
      this.$axios.postDownload('/learn/download').then(function (resp) {
        Utility.blobDownload(resp.data, resp.headers);
      });
    },
    initDrag() {
      var me = this;
      var sortable = $(".a");
      sortable.sortable({
        containment: sortable.closest('table'),
        connectWith: sortable,
        appendTo: sortable,
        handle:sortable.find('.fa-arrows'),
        revert: true,
        scrollSensitivity: 20,
        start: me.dragStart,
        stop: function(event, curEle) {return me.dragStop(event, curEle, me.list);}
      }).disableSelection();


      var sortable = $(".b");
      sortable.sortable({
        containment: sortable.closest('table'),
        connectWith: sortable,
        appendTo: sortable,
        handle:sortable.find('.fa-arrows'),
        revert: true,
        scrollSensitivity: 20,
        start: me.dragStart,
        stop: function(event, curEle) {return me.dragStop(event, curEle, me.list2);}
      }).disableSelection();
    },
    dragStart(event, curEle) {
        // 设置高度
        curEle.helper.css({backgroundColor:'rgba(255,255,255,0.5)'});
        curEle.placeholder.css({height:'29px'});
      },
      dragStop(event, curEle, list) {
        // 获取信息
        var map = this.findParentsAndCurrent(list, curEle);
        var newParent = map.get('newParent'), oldParent = map.get('oldParent'),
        newIndex = map.get('newIndex'), oldIndex = map.get('oldIndex'),
        current = map.get('current');

        // 不符合条件，不能再拖动
        if (current.lcseq > 0) {
          alert('不能拖动！');
          return false;
        }

        // 去掉原父级下的当前结点
        oldParent.dataList.splice(oldIndex, 1);
        // 添加到新结点
        newParent.dataList.splice(newIndex, 0, current);
        // 改父级id
        current.pId = newParent.id;
        // 处理序号
        for (var i = 0; i < newParent.dataList.length; i++) {
          newParent.dataList[i].xuhao = i;
        }
        for (var i = 0; i < oldParent.dataList.length; i++) {
          oldParent.dataList[i].xuhao = i;
        }
        // 撤销jquery的dom操作，因为数据列表已发生变化,vue会自动更新列表
        return false;
      },
      findParentsAndCurrent(list, curEle) {
        var map = new Map(), rongQi, newParent = curEle.item.closest('td'),
        oldParentRongQiId = curEle.item.attr('data-pid') + '', newParentRongQiId = newParent.attr('data-id') + '',
        elId = curEle.item.attr('data-id') + '', newIdList = [];
        // 查找父级
        for (var i = 0; i < list.length; i++) {
          rongQi = list[i];
            // 找到新父级
            if (rongQi.id == newParentRongQiId) {
              map.set('newParent', rongQi);
            }
            // 找到原父级
            if (rongQi.id == oldParentRongQiId) {
              map.set('oldParent', rongQi);
            }
          }
        // 新父级子节点顺序
        var children = newParent.children();
        for (var i = 0; i < children.length; i++) {
          newIdList.push($(children[i]).attr('data-id'));
        }
        // 当前元素在旧父级中的位置
        var oldParent = map.get('oldParent');
        for (var i = 0; i < oldParent.dataList.length; i++) {
          if (oldParent.dataList[i].id == elId) {
            map.set('current', oldParent.dataList[i]);
            map.set('oldIndex', i);
            break;
          }
        }
        // 当前元素在新父级中的位置
        for (var i = 0; i < newIdList.length; i++) {
          if (newIdList[i] == elId) {
            map.set('newIndex', i);
          }
        }
        return map;
      }

    },
    mounted: function () {
      var me = this;
      var list1 = [];
      var list2 = [];
      list1.push({id:1,pId:'A', xuhao:0,name:'第一个'});
      list1.push({id:2,pId:'A', xuhao:1,name:'第二个'});
      list1.push({id:3,pId:'A', xuhao:2,name:'第三个'});
      list2.push({id:4,pId:'B', xuhao:0,name:'第四个'});
      list2.push({id:5,pId:'B', xuhao:1,name:'第五个'});
      list2.push({id:6,pId:'B', xuhao:2,name:'第六个'});

      this.list.push({id:'A', dataList:list1});
      this.list.push({id:'B', dataList:list2});

      list1 = [];
      list2 = [];
      list1.push({id:1,pId:'A', xuhao:0,name:'第七个'});
      list1.push({id:2,pId:'A', xuhao:1,name:'第八个'});
      list1.push({id:3,pId:'A', xuhao:2,name:'第九个'});
      list2.push({id:4,pId:'B', xuhao:0,name:'第十个'});
      list2.push({id:5,pId:'B', xuhao:1,name:'第十一个'});
      list2.push({id:6,pId:'B', xuhao:2,name:'第十二个'});

      this.list2.push({id:'A', dataList:list1});
      this.list2.push({id:'B', dataList:list2});

      this.$nextTick(function() {
        me.initDrag();
      });
    }
  }
</script>
<style scoped>
  @import './static/js/jquery-ui.css'
  #mainRongQi td,#mainRongQi2 td{border:1px solid #aaa;height:30px;}
  .mytree{border: 1px solid #ddd;padding: 10px 10px 10px 5px;}
  .aa{width:200px;height:200px;background-color:#fff;border:3px solid;border-image:linear-gradient(to bottom, red 0%, gold 100%);border-image-slice:1;}
  .trcon{border:1px solid #999;}
  .sortable > span{font-size:14px;display:inline-block; padding:3px 5px;border:1px solid #aaa;}
</style>
