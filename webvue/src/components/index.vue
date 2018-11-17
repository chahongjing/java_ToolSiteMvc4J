<template>
  <div class='h100p'>
    <app-header></app-header>
    <div class="body">
      <app-menu></app-menu>
      <div class="right-main" :class='{"fullwin":!showMenu}'>
        <div class="bread">
          <ul>
            <li>
              <a class='w100p h100p inline-block' href='javascript:void(0)' title='首页'><i class='fa fa-home mr0' @click='goHomePage()'></i></a>
            </li>
          </ul>
        </div>
        <div class="right-content p15">
<div class=row>
			<div class="card border-info">
			  <div class="card-header text-center">
			    Featured
			  </div>
			  <div class="card-body text-info">
				<div class="list-group">
				  <a href="#" class="list-group-item list-group-item-action">
				    <i class='fa fa-comment fa-fw'></i>asdfas
				  </a>
				  <a href="#" class="list-group-item list-group-item-action">          <router-link to="/login">
            <button class="btn btn-default">登录</button>
          </router-link></a>
				  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
				  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
				  <a href="#" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
				</div>
			  </div>
			  <div class="card-footer text-muted">
			    2 days ago
			  </div>
			</div>

			<div class="card border-info">
			  <div class="card-header text-center">
			    Featured
			  </div>
			  <div class="card-body text-info">
				<div class="list-group">
				  <a href="#" class="list-group-item list-group-item-action">
				    <i class='fa fa-comment fa-fw'></i>asdfas
				  </a>
				  <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
				  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
				  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
				  <a href="#" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
				</div>
			  </div>
			  <div class="card-footer text-muted">
			    2 days ago
			  </div>
			</div>

			<div class="card border-info">
			  <div class="card-header text-center">
			    Featured
			  </div>
			  <div class="card-body text-info">
				<div class="list-group">
				  <a href="#" class="list-group-item list-group-item-action">
				    <i class='fa fa-comment fa-fw'></i>asdfas
				  </a>
				  <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
				  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
				  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
				  <a href="#" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
				</div>
			  </div>
			  <div class="card-footer text-muted">
			    2 days ago
			  </div>
			</div>

			<div class="card border-info">
			  <div class="card-header text-center">
			    Featured
			  </div>
			  <div class="card-body text-info">
				<div class="list-group">
				  <a href="#" class="list-group-item list-group-item-action">
				    <i class='fa fa-comment fa-fw'></i>asdfas
				  </a>
				  <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
				  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
				  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
				  <a href="#" class="list-group-item list-group-item-action disabled">


				  </a>
				</div>
			  </div>
			  <div class="card-footer text-muted">
			    2 days ago
			  </div>
			</div>
</div>
      	</div>
      <div class="footer"></div>
    </div>
  </div>
</div>
</template>

<script>
  import appHeader from './appHeader'
  import appMenu from './appMenu'
  export default {
    name: 'index',
    data () {
      return {
        showMenu: 'true'
      }
    },
    components: {appHeader, appMenu},
    methods: {
      goPage: function () {
    //this.$router.push({path: '/myPage', params:{ id:'1'}});
    //this.$router.push({name: 'myPage', params:{ id:'1'}});
    this.$router.push({path: '/myPage', query: {id: '1'}});
    //this.$router.push({name: 'myPage', query:{ id:'1'}});
  },
      goHomePage() {
      }
}
}
</script>

<style scoped>
.card{display:inline-block;}
.p15{padding:15px;}
.card-body {padding:0;}
.card-body .list-group .list-group-item{border-left:0;border-right:0;}
.card-body .list-group .list-group-item:first-child{border-top:0;}
.card-body .list-group .list-group-item:last-child{border-bottom:0;}
</style>