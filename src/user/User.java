package user;

public class User {
	String posOznaka;
	Integer posIdentifikator;
	Integer userId;
	String oznaka;
	Integer identifikator;
	Integer identifikatorZaposlenog;
	String username;
	String password;
	boolean aktivan;
	String UserRole;

	public User(String posOznaka, Integer posIdentifikator, Integer userId, String oznaka, Integer identifikator,
			Integer identifikatorZaposlenog, String username, String password, boolean aktivan, String userRole) {
		super();
		this.posOznaka = posOznaka;
		this.posIdentifikator = posIdentifikator;
		this.userId = userId;
		this.oznaka = oznaka;
		this.identifikator = identifikator;
		this.identifikatorZaposlenog = identifikatorZaposlenog;
		this.username = username;
		this.password = password;
		this.aktivan = aktivan;
		UserRole = userRole;
	}

	public String getPosOznaka() {
		return posOznaka;
	}

	public void setPosOznaka(String posOznaka) {
		this.posOznaka = posOznaka;
	}

	public Integer getPosIdentifikator() {
		return posIdentifikator;
	}

	public void setPosIdentifikator(Integer posIdentifikator) {
		this.posIdentifikator = posIdentifikator;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Integer getIdentifikator() {
		return identifikator;
	}

	public void setIdentifikator(Integer identifikator) {
		this.identifikator = identifikator;
	}

	public Integer getIdentifikatorZaposlenog() {
		return identifikatorZaposlenog;
	}

	public void setIdentifikatorZaposlenog(Integer identifikatorZaposlenog) {
		this.identifikatorZaposlenog = identifikatorZaposlenog;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
}
