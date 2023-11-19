package service;

import java.util.HashMap;

import dao.DBWorkDaoJDBC;

public class CheckFalseCount{
	//IDとパスの打ち間違いをカウント。３回間違えるとログインできなくする
    private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
    private String checkfalseSQL = "SELECT falsecnt FROM account WHERE userID = ?";
    private String falsecountSQL = "UPDATE account SET falsecnt = falsecnt + 1 WHERE id = 1";

    public Boolean checkFalsecnt(String userId) {
    	HashMap<String, Integer> check = new HashMap<String, Integer>(dao.CheckFalseCnt(userId, checkfalseSQL));
        if (check < 3) {
            dao.UpFalseCnt(userId, falsecountSQL);
            return true;
        }
        return false;
    }
}