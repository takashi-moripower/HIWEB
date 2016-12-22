package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.dao.TruckOpDao;

import org.springframework.stereotype.Repository;

@Repository("truckOpDao")
public class TruckOpDaoImpl extends BaseDao implements TruckOpDao {

	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName")
	public List<TruckOpEntity> getAllOption() {
		
		return (List<TruckOpEntity>) getSqlMapClientTemplate().queryForList("getAllOption");
	}
}
