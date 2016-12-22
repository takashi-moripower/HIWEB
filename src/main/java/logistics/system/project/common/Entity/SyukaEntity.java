package logistics.system.project.common.Entity;

public class SyukaEntity {
	// 案件情報ＩＤ
	private String ankenId;
	// 集荷先NO
	private String syukaSeq;
	// 集荷日付
	private String syukaDay;
	// 集荷時間
	private String syukaTime;
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
	// 担当者名
	private String tantoNm;
	// 担当者電話
	private String tantoTel;
	// 荷種コード
	private String nisyuCd;
	// 荷種名称
	private String nisyuNm;
	// 荷姿コード
	private String nisugataCd;
	// 荷姿名称
	private String nisugataNm;
	// 個数
	private String kosu;
	// 重量
	private String zyuryo;
	// 備考
	private String biko;

	public String getAnkenId() {
		return ankenId;
	}

	public void setAnkenId(String ankenId) {
		this.ankenId = ankenId;
	}

	public String getSyukaSeq() {
		return syukaSeq;
	}

	public void setSyukaSeq(String syukaSeq) {
		this.syukaSeq = syukaSeq;
	}

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

	public String getKosu() {
		return kosu;
	}

	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

	public String getZyuryo() {
		return zyuryo;
	}

	public void setZyuryo(String zyuryo) {
		this.zyuryo = zyuryo;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

}