<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ログイン画面</title>
</head>
<body>
    <h1>ログイン画面</h1>
    <%String failureMessage = (String)request.getAttribute("loginFailure"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (failureMessage != null) {%>
	    <%=failureMessage %>
	<%} %>
	<!-- ログインフォーム。ユーザーIDとパスワードの入力を行う -->
	<form action="start" method="post">
		<span><strong>ユーザーID</strong></span>
	    <input type="text" name="user_id">
	    <span><strong>パスワード</strong></span>
	    <input type="password" name="password">
	    <br>
	    <input type="submit" value="ログイン">
	</form>
	<br>
	<p><a href='newaccount'>アカウントを新規作成</a><p>
	
</body>
</html>