package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class CheckFalseCount{
	//IDとパスの打ち間違いをカウント。３回間違えるとログインできなくする
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkfalseSQL = "SELECT falsecnt FROM account WHERE userID = ?";
    private String falsecountSQL = "UPDATE account SET falsecnt = falsecnt + 1 WHERE userID = ?";
    private String countStopSQL = "UPDATE account SET falsecnt = 0 WHERE userID = ?";
    
    //使用可能なアカウントかチェック（カウントが3以上なら不可
    public boolean isAccountLocked (String userId) throws Exception {
	    	List<Object> params = Arrays.asList(userId);
	    	List<HashMap<String, Object>> result = dao.executeQuery(checkfalseSQL, params);
	    	//結果値が空ではないなら、得られた値をintに変換（失敗カウントの取り出し）
	    	if(!result.isEmpty()) {
		    	int falseCount = (Integer) result.get(0).get("falsecnt");
		    	return falseCount >= 3;
	    	}
	    return false;
    }
    
    //失敗カウントを増やす
    public Boolean incrementFalseCnt(String userId) throws Exception {
    	List<Object> params = Arrays.asList(userId);
        dao.executeUpdate(falsecountSQL, params);
        //現在の失敗回数を取得
        List<HashMap<String, Object>> result = dao.executeQuery(checkfalseSQL, params);

    	if(!result.isEmpty()) {
	    	int falseCount = (Integer) result.get(0).get("falsecnt");
	    	return falseCount < 3;
    	}
    	return true;
    }
    
    //ユーザー認証に成功したら、失敗カウントを0にする
    public void countReset(String userId) throws Exception {
    	List<Object> params = Arrays.asList(userId);
    	dao.executeUpdate(countStopSQL, params);
    } 
}