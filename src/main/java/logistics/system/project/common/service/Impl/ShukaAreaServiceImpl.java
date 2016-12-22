package logistics.system.project.common.service.Impl;

import java.util.List;

import logistics.system.project.common.Entity.AreaEntity;
import logistics.system.project.common.Entity.CityEntity;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.dao.AreaDao;
import logistics.system.project.common.dao.CityDao;
import logistics.system.project.common.dao.NisugateDao;
import logistics.system.project.common.dao.NisyuDao;
import logistics.system.project.common.dao.PrefDao;
import logistics.system.project.common.service.ShukaAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("areaService")
public class ShukaAreaServiceImpl implements ShukaAreaService {

	@Autowired
	@Qualifier("areaDao")
	private AreaDao areaDao;

	@Autowired
	@Qualifier("prefDao")
	private PrefDao prefDao;

	@Autowired
	@Qualifier("cityDao")
	private CityDao cityDao;

	@Autowired
	@Qualifier("nisugateDao")
	private NisugateDao nisugateDao;

	@Autowired
	@Qualifier("nisyuDao")
	private NisyuDao nisyuDao;

	@Override
	public List<AreaEntity> getAllArea() {
		return areaDao.getAllArea();
	}

	@Override
	public List<PrefEntity> getAllPref(String createDt) {
		return prefDao.getAllPref(createDt);
	}

	@Override
	public List<CityEntity> getCitiesByPrefCd(String prefCd, String createDt) {
		return cityDao.getCitiesByPrefCd(prefCd, createDt);
	}

	@Override
	public List<PrefEntity> getAllPrefList() {

		return prefDao.getAllPrefList();
	}

	@Override
	public List<NisugateEntity> getAllNisugateList() {
		return nisugateDao.getAllNisugateList();
	}

	@Override
	public List<NisyuEntity> getAllNisyuList() {
		System.out.println("test21");
		return nisyuDao.getAllNisyuList();
	}

	@Override
	public String getPreCdByPrefName(String prefName) {

		return prefDao.getPreCdByPrefName(prefName);
	}

	@Override
	public String getPreNameByPrefCd(String prefCd) {
		return prefDao.getPreNameByPrefCd(prefCd);
	}

}
