package logistics.system.project.tuchi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.dao.OptionDao;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.component.MailSendComponent;
import logistics.system.project.tuchi.dao.RelationDao;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.tuchi.service.TuchiService;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.LogRecord;

@Service("tuchiService")
public class TuchiServiceImpl implements TuchiService {
	@Autowired
	@Qualifier("tuchiDao")
	private TuchiDao tuchiDao;

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao ankenDao;

	@Autowired
	@Qualifier("optionDao")
	private OptionDao optionDao;

	@Autowired
	@Qualifier("tuchiTruckOpRelationDao")
	private RelationDao<Integer, String> tuchiTruckOp;

	@Autowired
	@Qualifier("tuchiSyasyuRelationDao")
	private RelationDao<Integer, String> tuchiSyasyu;

	@Autowired
	@Qualifier("tuchiCityRelationDao")
	private RelationDao<Integer, String> tuchiCity;

	@Autowired
	@Qualifier("tuchiPrefRelationDao")
	private RelationDao<Integer, String> tuchiPref;

	@Autowired
	@Qualifier("ankenDetailService")
	private AnkenDetailService ankenDetailService;

	@Autowired
	@Qualifier("mailSendComponent")
	private MailSendComponent mailSendComponent;

	String baseUrl;

	static SimpleDateFormat FORMAT_JUTU_SRC = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat FORMAT_JUTU_DEST = new SimpleDateFormat("yyyy/MM/dd");

	static SimpleDateFormat FORMAT_SYUKA_SRC = new SimpleDateFormat("yyyyMMddHHmm");
	static SimpleDateFormat FORMAT_SYUKA_DEST = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	@Override
	public TuchiEntity getTuchiById(int tuchiId, boolean link) {
		TuchiEntity entity = tuchiDao.getTuchiById(tuchiId);

		if (link) {
			setLink( entity );
		}
		return entity;
	}

	@Override
	public List<TuchiEntity> getTuchiByUser(String userId , boolean link) {
		List<TuchiEntity> result = tuchiDao.getTuchiByUser(userId);

		if (link) {
			for( TuchiEntity entity : result ){
				setLink( entity );
			}
		}

		return result;
	}

	protected void setLink( TuchiEntity entity){
		int tuchiId = entity.getTuchiId();
		entity.setTruckOp(tuchiTruckOp.getValues(tuchiId));
		entity.setSyasyu(tuchiSyasyu.getValues(tuchiId));
		entity.setCity(tuchiCity.getValues(tuchiId));
		entity.setPref(tuchiPref.getValues(tuchiId));
	}

	@Override
	public void save(TuchiEntity entity) {
		tuchiDao.save(entity);

		tuchiTruckOp.save(entity.getTuchiId(), entity.getTruckOp());
		tuchiSyasyu.save(entity.getTuchiId(), entity.getSyasyu());
		tuchiCity.save(entity.getTuchiId(), entity.getCity());
		tuchiPref.save(entity.getTuchiId(), entity.getPref());
	}

	@Override
	public void delete(int tuchiId) {
		tuchiDao.delete(tuchiId);
	}

	@Override
	public int checkMatching(String ankenId) {
		List<Integer> tuchiIds = tuchiDao.getMatchTuchi(ankenId);

		if (tuchiIds.isEmpty()) {
			return 0;
		}

		int result = tuchiDao.addQueue(tuchiIds, ankenId);
		tuchiDao.increaseCount(tuchiIds);

		return result;
	}

	@Override
	public int checkAll() {
		String sql = "select ANKEN_ID from T_ANKEN";

		List<String> ankenIds = tuchiDao.debug(sql);

		int result = 0;

		for (String ankenId : ankenIds) {
			result += checkMatching(ankenId);
		}

		return result;
	}

	@Override
	public void sendMail() {
		this.baseUrl = optionDao.get("base_url");
		List<String> DestEmails = tuchiDao.getDestEmails();

		LogRecord.info( "DEST EMAILS" );
		LogRecord.info( DestEmails.toString() );

		for (String Email : DestEmails) {
			List<Map<String, Object>> Queues = tuchiDao.getQueues(Email);
			LogRecord.info( "QUEUES" );
			LogRecord.info( Queues.toString() );

			// メール文面作成
			String text = getTuchiText( Queues );
			if( text.isEmpty()){
				continue;
			}

			int result = mailSendComponent.send(Constants.TUCHI_EMAIL_SUBJECT, Email, text);

			for (Map<String, Object> Queue : Queues) {
				long id = (long) Queue.get("ID");
				int status = (int) Queue.get("STATUS");
				afterSendMail(id, status, result);
			}
		}
	}

	protected String getTuchiText( List<Map<String, Object>> Queues ){
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		String header = "配送案件のご案内 "+ df.format(new Date()) + "<br>";


		String main = "";
		for( Map<String,Object> Queue : Queues ){
			main += getTuchiAnkenText( (String)Queue.get("ANKEN_ID"));
		}
		if( main.isEmpty() ){
			return "";
		}
		main += "-------------------------------------<br>";


		String footer;
		footer  = "＊既に他業者が配送を確定している場合があります。<br>"
				+ "＊このメールは配送マッチングによる自動配信です。<br>"
				+ "*************************************<br>"
				+ "問合先：株式会社TRAIL<br>"
				+ "TEL: XXX-XXX-XXXX info@keel.co.jp<br>"
				+ "*************************************<br>";

		return header + main + footer;
	}

	protected String getTuchiAnkenText(String ankenId) {
		String result = "";

		Map<String, Object> data = tuchiDao.getAnkenForTuchi(ankenId);

		if( data == null ){
			return result;
		}

		result += "-------------------------------------<br>";
		result += String.format("受注期限：%s<br>", formatJutuDate( data.get("JUTU_KG") ));

		String dateFrom = formatSyukaDate( (String)data.get("DAY_FROM") + (String)data.get("TIME_FROM"));
		long countFrom = (long) data.get("COUNT_FROM");
		String countFromText = (countFrom > 1) ? String.format("ほか%d箇所", countFrom) : "";
		String addrFrom = (String)data.get("ADDR_FROM");

		String dateTo = formatSyukaDate( (String)data.get("DAY_TO") + (String)data.get("TIME_TO"));
		long countTo = (long) data.get("COUNT_TO");
		String countToText = (countTo > 1) ? String.format("ほか%d箇所", countTo) : "";
		String addrTo = (String)data.get("ADDR_TO");

		String yosan = String.format("%,d" , data.get("YOSAN_MN"));
		String url = this.baseUrl + "initAnkenDetail?ankenNo="+ankenId + "101";

		result += String.format("集荷：%s %s %s<br>", dateFrom , addrFrom , countFromText );
		result += String.format("納品：%s %s %s<br>", dateTo , addrTo , countToText );
		result += String.format("予算：%s円 【<a href=\"%s\">詳細</a>】<br>", yosan , url);

		return result;
	}

	protected String formatSyukaDate( String src ){
		return formatDate( src , FORMAT_SYUKA_SRC , FORMAT_SYUKA_DEST );
	}
	protected String formatJutuDate( Object src ){
		return formatDate( (String)src , FORMAT_JUTU_SRC , FORMAT_JUTU_DEST );
	}

	protected String formatDate( String src , SimpleDateFormat FormatSrc , SimpleDateFormat FormatDest ){
		Date date;
		try {
			date = FormatSrc.parse(src);
		} catch (ParseException e) {
			e.printStackTrace();
			return "invalid date";
		}

		return FormatDest.format(date);
	}

	protected String getTuchiAnkenText0(String ankenId) {
		String result = "";

		Map<String, Object> data = tuchiDao.getAnkenForTuchi(ankenId);

		result += String.format("<td>%s</td>", data.get("JUTU_KG"));
		result += String.format("<td>%s</td>", data.get("DAY_FROM"));
		long countFrom = (long) data.get("COUNT_FROM");
		if( countFrom >1){
			result += String.format("<td>%s %s ほか%d箇所</td>", data.get("PREF_FROM"), data.get("CITY_FROM"), countFrom - 1);
		}else{
			result += String.format("<td>%s %s</td>", data.get("PREF_FROM"), data.get("CITY_FROM") );
		}
		result += String.format("<td>%s</td>", data.get("DAY_TO"));
		long countTo = (long) data.get("COUNT_TO");
		if( countTo >1){
			result += String.format("<td>%s %s ほか%d箇所</td>", data.get("PREF_TO"), data.get("CITY_TO"), countTo - 1);
		}else{
			result += String.format("<td>%s %s</td>", data.get("PREF_TO"), data.get("CITY_TO") );
		}

		String $url = this.baseUrl + "initAnkenDetail?ankenNo="+ankenId + "101";
		result += String.format("<td><a href='%s'>link</a></td>", $url);

		result = "<tr>" + result + "</tr>";

		return result;
	}


	protected void afterSendMail(long queueId, int status, int result) {
		// フィルターされたとき
		if (result == MailSendComponent.SEND_FILTERED) {
			tuchiDao.setQueueStatus(queueId, Constants.TUCHI_QUEUE_STATE_FILTERED);
		}

		// 送信成功時 最大回数送っても失敗だった時はキューから削除
		if (result == MailSendComponent.SEND_SUCCESS
				|| (result == MailSendComponent.SEND_FAILED && status == Constants.MAX_MAIL_SEND_KS)) {
			tuchiDao.removeQueue(queueId);
			return;
		}

		// 最大回数以下で失敗時
		if (result == MailSendComponent.SEND_FAILED) {
			tuchiDao.setQueueStatus(queueId, status + 1);
		}
	}


	@Override
	public void clearDaylyCount(){
		tuchiDao.clearDaylyCount();
	}

}
