package entity;

public class GenreWork {
	/** ジャンルID */
	private String genreId;
	/** ジャンル名 */
	private String genreName;
	/** ジャンル保有者名 */
	private String ownerId;
	
	
	/**
	 * コンストラクタ
	 */
	public GenreWork() {
	}

	
	public GenreWork(String ownerId) {
		this.ownerId = ownerId;
		
	}
	
	
	/**ジャンルIDのゲッターとセッター*/
	public String getGenreId() {
		return genreId;
	}
	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}
	
	/**ジャンル名のゲッターとセッター*/
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	/**オーナー名のゲッターとセッター*/
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
