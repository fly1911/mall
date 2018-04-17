<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:forward page="/productServlet">
	<jsp:param name="method" value="findIndexProducts" />
</jsp:forward>

<%-- <jsp:forward page="/categoryServlet">
	<jsp:param value="method" name="findAll"/>
</jsp:forward> --%>