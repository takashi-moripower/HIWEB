package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.dao.NisugateDao;

import org.springframework.stereotype.Repository;

@Repository("nisugateDao")
public class NisugateDaoImpl extends BaseDao implements NisugateDao{
	
	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName")
	public List<NisugateEntity> getAllNisugateList() {

		return (List<NisugateEntity>) getSqlMapClientTemplate().queryForList("getAllNisugateList");
	}
}
