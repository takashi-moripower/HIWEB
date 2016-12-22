package logistics.system.project.ninushi.form;

import logistics.system.project.utility.annotation.Date;
import logistics.system.project.utility.annotation.Digits;
import logistics.system.project.utility.annotation.DoubleByte;
import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.NotEmpty;
import logistics.system.project.utility.annotation.Patterns;
import logistics.system.project.utility.annotation.Time;

/**
 * 集荷先
 * 
 * @author
 * 
 */
public class SyukaForm {

	public String getSyukaDay() {
		return syukaDay;
	}

	public void setSyukaDay(String syukaDay) {
		this.syukaDay = syukaDay;
	}

	public String getSyukaTime() {
		return syukaTime;
	}

	public void setSyukaTime(String syukaTime) {
		this.syukaTime = syukaTime;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNisyuCd() {
		return nisyuCd;
	}

	public void setNisyuCd(String nisyuCd) {
		this.nisyuCd = nisyuCd;
	}

	public String getNisyuNm() {
		return nisyuNm;
	}

	public void setNisyuNm(String nisyuNm) {
		this.nisyuNm = nisyuNm;
	}

	public String getNisugataCd() {
		return nisugataCd;
	}

	public void setNisugataCd(String nisugataCd) {
		this.nisugataCd = nisugataCd;
	}

	public String getNisugataNm() {
		return nisugataNm;
	}

	public void setNisugataNm(String nisugataNm) {
		this.nisugataNm = nisugataNm;
	}

	// 集荷日付
	@NotEmpty(field = "集荷日付", message = "{field.not.empty}")
	@Date(field = "集荷日付", param="jp", message = "{data.format.incorrect}")
	@Length(field = "集荷日付", max = "8", types={Length.Type.DATE_TYPE})
//	@SingleByte(field = "集荷日時")
	private String syukaDay;

	// 集荷時間
	@NotEmpty(field = "集荷時間", message = "{field.not.empty}")
	@Time(field = "集荷時間", message = "{data.format.incorrect}")
	@Length(field = "集荷時間", max = "4", types={Length.Type.TIME_TYPE})
//	@SingleByte(field = "集荷時間")
	private String syukaTime;

	// 集荷先
	@NotEmpty(field = "集荷先", message = "{field.not.empty}")
	@Length(field = "集荷先", max = "20")
	@DoubleByte(field = "集荷先")
	private String addrNm;

	// 郵便番号
	@Length(field = "[集荷]郵便番号", max = "7", types={Length.Type.MINUS_TYPE})
	private String postCode;

	// 都道府県
	@NotEmpty(field = "[集荷]都道府県", message = "{field.not.empty}")
	private String prefNm;

	// 市区町村
	@NotEmpty(field = "[集荷]市区町村", message = "{field.not.empty}")
	@Length(field = "[集荷]市区町村", max = "40")
	@DoubleByte(field = "[集荷]市区町村")
	private String cityNm;

	// 番地等
	@Length(field = "[集荷]番地等", max = "60")
	@DoubleByte(field = "[集荷]番地等")
	private String addrOther;

	// 担当者名
	@Length(field = "[集荷]担当者名", max = "20")
	@DoubleByte(field = "[集荷]担当者名")
	private String tantoNm;

	// 担当TEL
//	@Patterns(regexp = "^$|^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message="{tel.format.incorrect}", field="担当")
	@Patterns(regexp = "^$|^[0-9-]*$", message="{tel.format.incorrect}", field="[集荷]担当")
	@Length(field = "[集荷]担当", max = "20", types={Length.Type.MINUS_TYPE})
	private String tantoTel;

	// 個数
	@Patterns(regexp = "^$|^[1-9]{1}[0-9]*$", message="{field.format.integer}", field="[集荷]個数")
	@Digits(fraction = 0, integer = 10, length = -1, message="{field.type.integer}", field = "[集荷]個数")
	private String amount;

	// 重量
	@Patterns(regexp = "^$|^[0]*[\\.]{0,1}[0-9]*$|^[1-9]{1}[0-9]*[\\.]{0,1}[0-9]*$", message="{field.format.integer}", field="[集荷]重量")
	@Digits(fraction = 1, integer = 9, length = 10, message="{field.type.decimal}", field = "[集荷]重量")
	private String weight;

	// 備考
	@Length(field = "[集荷]備考", max = "100")
	@DoubleByte(field = "[集荷]備考")
	private String remarks;

	// 荷種コード
	private String nisyuCd;

	// 荷種
	private String nisyuNm;

	// 荷姿コード
	@NotEmpty(field = "[集荷]荷姿", message = "{field.not.empty}")
	private String nisugataCd;

	// 荷姿
	private String nisugataNm;
}
