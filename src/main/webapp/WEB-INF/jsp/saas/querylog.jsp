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
                       舟谱快消日志查询
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
                  <input type="text" name="date" id="date" class="form-control" placeholder="日期">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="starttime" id="starttime" class="form-control" placeholder="开始时间">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="endtime" id="endtime" class="form-control" placeholder="结束时间">
                </div>
                <div class="col-xs-3">
                </div>
              
              </div>
              
              <div class="row" style="margin-top: 10px;">
                <div class="col-xs-3">
                    <input type="text" name="cid" id="cid" class="form-control" placeholder="CID">
                </div>
                <div class="col-xs-3">
                   <input type="text" name="uid" id="uid" class="form-control" placeholder="UID">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="cname" id="cname" class="form-control" placeholder="商家名称">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="url" id="url" class="form-control" placeholder="访问路径">
                </div>
              
              </div>
              
              <div class="row" style="margin-top: 10px;">
                <div class="col-xs-3">
                  <input type="text" name="action" id="action" class="form-control" placeholder="请求方法">
                </div>
                <div class="col-xs-3">
                  <input type="text" name="params" id="params" class="form-control" placeholder="请求参数">
                </div>
                <div class="col-xs-3">
                   <select class="form-control" name="success" id="success">
	                    <option value="">是否成功</option>
	                    <option value="true">是</option>
	                    <option value="false">否</option>
	               </select>
                </div>
                <div class="col-xs-3">
                  <input type="text" name="result" id="result" class="form-control" placeholder="请求结果">
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
			                <th>日期</th>
			                <th>时间</th>
			                <th>CID</th>
			                <th>商家名称</th>
			                <th>UID</th>
			                <th>IP</th>
			                <th>地区</th>
			                <th>系统</th>
			                <th>终端</th>
			                <th>路径</th>
			                <th>方法</th>
			                <th>参数</th>
			                <th>成功</th>
			                <th>结果</th>
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
          "url": "${applicationScope.contextPath}/saas",
          "type": "POST",
          "data": function (d) {
        	  var date = $('#date').val();
        	  if(date!=''){
        		  d.date = date;
        	  }
        	  
        	  var starttime = $('#starttime').val();
        	  if(starttime!=''){
        		  d.starttime = starttime;
        	  }
        	  
        	  var endtime = $('#endtime').val();
        	  if(endtime!=''){
        		  d.endtime = endtime;
        	  }
        	  
        	  var cid = $('#cid').val();
        	  if(cid !=''){
        		  d.cid = cid;
        	  }
        	  var uid = $('#uid').val();
        	  if(uid !=''){
        		  d.uid = uid;
        	  }
        	  
              var cname = $('#cname').val();
              if(cname!=''){
            	  d.cname = cname;
              }
              
              var url = $('#url').val();
              if(url !=''){
            	  d.url = url;
              }
             
              var action =  $('#action').val();
              if(action !=''){
            	  d.action = action;
              }
              
              var params = $('#params').val();
              if(params !=''){
            	  d.params = params;
              }
             
              var success = $('#success').val();
              if(success !=''){
            	  d.success = success;
              }
              
              var result = $('#result').val();
              if(result !=''){
            	  d.result = result;
              }
              
          }
      },
      //"scrollY": 1000,
      "scrollX": true,
      "autoWidth": true,
      "columns": [
          { "width": "60px","data": "date" },
          { "width": "150px","data": "timer" },
          { "width": "30px","data": "cid" },
          { "width": "150px","data": "cname" },
          { "width": "30px","data": "uid" },
          { "width": "100px","data": "ip" },
          { "width": "150px","data": "area" },
          { "width": "100px","data": "os" },
          { "width": "100px","data": "bname" },
          { "width": "200px","data": "url" },
          { "width": "200px","data": "action" },
          { "width": "400px","data": "params" },
          { "width": "40px","data": "success" },
          { "width": "200px","data": "result" }
         
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
	  $('#date').val('');
	  $('#starttime').val('');
	  $('#endtime').val('');
	  $('#cid').val('');
	  $('#uid').val('');
      $('#cname').val('');
      $('#url').val('');
      $('#action').val('');
      $('#params').val('');
      $('#success').val('');
      $('#result').val('');
      
  });
  
  
  $('#date').datetimepicker({
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
  
  $('#starttime').datetimepicker({
	  format: "yyyy-mm-dd hh:ii:ss",//设置时间格式，默认值: 'mm/dd/yyyy'
      autoclose : true,//当选择一个日期之后是否立即关闭此日期时间选择器
      startView : 2,//点开插件后显示的界面。0、小时1、天2、月3、年4、十年，默认值2
      minView : 0,//插件可以精确到那个时间，比如1的话就只能选择到天，不能选择小时了
      maxView : 4,//同理
      todayBtn : true,//是否在底部显示“今天”按钮
      todayHighlight : true,//是否高亮当前时间
      keyboardNavigation : true,//是否允许键盘选择时间
      language : 'zh-CN',//选择语言，前提是该语言已导入
      forceParse : true,//当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中
      minuteStep : 5,//分钟的间隔
      pickerPosition : "bottom-right",//显示的位置，还支持bottom-left
      viewSelect : 0,//默认和minView相同
      showMeridian : true,//是否加上网格
  });
  
  
  $('#endtime').datetimepicker({
	  format: "yyyy-mm-dd hh:ii:ss",//设置时间格式，默认值: 'mm/dd/yyyy'
      autoclose : true,//当选择一个日期之后是否立即关闭此日期时间选择器
      startView : 2,//点开插件后显示的界面。0、小时1、天2、月3、年4、十年，默认值2
      minView : 0,//插件可以精确到那个时间，比如1的话就只能选择到天，不能选择小时了
      maxView : 4,//同理
      todayBtn : true,//是否在底部显示“今天”按钮
      todayHighlight : true,//是否高亮当前时间
      keyboardNavigation : true,//是否允许键盘选择时间
      language : 'zh-CN',//选择语言，前提是该语言已导入
      forceParse : true,//当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中
      minuteStep : 5,//分钟的间隔
      pickerPosition : "bottom-right",//显示的位置，还支持bottom-left
      viewSelect : 0,//默认和minView相同
      showMeridian : true,//是否加上网格
  });
  

  //$(document).ajaxStart(function() { Pace.restart(); });
});
</script>
</body>
</html>

