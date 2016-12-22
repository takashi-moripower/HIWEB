package logistics.system.project.common.form;

import logistics.system.project.utility.annotation.NotEmpty;
import logistics.system.project.utility.annotation.Patterns;


public class ChangePasswordForm {
	@NotEmpty(field="古いパスワード", message = "{field.not.empty}")
//	@Patterns(regexp = "^[a-zA-Z0-9]{0,10}$", message="{field.password.input}", field="古いパスワード")
	private String oldPassword;

	@NotEmpty(field="新パスワード", message = "{field.not.empty}")
	@Patterns(regexp = "^[a-zA-Z0-9]{4,15}$", message="{field.password.input}", field="新パスワード")
	private String password;

	@NotEmpty(field="新パスワード確認", message = "{field.not.empty}")
	@Patterns(regexp = "^[a-zA-Z0-9]{4,15}$", message="{field.password.input}", field="新パスワード確認")
	private String confirmPassword;
	
	private String updateDt;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
}
