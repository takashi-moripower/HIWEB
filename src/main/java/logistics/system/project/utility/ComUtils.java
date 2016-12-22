package logistics.system.project.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.service.MailSendService;

import org.apache.commons.lang.StringUtils;

public class ComUtils {


	public static String getSysDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DateType.yyyyMMdd.getType());
		return sdf.format(cal.getTime());
	}

	public static String getSysDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DateType.yyyy_MM_dd_HH_mm_ss.getType());
		return sdf.format(cal.getTime());
	}


	public static String getDelayDate(String DispDl){
		Calendar cal = Calendar.getInstance();
		int dispDL = Integer.valueOf(DispDl);
		cal.add(Calendar.MINUTE, dispDL * (-1));
		SimpleDateFormat sdf = new SimpleDateFormat(DateType.yyyyMMddHHmmss.getType());
		return sdf.format(cal.getTime());
	}

	public static String getSysJapanDate(){
		return ComUtils.getJapanDate(ComUtils.getSysDate());
	}

	public static String getJapanDate(String date){
		return ComUtils.editDate(date, DateType.yyyyMMdd.getType(), DateType.yyyy_MM_dd_E.getType());
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static String editDate(String date, String parseFormat, String outputFormat) {

		if(StringUtils.isEmpty(date)) {
			return StringUtils.EMPTY;
		}

		StringBuffer sb = new StringBuffer(date);
		while (sb.length() < parseFormat.length()) {
			sb.append("0");
		}

		date = sb.toString();

		SimpleDateFormat sdfParse = new SimpleDateFormat(parseFormat, Locale.JAPANESE);
		Date d = null;
		try {
			d = sdfParse.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat, Locale.JAPANESE);

		return sdf.format(d);

	}

	public static String convertJapanDate(String date, String parseFormat, String outputFormat) {

		if(StringUtils.isEmpty(date)) {
			return StringUtils.EMPTY;
		}

		StringBuffer sb = new StringBuffer(date);
		while (sb.length() < parseFormat.length()) {
			sb.append("0");
		}

		date = sb.toString();

		SimpleDateFormat sdfParse = new SimpleDateFormat(parseFormat,Locale.JAPANESE);
		Date d = null;
		try {
			d = sdfParse.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);

		return sdf.format(d);

	}

	public static String convertDate(String date, String parseFormat, String outputFormat) {

		if(StringUtils.isEmpty(date)) {
			return StringUtils.EMPTY;
		}

		StringBuffer sb = new StringBuffer(date);
		while (sb.length() < parseFormat.length()) {
			sb.append("0");
		}

		date = sb.toString();

		SimpleDateFormat sdfParse = new SimpleDateFormat(parseFormat);
		Date d = null;
		try {
			d = sdfParse.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);

		return sdf.format(d);

	}

	public static String editOpList(String strOpList, List<TruckOpEntity> truckOpList) {

		String[] opList = strOpList.split(",");
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < opList.length; i++) {

			for (TruckOpEntity truckOp : truckOpList) {
				if (opList[i].equals(truckOp.getOpCd())) {
					if (i != 0)
						sb.append(",");
					sb.append(truckOp.getOpName());
					break;
				}
			}
		}

		return sb.toString();

	}

	public static String formatAnkenNo(String ankenNo) {
		if(ankenNo.length() != 18) {
			return ankenNo;
		}
		else {
			StringBuffer formatNo = new StringBuffer();
			formatNo.append(ankenNo.substring(0, 6));
			formatNo.append(Constants.CONNECT_DASH);
			formatNo.append(ankenNo.substring(6, 16));
			formatNo.append(Constants.CONNECT_DASH);
			formatNo.append(ankenNo.substring(16, 18));
			return formatNo.toString();
		}
	}

	public static void sendMail(String ankenNo, String prevUpdateUserId, String userId) throws Exception {

		MailSendService mailSendService = (MailSendService) Constants.WEB_APP_CONTEXT
				.getBean("mailSendService");

		mailSendService.insertMailSend(ankenNo, prevUpdateUserId, userId);
	}


	public static String formatPostCode(String postCode) {
		StringBuffer formatPostCode = new StringBuffer("");
		if(StringUtils.isEmpty(postCode)) {
			return "";
		}
		else {
			postCode = postCode.replaceAll("-", "");
			if(postCode.length() != 7) {
				return "";
			}
			else {
				formatPostCode.append(postCode.substring(0, 3));
				formatPostCode.append("-");
				formatPostCode.append(postCode.substring(3, 7));
				return formatPostCode.toString();
			}
		}
	}

	public static boolean isAjax(HttpServletRequest request){
		return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())) ;
	}
}
