<template>
  <div class="modal fade" tabindex="-1" :class='{show:showModal}'>
    <div class="modal-dialog modal-dialog-centered">
      <div class='blankheader'></div>
      <div class="modal-content">
          <slot name='headerSlot' />
          <slot name='bodySlot' />
          <slot name='footerSlot' />
      </div>
      <div class='blankfooter'></div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'commonModal',
    props:['showModal'],
    watch:{
      showModal(curVal, oldVal) {
        var thisEl = $(this.$el);
        if(curVal) {
          thisEl.show();
          $('#backdrop').removeClass('hide').addClass('show');
        } else {
          var me = this;
          setTimeout(function(){
            thisEl.hide();
            $('#backdrop').removeClass('show').addClass('hide');
          }, 300);
        }
      }
    },
    mounted: function() {
    }
  }
</script>

<style scoped>
  .modal-dialog-centered{flex-flow:column;}
  .blankheader{display:flex;flex:1;}
  .blankfooter{display:flex;flex:1.6;}
  .modal.fade.show{opacity:1;}
</style>
