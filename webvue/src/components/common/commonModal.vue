<template>
  <div class="modal fade" tabindex="-1" :class='{show:showModal}'>
    <div class="modal-dialog modal-dialog-centered" :style='getStyle()'>
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
    props:{showModal:false,width:null,height:null},
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
    methods: {
      getStyle: function() {
        var obj = {};
        if(this.width > 0) {
          obj.width = this.width + 'px';
        }
        if(this.height > 0) {
          obj.height = this.height + 'px';
        }
        return obj;
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
  .modal-content{border-radius: .3rem;border-width:0;}
</style>
