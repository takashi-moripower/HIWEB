package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.SyukaEntity;
import logistics.system.project.common.dao.SyukaDao;

import org.springframework.stereotype.Repository;

@Repository("syukaDao")
public class SyukaDaoImpl extends BaseDao implements SyukaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SyukaEntity> getSyukaByAnkenId(String ankenId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ankenId", ankenId);
		return (List<SyukaEntity>) getSqlMapClientTemplate().queryForList("getSyukaByAnkenId",
				param);
	}
	
	@Override
	public void insertSyuka(List<SyukaEntity> syukaEntityList) {		
		for(SyukaEntity syukaEntity :syukaEntityList) {
			getSqlMapClientTemplate().insert("insertCaseSyuka", syukaEntity);
		}					
	}
	
	@Override
	public int deleteSyuka(String ankenId) {
		SyukaEntity syukaEntity = new SyukaEntity();
		syukaEntity.setAnkenId(ankenId);
		return getSqlMapClientTemplate().delete("deleteSyuka", syukaEntity);
	}

}
