<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="myStyle.css">
</head>
<body>
<header>
	<h1 align="center">注文管理システム</h1>
</header>
<menu>
	<jsp:include page="menu_header.jsp"/>
</menu>
<section>
	<h2 align="center">ログイン結果</h2>
<%
	String result=request.getParameter("R");
	if(result.equals("OK")){//로그인 성공 메세지 출력
		out.print("ログインできました。<br/>");
		String id = (String)session.getAttribute("LOGINID");
		out.print("今日の一日も頑張ってください。"+ id +"様。");
	}else if(result.equals("NOPWD")){//암호불일치 메세지 출력
		out.print("パスワードが正しくないため、もう一度ご確認ください。");
	}else if(result.equals("NOID")){//계정없슴 메세지 출력
		out.print("アカウントが正しくないため、もう一度ご確認ください。");
	}
%>	
</section>
<footer>
	<h3 align="center">KOSEA37KHM copyright 2023 All 
	right reserved</h3>
</footer>
</body>
</html>