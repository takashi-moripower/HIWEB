package logistics.system.project.common.Entity;

public class PostCodeEntity {
	
	// ID
	private String pcId;
	
	// 郵便番号
	private String postCode;
	
	// 都道府県名
	private String prefNm;
	
	// 市区町村名
	private String cityNm;
	
	// 番地等
	private String addrOther;

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
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
}