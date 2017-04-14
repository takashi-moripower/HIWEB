package logistics.system.project.tuchi.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.utility.annotation.NotEmpty;

public class TuchiEditForm {
	@Valid
	int tuchiId;
	String userId;
	@NotEmpty(field = "配信先", message = "{field.not.empty}")
	String email;
	String ninusiCd;
	@NotEmpty(field = "タイトル", message = "{field.not.empty}")
	String title;
	@NotEmpty(field = "開始日時", message = "{field.not.empty}")
	String dateStart;
	@NotEmpty(field = "終了日時", message = "{field.not.empty}")
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
		this.ninusiCd = ninusiCd;
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

	public Timestamp s2t(String str) {
		if (str == null || str == "") {
			return null;
		}
		return Timestamp.valueOf(str + " 00:00:00.00");
	}

	public List<String> getSyasyu() {
		if (syasyu == null) {
			syasyu = new ArrayList<>();
		}
		return syasyu;
	}

	public void setSyasyu(List<String> syasyu) {
		this.syasyu = syasyu;
	}

	public List<String> getTruckOp() {
		if (truckOp == null) {
			truckOp = new ArrayList<>();
		}
		return truckOp;
	}

	public void setTruckOp(List<String> truckOp) {
		this.truckOp = truckOp;
	}

	public List<String> getCity() {
		if (city == null) {
			city = new ArrayList<>();
		}
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	public void initForm(TuchiEntity entity) {
		setTuchiId(entity.getTuchiId());
		setUserId(entity.getUserId());
		setEmail(entity.getEmail());
		setNinusiCd( entity.getNinusiCd() );
		setCity(entity.getCity());
		setTitle(entity.getTitle());
		setPrefCd(entity.getPrefCd());
		setDateStart(entity.getDateStartText());
		setDateEnd(entity.getDateEndText());
		setTruckOp(entity.getTruckOp());
		setSyasyu(entity.getSyasyu());
		setCity(entity.getCity());

	}

	public void updateEntity(TuchiEntity entity) {
		entity.setTuchiId(getTuchiId());
		entity.setUserId(getUserId());
		entity.setEmail(getEmail());
		entity.setNinusiCd(getNinusiCd());
		entity.setTitle(getTitle());
		entity.setPrefCd(getPrefCd());
		entity.setDateStart(s2t(getDateStart()));
		entity.setDateEnd(s2t(getDateEnd()));
		entity.setTruckOp(getTruckOp());
		entity.setSyasyu(getSyasyu());
		entity.setCity(getCity());
		return;
	}
}
