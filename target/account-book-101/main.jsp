<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="model.Item,java.util.ArrayList" %>
<%
@SuppressWarnings("unchecked")
ArrayList<Item> itemList = (ArrayList<Item>) request.getAttribute("itemList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿</title>
</head>
<body>
<h1>家計簿メイン</h1>
<p>
<c:out value="${account.name}" />さん、ログイン中
<a href="/account-book-101/LogoutServlet">ログアウト</a>
</p>
<form action="/account-book-101/MainServlet?action=search" method="post">
		<input type="search" name="search" placeholder="キーワードを入力">
		<input type="submit" name="submit" value="検索">
</form>
<p>
<a href="/account-book-101/MainServlet?action=done">登録</a>
</p>
<p>
-------------------------------------------------------------------------<br>
<c:forEach var="item" items="${itemList}">
	名称：${item.itemName}<br>
	${item.date}<br>
	支出：${item.payment}　収入：${item.income}<br>
	費目：${item.category}<br>
	<form action="/account-book-101/MainServlet?action=update" method="post">
		<input type="hidden" name="itemId" value="${item.itemId}">
		<input type="hidden" name="itemName" value="${item.itemName}">
		<input type="hidden" name="payment" value="${item.payment}">
		<input type="hidden" name="income" value="${item.income}">
		<input type="hidden" name="date" value="${item.date}">
		<input type="hidden" name="category" value="${item.category}">
		<input type="hidden" name="userName" value="${item.userName}">
		<input type="submit" value="更新">
	</form>
	<form action="/account-book-101/MainServlet?action=delete" method="post">
		<input type="hidden" name="itemId" value="${item.itemId}">
		<input type="submit" value="削除">
	</form>
	-------------------------------------------------------------------------<br>
</c:forEach>
</p>
</body>
</html>