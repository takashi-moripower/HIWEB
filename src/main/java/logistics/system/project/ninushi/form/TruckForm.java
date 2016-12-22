package logistics.system.project.ninushi.form;

import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.NotEmpty;

public class TruckForm {

	public String getOpNmList() {
		return opNmList;
	}

	public void setOpNmList(String opNmList) {
		this.opNmList = opNmList;
	}

	public String getSyasyuNm() {
		return syasyuNm;
	}

	public void setSyasyuNm(String syasyuNm) {
		this.syasyuNm = syasyuNm;
	}

	public String getYosanMn() {
		return yosanMn;
	}

	public void setYosanMn(String yosanMn) {
		this.yosanMn = yosanMn;
	}

	public String getKosokuKbn() {
		return kosokuKbn;
	}

	public void setKosokuKbn(String kosokuKbn) {
		this.kosokuKbn = kosokuKbn;
	}
	
	public String getKosokuKbnNm() {
		return kosokuKbnNm;
	}

	public void setKosokuKbnNm(String kosokuKbnNm) {
		this.kosokuKbnNm = kosokuKbnNm;
	}

	public String getNenryoscKbnNm() {
		return nenryoscKbnNm;
	}

	public void setNenryoscKbnNm(String nenryoscKbnNm) {
		this.nenryoscKbnNm = nenryoscKbnNm;
	}

	public String getNenryoscKbn() {
		return nenryoscKbn;
	}

	public void setNenryoscKbn(String nenryoscKbn) {
		this.nenryoscKbn = nenryoscKbn;
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

	public String getOpList() {
		return opList;
	}

	public void setOpList(String opList) {
		this.opList = opList;
	}

	public String getOrderMn() {
		return orderMn;
	}

	public void setOrderMn(String orderMn) {
		this.orderMn = orderMn;
	}

	public String getNenryoscMn() {
		return nenryoscMn;
	}

	public void setNenryoscMn(String nenryoscMn) {
		this.nenryoscMn = nenryoscMn;
	}

	// 予算金額
	@NotEmpty(field = "予算", message="{field.not.empty}")
	@Length(field = "予算", max = "10", types={Length.Type.MONEY_TYPE})
	private String yosanMn;
	
	// 高速使用料区分
	private String kosokuKbn;	

	private String kosokuKbnNm;
	
	// 燃料サーチャージ区分
	private String nenryoscKbn;	
	
	private String nenryoscKbnNm;	
	
	// 車種コード
	@NotEmpty(field = "車種", message="{field.not.empty}")
	private String syasyuCd;
	
	private String syasyuNm;
	
	// 台数
	@NotEmpty(field = "台数", message="{field.not.empty}")
	private String daisu;
	
	// オプション一覧
	private String opList;
	
	private String opNmList;
		
	// 受注金額
	@Length(field = "受注金額", max = "10", types={Length.Type.MONEY_TYPE})
	private String orderMn;
	
	// 燃料サーチャージ金額
	@Length(field = "燃料サーチャージ金額", max = "10", types={Length.Type.MONEY_TYPE})
	private String nenryoscMn;
	
	
}
