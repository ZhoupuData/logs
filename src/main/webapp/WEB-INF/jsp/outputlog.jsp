<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Apache Tomcat WebSocket Examples: Echo</title>
	<style type="text/css">
		#connect-container {
			float: left;
			width: 800px
		}

		#connect-container div {
			padding: 5px;
		}

		#console-container {
			float: left;
			margin-left: 15px;
			width: 1000px;
		}

		#console {
			border: 1px solid #CCCCCC;
			border-right-color: #999999;
			border-bottom-color: #999999;
			height: 370px;
			overflow-y: scroll;
			padding: 5px;
			width: 100%;
		}

		#console p {
			padding: 0;
			margin: 0;
		}
	</style>
	<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
    <script src="//cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>
	<script type="text/javascript">
		var ws = null;

		function connect() {
			var target = '/logs/echo';
			ws = new SockJS(target);
			ws.onopen = function () {
				log('Info: WebSocket connection opened.');
				//ws.send('');
			};
			ws.onmessage = function (event) {
				log('Received: ' + event.data);
			};
			ws.onclose = function () {
				log('Info: WebSocket connection closed.');
			};
		}
		
		

		function disconnect() {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}


		function log(message) {
			var console = document.getElementById('console');
			var p = document.createElement('p');
			p.style.wordWrap = 'break-word';
			p.appendChild(document.createTextNode(message));
			console.appendChild(p);
			while (console.childNodes.length > 25) {
				console.removeChild(console.firstChild);
			}
			console.scrollTop = console.scrollHeight;
		}
		
		window.onload=function(){
			connect();
		}
		window.onunload=function(){
			disconnect();
		}
	</script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable
	Javascript and reload this page!</h2></noscript>
<div>
	<div id="console-container">
		<div id="console"></div>
	</div>
	
</div>
</body>
</html>
