package service;

import java.util.HashMap;
import java.util.List;

import dao.DBWorkDaoJDBC;

public class SortItems{
	private DBWorkDaoJDBC dao = new DBWorkDaoJDBC();
	
	public List<HashMap<String, String>> getTodoListBySort(String no, String item, String sort) {
		String sortSQL = "SELECT * FROM todo INNER JOIN priority ON todo.priority = priority.id WHERE userID = ? ORDER BY " + item + " " + sort;
	    return dao.getTodosBySort(no,sortSQL);
	}
}