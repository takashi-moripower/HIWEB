package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.AnkenOrderEntity;
import logistics.system.project.common.dao.AnkenOrderDao;

import org.springframework.stereotype.Repository;

@Repository("ankenOrderDao")
public class AnkenOrderDaoImpl extends BaseDao implements AnkenOrderDao {

	@Override
	public void insertAnkenOrder(List<AnkenOrderEntity> ankenOrderList)  {		
		for(AnkenOrderEntity ankenOrder : ankenOrderList) {
			getSqlMapClientTemplate().insert("insertAnkenOrder", ankenOrder);
		}				
	}
	
	@Override
	public int getCountNot10(String ankenId, String updateDt) {
		
		AnkenOrderEntity ankenOrderEntity = new AnkenOrderEntity();
		ankenOrderEntity.setAnkenId(ankenId);
		ankenOrderEntity.setUpdateDt(updateDt);
		return (int)getSqlMapClientTemplate().queryForObject("getCountNot10", ankenOrderEntity);
	}

	@Override
	public int deleteAnkenOrder(String ankenId, String udpateDt) {
		AnkenOrderEntity ankenOrderEntity = new AnkenOrderEntity();
		ankenOrderEntity.setAnkenId(ankenId);
		ankenOrderEntity.setUpdateDt(udpateDt);
		return getSqlMapClientTemplate().delete("deleteAnkenOrder", ankenOrderEntity);
	}

	@Override
	public int ryokinToroku(AnkenOrderEntity ankenOrderEntity) {

		return getSqlMapClientTemplate().update("ryokinToroku", ankenOrderEntity);
	}
}
