package service;

import java.util.HashMap;

import dao.DBWorkDaoJDBC;

public class CheckFalseCount{
	//IDとパスの打ち間違いをカウント。３回間違えるとログインできなくする
    private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
    private String checkfalseSQL = "SELECT falsecnt FROM account WHERE userID = ?";
    private String falsecountSQL = "UPDATE account SET falsecnt = falsecnt + 1 WHERE userID = ?";
    private String countStopSQL = "UPDATE account SET falsecnt = 0 WHERE userID = ?";
    
    //使用可能なアカウントかチェック（カウントが3以上なら不可
    public boolean isAccountLocked (String userId) {
    	HashMap<String, Integer> check = dao.CheckFalseCnt(userId, checkfalseSQL);
    	int falseCount = check.getOrDefault("flg", 0);
    	return falseCount >= 3;
    }
    
    //失敗カウントを増やす
    public Boolean incrementFalseCnt(String userId) {
        dao.UpFalseCnt(userId, falsecountSQL);
        //現在の失敗回数を取得
        HashMap<String, Integer> check = dao.CheckFalseCnt(userId, checkfalseSQL);
    	int falseCount = check.getOrDefault("flg", 0);
    	return falseCount < 3;
    }
    
    //ユーザー認証に成功したら、失敗カウントを0にする
    public void countReset(String userID) {
    	dao.CountReset(userID,countStopSQL);
    }
    
    
}