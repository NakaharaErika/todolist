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

}