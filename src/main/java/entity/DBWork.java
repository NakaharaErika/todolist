package entity;

public class DBWork {
	/** ユーザーNo */
	private String no;
	/** ユーザーID */
	private String id;
	/** 名前 */
	private String name;
	/** ジャンル1 */
	private String genre1;
	/** ジャンル2 */
	private String genre2;
	/** ジャンル3 */
	private String genre3;

	/**
	 * コンストラクタ
	 */
	public DBWork() {
	}

	
	public DBWork(String id) {
		this.id = id;
	}
	
	
	/**Noのゲッターとセッター*/
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	/**IDのゲッターとセッター*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**名前のゲッターとセッター*/
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    /**ジャンル1のゲッターとセッター*/
    public String getGenre1() {
    	return genre1;
    }
    public void setGenre1(String genre1) {
    	this.genre1 = genre1;
    }
    
    /**ジャンル2のゲッターとセッター*/
    public String getGenre2() {
    	return genre2;
    }
    public void setGenre2(String genre2) {
    	this.genre2 = genre2;
    }
    
    /**ジャンル3のゲッターとセッター*/
    public String getGenre3() {
    	return genre3;
    }
    public void setGenre3(String genre3) {
    	this.genre3 = genre3;
    }
}
