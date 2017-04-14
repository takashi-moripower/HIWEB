package logistics.system.project.tuchi.Entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import logistics.system.project.utility.Constants;

public class TuchiEntity {
	protected int tuchiId;
	protected String userId;
	protected String email;
	protected String ninusiCd;
	protected String prefCd;
	protected Timestamp dateStart;
	protected Timestamp dateEnd;
	protected String title;
	protected int mailCount;
	protected int mailCountDay;
	protected List<String> truckOp;
	protected List<String> syasyu;
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

	public String getPrefCd() {
		return prefCd;
	}

	public void setPrefCd(String prefCd) {
		this.prefCd = prefCd;
	}

	public Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public Timestamp getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
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
		return Constants.getPrefName(prefCd);
	}

	public String getDateStartText() {
		return d2s(dateStart);
	}

	public String getDateEndText() {
		return d2s(dateEnd);
	}

	static public final String DATE_PATTERN = "yyyy-MM-dd";

	static public String d2s(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return (new SimpleDateFormat(DATE_PATTERN)).format(date);
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