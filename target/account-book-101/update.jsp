<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String itemId = request.getParameter("itemId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿</title>
</head>
<body>
<h1>入出金行為更新</h1>
<form action="BookServlet?action=done" method="post">
名称：<input type="text" name="itemName" required><br>
支出：<input type="text" name="payment" value="0" required><br>
収入：<input type="text" name="income" value="0" required><br>
費目：
<select name="category">
	<option value="101">食費</option>
	<option value="102">日用品</option>
	<option value="103">衣服</option>
	<option value="104">美容</option>
	<option value="105">交際費</option>
	<option value="106">医療費</option>
	<option value="107">教育費</option>
	<option value="108">光熱費</option>
	<option value="109">交通費</option>
	<option value="110">通信費</option>
	<option value="111">事務費</option>
	<option value="112">住居費</option>
	<option value="201">給料</option>
	<option value="202">おこづかい</option>
	<option value="203">賞与</option>
	<option value="204">臨時収入</option>
</select><br>
<input type="submit" value="登録">
</form>
</body>
</html>