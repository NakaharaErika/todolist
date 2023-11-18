package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.DBWork;
import service.HashGenerator;

public class DBWorkDaoJDBC {

	//本当はSQLをString型で渡してくる。次から実践！
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/kogi_3";
		String dbUser = "root";
		String dbPassword = "";
		// JDBCドライバーをロード
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
	
	   //ログイン時にユーザーが存在しているかどうか確認
		public DBWork checkAccount(DBWork dbWork, String pass,String sql) {
			// DBコネクション生成
			try (Connection connection = createConnection()) {
				// SQL実行オブジェクト生成
				PreparedStatement pstmt = connection.prepareStatement(sql);
				// SQL パラメータ設定
				pstmt.setString(1, dbWork.getId());
				pstmt.setString(2, pass);
				// SQL実行
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String name = rs.getString("name");//ユーザーの名前を取得
					dbWork.setName(name);
					String no = rs.getString("No");//ユーザーの主キーを取得
					dbWork.setNo(no);
					
					return dbWork;//エンティティに値をセット
				}
				return null;
			} catch (ClassNotFoundException | SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		// アカウント新規作成時にユーザーIDが既に存在するかどうかをチェック
		public boolean doesUserIdExist(String userId,String sql) {
		    try (Connection connection = createConnection()) {
		        PreparedStatement pstmt = connection.prepareStatement(sql);
		        pstmt.setString(1, userId);
		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            return rs.getInt(1) > 0;
		        }
		        return false;
		    } catch (ClassNotFoundException | SQLException e) {
		        throw new RuntimeException(e);
		    }
		}

		
		//ユーザーアカウントを新規登録
		public Boolean createAcc(String userId,String pass,String userName,String sql) {
			try (Connection conn = createConnection()) {
	            // 5.パスワードをハッシュ化する
	            String hashedPassword = HashGenerator.generateHash(pass);
	            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	                stmt.setString(1, userId);
	                // 6.ハッシュ化した値を利用
	                stmt.setString(2, hashedPassword);
	                stmt.setString(3, userName);
	  
	                stmt.execute();

	                return true;
	            }
			} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
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
					columns.put("date", rs.getString("date"));
		            
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
					columns.put("date", rs.getString("date"));
		            
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
		            todoData.put("date", rs.getString("date"));
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
		//priorityから値を取り出して、キーバリュー値として格納する
		public List<HashMap<String, String>> getPriorities() {
	        List<HashMap<String, String>> priorities = new ArrayList<>();
	        try (Connection connection = createConnection()) {
	            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM priority");
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                HashMap<String, String> priority = new HashMap<>();
	                priority.put("id", rs.getString("id"));
	                priority.put("priorityLevel", rs.getString("priorityLevel"));
	                priorities.add(priority);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            throw new RuntimeException(e);
	        }
	        return priorities;
	    }
		
		//updateServletから渡された値を入れてDBを更新する
		public void destroyTodo(String todoNo) {
		    try (Connection connection = createConnection()) {
		        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM todo WHERE No = ?");
		        pstmt.setString(1, todoNo);
		        pstmt.executeUpdate();
		    } catch (SQLException | ClassNotFoundException e) {
		        throw new RuntimeException(e);
		    }
		}
		
		//todoを新しく作成して挿入する
		public void createTodo(DBWork dbWork, String title,String content,String genre,String priority,String date) {
		    try (Connection connection = createConnection()) {
		        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO todo (userID, title, content, genre, priority, date) VALUES (?,?,?,?,?,?)");
		        pstmt.setString(1, dbWork.getNo());
		        pstmt.setString(2, title);
		        pstmt.setString(3, content);
		        pstmt.setString(4, genre);
		        pstmt.setString(5, priority);
		        pstmt.setString(6, date);
		        pstmt.executeUpdate();
		    } catch (SQLException | ClassNotFoundException e) {
		        throw new RuntimeException(e);
		    }
		}

		//todoを新しく作成して挿入する
		public void editGenre(DBWork dbWork, String genre1, String genre2, String genre3) {
		    try (Connection connection = createConnection()) {
		        PreparedStatement pstmt = connection.prepareStatement("UPDATE todo SET title=?, content=?, genre=?, priority=? WHERE No = ?");
		        
		        pstmt.setString(2, title);
		        pstmt.setString(3, content);
		        pstmt.setString(1, dbWork.getNo());
		        pstmt.executeUpdate();
		    } catch (SQLException | ClassNotFoundException e) {
		        throw new RuntimeException(e);
		    }
		}		

}