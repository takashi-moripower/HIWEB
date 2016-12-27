package logistics.system.project.tuchi.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import logistics.system.project.tuchi.Entity.TuchiEntity;

public class TuchiEditForm {

	int tuchiId;
	String userId;
	String title;
	String[] truckOp;
	String dateStart;
	String dateEnd;

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

	public String[] getTruckOp() {
		if (truckOp == null) {
			return new String[0];
		}
		return truckOp;
	}

	public void setTruckOp(String[] truckOp) {
		this.truckOp = truckOp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void initEntity(TuchiEntity entity) {
		entity.setTuchiId(this.tuchiId);
		entity.setUserId(this.userId);
		entity.setDateStart(s2t(this.dateStart));
		entity.setDateEnd(s2t(this.dateEnd));
		entity.setTitle(this.title);

	}

	public Timestamp s2t( String str ){
		if( str == null || str == "" ){
			return null;
		}
		return Timestamp.valueOf( str + " 00:00:00.00");
	}

}
