package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class CreateAccount{
	//アカウント新規作成。ユーザーIDがすでに登録されているものと重複していないかチェック後に、新規作成する
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkUserSQL = "SELECT COUNT(*) FROM account WHERE userID = ?";
    private String createAccountSQL = "INSERT INTO account (userID, pass, name, falsecnt) VALUES (?, ?, ?, 0)";

    public Boolean createAccount(String userId, String password, String userName) throws Exception {
    	//ユーザーIDが既に存在するかチェック
    	List<Object> params = Arrays.asList(userId);
    	List<HashMap<String, Object>> result = dao.executeQuery(checkUserSQL, params);
        
    	//ユーザーから得られた値がnullでなく、0個のデータ数である＝同名のユーザーは存在しない
        if (!result.isEmpty() && (Long) result.get(0).get("COUNT(*)") == 0) {
        	//パスワードをハッシュ化
        	String hashedPassword = HashGenerator.generateHash(password);
        	List<Object> createAccParams = Arrays.asList(userId, hashedPassword, userName);
            dao.executeUpdate(createAccountSQL,createAccParams);
            return true;
        }
        return false;
    }
}