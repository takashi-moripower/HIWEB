package logistics.system.project.common.form;

import java.util.Arrays;
import java.util.List;

import logistics.system.project.common.Entity.SeikyuAnkenEntity;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.annotation.Date;
import logistics.system.project.utility.annotation.NotEmpty;

public class SeikyuAnkenListForm {
	// 企業
	@NotEmpty(field="企業", message = "{field.not.empty}")
	private String kigyo;
	// 企業名称
	private String kigyoNm;
	// 企業住所
	private String kigyoJusho;
	// 分類
	private String bunrui;
	// 料率
	private String ryoritu;
	// 締日種別
	private String simbSb;
	// 支払サイト
	private String sihrSt;
	// 対象期間（FROM)
	@NotEmpty(field="対象期間（FROM)", message = "{field.not.empty}")
	@Date(field = "対象期間（FROM)", param="jp", message = "{data.format.incorrect}")
	private String syukaDayFr;
	// 対象期間（TO)
	@NotEmpty(field="対象期間（TO)", message = "{field.not.empty}")
	@Date(field = "対象期間（TO)", param="jp", message = "{data.format.incorrect}")
	private String syukaDayTo;
	// 案件状態
	private String[] status;
	// 状態確定
	private String kakutei;
	// 状態取り消す
	private String cancel;
	
	// 請求案件リスト
	private List<SeikyuAnkenEntity> seikyuAnkenList;

	public String getKigyo() {
		return kigyo;
	}

	public void setKigyo(String kigyo) {
		this.kigyo = kigyo;
	}

	public String getKigyoNm() {
		return kigyoNm;
	}

	public void setKigyoNm(String kigyoNm) {
		this.kigyoNm = kigyoNm;
	}

	public String getKigyoJusho() {
		return kigyoJusho;
	}

	public void setKigyoJusho(String kigyoJusho) {
		this.kigyoJusho = kigyoJusho;
	}

	public String getBunrui() {
		return bunrui;
	}

	public void setBunrui(String bunrui) {
		this.bunrui = bunrui;
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

	public String getSyukaDayFr() {
		return syukaDayFr;
	}

	public void setSyukaDayFr(String syukaDayFr) {
		this.syukaDayFr = syukaDayFr;
	}

	public String getSyukaDayTo() {
		return syukaDayTo;
	}

	public void setSyukaDayTo(String syukaDayTo) {
		this.syukaDayTo = syukaDayTo;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	public List<SeikyuAnkenEntity> getSeikyuAnkenList() {
		return seikyuAnkenList;
	}

	public void setSeikyuAnkenList(List<SeikyuAnkenEntity> seikyuAnkenList) {
		this.seikyuAnkenList = seikyuAnkenList;
	}

	public String getKakutei() {
		if (status != null && Arrays.asList(status).contains(Constants.ANKEN_STATUS_20)) {
			kakutei = Constants.ANKEN_STATUS_20;
		} else {
			kakutei = "";
		}
		return kakutei;
	}

	public void setKakutei(String kakutei) {
		this.kakutei = kakutei;
	}

	public String getCancel() {
		if (status != null && Arrays.asList(status).contains(Constants.ANKEN_STATUS_90)) {
			cancel = Constants.ANKEN_STATUS_90;
		} else {
			cancel = "";
		}
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

}
