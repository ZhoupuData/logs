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
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${applicationScope.contextPath}/adminlte/plugins/pace/pace.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
  
  <script src="${applicationScope.contextPath}/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">

		#console-container {
			float: left;
			width: 100%;
			height:100%;
		}

		#consolediv {
			border: 1px solid #CCCCCC;
			border-right-color: #999999;
			border-bottom-color: #999999;
			background: #333;
			height:600px; 
			color: #aaa;
			overflow-y: auto;
			padding: 5px;
			width: 100%;
		}

		#consolediv p {
			padding: 0;
			margin: 0;
		}
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
                       舟谱快消实时日志
      </h1>
    </section>

    <!-- Main content -->
    <section class="content" style="min-height: 700px;">
	     <div id="console-container">
			<div id="consolediv"></div>
		</div>
   </section>
    
    
  </div>
  <%@include file="../mainfooter.jsp" %>
  <%@include file="../controlsidebar.jsp" %>
</div>

<script src="${applicationScope.contextPath}/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/plugins/fastclick/fastclick.js"></script>
<script src="${applicationScope.contextPath}/adminlte/dist/js/app.min.js"></script>
<script src="${applicationScope.contextPath}/adminlte/dist/js/layout.js"></script>
 <script src="//cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>
 <script src="${applicationScope.contextPath}/adminlte/plugins/pace/pace.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
 
<script type="text/javascript">
var ws = null;
function connect() {
	var target = '${applicationScope.contextPath}/outsaaslog';
	ws = new SockJS(target);
	ws.onopen = function () {
		console.log("连接成功");
	};
	ws.onmessage = function (event) {
		outputlog(event.data);
	};
	ws.onclose = function () {
		console.log('连接关闭');
		connect();
	};
}

/*window.setInterval(function(){
	if (ws != null) {
		ws.send('o');
	}
},3000);*/

function disconnect() {
	if (ws != null) {
		ws.close();
		ws = null;
	}
}

function outputlog(message) {
	var consoleDiv = document.getElementById('consolediv');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document.createTextNode(message));
	consoleDiv.appendChild(p);
	while (consoleDiv.childNodes.length > 1000) {
		consoleDiv.removeChild(consoleDiv.firstChild);
	}
	consoleDiv.scrollTop = consoleDiv.scrollHeight;
}

window.onload=function(){
	connect();
}
window.onunload=function(){
	disconnect();
}
</script>
</body>
</html>

