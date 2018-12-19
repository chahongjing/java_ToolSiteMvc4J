<template>
  <div>
    401 未授权
    <button type="button" class="btn btn-outline-primary" @click='goBack()'>
      <i class='fa fa-arrow-circle-o-left'></i><span>返回</span>
    </button>
  </div>
</template>

<script>
  export default {
    name: '401',
    methods: {
      goBack() {
        this.$root.goBack();
      }
    }
  }
</script>
