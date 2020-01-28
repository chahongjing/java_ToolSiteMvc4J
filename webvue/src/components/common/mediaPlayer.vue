<template>
  <div class="file-contaniner">
    <template v-for="file in files">
      <img :data-type="mediaType.picture" class="file" :src="getUrl(file.url)" alt="图片" title="点击预览"
           v-if="getFileMediaType(file.url) == mediaType.picture"/>
      <img :data-type="mediaType.audio" class="file" @click="playAudio(file)" src="~@assets/M-v-Player_17.png"
           alt="音频" title="点击播放" v-if="getFileMediaType(file.url) == mediaType.audio"/>
      <img :data-type="mediaType.video" class="file" @click="playVedio(file)" src="~@assets/M-v-Player_16.png"
           alt="视频" title="点击播放" v-if="getFileMediaType(file.url) == mediaType.video"/>
    </template>
  </div>
</template>

<script>
  import commonSrv from '@/common/commonService';
  import Viewer from "../../../static/plugins/viewer/js/viewer.js";

  export default {
    data() {
      return {
        mediaType: commonSrv.mediaType,
        viewer: null
      }
    },
    props: ['files', 'fileDomain'],
    methods: {
      init: function () {
        var me = this;
        this.$nextTick(function () {
          me.viewer = new Viewer($(me.$el)[0], {
            filter: function (image) {
              return image.dataset.type == me.mediaType.picture;
            }
          });
        });
      },
      playAudio: function (file) {
        this.$cstModal.showAudio({src: this.getUrl(file.url)});
      },
      playVedio: function (file) {
        this.$cstModal.showVideo({src: this.getUrl(file.url)});
      },
      getUrl: function (url) {
        if (!url) return '';
        if (url.indexOf("http") > -1) {
          return url;
        } else {
          return this.fileDomain + '/' + url;
        }
      },
      getFileMediaType: function (fileName) {
        return commonSrv.getFileMediaType(fileName);
      },
      destoryCom: function() {
        if(this.viewer) {
          this.viewer.destroy();
        }
      }
    },
    mounted: function () {
      this.init();
    },
    beforeDestroy: function() {
      this.destoryCom();
    }
  }
</script>
<style src="../../../static/plugins/viewer/css/viewer.css"></style>
<style scoped>
  .file-contaniner{font-size:0;}
  .file {
    cursor: pointer;
    width: 70px;
    height: 70px;
    display: inline-block;
    padding: 5px;
    border:1px solid #ddd;
    margin:5px;
  }
</style>
