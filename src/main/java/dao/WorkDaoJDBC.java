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

	//DB接続のための共有メソッド
	//今回はこれでOKだが、実務上ではConnectionはDaoの外で行う
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

    // 汎用のクエリメソッド（SELECT)返ってきた値をResultSetに入れて、serviceに返す
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

    // パラメータ(変数)の設定
    private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
    	//変数の入っている個数分回す
        for (int i = 0; i < params.size(); i++) {
        	//i+1の？にi番目の変数を渡す
            stmt.setObject(i + 1, params.get(i));
        }
    }

    // ResultSetから中身を取り出し、キーバリュー形式のリストに変換
    //　todo課題では、取り出し後の値を型変換する手間が発生するので、このメソッドはDaoの外でもOK
    private List<HashMap<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        List<HashMap<String, Object>> list = new ArrayList<>();
        //ResultSetMetaDataオブジェクトでResultSetオブジェクトの列の型とプロパティに関する情報を取得する
        ResultSetMetaData md = rs.getMetaData();
        //selectで得られた列数を確認
        int columns = md.getColumnCount();
        //得られた行数分回す
        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            //得られた列数分回して、値を入れる
            for (int i = 1; i <= columns; ++i) {
            	//DB上の列名をキーにして、対応する値を入れる
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            //１列分のデータを確定してリストに挿入
            list.add(row);
        }
        return list;
    }
}

//更新"UPDATE todo SET title=?, content=?, genre=?, priority=? WHERE No = ?"
//新規"INSERT INTO todo (userID, title, content, genre, priority, date) VALUES (?,?,?,?,?,?)"