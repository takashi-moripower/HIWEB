package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.CityEntity;

public interface CityDao {

	public List<CityEntity> getCitiesByPrefCd(String prefCd, String createDt);

	public List<CityEntity> getAllCities();
}
