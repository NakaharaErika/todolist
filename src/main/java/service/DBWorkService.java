package service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import dao.DBWorkDaoJDBC;
import entity.DBWork;

public class DBWorkService {

	DBWorkDaoJDBC dao = new DBWorkDaoJDBC();

	public DBWork login(String id,String pass) {
		try {
	        String hashedPassword = HashGenerator.generateHash(pass);
	        DBWork dbWork = new DBWork(id, hashedPassword);
	        return dao.checkAccount(dbWork);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
	    }
	}
	
	public Boolean createAccount(String userId,String password,String userName,String genre1,String genre2,String genre3) {
		if (dao.isUserIdExist(userId)) {
			return dao.createAcc(userId, password,userName,genre1,genre2,genre3);
		}
		return false;
	}
	
	public List<HashMap<String, String>> getTodoListByUserId(String no) {
	    return dao.getTodosByUserId(no);
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
	
	public void createTodoList(String title,String content,String genre,String priority,String date) {
		dao.createTodo(title,content,genre,priority,date);
	}

}