package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.NohinEntity;
import logistics.system.project.common.dao.NohinDao;

import org.springframework.stereotype.Repository;

@Repository("nohinDao")
public class NohinDaoImpl extends BaseDao implements NohinDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<NohinEntity> getNohinByAnkenId(String ankenId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ankenId", ankenId);
		return (List<NohinEntity>) getSqlMapClientTemplate().queryForList("getNohinByAnkenId",
				param);
	}

	@Override
	public void insertNohin(List<NohinEntity> nohinEntityList) {		
		for(NohinEntity nohinEntity :nohinEntityList) {
			getSqlMapClientTemplate().insert("insertNohin", nohinEntity);
		}		
	}
	
	@Override
	public int deleteNohin (String ankenId) {
		NohinEntity nohinEntity = new NohinEntity();
		nohinEntity.setAnkenId(ankenId);
		return getSqlMapClientTemplate().delete("deleteNohin", nohinEntity);		
	}

}
