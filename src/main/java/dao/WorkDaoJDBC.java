package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkDaoJDBC {

	//本当はSQLをString型で渡してくる。次から実践！
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/kogi_3";
		String dbUser = "root";
		String dbPassword = "";
		// JDBCドライバーをロード
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
	
	// 汎用の更新メソッド
    public void executeUpdate(String sql, List<Object> params) throws SQLException, ClassNotFoundException {
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	//prepareしたsqlと変数のリストを渡す
            setParameters(stmt, params);
            stmt.executeUpdate();
        }
    }

    // 汎用のクエリメソッド（SELECT)返ってきた値をresultSetに入れて、serviceに返す
    public List<HashMap<String, Object>> executeQuery(String sql, List<Object> params) throws SQLException, ClassNotFoundException {
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	//prepareしたsqlと変数のリストを渡す
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                return resultSetToList(rs);
            }
        }
    }

    // パラメータの設定
    private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
    	//変数の入っている個数分回す
        for (int i = 0; i < params.size(); i++) {
        	//i+1の？にi番目の変数を渡す
            stmt.setObject(i + 1, params.get(i));
        }
    }

    // ResultSetからリストに変換
    private List<HashMap<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        List<HashMap<String, Object>> list = new ArrayList<>();
        //ResultSetオブジェクトの列の型とプロパティに関する情報を取得する
        ResultSetMetaData md = rs.getMetaData();
        //rs が持っている列の数(SELECTで得られた列の数）と、rs にある最初の列を WHERE 節に使用できるかどうかを判別
        int columns = md.getColumnCount();
        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            //得られた列の数分回す
            for (int i = 1; i <= columns; ++i) {
            	//得られた列名をキーにして、値を入れる
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }
}