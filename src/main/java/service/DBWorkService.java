package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;
import entity.DBWork;

public class DBWorkService {

	WorkDaoJDBC dao = new WorkDaoJDBC();
	
	private String checkUserIdSQL = "SELECT COUNT(*) FROM account WHERE userID = ?";
	private String checkUserPassSQL = "SELECT * FROM account WHERE userID=? AND pass=?";
	
	//ユーザーIDが既に登録されているか調べる
	public Boolean checkUserIDExist(String userId) throws Exception{
		//asList配列：引数で指定した配列をリストとして返す（?部分に入れる変数を格納）
        List<Object> params = Arrays.asList(userId);
        //SQLの実行
        List<HashMap<String, Object>> result = dao.executeQuery(checkUserIdSQL,params);
        //もし、得られた結果がnulはなく、１行目のキーの中身がnullでなければ（この場合は1)なら、1(true)を返す
		if (!result.isEmpty() && result.get(0).get("COUNT(*)") != null) {
			return (Long) result.get(0).get("COUNT(*)") > 0;
		}
		return false;
    }
	
	//パスワードが一致しているか調べる
    public DBWork login(String id,String pass) throws Exception {
		String hashedPassword = HashGenerator.generateHash(pass);
		//変数を格納
		List<Object> params = Arrays.asList(id, hashedPassword);
		//SQL実行
		List<HashMap<String, Object>> result = dao.executeQuery(checkUserPassSQL, params);
		
	    if(!result.isEmpty()) {
	    	//得られた配列の１番目（そもそも１個しか抽出されないけど）を取得して、キャスト後にDBWorkクラスに値を登録
	    	HashMap<String, Object> row = result.get(0);
	    	//DBWorkエンティティのコンストラクタにidを格納してインスタンス化
	    	DBWork dbWork = new DBWork(id);
	    	//キャスト
	    	dbWork.setName((String) row.get("name"));
	    	//int→Stringに変換
	    	dbWork.setNo(Integer.toString((Integer) row.get("No")));
	    	return dbWork;
	    }
	    return null;
	}
}