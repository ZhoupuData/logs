<%@ page language="java" pageEncoding="UTF-8"%>
 <header class="main-header">
    <!-- Logo -->
    <a href="${applicationScope.contextPath}/main" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini">日志</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>舟谱日志</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="javascript:;" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
         
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${applicationScope.contextPath}/adminlte/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">您好：${sessionScope.user.username}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${applicationScope.contextPath}/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                                               舟谱数据
                  <small>北京舟谱科技有限公司</small>
                </p>
              </li>
              <!-- Menu Body -->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-right">
                  <a href="javascript:;" class="btn btn-default btn-flat" id="logout">退出</a>
                </div>
              </li>
            </ul>
          </li>
        
        </ul>
      </div>
    </nav>
  </header>

<script type="text/javascript">
$('#logout').click(function(){
	 BootstrapDialog.confirm({
         title: '提示',
         message: '确定退出吗?',
         //type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
         closable: true, // <-- Default value is false
         draggable: true, // <-- Default value is false
         btnCancelLabel: '取消', // <-- Default value is 'Cancel',
         btnOKLabel: '确定', // <-- Default value is 'OK',
         btnOKClass: 'btn-warning', // <-- If you didn't specify it, dialog type will be used,
         callback: function(result) {
             if(result) {
            	 logout();
             }else {
                // alert('Nope.');
             }
         }
     });
	
})

function logout(){
	 $.post('${applicationScope.contextPath}/logout',function(data){
	        if (data.result){
	            window.location.replace("${applicationScope.contextPath}/tologin.html");
	        } else {
	           
	        }
	    });
}
</script>