package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.DBWork;

public class DBWorkDaoJDBC {

	
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/kogi_3";
		String dbUser = "root";
		String dbPassword = "";
		// JDBCドライバーをロード
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
	
	   //ユーザーが存在しているかどうか確認
		public DBWork checkAccount(DBWork dbWork) {
			// DBコネクション生成
			try (Connection connection = createConnection()) {
				// SQL実行オブジェクト生成
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM account WHERE userID=? AND pass=?");
				// SQL パラメータ設定
				pstmt.setString(1, dbWork.getId());
				pstmt.setString(2, dbWork.getPass());
				// SQL実行
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String name = rs.getString("name");
					dbWork.setName(name);
					String no = rs.getString("No");
					dbWork.setNo(no);
					String genre1 = rs.getString("genre1");
					dbWork.setGenre1(genre1);
					String genre2 = rs.getString("genre2");
					dbWork.setGenre2(genre2);
					String genre3 = rs.getString("genre3");
					dbWork.setGenre3(genre3);
					
					return dbWork;
				}
				return null;
			} catch (ClassNotFoundException | SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//todoテーブルから該当ユーザーのデータリストを取得
		public List<HashMap<String,String>> getTodosByUserId(String no) {
			List<HashMap<String,String>> rows = new ArrayList<>();
			try(Connection connection = createConnection()) {
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM todo INNER JOIN priority ON todo.priority = priority.id WHERE todo.userID=?");
				pstmt.setString(1,no);
				ResultSet rs = pstmt.executeQuery();
				
				//ユーザーのtodo情報を格納
				while (rs.next()) {
					HashMap<String,String> columns = new HashMap<>();
					columns.put("No", Integer.toString(rs.getInt("No")));
					columns.put("title", rs.getString("title"));
					columns.put("content", rs.getString("content"));
					columns.put("genre", rs.getString("genre"));
					columns.put("priority", rs.getString("priorityLevel"));
					
					//dateを格納
					java.sql.Timestamp timestamp = rs.getTimestamp("date");
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            String formattedDate = dateFormat.format(timestamp);
		            columns.put("date", formattedDate);
		            
		            rows.add(columns);
				}
			} catch (SQLException e) {
		        throw new RuntimeException(e);
		    } catch (ClassNotFoundException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		    return rows;
		}
		
		//todoテーブルからソート内容に沿って該当ユーザーのデータリストを取得
		public List<HashMap<String,String>> getTodosBySort(String no, String item, String sort) {
			List<HashMap<String,String>> rows = new ArrayList<>();
			try(Connection connection = createConnection()) {
				String sql = "SELECT * FROM todo INNER JOIN priority ON todo.priority = priority.id WHERE userID = ? ORDER BY " + item + " " + sort;
		        PreparedStatement pstmt = connection.prepareStatement(sql);
		        pstmt.setString(1, no);
		        ResultSet rs = pstmt.executeQuery();
				
				//ユーザーのtodo情報を格納
				while (rs.next()) {
					HashMap<String,String> columns = new HashMap<>();
					columns.put("No", Integer.toString(rs.getInt("No")));
					columns.put("title", rs.getString("title"));
					columns.put("content", rs.getString("content"));
					columns.put("genre", rs.getString("genre"));
					columns.put("priority", rs.getString("priorityLevel"));
					
					//dateを格納
					java.sql.Timestamp timestamp = rs.getTimestamp("date");
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            String formattedDate = dateFormat.format(timestamp);
		            columns.put("date", formattedDate);
		            
		            rows.add(columns);
				}
			} catch (SQLException e) {
		        throw new RuntimeException(e);
		    } catch (ClassNotFoundException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		    return rows;
		}
		
		//todoテーブルから該当番号のカラム詳細を取得
		public HashMap<String, String> getTodoByNo(String todoNo) {
		    HashMap<String, String> todoData = new HashMap<>();
		    try (Connection connection = createConnection()) {
		        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM todo INNER JOIN priority ON todo.priority = priority.id WHERE No = ?");
		        pstmt.setString(1, todoNo);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            todoData.put("id", rs.getString("No"));
		            todoData.put("title", rs.getString("title"));
		            todoData.put("content", rs.getString("content"));
		            todoData.put("genre", rs.getString("genre"));
		            todoData.put("priority", rs.getString("priorityLevel"));

		            java.sql.Timestamp timestamp = rs.getTimestamp("date");
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            todoData.put("date", dateFormat.format(timestamp));
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        throw new RuntimeException(e);
		    }
		    return todoData;
		}
		
		//updateServletから渡された値を入れてDBを更新する
				public void updateTodo(String todoNo,String title,String content,String genre,String priority) {
				    try (Connection connection = createConnection()) {
				        PreparedStatement pstmt = connection.prepareStatement("UPDATE todo SET title=?, content=?, genre=?, priority=? WHERE No = ?");
				        pstmt.setString(1, title);
				        pstmt.setString(2, content);
				        pstmt.setString(3, genre);
				        pstmt.setString(4, priority);
				        pstmt.setString(5, todoNo);
				        pstmt.executeUpdate();
				    } catch (SQLException | ClassNotFoundException e) {
				        throw new RuntimeException(e);
				    }
				}

				

}