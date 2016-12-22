package logistics.system.project.ninushi.form;

import logistics.system.project.utility.annotation.Date;
import logistics.system.project.utility.annotation.Digits;
import logistics.system.project.utility.annotation.DoubleByte;
import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.NotEmpty;
import logistics.system.project.utility.annotation.Patterns;
import logistics.system.project.utility.annotation.Time;

/**
 * 納品先
 * @author
 *
 */
public class NohinForm {

	public String getNohinDay() {
		return nohinDay;
	}

	public void setNohinDay(String nohinDay) {
		this.nohinDay = nohinDay;
	}

	public String getNohinTime() {
		return nohinTime;
	}

	public void setNohinTime(String nohinTime) {
		this.nohinTime = nohinTime;
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

	// 納品日付
	@NotEmpty(field = "納品日付", message="{field.not.empty}")
	@Date(field = "納品日付", param="jp", message = "{data.format.incorrect}")
	@Length(field = "納品日付", max = "8", types={Length.Type.DATE_TYPE})
	private String nohinDay;
		
	// 納品時間
	@NotEmpty(field = "納品時間", message="{field.not.empty}")
	@Time(field = "納品時間", message = "{data.format.incorrect}")
	@Length(field = "納品時間", max = "4", types={Length.Type.TIME_TYPE})
	private String nohinTime;	
	
	// 納品先
	@NotEmpty(field = "納品先", message="{field.not.empty}")
	@Length(field = "納品先", max = "20")
	@DoubleByte(field = "納品先")
	private String addrNm;
	
	// 郵便番号
	@Length(field = "[納品]郵便番号", max = "7", types={Length.Type.MINUS_TYPE})
	private String postCode;
	
	// 都道府県
	@NotEmpty(field = "[納品]都道府県", message="{field.not.empty}")
	private String prefNm;	
	
	// 市区町村
	@NotEmpty(field = "[納品]市区町村", message="{field.not.empty}")
	@Length(field = "[納品]市区町村", max = "40")
	@DoubleByte(field = "[納品]市区町村")
	private String cityNm;
	
	// 番地等
	@Length(field = "[納品]番地等", max = "60")
	@DoubleByte(field = "[納品]番地等")
	private String addrOther;
	
	// 担当者名
	@Length(field = "[納品]担当者名", max = "20")
	@DoubleByte(field = "[納品]担当者名")
	private String tantoNm;
	
	// 担当TEL
//	@Patterns(regexp = "^$|^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message="{tel.format.incorrect}", field="担当")
	@Patterns(regexp = "^$|^[0-9-]*$", message="{tel.format.incorrect}", field="[納品]担当")
	@Length(field = "[納品]担当", max = "20", types={Length.Type.MINUS_TYPE})
	private String tantoTel;	
	
	// 個数
	@Patterns(regexp = "^$|^[1-9]{1}[0-9]*$", message="{field.format.integer}", field="[納品]個数")
	@Digits(fraction = 0, integer = 10, length = -1, field="[納品]個数", message="{field.type.integer}")
	private String amount;
	
	// 重量
	@Patterns(regexp = "^$|^[0]*[\\.]{0,1}[0-9]*$|^[1-9]{1}[0-9]*[\\.]{0,1}[0-9]*$", message="{field.format.integer}", field="[納品]重量")
	@Digits(fraction = 1, integer = 9, length = 10, field="[納品]重量", message="{field.type.decimal}")
	private String weight;
	
	// 備考
	@Length(field = "[納品]備考", max = "100")
	@DoubleByte(field = "[納品]備考")
	private String remarks;
	
	// 荷種コード
	private String nisyuCd;
	
	// 荷種
	private String nisyuNm;
	
	// 荷姿コード
	@NotEmpty(field = "[納品]荷姿", message="{field.not.empty}")
	private String nisugataCd;
	
	// 荷姿
	private String nisugataNm;

}
