<template>
  <div class="form-group" style="width:100%!important;">
    <div class="control-form">
      <p class="help-block" v-text="getTip()"></p>
      <ul class="upload-files">
        <li v-for='file in files'>
          <p class="file"><img :src="getImageUrl(file)"><a class="close" @click="delFile(file)">×</a></p>
        </li>
        <li :class="{'disabled': isDisabled}">
          <form class="fileForm">
            <input type="file" class="upload" @change="addFile" ref="inputer" multiple
                   :accept="getMimeType" :disabled="isDisabled" />
          </form>
          <a class="add">
            <i class="iconfont icon-plus"></i><p>点击上传</p>
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import comSrv from '@/common/commonService';

  export default {
    data() {
      return {
        fileMaxSize: comSrv.uploadFileSize * 1024 * 1024, // 5mb
        fileMaxSizeStr: comSrv.uploadFileSize + 'M',
        fileDomain: null,
        jpgMimeTypeArr: ['image/png', 'image/jpeg', 'image/gif', 'image/jpg'],
        mp3MimeTypeArr: ['audio/mp3', 'audio/mpeg3', 'audio/x-mpeg-3', 'video/mpeg', 'video/x-mpeg'],
        mp4MimeTypeArr: ['video/mp4']
      }
    },
    props: ['files', 'fileSuffix', 'dataBus', 'maxFileNum', 'disabled'],
    methods: {
      addFile(event) {
        let inputDOM = this.$refs.inputer;
        this.dataBus.allDisabled = true;
        // 通过DOM取文件数据
        var files = inputDOM.files;
        if (!this.beforeUpload(files)) {
          this.dataBus.allDisabled = false;
          return;
        }
        // 上传图片
        var formData = new FormData();
        formData.append('busPath', 'workorder');
        for (var i = 0; i < files.length; i++) {
          formData.append('files', files[i]);
        }
        var me = this;
        this.$axios.post('url', formData).then(function (resp) {
          if (resp.data.code == ResultStatus.OK.code) {
            me.fileDomain = resp.data.data.fileDomain;
            for (var i = 0; i < resp.data.data.attachmentList.length; i++) {
              var fileTemp = {imgUrl: resp.data.data.attachmentList[i].imgUrl,
                fileName: resp.data.data.attachmentList[i].fileName,
                domain:me.fileDomain};
              fileTemp.type = comSrv.getFileMediaType(fileTemp.imgUrl);
              me.files.push(fileTemp);
            }
          }
          me.resetInput();
          me.dataBus.allDisabled = false;
        });
      },
      beforeUpload: function (files) {
        var suffixArr = this.fileSuffix || comSrv.pictureSuffix;
        if (!files || files.length == 0) {
          this.$toaster.warning('请选择要上传的附件！');
          return false;
        }
        if (files.length + this.files.length > (this.maxFileNum || 5)) {
          this.$toaster.warning('最多可上传' + (this.maxFileNum || 5) + '个附件！');
          this.resetInput();
          return false;
        }
        for (var i = 0; i < files.length; i++) {
          if (files[i].size > this.fileMaxSize) {
            this.$toaster.warning('请选择5M以内的附件！');
            this.resetInput();
            return false;
          }
          var suffix = comSrv.getFileExtension(files[i].name);
          if(!suffix || !suffixArr.includes(suffix.toLowerCase().replace('.', ''))) {
            this.$toaster.warning('只能上传后缀名为' + suffixArr.join(', ') + '的附件！');
            this.resetInput();
            return false;
          }
        }
        return true;
      },
      resetInput() {
        $(this.$el).find('.fileForm')[0].reset();
      },
      delFile(key) {
        this.files.splice(this.files.indexOf(key), 1);
      },
      getTip: function() {
        var fsArr = this.fileSuffix || [];
        return '(建议附件格式为：' + fsArr.join(', ') + '，大小不超过' + this.fileMaxSizeStr + '，最多可上传' + (this.maxFileNum || 5) + '个附件)';
      },
      getImageUrl: function(file) {
        var url;
        switch (file.type) {
          case comSrv.mediaType.picture:
            url = this.fileDomain + '/' + file.imgUrl;
            break;
          case comSrv.mediaType.audio:
            url = '/static/imgs/M-v-Player_17.png';
            break;
          case comSrv.mediaType.video:
            url = '/static/imgs/M-v-Player_16.png';
            break;
          default:
            url = this.fileDomain + '/' + file.imgUrl;
            break;
        }
        return url;
      }
    },
    mounted: function () {
    },
    computed: {
      getMimeType: function() {
        var arr = [];
        if(this.fileSuffix && this.fileSuffix.includes('jpg')) {
          Array.prototype.push.apply(arr, this.jpgMimeTypeArr);
        }
        if(this.fileSuffix && this.fileSuffix.includes('mp3')) {
          Array.prototype.push.apply(arr, this.mp3MimeTypeArr);
        }
        if(this.fileSuffix && this.fileSuffix.includes('mp4')) {
          Array.prototype.push.apply(arr, this.mp4MimeTypeArr);
        }
        return arr.join(',');
      },
      isDisabled: function() {
        return this.files.length > (this.maxFileNum || 5) || this.dataBus.allDisabled || this.disabled;
      }
    }
  }
</script>

<style lang="less" scoped>
  @them-color: rgb(237, 114, 77);
  .upload-files {
    margin: 10px 0 0px 0;
    overflow: hidden;
    font-size: 0;
  }

  .upload-files li {
    position: relative;
    width: 70px;
    height: 70px;
    font-size: 14px;
    display: inline-block;
    padding: 10px;
    margin-right: 25px;
    margin-bottom: 25px;
    border: 2px dashed #ccc;
    text-align: center;
    vertical-align: middle;
  }

  .upload-files li:hover {
    border-color: @them-color;
  }
  .upload-files li.disabled{border-color:#d5d5d5;opacity: 0.65;}

  .upload-files .add {
    display: block;
    background-color: #ccc;
    color: #ffffff;
    height: 50px;
    padding-top: 13px;
  }

  .upload-files .add .iconfont {
    font-size: 22px;
  }

  .upload-files .add p {
    font-size: 10px;
    margin-top: 10px;
    display: none;
  }

  .upload-files li:hover .add {
    background-color: @them-color;
  }
  .upload-files li.disabled input{cursor: default;}
  .upload-files li.disabled .add{background-color:#abbac3;opacity: 0.65;color:#fff;}

  .upload-files li .upload {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 70px;
    height: 70px;
    opacity: 0;
    cursor: pointer;
  }

  .upload-files .file {
    position: relative;
    width: 52px;
    height: 52px;
    top: -3px;
    left: -3px;
  }

  .upload-files .file img {
    vertical-align: middle;
    width: 100%;
    height: 100%;
  }

  .upload-files .file .close {
    display: none;
  }

  .upload-files li:hover .file .close {
    display: block;
    position: absolute;
    right: -6px;
    top: -10px;
    line-height: 1;
    font-size: 22px;
    color: #aaa;
    cursor: pointer;
  }
</style>
