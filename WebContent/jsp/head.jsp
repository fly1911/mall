<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="application/javascript">
	$(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/categoryServlet",
			method:"POST",
			data:"method=list",
			dataType:"json",
			success:function(data){
				$.each(data,function(i,e){
					$("#menu").append("<li><a href='${pageContext.request.contextPath}/productServlet?method=pageProduct&page=1&cid="+e.cid+"'>"+e.cname+"</a></li>");	
				});
			}
		});
	  }
	);
</script>
	<!--
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
						<c:if test="${not empty sessionScope.user}">
							<li>${user.username}</li>
							<li><a href="${pageContext.request.contextPath}/cartServlet?method=list">购物车</a></li>
							<li><a href="${pageContext.request.contextPath}/orderServlet?method=findUserOrder">我的订单</a></li>
							<li><a href="${pageContext.request.contextPath}/userServlet?method=logout">注销</a></li>
						</c:if>
						
						<c:if test="${empty sessionScope.user}">
							<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
						</c:if>
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
							<a class="navbar-brand" href="${pageContext.request.contextPath}">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="menu">
								<%-- <li class="active">
									<a href="${pageContext.request.contextPath}/jsp/product_list.jsp">手机数码<span class="sr-only">(current)</span></a>
								</li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li> --%>
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
			</div>