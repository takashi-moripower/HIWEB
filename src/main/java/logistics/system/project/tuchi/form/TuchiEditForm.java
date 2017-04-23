package logistics.system.project.tuchi.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.utility.Constants;
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
	List<String> syasyu;
	List<String> truckOp;
	List<String> city;
	List<String> pref;

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

	public List<String> getPref() {
		if (pref == null) {
			pref = new ArrayList<>();
		}
		return pref;
	}

	public void setPref(List<String> pref) {
		this.pref = pref;
	}

	public String getPrefName() {
		if (getPref().size() == 0) {
			return "全国";
		}

		String result = "";
		for (String prefCd : pref) {
			result += Constants.getPrefName(prefCd) + " ";
		}
		return result;
	}

	public static final String DATE_PATTERN_FORM = "yyyy/MM/dd (E)";
	public static final String DATE_PATTERN_DB = "yyyy-MM-dd ";

	public static Locale LOCALE = new Locale("ja", "JP", "JP");
	public static SimpleDateFormat DATE_FORMAT_FORM = new SimpleDateFormat(DATE_PATTERN_FORM , LOCALE);
	public static SimpleDateFormat DATE_FORMAT_DB = new SimpleDateFormat(DATE_PATTERN_DB , LOCALE);

	public static String formatDate(String source, SimpleDateFormat srcFormat, SimpleDateFormat destFormat) {
		String dest = null;
		Date date;

		try {
			date = srcFormat.parse(source);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			date = new Date();
		}

		dest = destFormat.format(date);

		System.out.println("DATE FORMAT CONVERTED [" + source + "]  >>>  [" + dest+"]");

		return dest;
	}

	public static String DateDB2Form(String str) {
		return formatDate(str, DATE_FORMAT_DB, DATE_FORMAT_FORM);
	}

	public static String DateForm2DB(String str) {
		return formatDate(str, DATE_FORMAT_FORM, DATE_FORMAT_DB);
	}

	public void initForm(TuchiEntity entity) {
		setTuchiId(entity.getTuchiId());
		setUserId(entity.getUserId());
		setEmail(entity.getEmail());
		setNinusiCd(entity.getNinusiCd());
		setCity(entity.getCity());
		setTitle(entity.getTitle());
		setPref(entity.getPref());
		setDateStart(DateDB2Form(entity.getDateStart()));
		setDateEnd(DateDB2Form(entity.getDateEnd()));
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
		entity.setPref(getPref());
		entity.setDateStart( DateForm2DB( getDateStart()));
		entity.setDateEnd( DateForm2DB( getDateEnd()));
		entity.setTruckOp(getTruckOp());
		entity.setSyasyu(getSyasyu());
		entity.setCity(getCity());
		return;
	}

	public static List<PrefEntity> getPrefByArea(String areaCd) {
		List<PrefEntity> result = new ArrayList<PrefEntity>();
		for (PrefEntity p : Constants.getPrefList()) {
			if (p.getAreaCd().equals(areaCd)) {
				result.add(p);
			}
		}
		return result;
	}

}
