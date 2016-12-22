package logistics.system.project.common.Entity;

public class CityEntity {
	// 市区町村コード
	private String cityCd;
	// 市区町村名
	private String cityName;
	// 市区町村表示名
	private String cityDisp;
	// 表示分類
	private String dispCateg;
	// 都道府県コード
	private String prefCd;
	// 件数
	private Integer kensu;

	public String getCityCd() {
		return cityCd;
	}

	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityDisp() {
		return cityDisp;
	}

	public void setCityDisp(String cityDisp) {
		this.cityDisp = cityDisp;
	}

	public String getDispCateg() {
		return dispCateg;
	}

	public void setDispCateg(String dispCateg) {
		this.dispCateg = dispCateg;
	}

	public String getPrefCd() {
		return prefCd;
	}

	public void setPrefCd(String prefCd) {
		this.prefCd = prefCd;
	}

	public Integer getKensu() {
		return kensu;
	}

	public void setKensu(Integer kensu) {
		this.kensu = kensu;
	}

}