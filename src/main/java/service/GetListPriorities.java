package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;

public class GetListPriorities {
    private WorkDaoJDBC dao = new WorkDaoJDBC();
    private String getPrioritySQL = "SELECT * FROM priority";

    
    public List<HashMap<String, String>> getListPriorities() throws Exception {
    	List<Object> params = Arrays.asList();
    	//記事Noに紐づくユーザーのtodoリストを取り出す。
        List<HashMap<String, Object>> result = dao.executeQuery(getPrioritySQL, params);
        List<HashMap<String, String>> priorityList = new ArrayList<>();

        // 全ての行をHashMap<String, String>に変換し、リストに追加
        for (HashMap<String, Object> row : result) {
            HashMap<String, String> priorityMap = new HashMap<>();
            for (String key : row.keySet()) {
                Object value = row.get(key);
                priorityMap.put(key, value != null ? value.toString() : null);
            }
            priorityList.add(priorityMap);
        }
        return priorityList;
    }
}