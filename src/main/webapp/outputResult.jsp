<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, sales.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>총매출조회</title>
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
	<h2 align="center">総売り上げ照会</h2>
	<%
		ArrayList<Output> list = (ArrayList<Output>) request.getAttribute("LIST");
		int count = list.size();
		int totalSales = 0;
		for (Output sales : list) {
			totalSales += sales.getTotal();
		}
	%>
	<h3 align="center">総販売件数: <%= count %> 件</h3>
	<h3 align="center">総売り上げ: <%= totalSales %>円</h3>
	<div align="center">
		<table border="1">
			<tr>
				<th>商品名</th>
				<th>販売件数</th>
				<th>金額</th>
			</tr>
			<%
				for (Output sales : list) {
					%>
					<tr>
						<td>
							<%
								String g = sales.getItem();
								switch(g) {
									case "M001":
										out.print("ステーキ");
										break;
									case "M002":
										out.print("パスター");
										break;
									case "M003":
										out.print("炒飯");
										break;
									case "T001":
										out.print("アールグレイティー");
										break;
									case "T002":
										out.print("抹茶");
										break;
									case "T003":
										out.print("ロイヤルミルクティー");
										break;
									case "B001":
										out.print("ブランドコーヒー");
										break;
									case "B002":
										out.print("コーラー");
										break;
									case "B003":
										out.print("サイダー");
										break;
									case "W001":
										out.print("レッドワイン");
										break;
									case "W002":
										out.print("ホワイトワイン");
										break;
									case "W003":
										out.print("シャルドネ");
										break;
									default:
										out.print(g);
								}
							%>
						</td>
						<td><%= sales.getCounter() %></td>
						<td><%= sales.getTotal() %></td>
					</tr>
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
