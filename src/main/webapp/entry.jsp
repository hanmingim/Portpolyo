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
<%
	String oid=request.getParameter("OID");
%>
<header>
	<h1 align="center">注文管理システム</h1>
</header>
<menu>
	<jsp:include page="menu_header.jsp"/>
</menu>
<section>
	<h2 align="center">注文登録</h2>
	<form action="orderRegister.do" method="post" 
			onSubmit="return check(this)">
	<div align="center">
	<table border="1">
	<tr><th>注文番号</th><td><input type="text" 
		name="OID" readonly value="<%= oid %>"/></td></tr>
	<tr><th>顧客名</th><td><input type="text"
		name="NAME"/></td></tr>
	<tr><th>連絡先</th><td><input type="text"
		name="TEL"/></td></tr>
	<tr><th>価格</th><td><input type="text"
		name="PRICE"/></td></tr>
	<tr><th>注文コード</th><td>
	<select name="ITEM">
			<option value="M001">M001</option>
			<option value="M002">M002</option>
			<option value="M003">M003</option>
			<option value="T001">T001</option>
			<option value="T002">T002</option>
			<option value="T003">T003</option>
			<option value="B001">B001</option>
			<option value="B002">B002</option>
			<option value="B003">B003</option>
			<option value="W001">W001</option>
			<option value="W002">W002</option>
			<option value="W003">W003</option>
		</select></td></tr>
	<tr><th>注文日付</th><td><input type="date"
		name="ODATE"/></td></tr>
	<tr><th>性別</th>
    <td>
        <label><input type="radio" name="GENDER" value="M"/>男</label>
        <label><input type="radio" name="GENDER" value="F"/>女</label>
    </td>
</tr>
	<tr><th>顧客の特徴</th><td><input type="text"
		name="TYPE"/></td></tr>
	<tr>
    <th>テーブル番号</th>
    <td>
        <select name="TBNUM">
            <option value="">-- 選択 --</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
        </select>
    </td>
</tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="登録" name="btn"/>
		<input type="reset" value="キャンセル" name="btn"/>
		<input type="button" value="照会" 
					onClick="doSelect()"/>
		</td></tr>
	</table>
	</div>
	</form>
</section>
<footer>
	<h3 align="center">KOSEA37KHM copyright 2022 All 
	right reserved</h3>
</footer>
<script type="text/javascript">
function doSelect(){
	if(confirm("照会を行ってもよろしいでしょうか。")){
		location.href="orderAll.do";//조회서블릿 호출
	}
}
function check(frm){
	if(frm.NAME.value == ''){
		alert("顧客名を入力してください。"); return false;
	}
	if(frm.TEL.value == ''){
		alert("連絡先をを入力してください。"); return false;
	}
	if(frm.PRICE.value == ''){
		alert("価格を入力してください。"); return false;
	}
	if(frm.ITEM.value == ''){
		alert("商品を選択してください。"); return false;
	}
	if(frm.ODATE.value == ''){
		alert("注文日付を選択してください。"); return false;
	}
	if(frm.GENDER.value == ''){
		alert("性別を選択してください。"); return false;
	}
	if(frm.TYPE.value == ''){
		alert("顧客の特徴を入力してください。"); return false;
	}
	if(frm.TBNUM.value == ''){
		alert("テーブル番号を選択してください。"); return false;
	}
	if(frm.TBNUM.value>=11){
		alert("テーブル番号は10番以下です。"); return false;
	}
	if(confirm("入力した内容は間違いありませんか。")){
		return true;
	}else{
		return false;
	}
}
</script>
</body>
</html>