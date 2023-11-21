package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.WorkDaoJDBC;
import entity.GenreWork;

public class GenreWorkService {
	private WorkDaoJDBC dao = new WorkDaoJDBC();
	String genreSQL = "SELECT * FROM genres WHERE ownerId = ?";
	//特定のユーザーIDに紐づくジャンルを取得
	public List<GenreWork> getGenresForUser(String userId) throws Exception{
		List<Object> params = Arrays.asList(userId);
		
		List<HashMap<String,Object>> result = dao.executeQuery(genreSQL,params);
		List<GenreWork> genres = new ArrayList<>();
		
		for (HashMap<String, Object> row : result) {
            GenreWork genre = new GenreWork();
            genre.setGenreId(Integer.toString((Integer) row.get("genreId")));
            genre.setGenreName((String) row.get("name"));
            genre.setOwnerId(userId);
            genres.add(genre);
        }
        return genres;
    }
}