<template>
  <div class='maincontent w100p h100p'>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter"
    @click=''>
    Launch demo modal
  </button>

  <span @click='show()'>a</span>

  <div class="modal fade" id="exampleModalCenter" tabindex="-1" :class='{show:showDialog}'>
    <div class="modal-dialog modal-dialog-centered">
      <div class='blankheader'></div>
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click='close()'>
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          ...
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
      <div class='blankfooter'></div>
    </div>
  </div>
</div>
</template>

<script>
  export default {
    name: 'userList',
    data () {
      return {
        showDialog:false
      }
    },
    methods: {
      show:function() {
        this.showDialog = true;
        //$('#exampleModalCenter').modal('show')
      },
      close: function() {
        this.showDialog = false;
      }
    },
    mounted: function() {
    },
    components:{}
  }
</script>

<style scoped>
  .modal-dialog-centered{flex-flow:column;}
  .blankheader{display:flex;flex:1;}
  .blankfooter{display:flex;flex:2;}
  .modal.fade.show{display:block;}
</style>
