<%@ page language="java" pageEncoding="UTF-8"%>
<aside class="main-sidebar">
    <section class="sidebar">
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${applicationScope.contextPath}/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>您好:${sessionScope.user.username}</p>
          <a href="javascript:;"><i class="fa fa-circle text-success"></i>在线</a>
        </div>
      </div>
      <ul class="sidebar-menu">
        <li class="header">菜单</li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>舟谱快消</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${applicationScope.contextPath}/saas/ouputlog"><i class="fa fa-circle-o"></i>实时控制台日志</a></li>
            <li><a href="${applicationScope.contextPath}/saas/querylog"><i class="fa fa-circle-o"></i>日志查询</a></li>
            <li><a href="${applicationScope.contextPath}/module/report"><i class="fa fa-circle-o"></i>功能统计查询</a></li>
            <li><a href="${applicationScope.contextPath}/urls/geturls"><i class="fa fa-circle-o"></i>URL维护</a></li>
            <li><a href="${applicationScope.contextPath}/actions/getactions"><i class="fa fa-circle-o"></i>ACTION维护</a></li>
            <li><a href="${applicationScope.contextPath}/moduleaction/getmoduleaction"><i class="fa fa-circle-o"></i>功能维护</a></li>
          </ul>
        </li>
        
        <li class="treeview"><a href="javascript:;"><i class="fa fa-book"></i> <span>文档</span></a></li>
        <li class="header">标签</li>
        <li class="treeview"><a href="${applicationScope.contextPath}/user/getuser"><i class="fa fa-circle-o text-red"></i> <span>用户管理</span></a></li>
      </ul>
    </section>
  </aside>
  
 <script type="text/javascript">
function initMenu(){
    var url = window.location.pathname;
    $('.treeview').find("a").each(function(i){
    	if($(this).attr("href")==url){
    		$(this).parent().addClass("active");
    		$(this).parents(".treeview").addClass("active");
    		return false;
    	}
    });
}
$(function(){
	initMenu();
})
 </script>
