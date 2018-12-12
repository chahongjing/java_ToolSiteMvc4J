<template>
  <div class='nodata' v-if='!list || list.length == 0 || loading'>
    <div v-if='loading'><i class="fa fa-spinner fa-spin text-success"></i>加载中...</div>
    <div v-if='!loading'>没有数据！</div>
  </div>
</template>

<script>
  export default {
    name: 'tableListLoading',
    props: {list: Array, loading: Boolean}
  }
</script>

<style scoped>
</style>
