package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class GetTodoListByUserId {
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String todoListSQL = "SELECT * FROM todo "
                                + "INNER JOIN priority ON todo.priority = priority.id "
                                + "INNER JOIN genres ON todo.genreNo  = genres.genreId "
                                + "WHERE todo.userID=?";
    //ログインしたユーザーIDに紐づくtodoのリストを取得する
    public List<HashMap<String, String>> getTodoListByUserId(String no) throws Exception {
    	List<Object> params = Arrays.asList(no);
    	//ログインしたID紐づくユーザーのtodoリストを取り出す。
        List<HashMap<String, Object>> result = dao.executeQuery(todoListSQL, params);
        List<HashMap<String, String>> todos = new ArrayList<>();
        
        //取り出したリストをArrayListに格納する
        for (HashMap<String, Object> row : result) {
        	//今回は全てString型に変換する
            HashMap<String, String> todo = new HashMap<>();
            for (String key : row.keySet()) {
                todo.put(key, row.get(key).toString());
            }
            todos.add(todo);
        }
        return todos;
    }
}
