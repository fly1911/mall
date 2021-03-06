﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员注册</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
</head>
<body>
			<jsp:include page="/jsp/head.jsp"/>

			<%-- <!--
            	描述：菜单栏
            -->
			<div class="container">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/img/logo2.png" />
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
						<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
					</ol>
				</div>
			</div>
			<!--
            	描述：导航条
            -->
			<div class="container">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li class="active"><a href="#">手机数码<span class="sr-only">(current)</span></a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div> --%>





		<div class="container" style="background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
		<div class="row"> 
			<div class="col-md-1"></div>
			<div class="col-md-10" style="background:#fff;padding:40px 40px;margin:30px;border:7px solid #ccc;">
				<font>会员注册</font>USER REGISTER<span><font style="color:red;">${msg}</font></span>
				<form class="form-horizontal" action="${pageContext.request.contextPath}/userServlet?method=register" method="POST" style="margin-top:5px;">
					 <div class="form-group">
					    <label for="username" class="col-sm-2 control-label">用户名</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" name="username" onblur="checkUsername()" id="username" placeholder="请输入用户名">
					    </div>
					    <div class="col-sm-3" id="username_ajax_msg">
					    	
						</div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
					    <div class="col-sm-7">
					      <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
					    </div>
					    <div class="col-sm-3"></div>
					  </div>
					   <div class="form-group">
					    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
					    <div class="col-sm-7">
					      <input type="password" class="form-control" name="confirmpwd" id="confirmpwd" placeholder="请输入确认密码">
					    </div>
					    <div class="col-sm-3"></div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					    <div class="col-sm-7">
					      <input type="email" class="form-control" name="email" id="email" placeholder="Email">
					    </div>
					    <div class="col-sm-3"></div>
					  </div>
					 <div class="form-group">
					    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">
					    </div>
					    <div class="col-sm-3"></div>
					  </div>
					  <div class="form-group opt">  
						  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
						  <div class="col-sm-7">
						    <label class="radio-inline">
						  <input type="radio" name="sex" checked="true" id="inlineRadio1" value="1"> 男
						</label>
						<label class="radio-inline">
						  <input type="radio" name="sex" id="inlineRadio2" value="2"> 女
						</label>
						
						</div>
						<div class="col-sm-3"></div>
					  </div>		
					  <div class="form-group">
					    <label for="date" class="col-sm-2 control-label">出生日期</label>
					    <div class="col-sm-7">
					      <input type="date" class="form-control" name="birthday">		      
					    </div>
					    <div class="col-sm-3"></div>
					  </div>
					  
					  <div class="form-group">
					    <label for="date" class="col-sm-2 control-label">验证码</label>
					    <div class="col-sm-3">
					      <input type="text" class="form-control" name="validateCode">
					    </div>
					    <div class="col-sm-2">
					   	 <img id="validateCode" src="${pageContext.request.contextPath}/userServlet?method=validateCode" onclick="changeVcode()"/>
					    </div>
					    <div class="col-sm-3"></div>
					  </div>
					 
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <input type="submit"  width="100" value="注册" name="submit" border="0"
						    style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						    height:35px;width:100px;color:white;">
					    </div>
					  </div>
					</form>
			</div>
			
			<div class="col-md-1"></div>
		  
		</div>
		</div>

	  
	<div class="container" >
		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="${pageContext.request.contextPath}/jsp/info.jsp">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>
	</div>
</body>
<script type="application/javascript">
	function changeVcode() {
		var img = document.getElementById("validateCode");
		img.src = "${pageContext.request.contextPath}/userServlet?method=validateCode&num="+Math.random();
	}
	
	function checkUsername() {
		var username = $("#username").val();
		var msg = $("#username_ajax_msg");
		
		$.ajax({
			url:"${pageContext.request.contextPath}/userServlet",
			method:"POST",
			data:"method=checkUsername&username="+username,
			dataType:"text",
			success:function(data){
				//1:被注册
				if (data=="1"){
					msg.html("<font style='color:red'>该用户名已被注册！</font>");
				} else {
					msg.html("<font style='color:green'>该用户名可以使用！</font>");
				}
			}
		});
	}
</script>
</html>




