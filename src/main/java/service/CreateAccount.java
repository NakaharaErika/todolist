package service;

import dao.DBWorkDaoJDBC;

public class CreateAccount{
	//アカウント新規作成。ユーザーIDがすでに登録されているものと重複していないかチェック後に、新規作成する
    private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
    private String checkUserSQL = "SELECT COUNT(*) FROM account WHERE userID = ?";
    private String createAccountSQL = "INSERT INTO account (userID, pass, name) VALUES (?, ?, ?)";

    public Boolean createAccount(String userId, String password, String userName) {
        if (!dao.doesUserIdExist(userId, checkUserSQL)) {
            return dao.createAcc(userId, password, userName, createAccountSQL);
        }
        return false;
    }
}