package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.NohinEntity;


public interface NohinDao {

	public List<NohinEntity> getNohinByAnkenId(String ankenId);
	
	public void insertNohin(List<NohinEntity> nohinEntityList);

	public int deleteNohin(String ankenId);
}
