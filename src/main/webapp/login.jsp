<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿</title>
</head>
<body>
<h1>家計簿ログイン</h1>
<form action="/account-book-101/LoginServlet" method="post">
ユーザーID：<input type="text" name="userId" required><br>
パスワード：<input type="password" name="pass" required><br>
<input type="submit" value="ログイン">
</form>
<br>
ユーザーID：1<br>
パスワード：1234
</body>
</html>