package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class GetTodoListByNo {
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String todoListSQL = "SELECT * FROM kogi_3.todo "
    		+ "INNER JOIN priority ON todo.priority = priority.id "
    		+ "INNER JOIN genres ON todo.genreNo = genres.genreId "
    		+ "WHERE todo.No = ?";

    
    public HashMap<String, String> getTodoListByNo(String no) throws Exception {
    	List<Object> params = Arrays.asList(no);
    	//記事Noに紐づくユーザーのtodoリストを取り出す。
        List<HashMap<String, Object>> result = dao.executeQuery(todoListSQL, params);
        
        // リストから最初の結果を取得し、HashMap<String, String>に変換
        if (!result.isEmpty()) {
            HashMap<String, Object> row = result.get(0);
            HashMap<String, String> todoDetails = new HashMap<>();
            for (String key : row.keySet()) {
                Object value = row.get(key);
                todoDetails.put(key, value != null ? value.toString() : null);
            }
            return todoDetails;
        }
        return null;
    }
}