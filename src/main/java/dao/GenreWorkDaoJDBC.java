package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.GenreWork;

public class GenreWorkDaoJDBC {

	//本当はSQLをString型で渡してくる。次から実践！
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/kogi_3";
		String dbUser = "root";
		String dbPassword = "";
		// JDBCドライバーをロード
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
	
	   //ログイン時にユーザーのジャンル一覧を確認
		public List<GenreWork> getGenresByUserId(String userId,String sql) {
			List<GenreWork> genres = new ArrayList<>();
			// DBコネクション生成
			try (Connection connection = createConnection()) {
				// SQL実行オブジェクト生成
				PreparedStatement pstmt = connection.prepareStatement(sql);
				// SQL パラメータ設定
				pstmt.setString(1, userId);
				// SQL実行
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					GenreWork genre = new GenreWork();
					genre.setGenreId(rs.getInt("genreId"));
					genre.setGenreName(rs.getString("name"));
					genres.add(genre);
				}
			} catch (ClassNotFoundException | SQLException e) {
				throw new RuntimeException(e);
			}
			return genres;
		}
		
		}