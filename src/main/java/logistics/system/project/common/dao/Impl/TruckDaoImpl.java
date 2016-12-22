package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.TruckEntity;
import logistics.system.project.common.dao.TruckDao;

import org.springframework.stereotype.Repository;

@Repository("truckDao")
public class TruckDaoImpl extends BaseDao implements TruckDao {

	@Override
	public TruckEntity getTruckByPK(String ankenId, String truckNo) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ankenId", ankenId);
		param.put("truckNo", truckNo);
		return (TruckEntity) getSqlMapClientTemplate().queryForObject("getTruckByPK", param);
	}

	@Override
	public void insertCaseTruck(List<TruckEntity> truckEntityList) {		
		for(TruckEntity truckEntity : truckEntityList) {
			getSqlMapClientTemplate().insert("insertTruck", truckEntity);
		}			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TruckEntity> getTruckById(String ankenId) {		
		return (List<TruckEntity>) getSqlMapClientTemplate().queryForList("getTruckById", ankenId);
	}
	
	@Override
	public int deleteTruck(String ankenId) {
		TruckEntity truckEntity = new TruckEntity();
		truckEntity.setAnkenId(ankenId);
		return getSqlMapClientTemplate().delete("deleteTruck", truckEntity);
	}

}
