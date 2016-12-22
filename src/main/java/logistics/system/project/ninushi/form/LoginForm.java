package logistics.system.project.ninushi.form;

import logistics.system.project.utility.annotation.NotEmpty;

public class LoginForm {
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	// ユーザID
	@NotEmpty(field = "ユーザID", message="{field.not.empty}")
	private String userId;
	
	// パスワード
	@NotEmpty(field = "パスワード", message="{field.not.empty}")
	private String password;
	
	private String username;

}
