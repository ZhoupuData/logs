<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>舟谱日志管理系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/js/datetimepicker/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/select2/select2.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/pace/pace.min.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
  <script src="${applicationScope.contextPath}/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">

	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@include file="../mainheader.jsp" %>
  <%@include file="../mainsidebar.jsp" %>
  <div class="content-wrapper">
   
    <!-- 内容开始 -->
    <section class="content-header">
      <h1>
                       模块操作统计查询
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
       <div class="box box-danger">
            <div class="box-header with-border">
              <h3 class="box-title">查询条件</h3>
            </div>
            <div class="box-body">
              <div class="row">
                <div class="col-xs-3">
                  <input type="text" name="startdate" id="startdate" class="form-control" placeholder="开始日期">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="enddate" id="enddate" class="form-control" placeholder="结束日期">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="url" id="url" class="form-control" placeholder="URL">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="action" id="action" class="form-control" placeholder="方法">
                </div>
              </div>
            </div>
            <!-- /.box-body -->
             <div class="box-footer">
	             <div class="row">
	                  <button type="button" id="clearData" class="btn btn-info pull-right" style="margin-right: 20px;">清空</button>
	                  <button type="button" id="querData" class="btn btn-info pull-right" style="margin-right: 20px;">查询</button>
	             </div>
              
              </div>
          </div>
          
	     <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <table id="mydatatable" class="table table-bordered table-hover table-striped responsive">
                   <thead>
			            <tr>
			                <th>CID</th>
			                <th>商家名称</th>
			                <th>请求次数</th>
			            </tr>
			        </thead>
              </table>
            </div>
          </div>

        </div>
      </div>
    </section>
    
  </div>
  <%@include file="../mainfooter.jsp" %>
  <%@include file="../controlsidebar.jsp" %>
</div>
<script src="${applicationScope.contextPath}/adminlte/plugins/datatables/chinese.js"></script>
<script src="${applicationScope.contextPath}/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/fastclick/fastclick.js"></script>
<script src="${applicationScope.contextPath}/adminlte/dist/js/app.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/dist/js/layout.js"></script>
<script src="${applicationScope.contextPath}/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${applicationScope.contextPath}/js/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/select2/select2.full.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/pace/pace.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
$(function () {
   //$(".select2").select2();
   var mydatatable = $('#mydatatable').DataTable({
	  "language":datatable_language,
	  "searching": false,
	  "ordering":false,
	  "processing": true,
      "serverSide": true,
      "ajax": {
          "url": "${applicationScope.contextPath}/module/reportdata",
          "type": "POST",
          "data": function (d) {
        	  var startdate = $('#startdate').val();
        	  if(startdate!=''){
        		  d.startdate = startdate;
        	  }
        	  
        	  var enddate = $('#enddate').val();
        	  if(enddate!=''){
        		  d.enddate = enddate;
        	  }
        	  
              var url = $('#url').val();
              if(url !=''){
            	  d.url = url;
              }
             
              var action =  $('#action').val();
              if(action !=''){
            	  d.action = action;
              }
          }
      },
      //"scrollY": 1000,
      //"scrollX": true,
      "autoWidth": true,
      "columns": [
          { "data": "cid" },
          { "data": "cname" },
          { "data": "count" }
      ],
      //dom: 'rtlip',
      lengthMenu: [
          [10,20,50, 70, 100, 150, 200],
          ['10','20','50', '70', '100', '150', '200']
      ],
      pageLength: 20,
      stateSave: false
  });
  
  $("#querData").bind("click",function(){
	  mydatatable.draw(); 
  });
  
  $("#clearData").click(function(){
	  $('#startdate').val('');
	  $('#enddate').val('');
      $('#cname').val('');
      $('#url').val('');
      $('#action').val('');
      
  });
  
  
  $('#startdate').datetimepicker({
       language: 'zh-CN',
       format: "yyyy-mm-dd",
       weekStart: 1,
       todayBtn:  1,
	   autoclose: 1,
	   todayHighlight: 1,
	   startView: 2,
	   minView: 2,
	   forceParse: 0
  });
  
  $('#enddate').datetimepicker({
      language: 'zh-CN',
      format: "yyyy-mm-dd",
      weekStart: 1,
      todayBtn:  1,
      autoclose: 1,
      todayHighlight: 1,
      startView: 2,
      minView: 2,
      forceParse: 0
  });

  //$(document).ajaxStart(function() { Pace.restart(); });
});
</script>
</body>
</html>

