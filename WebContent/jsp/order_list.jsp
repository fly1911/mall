<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
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

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${page.data}" var="order">
							<tbody>
								<tr class="success">
									<th colspan="6">订单编号:${order.oid}</th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
									<th>状态</th>
								</tr>
								<c:forEach items="${order.items}" var="item">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${item.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${item.product.pname}</a>
										</td>
										<td width="20%">
											${item.product.shop_price}
										</td>
										<td width="10%">
											${item.count}
										</td>
										<td width="15%">
											<span class="subtotal">￥${item.subtotal}</span>
										</td>
										<td width="15%">
											<c:if test="${order.state==1}">
												未付款
											</c:if>
											<c:if test="${order.state==2}">
												未发货
											</c:if>
											<c:if test="${order.state==3}">
												未签收
											</c:if>
											<c:if test="${order.state==4}">
												未结束
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="text-align: center;">
				<ul class="pagination" style="text-align:center; margin-top:10px;">
					<c:if test="${page.page!=1}">
						<li>
							<a href="${pageContext.request.contextPath}/orderServlet?method=findUserOrder&page=${page.page-1}" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					
					<!--1、第一页-->
					<!--2、不是第一页或是最后一页-->
					<!--3、最后一页-->
					<c:forEach begin="${page.page}" end="${page.page+5}" var="i">
							<c:if test="${i==page.page}">
								<li class="active, disabled">
									<a href="${pageContext.request.contextPath}/orderServlet?method=findUserOrder&page=${i}">${i}</a>
								</li>
							</c:if>
							<c:if test="${i!=page.page and i<=page.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath}/orderServlet?method=findUserOrder&page=${i}">${i}</a>
								</li>
							</c:if>
					</c:forEach>
					<c:if test="${page.page!=page.totalPage}">
						<li>
							<a href="${pageContext.request.contextPath}/orderServlet?method=findUserOrder&page=${page.page+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
				<div >总共${page.totalPage}页，${page.count}条记录</div>
			</div>
		</div>

		<div class="container">
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

</html>