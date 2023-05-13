<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="order.*" %>    
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
	<h2 align="center">注文情報の修正</h2>
	<div align="center">
	<form action="orderUpdate.do" method="post"
			onSubmit="return check()">
	<table border="1">
<%
	Order order=(Order)request.getAttribute("ODR");
%>	
	<tr><th>注文番号</th><td><input type="text" name="OID" 
		readonly value="<%= order.getOrderno() %>"/></td></tr>
	<tr><th>顧客名</th><td><input type="text" name="NAME"
		value="<%= order.getName() %>"/></td></tr>
	<tr><th>連絡先</th><td><input type="text" name="TEL"
		value="<%= order.getTel() %>"/></td></tr>
	<tr><th>価格</th><td><input type="text" name="PRICE"
		value="<%= order.getPrice() %>"/></td></tr>
	<tr>
    <th>注文コード</th>
    <td>
        <select name="ITEM">
            <option value="M001" <%= order.getItem().equals("M001") ? "selected" : "" %>>M001</option>
            <option value="M002" <%= order.getItem().equals("M002") ? "selected" : "" %>>M002</option>
            <option value="M003" <%= order.getItem().equals("M003") ? "selected" : "" %>>M003</option>
            <option value="T001" <%= order.getItem().equals("T001") ? "selected" : "" %>>T001</option>
            <option value="T002" <%= order.getItem().equals("T002") ? "selected" : "" %>>T002</option>
            <option value="T003" <%= order.getItem().equals("T003") ? "selected" : "" %>>T003</option>
            <option value="B001" <%= order.getItem().equals("B001") ? "selected" : "" %>>B001</option>
            <option value="B002" <%= order.getItem().equals("B002") ? "selected" : "" %>>B002</option>
            <option value="B003" <%= order.getItem().equals("B003") ? "selected" : "" %>>B003</option>
            <option value="W001" <%= order.getItem().equals("W001") ? "selected" : "" %>>W001</option>
            <option value="W002" <%= order.getItem().equals("W002") ? "selected" : "" %>>W002</option>
            <option value="W003" <%= order.getItem().equals("W003") ? "selected" : "" %>>W003</option>
        </select>
    </td>
</tr>
	<tr><th>注文日付</th><td><input type="date" name="ODATE"
		value="<%= order.getOrderdate() %>"/></td></tr>
	<tr>
        <th>性別</th>
        <td>
            <% 
                String gender = order.getGender();
                if (gender.equals("M")) {
            %>
            <input type="radio" name="GENDER" value="M" checked="checked">男
            <input type="radio" name="GENDER" value="F">女
            <% } else { %>
            <input type="radio" name="GENDER" value="M">男
            <input type="radio" name="GENDER" value="F" checked="checked">女
            <% } %>
        </td>
    </tr>
	<tr><th>顧客の特徴</th><td><input type="text" name="TYPE"
		value="<%= order.getType() %>"/></td></tr>
	<tr>
    <th>テーブル番号</th>
    <td>
        <select name="TBNUM">
            <%-- 選択したテーブルの番号に selected 追加 --%>
            <option value="" <% if (order.getTbnum() == null) out.print("selected"); %>>-- 선택 --</option>
            <option value="1" <% if ("1".equals(order.getTbnum())) out.print("selected"); %>>1</option>
            <option value="2" <% if ("2".equals(order.getTbnum())) out.print("selected"); %>>2</option>
            <option value="3" <% if ("3".equals(order.getTbnum())) out.print("selected"); %>>3</option>
            <option value="4" <% if ("4".equals(order.getTbnum())) out.print("selected"); %>>4</option>
            <option value="5" <% if ("5".equals(order.getTbnum())) out.print("selected"); %>>5</option>
            <option value="6" <% if ("6".equals(order.getTbnum())) out.print("selected"); %>>6</option>
            <option value="7" <% if ("7".equals(order.getTbnum())) out.print("selected"); %>>7</option>
            <option value="8" <% if ("8".equals(order.getTbnum())) out.print("selected"); %>>8</option>
            <option value="9" <% if ("9".equals(order.getTbnum())) out.print("selected"); %>>9</option>
            <option value="10" <% if ("10".equals(order.getTbnum())) out.print("selected"); %>>10</option>
        </select>
    </td>
</tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="修正" name="btn"/>
		<input type="submit" value="削除" name="btn"/>
		<input type="reset" value="キャンセル" name="btn"/>
		<input type="submit" value="照会" name="btn"/></td></tr>
	</table>
	</form>
	</div>
</section>
<footer>
	<h3 align="center">KOSEA37KHM copyright 2023 All 
	right reserved</h3>
</footer>
<script type="text/javascript">
function check(){
	if(confirm("作業を進みますか。")){
		return true;
	}else{
		return false;
	}
}
</script>
</body>
</html>




