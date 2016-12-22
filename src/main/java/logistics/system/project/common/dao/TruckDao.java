package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.TruckEntity;

public interface TruckDao {

	public TruckEntity getTruckByPK(String ankenId, String truckNo);
	
	public void insertCaseTruck (List<TruckEntity> truckEntityList);

	public List<TruckEntity> getTruckById(String ankenId);

	public int deleteTruck(String ankenId);
}
