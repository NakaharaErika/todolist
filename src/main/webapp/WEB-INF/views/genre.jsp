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
  <title>Todo編集</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
  <script>
		function confirmUpdate() {
		    let genre1Input = document.querySelector('[name="genre1"]');
		    let genre1Original = genre1Input.nextElementSibling.textContent.trim();
		    let genre2Input = document.querySelector('[name="genre2"]');
		    let genre2Original = genre2Input.nextElementSibling.textContent.trim();
		    let genre3Input = document.querySelector('[name="genre3"]');
		    let genre3Original = genre3Input.nextElementSibling.textContent.trim();
		
		    // 元の値と現在の値を比較
		    if ((genre1Original !== "-" && genre1Input.value !== genre1Original) ||
		    	(genre2Original !== "-" && genre2Input.value !== genre2Original) ||
		    	(genre3Original !== "-" && genre3Input.value !== genre3Original)) {
		        // 変更があった場合、確認ダイアログを表示
		        return confirm("現在登録されているタスクのジャンルが変わる可能性があります。本当によろしいですか？");
		    }
		
		    // 変更がなければそのまま送信
		    return true;
		}
		
</script>
  
  
  
</head>
<body>
	<h1>ジャンル</h1>
	<% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
			<% if (loggedInUser != null) { %>
			    <p><%= loggedInUser.getName() %>さん</p>
			<% } %>
	<p><%= request.getAttribute("message") %></p>
	
		<form action="createGenre" method="get" onsubmit="return confirmUpdate()">
			<strong>新しいジャンル構成</strong>
			<strong>現在のジャンル構成</strong><br>
			
				<input type="text" name="genre1" value="<%= loggedInUser.getGenre1() != null ? loggedInUser.getGenre1() : "" %>">
				<label><%= loggedInUser.getGenre1() != null ? loggedInUser.getGenre1() : "-" %></label>
				<br>
				<input type="text" name="genre2" value="<%= loggedInUser.getGenre2() != null ? loggedInUser.getGenre2() : "" %>">
				<label><%= loggedInUser.getGenre2() != null ? loggedInUser.getGenre2() : "-" %></label>
				<br>
				<input type="text" name="genre3" value="<%= loggedInUser.getGenre3() != null ? loggedInUser.getGenre3() : "" %>">
				<label><%= loggedInUser.getGenre3() != null ? loggedInUser.getGenre3() : "-" %></label>
				<br>
		<br>
		<button type="submit">保存する</button>
		<a href='list'>キャンセル</a>
	</form>
	
	 
	<ul>
		<li><a href="list">戻る</a></li>
	</ul>
	
</body>
</html>