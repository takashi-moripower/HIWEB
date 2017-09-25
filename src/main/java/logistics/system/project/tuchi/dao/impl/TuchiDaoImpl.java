package logistics.system.project.tuchi.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.TuchiDao;

@Repository("tuchiDao")
public class TuchiDaoImpl extends BaseDao implements TuchiDao {

	@Value("#{configProperties['DefaultDISPDL']}")
	private String defaultDisplayDelay;

	@Value("#{configProperties['mail.batch.count.max']}")
	private String mailCountMax;

	@SuppressWarnings("unchecked")
	@Override
	public String[] getTruckOp(int tuchiCd) {
		List<Object> l = getSqlMapClientTemplate().queryForList("getTruckOpByTuchiCd", tuchiCd);

		String[] s = l.toArray(new String[l.size()]);

		return s;
	}

	@Override
	public TuchiEntity getTuchiById(int tuchiId) {
		return (TuchiEntity) getSqlMapClientTemplate().queryForObject("getTuchiByCd", tuchiId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TuchiEntity> getTuchiByUser(String userId) {
		return getSqlMapClientTemplate().queryForList("getTuchiByUser", userId);
	}

	@Override
	public int save(TuchiEntity entity) {
		if (entity.getTuchiId() == 0) {
			int newId = (int) getSqlMapClientTemplate().queryForObject("getNextTuchiId");
			entity.setTuchiId(newId);

			getSqlMapClientTemplate().insert("insertTuchi", entity);

			return 1;
		}

		getSqlMapClientTemplate().update("updateTuchi", entity);

		return 0;
	}

	@Override
	public void delete(int tuchiId) {
		getSqlMapClientTemplate().delete("deleteTuchi", tuchiId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getMatchTuchi(String ankenId) {
		return getSqlMapClientTemplate().queryForList("getMatchTuchi", ankenId);
	}

	@Override
	public int addQueue(List<Integer> tuchiIds, String ankenId) {
		int count = 0;
		for (Integer tuchiId : tuchiIds) {
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("tuchiId", tuchiId);
			param.put("ankenId", ankenId);
			param.put("created", new Date());

			getSqlMapClientTemplate().insert("addTuchiQueue", param);
			count++;
		}
		return count;
	}

	@Override
	public void removeQueue(long QueueId) {
		getSqlMapClientTemplate().delete("removeTuchiQueue", QueueId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDestEmails() {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("limit", Integer.parseInt(this.mailCountMax));
		param.put("defaultDelay", Integer.parseInt(this.defaultDisplayDelay));

		return getSqlMapClientTemplate().queryForList("getTuchiDestEmails", param);
	}

	@Override
	public void increaseCount(List<Integer> tuchiIds) {
		for (Integer tuchiId : tuchiIds) {
			getSqlMapClientTemplate().update("tuchiIncreaseCount", tuchiId);
		}
	}

	@Override
	public void setQueueStatus(long id, int status) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("status", status);
		getSqlMapClientTemplate().update("setQueueStatus", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getQueues(String email) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("email", email);
		param.put("defaultDelay", Integer.parseInt(this.defaultDisplayDelay));
		return getSqlMapClientTemplate().queryForList("getTuchiQueueByEmail", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAnkenForTuchi(String ankenId) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("getAnkenForTuchi", ankenId);
	}

	@Override
	public void clearDaylyCount() {
		getSqlMapClientTemplate().update("clearTuchiDaylyCount");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> debug(String sql) {
		return getSqlMapClientTemplate().queryForList("debugSelect", sql);
	}

}
