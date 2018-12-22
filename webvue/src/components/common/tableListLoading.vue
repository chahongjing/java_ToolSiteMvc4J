<template>
  <div class='nodata' v-if='!list || list.length == 0 || loading'>
    <div class='text-success' v-if='loading'><i class="fa fa-spinner fa-spin"></i>加载中...</div>
    <div v-if='!loading' class='text-info'><i class="fa fa-desktop"></i>没有数据！</div>
  </div>
</template>

<script>
  export default {
    name: 'tableListLoading',
    props: {list: Array, loading: Boolean}
  }
</script>