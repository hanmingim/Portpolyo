<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,order.*" %>    
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
	<h2 align="center">注文情報の照会/修正/削除</h2>
	<div align="center">
	<table border="1">
	<tr><th>注文番号</th><th>顧客名</th><th>連絡先</th>
		<th>価格</th><th>商品名</th><th>注文日付</th>
		<th>性別</th><th>顧客の特徴</th><th>テーブル番号</th></tr>
<%
	ArrayList orders=(ArrayList)request.getAttribute("ALL");
	int size = orders.size();
	for(int i=0; i<size; i++){
		Order odr = (Order)orders.get(i);
%>
	<tr><td><a href="orderSelect.do?OID=<%= odr.getOrderno() %>"><%= odr.getOrderno() %></a></td>
		<td><%= odr.getName() %></td>
		<td><%= odr.getTel() %></td>
		<td><%= odr.getPrice() %></td>
		<td>
<%
		String g = odr.getItem();
		if(g.equals("M001")){
			out.print("ステーキ");
		}else if(g.equals("M002")){
			out.print("パスター");
		}else if(g.equals("M003")){
			out.print("炒飯");	
		}else if(g.equals("T001")){
			out.print("アールグレイティー");	
		}else if(g.equals("T002")){
			out.print("抹茶");	
		}else if(g.equals("T003")){
			out.print("ロイヤルミルクティー");	
		}else if(g.equals("B001")){
			out.print("ブランドコーヒー");	
		}else if(g.equals("B002")){
			out.print("コーラー");	
		}else if(g.equals("B003")){
			out.print("サイダー");	
		}else if(g.equals("W001")){
			out.print("レッドワイン");	
		}else if(g.equals("W002")){
			out.print("ホワイトワイン");	
		}else if(g.equals("W003")){
			out.print("シャルドネ");	
		}
%>
		</td>
		<td><%= odr.getOrderdate() %></td>
		<td><%= odr.getGender() %></td>
		<td><%= odr.getType() %></td>
		<td><%= odr.getTbnum() %></td></tr>
<%
	}
%>	
	</table>
	</div>
</section>
<footer>
	<h3 align="center">KOSEA37KHM copyright 2023 All 
	right reserved</h3>
</footer>
</body>
</html>




