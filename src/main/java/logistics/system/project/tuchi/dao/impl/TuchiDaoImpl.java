package logistics.system.project.tuchi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.TuchiDao;

@Repository("tuchiDao")
public class TuchiDaoImpl extends BaseDao implements TuchiDao {

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
	public void delete( int tuchiId ){
		getSqlMapClientTemplate().delete("deleteTuchi", tuchiId );
	}

	@Override
	public List<Integer> getMatchTuchi( String ankenId ){
		return getSqlMapClientTemplate().queryForList("getMatchTuchi", ankenId);
	}
}
