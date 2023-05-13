<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String result=request.getParameter("R");
	if(result.equals("OK")){
%>
	<script type="text/javascript">
		alert("注文情報が修正できました。");
		location.href="index.jsp";
	</script>
<% } else { %>
	<script type="text/javascript">
		alert("注文情報が修正できませんでした。");
		location.href="index.jsp";
	</script>
<% } %>
</body>
</html>







