package logistics.system.project.common.Entity;

public class TruckEntity {
	// 案件情報ＩＤ
	private String ankenId;
	// トラックNO
	private String truckNo;
	// 予算金額
	private String yosanMn;
	// 受注金額
	private String orderMn;
	// 高速使用料区分
	private String kosokuKbn;
	// 燃料サーチャージ区分
	private String nenryoscKbn;
	// 燃料サーチャージ金額
	private String nenryoscMn;
	// 車種コード
	private String syasyuCd;
	// 車種名称
	private String syasyuNm;
	// 台数
	private String daisu;
	// オプション一覧
	private String opList;

	public String getAnkenId() {
		return ankenId;
	}

	public void setAnkenId(String ankenId) {
		this.ankenId = ankenId;
	}

	public String getTruckNo() {
		return truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public String getYosanMn() {
		return yosanMn;
	}

	public void setYosanMn(String yosanMn) {
		this.yosanMn = yosanMn;
	}

	public String getOrderMn() {
		return orderMn;
	}

	public void setOrderMn(String orderMn) {
		this.orderMn = orderMn;
	}

	public String getKosokuKbn() {
		return kosokuKbn;
	}

	public void setKosokuKbn(String kosokuKbn) {
		this.kosokuKbn = kosokuKbn;
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

	public String getSyasyuCd() {
		return syasyuCd;
	}

	public void setSyasyuCd(String syasyuCd) {
		this.syasyuCd = syasyuCd;
	}

	public String getSyasyuNm() {
		return syasyuNm;
	}

	public void setSyasyuNm(String syasyuNm) {
		this.syasyuNm = syasyuNm;
	}

	public String getDaisu() {
		return daisu;
	}

	public void setDaisu(String daisu) {
		this.daisu = daisu;
	}

	public String getOpList() {
		return opList;
	}

	public void setOpList(String opList) {
		this.opList = opList;
	}
}