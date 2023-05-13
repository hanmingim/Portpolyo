<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
<table><tr>
<td><a href="index.jsp"> ホームへ</a>&nbsp;</td>
<td><a href="orderEntry.do"> 注文登録</a>&nbsp;</td>
<td><a href="orderAll.do"> 注文情報</a>&nbsp;</td>
<td><a href="salesAll.do"> 総売り上げ照会</a>&nbsp;</td>
<td><a href="ordercode.jsp"> 注文コード</a>&nbsp;</td>
<td><a href="table.jsp"> テーブルの構造</a>&nbsp;</td>
<%
	String id=(String)session.getAttribute("LOGINID");
	if(id == null){
%>
<td><a href="login.jsp"> ログイン</a></td>
<%
	} else {
%>
<td>
<table><tr><td>
<font color="black">今日の一日も頑張ってください。 <br/><%= id %>様。</font><br/>
</td></tr></table>
<a href="logout.do"> ログアウト</a>
</td>
<%
	}
%>
</tr></table></div>








