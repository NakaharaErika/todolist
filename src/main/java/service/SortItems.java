package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class SortItems {
    private WorkDaoJDBC dao = new WorkDaoJDBC();

    public List<HashMap<String, String>> getTodoListBySort(String no, String item, String sortItem) throws Exception {
        String sortSQL = "SELECT * FROM todo "
                + "INNER JOIN priority ON todo.priority = priority.id "
                + "INNER JOIN genres ON todo.genreNo  = genres.genreId "
                + "WHERE userID = ? ORDER BY " + item + " " + sortItem;
        
        
        List<Object> params = Arrays.asList(no);
        
        List<HashMap<String, Object>> result = dao.executeQuery(sortSQL, params);
        List<HashMap<String, String>> sortedTodos = new ArrayList<>();

        for (HashMap<String, Object> row : result) {
            HashMap<String, String> todo = new HashMap<>();
            for (String key : row.keySet()) {
                todo.put(key, row.get(key).toString());
            }
            sortedTodos.add(todo);
        }
        return sortedTodos;
    }
}
