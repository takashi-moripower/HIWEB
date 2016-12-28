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
	String prefCd;
	String[] syasyu;

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
			truckOp = new String[0];
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

	public String getPrefCd() {
		return prefCd;
	}

	public void setPrefCd(String prefCd) {
		this.prefCd = prefCd;
	}

	public String[] getSyasyu() {
		if (syasyu == null) {
			syasyu = new String[0];
		}
		return syasyu;
	}

	public void setSyasyu(String[] syasyu) {
		this.syasyu = syasyu;
	}

	public void initEntity(TuchiEntity entity) {
		entity.setTuchiId(getTuchiId());
		entity.setUserId(getUserId());
		entity.setTitle(getTitle());
		entity.setPrefCd(getPrefCd());
		entity.setDateStart(s2t(getDateStart()));
		entity.setDateEnd(s2t(getDateEnd()));
		entity.setTruckOp(getTruckOp());
		entity.setSyasyu(getSyasyu());
		return;
	}

	public Timestamp s2t(String str) {
		if (str == null || str == "") {
			return null;
		}
		return Timestamp.valueOf(str + " 00:00:00.00");
	}

}
