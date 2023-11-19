package service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import dao.DBWorkDaoJDBC;
import entity.DBWork;

public class DBWorkService {

	DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
	
	private String checkUserIdSQL = "SELECT COUNT(*) FROM account WHERE userID = ?";
	private String checkUserPassSQL = "SELECT * FROM account WHERE userID=? AND pass=?";
	
	//ユーザーIDが既に登録されているか調べる
	public Boolean checkUserIDExist(String userId) {
        if (dao.doesUserIdExist(userId, checkUserIdSQL)) {
            return true;
        }
        return false;
    }
	
	//パスワードが一致しているか調べる
    public DBWork login(String id,String pass) {
		try {
	        String hashedPassword = HashGenerator.generateHash(pass);
	        DBWork dbWork = new DBWork(id);
	        return dao.checkAccount(dbWork,hashedPassword,checkUserPassSQL);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
	    }
	}
	
	

	public List<HashMap<String, String>> getTodoListBySort(String no, String item, String sort) {
	    return dao.getTodosBySort(no,item,sort);
	}
	
	public HashMap<String, String> getTodoListByNo(String todoNo) {
		return dao.getTodoByNo(todoNo);
	}
	
	public void updateTodoList(String todoNo,String title,String content,String genre,String priority) {
		dao.updateTodo(todoNo,title,content,genre,priority);
	}
	
	public List<HashMap<String, String>> getListPriorities() {
	    return dao.getPriorities();
	}
	
	public void destroyTodoList(String todoNo) {
		dao.destroyTodo(todoNo);
	}
	
	public void createTodoList(DBWork dbWork, String title,String content,String genre,String priority,String date) {
		dao.createTodo(dbWork, title,content,genre,priority,date);
	}
	
	public void editGenreList(DBWork dbWork, String genre1,String genre2,String genre3) {
		dao.editGenre(dbWork,genre1,genre2,genre3);
	}

}