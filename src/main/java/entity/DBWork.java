package entity;

public class DBWork {
	/** ユーザーNo */
	private String no;
	/** ユーザーID */
	private String id;
	/** パスワード */
	private String pass;
	/** 名前 */
	private String name;

	/**
	 * コンストラクタ
	 */
	public DBWork() {
	}

	
	public DBWork(String id, String pass) {
		this.id = id;
		this.pass = pass;
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

	/**パスワードのゲッターとセッター*/
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**名前のゲッターとセッター*/
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
