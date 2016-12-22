package logistics.system.project.utility;

public enum DateType {
	yyyyMMdd("yyyyMMdd"), yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
	yyyyMMddHHmmss("yyyyMMddHHmmss"), yyyy_MM_dd_E("yyyy/MM/dd (E)");

	private String type;
	private DateType(String type){
	    	 this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
