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
  <title>Todo新規作成</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
	<h1>Todo新規作成</h1>
	<% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
			<% if (loggedInUser != null) { %>
			    <p><%= loggedInUser.getName() %>さん</p>
			<% } %>
	<p><%= request.getAttribute("message") %></p>
	
	<form action="create" method="get">
		<label for="title">タイトル</label><br>
		<input type="text" name="title"><br>
		<br>
		<label for="content">本文</label><br>
		<textarea name="content" cols="30" rows="10"></textarea><br>
		<br>
		<label for="date">締切日</label><br>
		<input type="date" name="date"><br>
		
	
			<label for="genre">ジャンル</label><br>
			<select name="genre" id="genre">
			    <% if (loggedInUser.getGenre1() != null) { %>
			        <option value="<%= loggedInUser.getGenre1() %>" selected><%= loggedInUser.getGenre1() %></option>
			    <% } %>
			    <% if (loggedInUser.getGenre2() != null) { %>
			        <option value="<%= loggedInUser.getGenre2() %>"><%= loggedInUser.getGenre2() %></option>
			    <% } %>
			    <% if (loggedInUser.getGenre3() != null) { %>
			        <option value="<%= loggedInUser.getGenre3() %>"><%= loggedInUser.getGenre3() %></option>
		    	<% } %>
			</select>
			
		<br>
		
		<label for="priority">優先度</label><br>
		<% List<HashMap<String, String>> priorities = (List<HashMap<String, String>>) request.getAttribute("priorities"); %>
		<% for (HashMap<String, String> prioritylist : priorities) { %>
		    <input type="radio" name="priority" value="<%= prioritylist.get("id") %>"> <%= prioritylist.get("priorityLevel") %>
		<% } %>
		<br>

		
		
		<button type="submit">保存する</button>
	</form>
	
	<ul>
		<li><p><a href='list'>戻る</a></p></li>
	</ul>
</body>
</html>