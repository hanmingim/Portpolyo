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
	<%
		String m = request.getParameter("M");
		if(m != null){
	%>
		<script type="text/javascript">
			alert("サービスを利用する際に、ログインが必要です。");
		</script>
	<% 	} %>
	<h2 align="center">ログイン</h2>
	<div align="center">
	<form action="login.do" method="post" onSubmit="return check(this)">
	アカウント : <input type="text" name="ID" size="10"/><br/>
	パスワード : <input type="password" name="PWD" size="10"/><br/><br/>
	<input type="submit" value="ログイン"/>
	<input type="reset" value="キャンセル"/>
	</form>
	</div>
</section>
<footer>
	<h3 align="center">KOSEA37KHM copyright 2023 All 
	right reserved</h3>
</footer>
<script type="text/javascript">
function check(f){
	if(f.ID.value == ''){
		alert("アカウントを入力してください。"); return false;
	}
	if(f.PWD.value == ''){
		alert("パスワードを入力してください。"); return false;
	}
}
</script>
</body>
</html>






