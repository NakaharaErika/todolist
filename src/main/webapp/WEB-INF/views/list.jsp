<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="entity.DBWork" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Todoリスト</title>
</head>
<body>
	<h1>Todoリスト</h1>
		<% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
			<% if (loggedInUser != null) { %>
			    <p>ようこそ、<%= loggedInUser.getName() %>さん</p>
			<% } %>

	<br>
		<% String message = (String) request.getAttribute("message"); %>
			<% if (message != null) { %>
			    <p><%= message %></p>
			<% } %>
	<span><strong>ID</strong></span>
	<span><strong>タイトル</strong></span>
	<span><strong>ジャンル</strong></span>
	<span><strong>登録日</strong></span>
	<span><strong>優先度</strong></span><br>
	
	<% ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>)request.getAttribute("rows"); %>
	<% int counter = 1; %>
	<% for(HashMap<String,String> columns : rows){ %>
	    <span><%= counter++ %></span>
	    <span><a href='show?id=<%= columns.get("No") %>'><%= columns.get("title") %></a></span>
	    <span><%= columns.get("genre") %></span>
	    <span><%= columns.get("date") %></span>
	    <span><%= columns.get("priority") %></span>
	    <br>
	<% } %>

	
	<br>
	<form action="sort" method = "POST">
	<p>todoリストの並び順を指定してください</p>
	<select name = "items">
		<option selected value = "#">項目</option> 
		<option value = "id">id</option>
		<option value = "date">登録日</option>
		<option value = "genre">ジャンル</option>
		<option value = "priority">優先度</option>
	</select>
	<select name = "sort">
		<option selected value = "#">並び替え順</option> 
		<option value = "asc">昇順</option>
		<option value = "desc">降順</option>
	</select>
	<input type = "submit" value = "実行">
</form>
	<br>
	<p><a href='new'>新規作成</a><p>
	
</body>
</html>