<%@page import="java.util.Calendar"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%Calendar d = Calendar.getInstance();%>
<!DOCTYPE html>
<html lang="zh">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>舟谱数据日志系统</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="author" />
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="${applicationScope.contextPath}/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${applicationScope.contextPath}/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${applicationScope.contextPath}/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${applicationScope.contextPath}/metronic/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${applicationScope.contextPath}/metronic/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="${applicationScope.contextPath}/metronic/assets/pages/css/login-4.min.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="favicon.ico" /> </head>


    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="https://www.zhoupu123.com" target="_blank">
                <img src="${applicationScope.contextPath}/metronic/assets/pages/img/logo-big.png" alt="" /> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form"  method="post">
                <h3 class="form-title">舟谱日志</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span>请输入手机号或密码</span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">手机号</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="手机号" name="username" id="username"/> </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">验证码</label>
                    <div class="input-icon">
                         
                        <div class="input-group">
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="验证码" name="password" /> 
                        <span class="input-group-btn">
                              <input type="button" class="btn blue disabled" type="button" id="btnGetCode" value="发送验证码"/>
                          </span>
                        </div>
                    </div>
                </div>
                
                <div class="form-actions">
                    <label class="">
                        <span></span>
                    </label>
                    <button type="submit" class="btn blue pull-right"> 登录 </button>
                </div>
               
               
            </form>
         
            </div>

        <div class="copyright"> <%=d.get(Calendar.YEAR)%> &copy; 北京舟谱科技有限公司 </div>
  
        <!--[if lt IE 9]>
<script src="${applicationScope.contextPath}/metronic/assets/global/plugins/respond.min.js"></script>
<script src="${applicationScope.contextPath}/metronic/assets/global/plugins/excanvas.min.js"></script> 
<script src="${applicationScope.contextPath}/metronic/assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->

        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>

        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>

        <script src="${applicationScope.contextPath}/metronic/assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="${applicationScope.contextPath}/adminlte/dist/js/jquery.form.js"></script>
        

        <script src="${applicationScope.contextPath}/metronic/assets/global/scripts/app.min.js" type="text/javascript"></script>

        <script src="${applicationScope.contextPath}/metronic/assets/pages/scripts/login-4.js?1212" type="text/javascript"></script>
    
    </body>

</html>