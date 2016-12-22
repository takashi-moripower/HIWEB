package logistics.system.project.common.parameterClass;

import java.util.ArrayList;
import java.util.List;

public class SeikyuAnkenListParameter {
	// 企業
	private String kigyo;
	// 対象期間（FROM)
	private String syukaDayFr;
	// 対象期間（TO)
	private String syukaDayTo;
	// 案件状態
	private List<String> statusList = new ArrayList<String>();
	// 業務種別
	private String gyomuSb;
	// 料数
	private String ryoritu;

	public String getKigyo() {
		return kigyo;
	}

	public void setKigyo(String kigyo) {
		this.kigyo = kigyo;
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

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String getGyomuSb() {
		return gyomuSb;
	}

	public void setGyomuSb(String gyomuSb) {
		this.gyomuSb = gyomuSb;
	}

	public String getRyoritu() {
		return ryoritu;
	}

	public void setRyoritu(String ryoritu) {
		this.ryoritu = ryoritu;
	}
	
}
