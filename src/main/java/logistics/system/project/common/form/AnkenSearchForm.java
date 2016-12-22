package logistics.system.project.common.form;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

public class AnkenSearchForm {
	// 　選択
	private String func;
	private String prefName;
	// 　案件検索
	private String[] prefNameList;
	private String todohuen;
	private String[] cityNameList;
	private String sikutyoson;
	private String[] syasyuList;
	private String syukaDayFr;
	private String syukaDayTo;
	private String[] opList;
	private String ankenNo;
	private String[] ankenStatusList;
	private String sortId;
	private String jutuKg;
	
	// 請求先荷主コード
	private String seikyuKsCd;
	
	// 支払先会社コード
	private String shihraiKsCd;
	
	// 請求先荷主名称
	private String seikyuKsNm;
	
	// 請求先荷主住所
	private String seikyuKsAddr;
	
	// 支払先会社
	private String shihraiKsNm;
	
	// 支払先会社住所
	private String shihraiKsAddr;
	
	private String panelClosed;

	public String[] getPrefNameList() {
		return prefNameList;
	}

	public void setPrefNameList(String[] prefNameList) {
		this.prefNameList = prefNameList;
		this.todohuen = StringUtils.join(prefNameList, ",");
	}

	public String[] getCityNameList() {
		return cityNameList;
	}

	public void setCityNameList(String[] cityNameList) {
		this.cityNameList = cityNameList;
		this.sikutyoson = StringUtils.join(cityNameList, ",");
	}

	public String[] getSyasyuList() {
		return syasyuList;
	}

	public void setSyasyuList(String[] syasyuList) {
		this.syasyuList = syasyuList;
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

	public String[] getOpList() {
		return opList;
	}

	public void setOpList(String[] opList) {
		this.opList = opList;
	}

	public String getAnkenNo() {
		return ankenNo;
	}

	public void setAnkenNo(String ankenNo) {
		this.ankenNo = ankenNo;
	}

	public String[] getAnkenStatusList() {
		return ankenStatusList;
	}

	public void setAnkenStatusList(String[] ankenStatusList) {
		this.ankenStatusList = ankenStatusList;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getTodohuen() {
		return todohuen;
	}

	public void setTodohuen(String todohuen) {
		this.todohuen = todohuen;
		if (!StringUtils.isEmpty(todohuen)) {
			this.prefNameList = todohuen.split(",");
		} else {
			this.prefNameList = new String[] {};
		}

	}

	public String getSikutyoson() {
		return sikutyoson;
	}

	public void setSikutyoson(String sikutyoson) {
		this.sikutyoson = sikutyoson;
		if (!StringUtils.isEmpty(sikutyoson)) {
			this.cityNameList = sikutyoson.split(",");
		} else {
			this.cityNameList = new String[] {};
		}
	}


	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public String getSeikyuKsCd() {
		return seikyuKsCd;
	}

	public void setSeikyuKsCd(String seikyuKsCd) {
		this.seikyuKsCd = seikyuKsCd;
	}

	public String getShihraiKsCd() {
		return shihraiKsCd;
	}

	public void setShihraiKsCd(String shihraiKsCd) {
		this.shihraiKsCd = shihraiKsCd;
	}

	public String getSeikyuKsNm() {
		return seikyuKsNm;
	}

	public void setSeikyuKsNm(String seikyuKsNm) {
		this.seikyuKsNm = seikyuKsNm;
	}

	public String getSeikyuKsAddr() {
		return seikyuKsAddr;
	}

	public void setSeikyuKsAddr(String seikyuKsAddr) {
		this.seikyuKsAddr = seikyuKsAddr;
	}

	public String getShihraiKsNm() {
		return shihraiKsNm;
	}

	public void setShihraiKsNm(String shihraiKsNm) {
		this.shihraiKsNm = shihraiKsNm;
	}

	public String getShihraiKsAddr() {
		return shihraiKsAddr;
	}

	public void setShihraiKsAddr(String shihraiKsAddr) {
		this.shihraiKsAddr = shihraiKsAddr;
	}

	public String getJutuKg() {
		return jutuKg;
	}

	public void setJutuKg(String jutuKg) {
		this.jutuKg = jutuKg;
	}

	public String getPanelClosed() {
		return panelClosed;
	}

	public void setPanelClosed(String panelClosed) {
		this.panelClosed = panelClosed;
	}

	public String getJson() {
		String result = " {\"func\":\"" + func + "\",\"prefName\":\"" + prefName
				+ "\",\"prefNameList\":" + JSONArray.fromObject(prefNameList).toString()
				+ ",\"todohuen\":\"" + todohuen + "\",\"cityNameList\":"
				+ JSONArray.fromObject(cityNameList).toString() + ",\"sikutyoson\":\"" + sikutyoson
				+ "\",\"syasyuList\":" + JSONArray.fromObject(syasyuList).toString()
				+ ",\"syukaDayFr\":\"" + syukaDayFr + "\",\"syukaDayTo\":\"" + syukaDayTo
				+ "\",\"opList\":" + JSONArray.fromObject(opList).toString() + ",\"ankenNo\":\""
				+ ankenNo + "\",\"ankenStatusList\":"
				+ JSONArray.fromObject(ankenStatusList).toString() + ",\"sortId\":\"" + sortId
				+ "\",seikyuKsCd:\"" + seikyuKsCd + "\",seikyuKsNm:\"" + seikyuKsNm + "\",seikyuKsAddr:\"" + seikyuKsAddr
				+ "\",shihraiKsCd:\"" + shihraiKsCd + "\",shihraiKsNm:\"" + shihraiKsNm + "\",shihraiKsAddr:\"" + shihraiKsAddr + "\",panelClosed:\"" + panelClosed
				+ "\"}  ";
		return result.replaceAll("null", "");
	}

}
