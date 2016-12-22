package logistics.system.project.common.parameterClass;

import java.util.ArrayList;
import java.util.List;

public class AnkenListParameter {
	private String gyomuSb;
	private String companyCd;
	private List<String> prefNameList = new ArrayList<String>();
	private List<String> cityNameList = new ArrayList<String>();
	private List<String> syasyuList = new ArrayList<String>();
	private String syukaDayFr;
	private String syukaDayTo;
	private List<String> opList  = new ArrayList<String>();
	private String ankenNo;
	private List<String> ankenStatusList = new ArrayList<String>();
	private String sortId;
	private String jutuKg;
	private String createDt;
	private int startIndex;
	private int countPerPage;
	
	// 請求先荷主コード
	private String seikyuKsCd;
	
	// 支払先会社コード
	private String shihraiKsCd;

	public String getGyomuSb() {
		return gyomuSb;
	}

	public void setGyomuSb(String gyomuSb) {
		this.gyomuSb = gyomuSb;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public List<String> getPrefNameList() {
		return prefNameList;
	}

	public void setPrefNameList(List<String> prefNameList) {
		this.prefNameList = prefNameList;
	}

	public List<String> getCityNameList() {
		return cityNameList;
	}

	public void setCityNameList(List<String> cityNameList) {
		this.cityNameList = cityNameList;
	}

	public List<String> getSyasyuList() {
		return syasyuList;
	}

	public void setSyasyuList(List<String> syasyuList) {
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

	public List<String> getOpList() {
		return opList;
	}

	public void setOpList(List<String> opList) {
		this.opList = opList;
	}

	public String getAnkenNo() {
		return ankenNo;
	}

	public void setAnkenNo(String ankenNo) {
		this.ankenNo = ankenNo;
	}

	public List<String> getAnkenStatusList() {
		return ankenStatusList;
	}

	public void setAnkenStatusList(List<String> ankenStatusList) {
		this.ankenStatusList = ankenStatusList;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getJutuKg() {
		return jutuKg;
	}

	public void setJutuKg(String jutuKg) {
		this.jutuKg = jutuKg;
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

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

}
