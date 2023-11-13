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

}