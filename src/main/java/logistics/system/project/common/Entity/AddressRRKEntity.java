package logistics.system.project.common.Entity;

public class AddressRRKEntity {

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

	public String getAddrNm() {
		return addrNm;
	}

	public void setAddrNm(String addrNm) {
		this.addrNm = addrNm;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPrefNm() {
		return prefNm;
	}

	public void setPrefNm(String prefNm) {
		this.prefNm = prefNm;
	}

	public String getCityNm() {
		return cityNm;
	}

	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	public String getAddrOther() {
		return addrOther;
	}

	public void setAddrOther(String addrOther) {
		this.addrOther = addrOther;
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

	private String id;

	// 会社コード
	private String companyCd;

	// 住所区分
	private String addressKbn;

	// 住所名称
	private String addrNm;

	// 郵便番号
	private String postCode;

	// 都道府県名
	private String prefNm;

	// 市区町村名
	private String cityNm;

	// 番地等
	private String addrOther;
	
	// 登録者ＩＤ
	private String createId;
	
	// 登録者日時
	private String createDt;
	
	// 更新者ＩＤ
	private String updateId;
	
	// 更新者日時
	private String updateDt;
}
