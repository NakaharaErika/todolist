package service;

import java.util.List;

import dao.GenreWorkDaoJDBC;
import entity.GenreWork;

public class GenreWorkService {
	private GenreWorkDaoJDBC genreDao = new GenreWorkDaoJDBC();
	String genreSQL = "SELECT * FROM genres WHERE ownerId = ?";
	//特定のユーザーIDに紐づくジャンルを取得
	public List<GenreWork> getGenresForUser(String userId){
		return genreDao.getGenresByUserId(userId,genreSQL);
	}

}