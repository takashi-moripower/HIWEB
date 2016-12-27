package logistics.system.project.tuchi.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.TuchiDao;

@Repository("tuchiDao")
public class TuchiDaoImpl extends BaseDao implements TuchiDao {

	@Override
	public TuchiEntity getTuchiByCd(int tuchiCd) {
		return (TuchiEntity) getSqlMapClientTemplate().queryForObject("getTuchiByCd", tuchiCd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TuchiEntity> getTuchiByUser(String userId) {
		return getSqlMapClientTemplate().queryForList( "getTuchiByUser" , userId);
	}


}
