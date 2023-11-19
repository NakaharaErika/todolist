package entity;

public class GenreWork {
	/** ジャンルID */
	private int genreId;
	/** ジャンル名 */
	private String genreName;
	
	/**
	 * コンストラクタ
	 */
	public GenreWork() {
	}

	
	public GenreWork(int genreId,String genreName) {
		this.genreId = genreId;
		this.genreName = genreName;
	}
	
	
	/**ジャンルIDのゲッターとセッター*/
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	/**ジャンル名のゲッターとセッター*/
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
