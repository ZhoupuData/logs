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
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/select2/select2.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/pace/pace.min.css">
  <script src="${applicationScope.contextPath}/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />

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
          模块维护
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
          
	     <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <button type="button" id="addinfo" class="btn btn-info pull-right">新增</button>
              <table id="mydatatable" class="table table-bordered table-hover table-striped responsive">
                   <thead>
			            <tr>
			                <th>名称</th>
                            <th>URL</th>
                            <th>方法</th>
			                <th>操作</th>
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
<script src="${applicationScope.contextPath}/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/select2/select2.full.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/pace/pace.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/dist/js/jquery.form.js"></script>

<script type="text/javascript">
var register = {};
$(function () {
   //$(".select2").select2();
   var mydatatable = $('#mydatatable').DataTable({
	  "language":datatable_language,
	  "searching": false,
	  "ordering":false,
	  "processing": true,
      "serverSide": true,
      "ajax": {
          "url": "${applicationScope.contextPath}/moduleaction"
      },
      //"scrollY": 1000,
      //"scrollX": true,
      //"autoWidth": false,
      /*"columnDefs": [
    	    { "width": "100px", "targets": [0] }
    	  ],*/
      "columns": [
          {"data": "name" },
          {"data": "url" },
          {"data": "action" },
          {"data": null, 
        	  render: function ( data, type, row ) {
                  return '<a class="btn btn-primary btn-xs" href="javascript:;" onclick=edit('+row.id+')> 编辑</a> <a class="btn btn-danger btn-xs" href="javascript:;" onclick=del('+row.id+')>删除</a>';
              } 
          }
         
      ],
      lengthMenu: [
          [50, 70, 100, 150, 200],
          ['50', '70', '100', '150', '200']
      ],
      pageLength: 50,
      stateSave: false
  });
  
  $("#querData").bind("click",function(){
	  mydatatable.draw(); 
  });
  
  register.datatable = mydatatable;
  
  $('#date').datepicker({
	  language: 'zh-CN',
	  //todayBtn:true,
	  clearBtn: true,
      autoclose: true
  });
});

function del(id){
	 BootstrapDialog.confirm({
         title: '提示',
         message: '确定删除吗?',
         type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
         closable: true, // <-- Default value is false
         draggable: true, // <-- Default value is false
         btnCancelLabel: '取消', // <-- Default value is 'Cancel',
         btnOKLabel: '确定', // <-- Default value is 'OK',
         btnOKClass: 'btn-warning', // <-- If you didn't specify it, dialog type will be used,
         callback: function(result) {
             if(result) {
            	 remove(id)
             }else {
                // alert('Nope.');
             }
         }
     });
}



$('#addinfo').click(function(){
	edit(0);
});

function edit(id){
	 BootstrapDialog.show({
		 title: '编辑',
		 message: $('<div></div>').load('${applicationScope.contextPath}/moduleaction/geteditmoduleaction/'+id),
         buttons: [{
             label: '关闭',
             action: function(dialog){
            	 dialog.close();
             }
         },{
             label: '保存',
             action: function(dialog) {
            	 save(function(result){
            		 if(result){
            			 dialog.close(); 
            		 }else{
            			 alert('失败');
            		 }
            	 });
             }
         }]
     });
}
function save(callback){
	$('#frm').ajaxSubmit({
	    type: 'post', // 提交方式 get/post
	    url: '${applicationScope.contextPath}/moduleaction/save', // 需要提交的 url
	    //data: data,
	    beforeSubmit:function(){
	   	 //return $('#docForm').form('validate');
	    },
	    beforeSend: function(){
	    	//loading('正在保存，请稍等...');
	    },
	    complete: function() {
	    	//removeLoading();
	    },
	    success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	    	 if (data.result) {
	    		 register.datatable.draw(); 
	    		 callback(true);
	    	 }else{
	    		 callback(false);
	    	 }
	    }
	});
}

function remove(id){
	 var idarr = [];
	 idarr.push(id);
	 idstr = idarr.join(",");
	
	 $.ajax({
         type: "delete",
         url: '${applicationScope.contextPath}/moduleaction/delete/'+idstr,
         beforeSend: function(){
         	//loading('正在删除，请稍等...');
         },
         complete: function() {
         	//removeLoading();
         },
         success: function(msg){
             if(msg.result){
            	 register.datatable.draw();  
             }else{
            	 
             }
         }
     });
}


</script>
</body>
</html>

