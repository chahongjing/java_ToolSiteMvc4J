<template>
  <div>
    <div class="modal-backdrop fade hide">
    </div>
    <div class="modal fade" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered" :style='getStyle()'>
        <div class='blankheader'></div>
        <div class="modal-content">
          <slot name='headerSlot'/>
          <slot name='bodySlot'/>
          <slot name='footerSlot'/>
        </div>
        <div class='blankfooter'></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'commonModal',
    props: {showModal: false, width: null, height: null},
    watch: {
      showModal(curVal, oldVal) {
        var cur = $(this.$el);
        var back = cur.find('.modal-backdrop');
        var modal = cur.find('.modal');

        if (curVal) {
          back.removeClass('hide').show().addClass('show');
          modal.show();
          setTimeout(function () {
            modal.addClass('show')
          }, 40);
        } else {
          var me = this;
          modal.removeClass('show');
          setTimeout(function () {
            modal.hide()
            back.hide().removeClass('show').addClass('hide');
          }, 200);
        }
      }
    },
    methods: {
      getStyle: function () {
        var obj = {};
        if (this.width > 0) {
          obj.width = this.width + 'px';
        }
        if (this.height > 0) {
          obj.height = this.height + 'px';
        }
        return obj;
      }
    }
  }
</script>

<style scoped>
  .modal-backdrop.hide {
    display: none;
  }

  .modal-dialog-centered {
    flex-flow: column;
    margin-left: auto;
    margin-right: auto;
  }

  .blankheader {
    display: flex;
    flex: 1;
  }

  .blankfooter {
    display: flex;
    flex: 1.6;
  }

  .modal.fade.show {
    opacity: 1;
  }

  .modal-content {
    border-radius: .3rem;
    border-width: 0;
    box-shadow: 0 0 10px #224acd;
  }
</style>
