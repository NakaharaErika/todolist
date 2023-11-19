package service;

import dao.DBWorkDaoJDBC;

public class ResetAccount{
	//アカウントと紐づいているパスワードを新しいものに変更
	private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
    private String ResetPassSQL = "UPDATE account SET pass = ? WHERE userID = ?;";

    public void ResetPass(String userId, String password) {
        dao.resetPass(userId, password, ResetPassSQL);
    }
}