package logistics.system.project.common.Entity;

public class SeikyuAnkenEntity {
	// 案件番号
	private String ankenNo;
	// 荷主コード
	private String ninushiCd;
	// 支払会社コード
	private String shihraiKsCd;
	// 集荷日時
	private String syukaDay;
	// 集荷名
	private String syukaNm;
	// 集荷場所
	private String syukaBasho;
	// 集荷荷姿
	private String syukaNisugata;
	// 集荷荷姿Cd
	private String syukaNisugataCd;
	// 集荷備考
	private String syukaBiko;
	// 個数
	private String kosu;
	// 重量
	private String zyuryo;
	// 台数
	private String daisu;
	// 納品日時
	private String nohinDay;
	// 納品名
	private String nohinNm;
	// 納品場所
	private String nohinBasho;
	// 納品備考
	private String nohinBiko;
	// 車種
	private String syasyuNm;
	// 車種CD
	private String syasyuCd;
	// 高速使用料区分
	private String kosokuKbn;
	// 高速使用料金
	private String kosokuMn;
	// 燃料サーチャージ区分
	private String nenryoscKbn;
	// 燃料サーチャージ金額
	private String nenryoscMn;
	// 案件ステータス
	private String status;
	// キャンセル金額
	private String cancelMn;
	// 予算
	private String yosan;
	// 更新フラグ
	private boolean updateFlag;
	// 手数料
	private String tesuryo;
	// 更新者ID
	private String updateId;
	// 更新者日時
	private String updateDt;
	
	// 運賃
	private String fare;

	public String getAnkenNo() {
		return ankenNo;
	}

	public void setAnkenNo(String ankenNo) {
		this.ankenNo = ankenNo;
	}

	public String getNinushiCd() {
		return ninushiCd;
	}

	public void setNinushiCd(String ninushiCd) {
		this.ninushiCd = ninushiCd;
	}

	public String getShihraiKsCd() {
		return shihraiKsCd;
	}

	public void setShihraiKsCd(String shihraiKsCd) {
		this.shihraiKsCd = shihraiKsCd;
	}

	public String getSyukaDay() {
		return syukaDay;
	}

	public void setSyukaDay(String syukaDay) {
		this.syukaDay = syukaDay;
	}

	public String getSyukaNm() {
		return syukaNm;
	}

	public void setSyukaNm(String syukaNm) {
		this.syukaNm = syukaNm;
	}

	public String getSyukaBasho() {
		return syukaBasho;
	}

	public void setSyukaBasho(String syukaBasho) {
		this.syukaBasho = syukaBasho;
	}

	public String getSyukaNisugata() {
		return syukaNisugata;
	}

	public void setSyukaNisugata(String syukaNisugata) {
		this.syukaNisugata = syukaNisugata;
	}

	public String getNohinDay() {
		return nohinDay;
	}

	public void setNohinDay(String nohinDay) {
		this.nohinDay = nohinDay;
	}

	public String getNohinNm() {
		return nohinNm;
	}

	public void setNohinNm(String nohinNm) {
		this.nohinNm = nohinNm;
	}

	public String getNohinBasho() {
		return nohinBasho;
	}

	public void setNohinBasho(String nohinBasho) {
		this.nohinBasho = nohinBasho;
	}

	public String getSyasyuNm() {
		return syasyuNm;
	}

	public void setSyasyuNm(String syasyuNm) {
		this.syasyuNm = syasyuNm;
	}

	public String getKosokuKbn() {
		return kosokuKbn;
	}

	public void setKosokuKbn(String kosokuKbn) {
		this.kosokuKbn = kosokuKbn;
	}

	public String getKosokuMn() {
		return kosokuMn;
	}

	public void setKosokuMn(String kosokuMn) {
		this.kosokuMn = kosokuMn;
	}

	public String getNenryoscKbn() {
		return nenryoscKbn;
	}

	public void setNenryoscKbn(String nenryoscKbn) {
		this.nenryoscKbn = nenryoscKbn;
	}

	public String getNenryoscMn() {
		return nenryoscMn;
	}

	public void setNenryoscMn(String nenryoscMn) {
		this.nenryoscMn = nenryoscMn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCancelMn() {
		return cancelMn;
	}

	public void setCancelMn(String cancelMn) {
		this.cancelMn = cancelMn;
	}

	public String getYosan() {
		return yosan;
	}

	public void setYosan(String yosan) {
		this.yosan = yosan;
	}

	public String getTesuryo() {
		return tesuryo;
	}

	public void setTesuryo(String tesuryo) {
		this.tesuryo = tesuryo;
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

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getSyukaBiko() {
		return syukaBiko;
	}

	public void setSyukaBiko(String syukaBiko) {
		this.syukaBiko = syukaBiko;
	}

	public String getNohinBiko() {
		return nohinBiko;
	}

	public void setNohinBiko(String nohinBiko) {
		this.nohinBiko = nohinBiko;
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

	public String getSyukaNisugataCd() {
		return syukaNisugataCd;
	}

	public void setSyukaNisugataCd(String syukaNisugataCd) {
		this.syukaNisugataCd = syukaNisugataCd;
	}

	public String getSyasyuCd() {
		return syasyuCd;
	}

	public void setSyasyuCd(String syasyuCd) {
		this.syasyuCd = syasyuCd;
	}

	public String getDaisu() {
		return daisu;
	}

	public void setDaisu(String daisu) {
		this.daisu = daisu;
	}
}
