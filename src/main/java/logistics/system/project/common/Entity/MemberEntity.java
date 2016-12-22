package logistics.system.project.common.Entity;

import logistics.system.project.utility.Constants;
import logistics.system.project.utility.annotation.Date;
import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.Mail;
import logistics.system.project.utility.annotation.NotEmpty;
import logistics.system.project.utility.annotation.Patterns;
import logistics.system.project.utility.annotation.PostCode;
import logistics.system.project.utility.annotation.Range;
import logistics.system.project.utility.annotation.Tel;
import net.sf.json.JSONArray;

public class MemberEntity extends BaseEntity {
	
	// 会社コード
	private String companyCd;
	
	// 得意先コード
	@NotEmpty(field="得意先コード", message = "{field.not.empty}")
	@Patterns(regexp = "^[a-zA-Z0-9]{4}$", message="{field.custom.cd.input}", field="得意先コード")
	private String customCd;
	
	// 事業所コード
	@NotEmpty(field="事業所コード", message = "{field.not.empty}")
	@Patterns(regexp = "^[a-zA-Z0-9]{2}$", message="{field.office.cd.input}", field="事業所コード")
	private String officeCd;
	
	// 得意先名
	@Length(field = "得意先名", max = "20")
	private String companyNm;
	
	// 事業所名
	@Length(field = "事業所名", max = "20")
	private String officeNm;
	
	// 契約日
	private String keiyakuDay;
	
	// 契約日
	@Date(field = "契約日", param="jp", message = "{data.format.incorrect}")
	@Length(field = "契約日", max = "8", types={Length.Type.DATE_TYPE})
	private String keiyakuDayDis;
	
	// 契約期間
	private String keiyakuKgDay;
	
	// 契約期間
	@Date(field = "契約期間", param="jp", message = "{data.format.incorrect}")
	@Length(field = "契約期間", max = "8", types={Length.Type.DATE_TYPE})
	private String keiyakuKgDayDis;
	
	// 利用端末数
	@Patterns(regexp = "^[0-9]{0,11}$", message="{field.format.integer}", field="利用端末数")
	@Length(field = "利用端末数", max = "20")
	private String riyoTm;
	
	// 業務種別
	@NotEmpty(field="業務種別", message = "{field.not.empty}")
	@Patterns(regexp = "^[019]$", message="{field.not.selected}", field="業務種別")
	private String gyomuSb;
	
	// 料率
	@Patterns(regexp = "^[0-9]{0,2}$", message="{field.format.integer}", field="料率")
	@Range(min=0, max=99, field = "料率", message="{field.not.in.range}")
	private String ryoritu;
	
	// 保険金額
	@Patterns(regexp = "^[0-9]{0,11}$", message="{field.format.integer}", field="保険金額")
	private String hokenMn;
	
	// 郵便番号
	@PostCode(field="郵便番号")
	private String postCode;
	
	// 都道府県名
	@NotEmpty(field = "都道府県名", message="{field.not.empty}")
	private String prefNm;
	
	// 市区町村名
	@NotEmpty(field = "市区町村名", message="{field.not.empty}")
	@Length(field = "市区町村名", max = "40")
	private String cityNm;
	
	// 番地等
	@Length(field = "番地等", max = "60")
	private String addrOther;
	
	// 責任者名
	@Length(field = "責任者名", max = "20")
	private String sekininNm;
	
	// 会社TEL
	@Tel(field = "会社TEL")
	@Length(field = "会社TEL", max = "20")
	private String companyTel;
	
	// 会社FAX
	@Tel(field = "会社FAX")
	@Length(field = "会社FAX", max = "20")
	private String companyFax;
	
	// 会社EMAIL
	@Length(field = "会社EMAIL", max = "60")
	@Mail(field = "会社EMAIL")
	private String companyEmail;
	
	// 経理名
	@Length(field = "経理名", max = "20")
	private String keiriNm;
	
	// 経理部署
	@Length(field = "経理部署", max = "10")
	private String keiriBs;
	
	// 経理役職
	@Length(field = "経理役職", max = "10")
	private String keiriYk;
	
	// 経理TEL
	@Tel(field = "経理TEL")
	@Length(field = "経理TEL", max = "20")
	private String keiriTel;
	
	// 経理EMAIL
	@Length(field = "経理EMAIL", max = "60")
//	@Patterns(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", message="{email.not.correct}", field="経理EMAIL")
	@Mail(field = "経理EMAIL")
	private String keiriEmail;
	
	// 銀行コード
	@Length(field = "銀行コード", max = "10")
	@Patterns(regexp = "^[A-Za-z0-9]*$", message="{field.format.engInteger}", field="銀行コード")
	private String ginkouCd;
	
	// 銀行名
	@Length(field = "銀行名", max = "20")
	private String ginkouNm;
	
	// 支店コード
	@Length(field = "支店コード", max = "10")
	@Patterns(regexp = "^[A-Za-z0-9]*$", message="{field.format.engInteger}", field="支店コード")
	private String sitenCd;
	
	// 支店名
	@Length(field = "支店名", max = "20")
	private String sitenNm;
	
	// 口座種類
	private String kozaSr;
	
	// 口座番号
	@Length(field = "口座番号", max = "20")
	@Patterns(regexp = "^[0-9]*$", message="{field.format.integer}", field="口座番号")
	private String kozaNo;
	
	// 締日種別
	private String simbSb;
	
	// 支払サイト
	private String sihrSt;
	
	// 表示遅延時間
	@Patterns(regexp = "^[0-9]{0,11}$", message="{field.format.integer}", field="表示遅延時間")
	private String dispDl;
	
	// 業務種別
	private String gyomuSbNm;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getCustomCd() {
		return customCd;
	}

	public void setCustomCd(String customCd) {
		this.customCd = customCd;
	}

	public String getOfficeCd() {
		return officeCd;
	}

	public void setOfficeCd(String officeCd) {
		this.officeCd = officeCd;
	}

	public String getCompanyNm() {
		return companyNm;
	}

	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}

	public String getOfficeNm() {
		return officeNm;
	}

	public void setOfficeNm(String officeNm) {
		this.officeNm = officeNm;
	}

	public String getKeiyakuDay() {
		return keiyakuDay;
	}

	public void setKeiyakuDay(String keiyakuDay) {
		this.keiyakuDay = keiyakuDay;
	}

	public String getKeiyakuKgDay() {
		return keiyakuKgDay;
	}

	public void setKeiyakuKgDay(String keiyakuKgDay) {
		this.keiyakuKgDay = keiyakuKgDay;
	}


	public String getRiyoTm() {
		return riyoTm;
	}

	public void setRiyoTm(String riyoTm) {
		this.riyoTm = riyoTm;
	}

	public void setRyoritu(String ryoritu) {
		this.ryoritu = ryoritu;
	}

	public void setHokenMn(String hokenMn) {
		this.hokenMn = hokenMn;
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

	public String getSekininNm() {
		return sekininNm;
	}

	public void setSekininNm(String sekininNm) {
		this.sekininNm = sekininNm;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getKeiriNm() {
		return keiriNm;
	}

	public void setKeiriNm(String keiriNm) {
		this.keiriNm = keiriNm;
	}

	public String getKeiriBs() {
		return keiriBs;
	}

	public void setKeiriBs(String keiriBs) {
		this.keiriBs = keiriBs;
	}

	public String getKeiriYk() {
		return keiriYk;
	}

	public void setKeiriYk(String keiriYk) {
		this.keiriYk = keiriYk;
	}

	public String getKeiriTel() {
		return keiriTel;
	}

	public void setKeiriTel(String keiriTel) {
		this.keiriTel = keiriTel;
	}

	public String getKeiriEmail() {
		return keiriEmail;
	}

	public void setKeiriEmail(String keiriEmail) {
		this.keiriEmail = keiriEmail;
	}

	public String getGinkouNm() {
		return ginkouNm;
	}

	public void setGinkouNm(String ginkouNm) {
		this.ginkouNm = ginkouNm;
	}

	public String getSitenNm() {
		return sitenNm;
	}

	public void setSitenNm(String sitenNm) {
		this.sitenNm = sitenNm;
	}

	public String getKozaSr() {
		return kozaSr;
	}

	public void setKozaSr(String kozaSr) {
		this.kozaSr = kozaSr;
	}

	public String getKozaNo() {
		return kozaNo;
	}

	public void setKozaNo(String kozaNo) {
		this.kozaNo = kozaNo;
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

	public String getDispDl() {
		return dispDl;
	}

	public void setDispDl(String dispDl) {
		this.dispDl = dispDl;
	}

	public String getGyomuSbNm() {
		if (Constants.GYOMU_SB_NINUSHI.equals(this.getGyomuSb())) {
			this.gyomuSbNm = "荷主";
		} else if (Constants.GYOMU_SB_UNSO.equals(this.getGyomuSb())) {
			this.gyomuSbNm = "運送会社";
		} else if (Constants.GYOMU_SB_TRAIL.equals(this.getGyomuSb())) {
			this.gyomuSbNm = "管理者";
		}
		return gyomuSbNm;
	}

	public void setGyomuSbNm(String gyomuSbNm) {
		this.gyomuSbNm = gyomuSbNm;
	}

	public String getKeiyakuDayDis() {
		return keiyakuDayDis;
	}

	public void setKeiyakuDayDis(String keiyakuDayDis) {
		this.keiyakuDayDis = keiyakuDayDis;
	}

	public String getKeiyakuKgDayDis() {
		return keiyakuKgDayDis;
	}

	public void setKeiyakuKgDayDis(String keiyakuKgDayDis) {
		this.keiyakuKgDayDis = keiyakuKgDayDis;
	}

	public String getRyoritu() {
		return ryoritu;
	}

	public String getHokenMn() {
		return hokenMn;
	}

	public String getGinkouCd() {
		return ginkouCd;
	}

	public void setGinkouCd(String ginkouCd) {
		this.ginkouCd = ginkouCd;
	}

	public String getSitenCd() {
		return sitenCd;
	}

	public void setSitenCd(String sitenCd) {
		this.sitenCd = sitenCd;
	}
	
	public String toString() {
		JSONArray jsonArray = JSONArray.fromObject(this);
		return jsonArray.toString();
	}

}
