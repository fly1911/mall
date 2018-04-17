<%@ page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css">
	</HEAD>
	<body>
		<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img width="100%" src="${pageContext.request.contextPath}/img/admin/top_01.jpg">
				</td>

				<td width="100%" background="${pageContext.request.contextPath}/img/admin/top_100.jpg">
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" valign="bottom" background="${pageContext.request.contextPath}/img/admin/mis_01.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
						<% 
							request.setAttribute("date", new Date());
						%>
							<td width="85%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#000000"> 
									<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</font>
							</td>
							<td width="15%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="16"
											background="${pageContext.request.contextPath}/img/admin/mis_05b.jpg">
											<img
												src="${pageContext.request.contextPath}/img/admin/mis_05a.jpg"
												width="6" height="18">
										</td>
										<td width="155" valign="bottom"
											background="${pageContext.request.contextPath}/img/admin/mis_05b.jpg">
											用户名：<font style="color:green">${user.username}</font>
											<font color="blue"><s:property value="#session.existAdminUser.username"/></font>
										</td>
										<td width="10" align="right"
											background="${pageContext.request.contextPath}/img/admin/mis_05b.jpg">
											<img src="${pageContext.request.contextPath}/img/admin/mis_05c.jpg" width="6" height="18">
										</td>
									</tr>
								</table>
							</td>
							<td align="right" width="5%">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</HTML>
