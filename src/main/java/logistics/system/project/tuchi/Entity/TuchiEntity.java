package logistics.system.project.tuchi.Entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TuchiEntity {
	protected int tuchiId;
	protected String userId;
	protected String ninusiCd;
	protected String prefCd;
	protected Timestamp dateStart;
	protected Timestamp dateEnd;
	protected String title;
	protected int mailCount;
	protected int mailCountDay;
	protected String[] truckOp =  new String[0];

	public String[] getTruckOp() {
		return truckOp;
	}

	public void setTruckOp(String[] truckOp) {
		this.truckOp = truckOp;
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

	public String getNinusiCd() {
		return ninusiCd;
	}

	public void setNinusiCd(String ninusiCd) {
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

	static public final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	static public String d2s(java.util.Date date) {
		return (new SimpleDateFormat(DATE_PATTERN)).format(date);
	}
}