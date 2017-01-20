package logistics.system.project.tuchi.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import logistics.system.project.tuchi.Entity.TuchiEntity;

public class TuchiEditForm {

	int tuchiId;
	String userId;
	String title;
	String dateStart;
	String dateEnd;
	String prefCd;
	List<String> syasyu;
	List<String> truckOp;
	List<String> city;

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

	public void initEntity(TuchiEntity entity) {
		entity.setTuchiId(getTuchiId());
		entity.setUserId(getUserId());
		entity.setTitle(getTitle());
		entity.setPrefCd(getPrefCd());
		entity.setDateStart(s2t(getDateStart()));
		entity.setDateEnd(s2t(getDateEnd()));
		entity.setTruckOp(getTruckOp());
		entity.setSyasyu(getSyasyu());
		entity.setCity(getCity());
		return;
	}

	public Timestamp s2t(String str) {
		if (str == null || str == "") {
			return null;
		}
		return Timestamp.valueOf(str + " 00:00:00.00");
	}

	public List<String> getSyasyu() {
		if( syasyu == null ){
			syasyu =  new ArrayList<>();
		}
		return syasyu;
	}

	public void setSyasyu(List<String> syasyu) {
		this.syasyu = syasyu;
	}

	public List<String> getTruckOp() {
		if( truckOp == null ){
			truckOp = new ArrayList<>();
		}
		return truckOp;
	}

	public void setTruckOp(List<String> truckOp) {
		this.truckOp = truckOp;
	}

	public List<String> getCity() {
		if( city == null ){
			city = new ArrayList<>();
		}
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

}
