package service;

import java.util.Arrays;
import java.util.List;

import dao.WorkDaoJDBC;

public class DestroyTodo{
	//アカウント削除
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String checkUserSQL = "DELETE FROM todo WHERE No = ?\"";
    
    public void destroyTodoList(String no) throws Exception {
    	List<Object> params = Arrays.asList(no);
    	dao.executeUpdate(checkUserSQL, params);
 
    }
}