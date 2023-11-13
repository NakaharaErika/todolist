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
  <title>Todo編集</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
	<h1>todo編集</h1>
	<p><%= request.getAttribute("message") %></p>
	<form action="update" method="get">
		<input type="hidden" name="id" value='<%= request.getAttribute("id") %>'>
		<label for="title">タイトル</label><br>
		<input type="text" name="title" value='<%= request.getAttribute("title") %>'><br>
		<br>
		<label for="content">本文</label><br>
		<textarea name="content" cols="30" rows="10"><%= request.getAttribute("content") %></textarea><br>
		<br>
		<button type="submit">保存する</button>
		<a href='/show?id=<%= request.getAttribute("id") %>'>キャンセル</a>
	</form>
	<ul>
		<li><a href="list">戻る</a></li>
	</ul>
	
</body>
</html>