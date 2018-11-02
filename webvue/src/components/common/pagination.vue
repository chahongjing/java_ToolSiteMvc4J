<template>
  <div class='list-pager'>
      <div class='footer-left inline-block pl5' :style="{'width':'calc(100% - ' + width + 'px)'}">
      当前第<b v-text='pagerInfo && pagerInfo.pageNum || 0'></b>页，共<b v-text='pagerInfo && pagerInfo.pages || 0'></b>页</div>
      <div class='footer-right inline-block text-right pr5' :style="{'width':width + 'px'}">
        <ul class="pagination inline-block">
          <li class="page-item" :class='{"disabled":!(pagerInfo && pagerInfo.pageNum > 1)}'>
            <a class="page-link" href="javascript:void(0)" @click='jumpPage(1)'>&laquo;</a>
          </li>
          <li class="page-item" :class='{"disabled":!(pagerInfo && pagerInfo.pageNum > 1)}'>
            <a class="page-link" href="javascript:void(0)" @click='jumpPage(pagerInfo.pageNum - 1)'>&lsaquo;</a>
          </li>
          <li class="page-item active" v-for='item in getList'>
            <a class="page-link" href="javascript:void(0)" @click='jumpPage(item)' v-text='item'></a>
          </li>
          <li class="page-item" :class='{"disabled":!(pagerInfo && pagerInfo.pageNum < pagerInfo.pages)}'>
            <a class="page-link" href="javascript:void(0)" @click='jumpPage(pagerInfo.pageNum + 1)'>&rsaquo;</a>
          </li>
          <li class="page-item" :class='{"disabled":!(pagerInfo && pagerInfo.pageNum < pagerInfo.pages)}'>
            <a class="page-link" href="javascript:void(0)" @click='jumpPage(pagerInfo.pages)'>&raquo;</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'pagination',
    props: {pagerInfo:{pageNum:null, pages:null, callback:null}},
    data () {
      return {
        width:0
      }
    },
    methods: {
      jumpPage(page) {
        if(!this.pagerInfo || page < 1 || page == this.pagerInfo.pageNum || page > this.pagerInfo.pages) {
          return;
        }
        this.pagerInfo.callback && this.pagerInfo.callback(page);
      }
    },
    computed:{
      getList: function() {
        var me = this;
        var list = [];
        if(this.pagerInfo == null) {
          return list;
        }
        list.push(this.pagerInfo.pageNum);
        for(var i = 1; i < 5; i++) {
          list.unshift(this.pagerInfo.pageNum - i);
          list.push(this.pagerInfo.pageNum + i);
        }
        list = list.filter(function(item) {return item > 0 && item <= me.pagerInfo.pages; });
        me.width = list.length * 40 + 130;
        return list;
      }
    }
  }
</script>

<style scoped>
.list-pager{margin-top:10px;overflow: hidden;}
 .pagination .page-item{float:left;}
.footer-left, .footer-right{line-height: 40px;height:40px;float:left;}
</style>
