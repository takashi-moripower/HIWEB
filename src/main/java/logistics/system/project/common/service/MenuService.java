package logistics.system.project.common.service;

import logistics.system.project.common.Entity.NinushiKensuEntity;

public interface MenuService {


	public NinushiKensuEntity getNinushiKensu(String companyCode, String sysDate);

	public int getKakuinZumiKensu(String companyCode, String sysDate, String gyomuSb);

	public int getShabanMinyuRyokuKensu(String companyCode, String sysDate);
}
