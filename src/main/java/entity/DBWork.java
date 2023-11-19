package entity;

public class DBWork {
	/** ユーザーNo */
	private String no;
	/** ユーザーID */
	private String id;
	/** 名前 */
	private String name;

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
    
    
}
