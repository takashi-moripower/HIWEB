package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.SyukaEntity;

public interface SyukaDao {

	public List<SyukaEntity> getSyukaByAnkenId(String ankenId);
	
	public void insertSyuka(List<SyukaEntity> syukaEntityList);

	public int deleteSyuka(String ankenId);
}
