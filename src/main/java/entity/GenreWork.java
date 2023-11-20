package entity;

public class GenreWork {
	/** ジャンルID */
	private int genreId;
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
	
	/**オーナー名のゲッターとセッター*/
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
