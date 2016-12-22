package logistics.system.project.common.Entity;

public class NisyuEntity {
	
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

	// 荷種コード
	private String nisyuCd;

	// 荷種名称
	private String nisyuNm;
}
