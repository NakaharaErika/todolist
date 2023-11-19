package service;

import java.util.List;

public class GenreWorkService {

	//特定のユーザーIDに紐づくジャンルを取得
	public List<Genre> getGenresForUser(int userId){
		List<Genre> genres = new arrayList<>();
		String sql = "SELECT * FROM genres WHERE ownerId = ?"
		
		return genres;
	}

}