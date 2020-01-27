<template>
  <div id="jp_container_1" class="jp-video jp-video-360p">
    <div class="jp-type-single">
      <div id="jquery_jplayer_2" class="jp-jplayer" style="height:450px;"></div>
      <div class="jp-gui">
        <div class="jp-video-play">
          <button class="jp-video-play-icon" role="button" tabindex="0">play</button>
        </div>
        <div class="jp-interface">
          <div class="jp-progress">
            <div class="jp-seek-bar">
              <div class="jp-play-bar"></div>
            </div>
          </div>
          <div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
          <div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
          <div class="jp-controls-holder">
            <div class="jp-controls">
              <button class="jp-play" role="button" tabindex="0">play</button>
              <button class="jp-stop" role="button" tabindex="0">stop</button>
            </div>
            <div class="jp-volume-controls">
              <button class="jp-mute" role="button" tabindex="0">mute</button>
              <button class="jp-volume-max" role="button" tabindex="0">max volume</button>
              <div class="jp-volume-bar">
                <div class="jp-volume-bar-value"></div>
              </div>
            </div>
            <div class="jp-toggles">
              <button class="jp-repeat" role="button" tabindex="0">repeat</button>
              <button class="jp-full-screen" role="button" tabindex="0">full screen</button>
            </div>
          </div>
          <div class="jp-details hide">
            <div class="jp-title" aria-label="title" v-text="src"></div>
          </div>
        </div>
      </div>
      <div class="jp-no-solution hide">
        <span>Update Required</span>
        To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
      </div>
    </div>
  </div>
</template>

<script>
  import "../../../static/plugins/jPlayer/js/jquery.jplayer.min.js";
  import commonSrv from '@/common/commonService';

  export default {
    data() {
      return {
        isInit: false
      }
    },
    props: ['src'],
    methods: {
      show: function () {
        this.showDialog = true;
      },
      hide: function () {
        this.showDialog = false;
        // 删除弹框元素
        var me = this;
        setTimeout(function () {
          me.$destroy(true);
          me.$el.parentNode.removeChild(me.$el);
        }, 200);
      },
      defaultClose: function () {
        var me = this;
        if (me.closeBtn && me.closeBtn.fn) {
          me.closeBtn && me.closeBtn.fn();
        }
        me.showDialog = false;
        this.hide();
      },
      defaultConfirm: function () {
        var me = this;
        if (me.confirmBtn && me.confirmBtn.fn) {
          me.confirmBtn && me.confirmBtn.fn();
        }
        me.showDialog = false;
        me.hide();
      },
      init: function () {
        if (this.isInit) {
          this.destory();
        }
        if (this.src) {
          this.initPlayer();
        }
      },
      initPlayer: function () {
        var me = this;
        me.isInit = true;
        var suffix = commonSrv.getFileExtension(me.src).replace('.', '');
        if(suffix == 'webm') suffix = 'webmv';
        if(suffix == 'mp4') suffix = 'm4v';
        var obj = {};
        obj[suffix] = me.src;
        this.$nextTick(function () {
          $(me.$el).find('#jquery_jplayer_2').jPlayer({
            ready: function (event) {
              // $(this).jPlayer("setMedia", {
              //   // title: "title提示",
              //   m4v: me.src,
              //   // webmv: me.src,
              // });
              $(this).jPlayer("setMedia", obj);
            },
            swfPath: "../../../static/plugins/jPlayer/js/",
            // supplied: "m4v, webmv, ogv",
            supplied: suffix,
            wmode: "window",
            useStateClassSkin: true,
            autoBlur: false,
            smoothPlayBar: true,
            keyEnabled: true,
            remainingDuration: true,
            toggleDuration: true,
            // solution:'flash',
            size: {
              width: "100%",
              height: "450px",
              cssClass: "jp-video-360p"
            }
          });
        });
      }
    },
    mounted: function () {
      this.init();
    },
    destory: function () {
      $(this.$el).find('#jquery_jplayer_2').jPlayer("pause").jPlayer("destroy");
      this.isInit = false;
    },
    watch: {
      src: function (curVal, oldVal) {
        this.init();
      }
    }
  }
</script>

<style src="../../../static/plugins/jPlayer/css/jplayer.blue.monday.css"></style>
<style scoped>
  .jp-video {
    width: 900px;
    border: none;
  }
  .jp-video-360p .jp-video-play {
    height: 450px;
    margin-top: -450px;
  }
</style>
