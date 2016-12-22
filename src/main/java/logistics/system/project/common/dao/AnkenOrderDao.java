package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.AnkenOrderEntity;

public interface AnkenOrderDao {	

	public void insertAnkenOrder(List<AnkenOrderEntity> ankenOrderList);

	public int getCountNot10(String ankenId, String updateDt);

	public int deleteAnkenOrder(String ankenId, String udpateDt);
	
	public int ryokinToroku(AnkenOrderEntity ankenOrderEntity);
}
