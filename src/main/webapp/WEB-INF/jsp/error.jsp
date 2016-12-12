<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>页面不存在</title>
    <style>
        *{
            margin:0;
            padding: 0;
        }
        .fac-box{
            position: absolute;
            top: 10px;
            left:50%;
            width:400px;
            margin-left: -200px;
        }
        .img-box{
            width:250px;
            height: 250px;
            margin-left: 70px;
        }
        .four-font{
            font-size: 100px;
            font-family: "微软雅黑";
            font-weight: bolder;
            color: #8B8A8B;
            text-align: center;
            border-bottom: 2px solid #C9C9C9;
            width:260px;
            margin:0 auto;
        }
        .reason{
            height:150px;
            padding-top: 10px;
        }
        .reason>h2{
            color: #8B8A8B;
            line-height: 60px;
        }
        .reason>h2,.reason span{
            text-align: center;
        }
        .reason-box>span{
            margin-left: 80px;
        }
        .reason-box>span,.reason-box>ul{
            float: left;
            color: #858585;
            line-height: 30px;
        }
        .reason-box>ul{
            margin-left: 30px;
        }
        .btn-box>a{
            display: inline-block;
            width:60px;
            height:35px;
            text-decoration: none;
            border:1px solid #3787C4;
            text-align: center;
            line-height: 35px;
            color: #3787C4;
            /*background-color: #3787C4;*/
        }
        .btn-box>a:first-child{
            margin-left: 100px;
            margin-right: 50px;
            margin-top: 10px;
            font-size: 14px;
        }
        .btn-box>a:hover{
            background-color: #3787C4;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="fac-box">
        <div>
            <div class="img-box"><img width="100%"height="100%" src="<%=request.getContextPath()%>/images/ruhua404.png" alt=""></div>
            <div class="four-font">404</div>
        </div>
        <div class="reason">
            <h2>哎呀妈呀找不到页面啦~</h2>
            <div class="reason-box">
                <span>可能原因:</span>
                <ul>
                    <li>找不到请求页面</li>
                    <li>输入的地址不正确</li>
                </ul>
            </div>
        </div>
        <div class="btn-box">
            <a href="javascript:window.location.reload();">刷新</a>
            <a target="view_window" href="https://www.zhoupu123.com">首页</a>
        </div>
    </div>
</body>
</html>