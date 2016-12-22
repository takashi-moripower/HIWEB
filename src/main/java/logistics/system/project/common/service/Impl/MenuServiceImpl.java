package logistics.system.project.common.service.Impl;

import logistics.system.project.common.Entity.NinushiKensuEntity;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl implements MenuService {


	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao dao;

	@Override
	public int getKakuinZumiKensu(String companyCode, String sysDate, String gyomuSb) {
		return dao.getKakuinZumiKensu(companyCode, sysDate, gyomuSb);
	}

	@Override
	public int getShabanMinyuRyokuKensu(String companyCode, String sysDate) {
		return dao.getShabanMinyuRyokuKensu(companyCode, sysDate);
	}

	@Override
	public NinushiKensuEntity getNinushiKensu(String companyCode, String sysDate) {
		return dao.getNinushiKensu(companyCode, sysDate);}
}
