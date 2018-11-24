<template>
  <div>
    <form class='myform form-label-w120 block-form-group form-group-w300 w300 mt20'>
        <div class="form-group">
          <label class="form-label">文件：</label>
          <div class="form-content">
            <input type="file" id='testFile' class="form-control" multiple placeholder="文件" autofocus v-focus />
          </div>
          <div class='form-info'>
            <i class='fa'></i>
          </div>
        </div>
        <div class="form-group text-right mb0">
          <button type="button" class="btn btn-primary mr5" @click="ajaxUploadFile">上传</button>
          <button type="button" class="btn btn-primary mr5" @click="ajaxDownload">下载</button>
        </div>
      </form>
  </div>
</template>

<script>
  export default {
    name: 'test',
    data () {
      return {
      }
    },
    methods: {
      ajaxUploadFile() {
        var me = this;
        var formData = new FormData();
        formData.append('userCode', 'zjy');
        formData.append('userName', '曾军毅');
        formData.append('birthday', new Date());
        var files = $('#testFile')[0].files;
        if(files && files.length > 0) {
          for(var i = 0; i < files.length; i++) {
            formData.append('myfile', files[i]);
          }
        }
        this.axios.postFormData('/learn/testPostWithFile', formData).then(function(resp) {
          if(resp.data.status == ResultStatus.OK.key) {
            me.$toaster.success('上传成功！');
          } else if(resp.data.status == ResultStatus.NO.key) {
          }
        });
      },
      ajaxDownload() {
        this.axios.postDownload('/learn/download').then(function(resp) {
          if(resp) {
            Utility.blobDownload(resp.data, resp.headers);
          }
        });
      }
    },
    mounted: function() {
    }
  }
</script>
