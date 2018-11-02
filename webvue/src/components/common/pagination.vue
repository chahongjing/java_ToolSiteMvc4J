<template>
  <div class='list-pager'>
      <div class='footer-left inline-block'>sdfasdf</div>
      <div class='footer-right inline-block'>
        <ul class="pagination inline-block">
          <li class="page-item">
            <a class="page-link" href="#">&laquo;</a>
          </li>
          <li class="page-item disabled">
            <a class="page-link" href="#">&lsaquo;</a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item active">
            <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
          </li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#">&rsaquo;</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">&raquo;</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'pagination',
    props: {pagerInfo:{pageNum:null}},
    data () {
      return {
      }
    },
    mounted:function() {
    }
  }
</script>

<style scoped>
.list-pager{margin-top:10px;overflow: hidden;}
 .pagination .page-item{float:left;}
.footer-left, .footer-right{line-height: 40px;height:40px;float:left;}
</style>
