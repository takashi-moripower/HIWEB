package logistics.system.project.common.Entity;

public class PrefEntity {
	// 都道府県コード
	private String prefCd;
	// 都道府県名
	private String prefName;
	// 地域コード
	private String areaCd;
	// 件数
	private Integer kensu;

	public String getPrefCd() {
		return prefCd;
	}

	public void setPrefCd(String prefCd) {
		this.prefCd = prefCd;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public Integer getKensu() {
		return kensu;
	}

	public void setKensu(Integer kensu) {
		this.kensu = kensu;
	}
	
	

}