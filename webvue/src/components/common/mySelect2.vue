<template>
  <div>
    <select ref=myselect style='width:100%;'>
    </select>
  </div>
</template>

<script>
  var data = [{
    "id": 1,
    "text": "Option 1"
  },
    {
      "id": 2,
      "text": "Option 2"
    }];

  function format(item) {return item.tag;}
  export default {
    name: "mySelect2",
    props: ['disabled'],
    model: {},
    computed: {},
    methods: {
      init: function () {
        $(this.$refs.myselect).select2(
          {
            language: "zh-CN",
            multiple: true,
            placeholder: {
              id: '-1',
              text: 'Select an option'
            },
            allowClear: true,
            data:data
          }
        );
      }
    },
    mounted: function () {
      this.init();
    },
    beforeDestroy: function () {
      $(this.$refs.myselect).select2("destroy");
    }
  }
</script>

<style scoped>
</style>
