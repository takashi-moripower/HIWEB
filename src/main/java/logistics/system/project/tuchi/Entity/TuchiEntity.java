package logistics.system.project.tuchi.Entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logistics.system.project.utility.Constants;

public class TuchiEntity {
	protected int tuchiId;
	protected String userId;
	protected String email;
	protected String ninusiCd;
	protected Date dateStart;
	protected Date dateEnd;
	protected String title;
	protected int mailCount;
	protected int mailCountDay;
	protected List<String> truckOp;
	protected List<String> syasyu;
	protected List<String> pref;
	protected List<String> city;
	protected String userNm;

	public static final String DATE_PATTERN_ENTITY = "yyyy/MM/dd";
	public static SimpleDateFormat DATE_FORMAT_ENTITY = new SimpleDateFormat(DATE_PATTERN_ENTITY);

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

	public String getArea(){
		if( pref==null || pref.size() == 0 ){
			return "全国";
		}

		if( city==null || city.size() == 0 ){
			String result = "";
			for(String prefCd : pref){
				result += Constants.getPrefName(prefCd) + " ";
			}
			return result;
		}

		String result = Constants.getPrefName( pref.get(0) ) + "（";
		for(String cityCd : city){
			result += Constants.getCityName(cityCd) + " ";
		}
		result += "）";
		return result;
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

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDateStartText(){
		return Date2Str(getDateStart());
	}

	public String getDateEndText(){
		return Date2Str(getDateEnd());
	}

	public static String Date2Str( Date date ){
		return DATE_FORMAT_ENTITY.format(date);
	}

}