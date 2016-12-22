package logistics.system.project.common.form;

public class UpdateInfo {

	private boolean success;
	private String updateDt;
	
	public UpdateInfo() {

	}

	public UpdateInfo(boolean success) {
		this.success = success;
	}
	
	public UpdateInfo(boolean success, String updateDt) {
		this.success = success;
		this.updateDt = updateDt;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}

}
