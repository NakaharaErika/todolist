package service;

import java.util.HashMap;
import java.util.List;

import dao.DBWorkDaoJDBC;
import entity.DBWork;

public class DBWorkService {

	DBWorkDaoJDBC dao = new DBWorkDaoJDBC();

	public DBWork login(String id,String password) {
		DBWork dbWork = new DBWork(id,password);
		return dao.checkAccount(dbWork);
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