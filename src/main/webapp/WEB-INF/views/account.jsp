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
  <title>アカウント新規作成</title>
</head>
<body>
    <h1>新規作成</h1>
    <%String message = (String)request.getAttribute("message"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (message != null) {%>
	    <%=message %>
	<%} %>
	<!-- ログインフォーム。ユーザーIDとパスワードの入力を行う -->
	<form action="start" method="account">
		<span><strong>ユーザーID</strong></span>
	    <input type="text" name="user_id">
	    <br>
	    <span><strong>パスワード</strong></span>
	    <input type="text" name="password">
	    <br>
	    <span><strong>ユーザー名</strong></span>
	    <input type="text" name="name">
	    <br>
	    <br>
	    <span><strong>ジャンル登録</strong></span><br>
	    <span>タスク分類に使うジャンルを一つ以上登録してください（最大3つ）</span>
	    <input type="text" name="genre1">
	    <input type="text" name="genre2">
	    <input type="text" name="genre3">
		
		<br>
		<br>    
	    <input type="submit" value="新規登録">
	</form>
	
</body>
</html>