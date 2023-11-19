package service;

import java.util.HashMap;
import java.util.List;

import dao.DBWorkDaoJDBC;

public class GetTodoListByUserId{
	//ユーザーIDの主キーを元に、todoリストの一覧を作成する。
    private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
    private String createAccountSQL = "SELECT * FROM todo "
    		+ "INNER JOIN priority ON todo.priority = priority.id "
    		+ "INNER JOIN genres ON todo.genreNo  = genres.genreId "
    		+ "WHERE todo.userID=?";

    public List<HashMap<String, String>> getTodoListByUserId(String no) {
	    return dao.getTodosByUserId(no,createAccountSQL);
	}
}