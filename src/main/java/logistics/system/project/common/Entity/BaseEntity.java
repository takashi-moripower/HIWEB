package logistics.system.project.common.Entity;


public class BaseEntity {
	// 削除フラグ
	private String deleteFlg;
	
	// 登録者ＩＤ
	private String createId;
	
	// 登録日時
	private String createDt;
	
	// 更新者ＩＤ
	private String updateId;
	
	// 更新日時
	private String updateDt;

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
}
