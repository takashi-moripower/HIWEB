package logistics.system.project.tuchi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.component.MailSendComponent;
import logistics.system.project.tuchi.dao.RelationDao;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.tuchi.service.TuchiService;
import logistics.system.project.utility.Constants;

@Service("tuchiService")
public class TuchiServiceImpl implements TuchiService {
	@Value("#{dynamicProperties['base_url']}")
	private String baseUrl;

	@Autowired
	@Qualifier("tuchiDao")
	private TuchiDao tuchiDao;

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao ankenDao;

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

	@Override
	public TuchiEntity getTuchiById(int tuchiId, boolean link) {
		TuchiEntity entity = tuchiDao.getTuchiById(tuchiId);

		if (link) {
			entity.setTruckOp(tuchiTruckOp.getValues(tuchiId));
			entity.setSyasyu(tuchiSyasyu.getValues(tuchiId));
			entity.setCity(tuchiCity.getValues(tuchiId));
			entity.setPref(tuchiPref.getValues(tuchiId));
		}
		return entity;
	}

	@Override
	public List<TuchiEntity> getTuchiByUser(String userId , boolean link) {
		List<TuchiEntity> result = tuchiDao.getTuchiByUser(userId);

		if (link) {
/*
			for(int i = 0; i < result.size(); ++i){
	        	TuchiEntity entity = result.get(i);
				int tuchiId = entity.getTuchiId();
				entity.setTruckOp(tuchiTruckOp.getValues(tuchiId));
				entity.setSyasyu(tuchiSyasyu.getValues(tuchiId));
				entity.setCity(tuchiCity.getValues(tuchiId));
				entity.setPref(tuchiPref.getValues(tuchiId));

				result.set(i, entity);
	        }
*/
			for( TuchiEntity entity : result ){
				int tuchiId = entity.getTuchiId();
				entity.setTruckOp(tuchiTruckOp.getValues(tuchiId));
				entity.setSyasyu(tuchiSyasyu.getValues(tuchiId));
				entity.setCity(tuchiCity.getValues(tuchiId));
				entity.setPref(tuchiPref.getValues(tuchiId));
			}

		}

		return result;
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
		List<String> DestEmails = tuchiDao.getDestEmails(Constants.MAX_TUCHI_MAIL);

		for (String Email : DestEmails) {
			List<HashMap<String, Object>> Queues = tuchiDao.getQueues(Email);

			// メール文面作成
			String text = "<html><body><table>";
			text += "<td>受注期限</td>";
			text += "<td colspan='2'>集荷</td>";
			text += "<td colspan='2'>納品</td>";
			for (HashMap<String, Object> Queue : Queues) {

				String ankenId = (String) Queue.get("ANKEN_ID");
				text += getTuchiText(ankenId);
			}

			text += "</table></body></html>";

			int result = mailSendComponent.send(Constants.TUCHI_EMAIL_SUBJECT, Email, text);

			for (HashMap<String, Object> Queue : Queues) {
				int id = (int) Queue.get("ID");
				int status = (int) Queue.get("STATUS");
				afterSendMail(id, status, result);
			}
		}
	}

	protected void afterSendMail(int id, int status, int result) {
		// フィルターされたとき
		if (result == mailSendComponent.SEND_FILTERED) {
			tuchiDao.setQueueStatus(id, Constants.TUCHI_QUEUE_STATE_FILTERED);
		}

		// 送信成功時 最大回数送っても失敗だった時はキューから削除
		if (result == mailSendComponent.SEND_SUCCESS
				|| (result == mailSendComponent.SEND_FAILED && status == Constants.MAX_MAIL_SEND_KS)) {
			tuchiDao.removeQueue(id);
			return;
		}

		// 最大回数以下で失敗時
		if (result == mailSendComponent.SEND_FAILED) {
			tuchiDao.setQueueStatus(id, status + 1);
		}
	}

	protected String getTuchiText(String ankenId) {
		String result = "";

		HashMap<String, Object> data = tuchiDao.getAnkenForTuchi(ankenId);

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

}
