package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.AnkenListEntity;
import logistics.system.project.common.Entity.NinushiKensuEntity;
import logistics.system.project.common.Entity.SeikyuAnkenEntity;
import logistics.system.project.common.parameterClass.AnkenListParameter;
import logistics.system.project.common.parameterClass.SeikyuAnkenListParameter;

public interface AnkenDao {

	public NinushiKensuEntity getNinushiKensu(String companyCode, String sysDate);

	public int getKakuinZumiKensu(String companyCode, String sysDate, String gyomuSb);

	public int getShabanMinyuRyokuKensu(String companyCode, String sysDate);

	public List<AnkenListEntity> getAnkenList(AnkenListParameter ankenListParameter);

	public int getAnkenListCount(AnkenListParameter ankenListParameter);

	public AnkenDetailEntity getAnkenDetail(String ankenNo);

	public String getMaxSeqNo(String startSeq);

	public void insertAnken(AnkenDetailEntity ankenDetailEntity);

	public int updateAnkenStatus(AnkenDetailEntity ankenDetailEntity);

	public int updateAnkenShiHrai(AnkenDetailEntity ankenDetailEntity);

	public int updateSyaban(AnkenDetailEntity ankenDetailEntity);

	public int deleteAnken(String ankenId, String udpateDt);

	public int updateAnken(AnkenDetailEntity ankenDetailEntity);

	public List<SeikyuAnkenEntity>  getSeikyuAnkenList(SeikyuAnkenListParameter seikyuAnkenListParameter);
}
