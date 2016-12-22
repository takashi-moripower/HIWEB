package logistics.system.project.common.Entity;

public class ContactRRKEntity {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getAddressKbn() {
		return addressKbn;
	}

	public void setAddressKbn(String addressKbn) {
		this.addressKbn = addressKbn;
	}

	public String getCompanyNm() {
		return companyNm;
	}

	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}

	public String getTantoNm() {
		return tantoNm;
	}

	public void setTantoNm(String tantoNm) {
		this.tantoNm = tantoNm;
	}

	public String getTantoTel() {
		return tantoTel;
	}

	public void setTantoTel(String tantoTel) {
		this.tantoTel = tantoTel;
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

	// 履歴ＩＤ
	private String id;

	// 会社コード
	private String companyCd;

	// 住所区分
	private String addressKbn;

	// 担当会社名
	private String companyNm;

	// 担当者名
	private String tantoNm;
	
	// 担当者電話
	private String tantoTel;
	
	// 登録者ＩＤ
	private String createId;
	
	// 登録者日時
	private String createDt;
	
	// 更新者ＩＤ
	private String updateId;
	
	// 更新者日時
	private String updateDt;
}
