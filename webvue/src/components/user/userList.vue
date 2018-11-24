<template>
	<div class='maincontent w100p h100p'>
		<div class='list-header-but-group'>
			<button type="button inline-block" class="btn btn-outline-purple" @click="add()">
				<i class='fa fa-plus mr5'></i>添加
			</button>
			<button type="button inline-block" class="btn btn-outline-purple" @click="confirm1()">
				<i class='fa fa-plus mr5'></i>弹框1
			</button>
			<button type="button inline-block" class="btn btn-outline-purple" @click="confirm2()">
				<i class='fa fa-plus mr5'></i>弹框2
			</button>
		</div>
		<div class='searchbar'>
			<form class='myform form-inline form-group-w280 form-label-w80'>
				<div class="form-group">
					<label class="form-label">名称：</label>
					<div class="form-content">
						<input type="text" class="form-control" placeholder="名称" autofocus v-model='searchKey'>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label">邮箱：</label>
					<div class="form-content">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">&yen;</span>
							</div>
							<input type="text" class="form-control">
							<div class="input-group-append">
								<span class="input-group-text">@qq.com</span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label">性别：</label>
					<div class="form-content">
						<select class='form-control' v-model="sexValue">
							<option value="">--全部--</option>
							<option v-for="item in sexList" :value="item.value" v-text="item.name"></option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label">创建日期：</label>
					<div class="form-content">
						<date-time-picker v-model='mydate' :option='dateOpt'></date-time-picker>
					</div>
				</div>
				<div class="form-group btn100">
					<button type="button" class="btn btn-purple ml20" @click='search()'>
						<i class='fa fa-search mr5'></i>搜索
					</button>
				</div>
			</form>
		</div>
		<div class='table-list'>
			<table class="table table-hover">
				<thead>
					<tr>
						<th class='w50'>#</th>
						<th>名称</th>
						<th class='w100'>编码</th>
						<th class='w155'>创建时间</th>
						<th class='w70'>性别</th>
						<th class='w70'>系统用户</th>
						<th class='w70 text-center'>是否禁用</th>
						<th class='w100'>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(item, index) in list">
						<td class="text-center" v-text='((pager.pageNum - 1) * pager.pageSize) + index + 1'></td>
						<td>
							<a class='block w100p h100p' href='javascript:void(0)' v-text='item.userName' @click='edit(item)'></a>
						</td>
						<td v-text='item.userCode'></td>
						<td class='text-center' v-text='$options.filters.formatDate(item.createdOn)'></td>
						<td class='text-center' v-text='item.sexName'></td>
						<td class='text-center' v-text='item.isSystemName'></td>
						<td class='text-center' v-text='item.isDisabledName'></td>
						<td class='operate'>
							<a class='inline-block' href='javascript:void(0)' @click='grant(item)' title='授权'>
								<i class='fa fa-id-badge'></i>
							</a>
							<a class='inline-block' href='javascript:void(0)' @click='deleteItem(item)' title='删除'>
								<i class='fa fa-trash'></i>
							</a>
						</td>
					</tr>
				</tbody>
			</table>
			<div class='nodata' v-if='!list || list.length == 0'>
				 <div>没有数据！</div>
			</div>
		</div>
		<div class='footer-pager'>
			<pagination :pager-info='pager'></pagination>
		</div>
	</div>
</template>

<script>
	import commonSrv from '../../common/commonService'

	export default {
		name: 'userList',
		data () {
			return {
				mydate:new Date(),
				dateOpt: {format:'yyyy-mm-dd hh:ii:ss', minView:0},
				searchKey:null,
				list: [],
				sexValue:null,
				sexList: [],
				pager: {pageNum:1,pageSize:5,loading:true}
			}
		},
		methods: {
			add() {
				var me = this;
				this.axios.get('/comm/getId').then(function(resp) {
					me.$router.push({path: '/user/userEdit', query: {id: resp.data.value}});
				});

			},
			edit(entity) {
				this.$router.push({path: '/user/userEdit', query: {id: entity.userId}});

			},
			search() {
				var me = this;
				me.pager.loading = true;
				this.axios.get('/userinfo/queryPageList', {userName: this.searchKey,pageNum:this.pager.pageNum,pageSize:this.pager.pageSize}).then(function(resp) {
					me.list = resp.data.value.list;
					me.pager = commonSrv.getPagerInfo(resp.data.value, me.goPage);
				});
			},
			goPage(page) {
				this.pager.pageNum = page;
				this.search();
			},
			deleteItem:function(entity) {
				var me = this;
				this.$confirm.confirm('确定要删除用户吗？', function() {
					me.axios.get('/userinfo/delete', {id: entity.userId}).then(function(resp) {
						me.$toaster.success('删除成功！');
						me.search();
					});
				});
			},
			grant(entity) {
				this.$router.push({path: '/user/userRole', query: {id: entity.userId}});
			},
			confirm1() {
				var option = {
					title: '提示1',
					message: '确定要退出吗1确定要退出吗1确定要退出吗1确定要退出吗1确定要退出吗1？',
					closeBtn: {fn: null},
					confirmBtn: {fn: function() {console.log('adsf');}}
				}
				this.$confirm.confirmCore(option);
			},
			confirm2() {
				var option = {
					title: '提示2',
					message: '确定要退出吗2？',
					closeBtn: {fn: null},
					confirmBtn: {fn: null}
				}
				this.$confirm.confirmCore(option);
			},
		      getEnumList() {
		        var list = [];
		        for(var item in Sex) {
		          list.push(Sex[item]);
		        }
		        this.sexList = list;
		      }
		},
		mounted: function() {
			this.search();
            this.getEnumList();
		}
	}
</script>
