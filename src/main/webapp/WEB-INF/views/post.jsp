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
  <title>Todo詳細</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
    <h1>Todo詳細</h1>
   	<% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
			<% if (loggedInUser != null) { %>
			    <p><%= loggedInUser.getName() %>さん</p>
			<% } %>
    <p><%= request.getAttribute("message") %></p>
    
    <% HashMap<String, String> todoDetails = (HashMap<String, String>) request.getAttribute("todoDetails"); %>
    <% if (todoDetails != null) { %>
    <p><strong>タイトル：</strong><%= todoDetails.get("title") %></p>
    <p><strong>本文：</strong><%= todoDetails.get("content") %></p>
    <p><strong>登録日：</strong><%= todoDetails.get("date") %></p>
    <p><strong>ジャンル：</strong><%= todoDetails.get("genre") %></p>
    <p><strong>優先度：</strong><%= todoDetails.get("priority") %></p>
    
    <% } %>
    <ul>
      <li><p><a href="list">戻る</a></p></li>
      <li><p><a href='edit?id=<%= todoDetails.get("id") %>'>編集</a></p></li>
      <li><p><a href='destroy?id=<%= todoDetails.get("id") %>'>削除</a></p></li>
    </ul>      
</body>
</html>