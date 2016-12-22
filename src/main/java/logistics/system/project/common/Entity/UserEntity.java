package logistics.system.project.common.Entity;

import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.Patterns;

public class UserEntity  extends BaseEntity {

	public UserEntity(){}

	public UserEntity( String index){
		this.index = index;
	}

	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	public String getGyomuSb() {
		return gyomuSb;
	}
	public void setGyomuSb(String gyomuSb) {
		this.gyomuSb = gyomuSb;
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
	public String getRenrakuTel() {
		return renrakuTel;
	}
	public void setRenrakuTel(String renrakuTel) {
		this.renrakuTel = renrakuTel;
	}
	public String getUserBS() {
		return userBS;
	}
	public void setUserBS(String userBS) {
		this.userBS = userBS;
	}
	public String getUserYK() {
		return userYK;
	}
	public void setUserYK(String userYK) {
		this.userYK = userYK;
	}

	public UpdateType getType() {
		return type;
	}

	public void setType(UpdateType type) {
		this.type = type;
	}

	public String getDispDl() {
		return dispDl;
	}

	public void setDispDl(String dispDl) {
		this.dispDl = dispDl;
	}

	public String getCompanyNm() {
		return companyNm;
	}

	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}

	public String getRyoritu() {
		return ryoritu;
	}

	public void setRyoritu(String ryoritu) {
		this.ryoritu = ryoritu;
	}

	public String getSimbSb() {
		return simbSb;
	}

	public void setSimbSb(String simbSb) {
		this.simbSb = simbSb;
	}

	public String getSihrSt() {
		return sihrSt;
	}

	public void setSihrSt(String sihrSt) {
		this.sihrSt = sihrSt;
	}

	public String getHokenMn() {
		return hokenMn;
	}

	public void setHokenMn(String hokenMn) {
		this.hokenMn = hokenMn;
	}

	public enum UpdateType{
		DELETE, UPDATE, INSERT
	}

	private String index;

	// ユーザＩＤ
	private String userId;
	// ユーザ名
	@Length(field = "ユーザ名", max = "20")
	private String username;
	// ログインＩＤ
	@Length(field = "担当者Email", max = "60")
	@Patterns(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", message="{email.not.correct}", field="担当者Email")
	private String loginId;
	// ログインパスワード
	private String loginPassword;
	// 会社コード
	private String companyCd;
	// 会社名称
	private String companyNm;
	// 業務種別
	private String gyomuSb;
	// 郵便番号
	private String postCode;
	// 都道府県名
	private String prefNm;
	// 市区町村名
	private String cityNm;
	// 番地等
	private String addrOther;

	// 連絡先電話
//	@Patterns(regexp = "^$|^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message="{tel.format.incorrect}", field="担当者電話")
	@Patterns(regexp = "^$|^[0-9-]*$", message="{tel.format.incorrect}", field="担当者電話")
	private String renrakuTel;

	// 部署
	@Length(field = "部署", max = "10")
	private String userBS;

	// 役職
	@Length(field = "役職", max = "10")
	private String userYK;

	// 更新種類
	private UpdateType type;

	// 表示遅延時間
	private String dispDl;
	// 料率
	private String ryoritu;
	// 締日種別
	private String simbSb;
	// 支払サイト
	private String sihrSt;

	//保険金額
	private String hokenMn;

}
