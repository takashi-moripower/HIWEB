package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.AreaEntity;
import logistics.system.project.common.Entity.CityEntity;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;

public interface ShukaAreaService {

	public List<AreaEntity> getAllArea();

	public List<PrefEntity> getAllPref(String createDt);

	public List<CityEntity> getAllCities();

	public List<CityEntity> getCitiesByPrefCd(String prefCd, String createDt);

	public String getPreCdByPrefName(String prefName);

	public String getPreNameByPrefCd(String prefCd);

	public List<PrefEntity> getAllPrefList();

	public List<NisugateEntity> getAllNisugateList();

	public List<NisyuEntity> getAllNisyuList();

}
