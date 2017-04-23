package logistics.system.project.tuchi.Entity;

import java.util.ArrayList;
import java.util.List;

import logistics.system.project.utility.Constants;

public class TuchiEntity {
	protected int tuchiId;
	protected String userId;
	protected String email;
	protected String ninusiCd;
	protected String dateStart;
	protected String dateEnd;
	protected String title;
	protected int mailCount;
	protected int mailCountDay;
	protected List<String> truckOp;
	protected List<String> syasyu;
	protected List<String> pref;
	protected List<String> city;
	protected String userNm;

	public TuchiEntity(){
	}

	public void initLinks(){
		setTruckOp( new ArrayList<String>() );
		setSyasyu( new ArrayList<String>() );
		setCity( new ArrayList<String>() );
	}

	public int getTuchiId() {
		return tuchiId;
	}

	public void setTuchiId(int tuchiId) {
		this.tuchiId = tuchiId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNinusiCd() {
		return ninusiCd;
	}

	public void setNinusiCd(String ninusiCd) {
		if( ninusiCd == null || ninusiCd.isEmpty() ){
			ninusiCd = null;
		}
		this.ninusiCd = ninusiCd;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMailCount() {
		return mailCount;
	}

	public void setMailCount(int mailCount) {
		this.mailCount = mailCount;
	}

	public int getMailCountDay() {
		return mailCountDay;
	}

	public void setMailCountDay(int mailCountDay) {
		this.mailCountDay = mailCountDay;
	}

	public String getPrefName(){
		if( pref==null || pref.size() == 0 ){
			return "全国";
		}

		String result = "";
		for(String prefCd : pref){
			result += Constants.getPrefName(prefCd) + " ";
		}
		return result;
	}

	public String getDateStartText() {
		return d2s(dateStart);
	}

	public String getDateEndText() {
		return d2s(dateEnd);
	}

	static public String d2s(String date) {
		if (date == null) {
			return null;
		}
		return date;
	}

	public List<String> getTruckOp() {
		return truckOp;
	}

	public void setTruckOp(List<String> truckOp) {
		this.truckOp = truckOp;
	}

	public List<String> getSyasyu() {
		return syasyu;
	}

	public void setSyasyu(List<String> syasyu) {
		this.syasyu = syasyu;
	}

	public List<String> getPref() {
		return pref;
	}

	public void setPref(List<String> pref) {
		this.pref = pref;
	}
	public List<String> getCity() {
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
}