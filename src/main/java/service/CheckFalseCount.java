package service;

import java.util.HashMap;

import dao.DBWorkDaoJDBC;

public class CheckFalseCount{
	//IDとパスの打ち間違いをカウント。３回間違えるとログインできなくする
    private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
    private String checkfalseSQL = "SELECT falsecnt FROM account WHERE userID = ?";
    private String falsecountSQL = "UPDATE account SET falsecnt = falsecnt + 1 WHERE userID = ?";
    private String countStopSQL = "UPDATE account SET falsecnt = 0 WHERE userID = ?";

    public Boolean checkFalsecnt(String userId) {
    	//現在のカウントをチェック
    	HashMap<String, Integer> check = dao.CheckFalseCnt(userId, checkfalseSQL);
    	int falseCount = check.getOrDefault("flg", 0);
        if (falseCount < 3) {
        	//3回いないならカウントアップして終了
            dao.UpFalseCnt(userId, falsecountSQL);
            return true;
        }
        return false;
    }
    //ユーザー認証に成功したら、失敗カウントを0にする
    public void countReset(String userID) {
    	dao.CountReset(userID,countStopSQL);
    }
    
    
}