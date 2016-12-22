package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.AnkenListEntity;
import logistics.system.project.common.Entity.NinushiKensuEntity;
import logistics.system.project.common.Entity.SeikyuAnkenEntity;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.parameterClass.AnkenListParameter;
import logistics.system.project.common.parameterClass.SeikyuAnkenListParameter;

import org.springframework.stereotype.Repository;

@Repository("ankenDao")
public class AnkenDaoImpl extends BaseDao implements AnkenDao {

	@Override
	public int getKakuinZumiKensu(String companyCode, String sysDate, String gyomuSb) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("companyCode", companyCode);
		param.put("sysDate", sysDate);
		param.put("gyomuSb", gyomuSb);
		return (int) getSqlMapClientTemplate().queryForObject("getKakuinZumiKensu", param);
	}

	@Override
	public int getShabanMinyuRyokuKensu(String companyCode, String sysDate) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("companyCode", companyCode);
		param.put("sysDate", sysDate);
		return (int) getSqlMapClientTemplate().queryForObject("getShabanMinyuRyokuKensu", param);
	}

	@Override
	public NinushiKensuEntity getNinushiKensu(String companyCode, String sysDate) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("sysDate1", sysDate);
		param.put("sysDate2", sysDate);
		param.put("companyCode", companyCode);
		return (NinushiKensuEntity) getSqlMapClientTemplate().queryForObject("getNinushiKensu",
				param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnkenListEntity> getAnkenList(AnkenListParameter ankenListParameter) {
		return (List<AnkenListEntity>) getSqlMapClientTemplate().queryForList("getAnkenList",
				ankenListParameter);
	}

	@Override
	public int getAnkenListCount(AnkenListParameter ankenListParameter) {
		return  (int)getSqlMapClientTemplate().queryForObject("getAnkenListCount",
				ankenListParameter);
	}

	@Override
	public AnkenDetailEntity getAnkenDetail(String ankenNo) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("ankenNo", ankenNo);
		return (AnkenDetailEntity) getSqlMapClientTemplate()
				.queryForObject("getAnkenDetail", param);
	}

	@Override
	public String getMaxSeqNo(String startSeq) {
		return (String) getSqlMapClientTemplate().queryForObject("getAnKenMaxSeqNo", startSeq);
	}

	@Override
	public void insertAnken(AnkenDetailEntity ankenDetailEntity) {
		getSqlMapClientTemplate().insert("insertAnken", ankenDetailEntity);
	}

	@Override
	public int updateAnkenStatus(AnkenDetailEntity ankenDetailEntity) {

		return getSqlMapClientTemplate().update("updateAnkenStatus", ankenDetailEntity);
	}

	@Override
	public int updateSyaban(AnkenDetailEntity ankenDetailEntity) {
		return getSqlMapClientTemplate().update("updateSyaban", ankenDetailEntity);
	}

	@Override
	public int updateAnkenShiHrai(AnkenDetailEntity ankenDetailEntity) {
		return getSqlMapClientTemplate().update("updateAnkenShiHrai", ankenDetailEntity);
	}

	@Override
	public int deleteAnken(String ankenId, String udpateDt) {
		AnkenDetailEntity ankenDetailEntity = new AnkenDetailEntity();
		ankenDetailEntity.setAnkenId(ankenId);
		ankenDetailEntity.setUpdateDt(udpateDt);
		return getSqlMapClientTemplate().delete("deleteAnken", ankenDetailEntity);
	}

	@Override
	public int updateAnken(AnkenDetailEntity ankenDetailEntity) {
		return getSqlMapClientTemplate().update("updateAnken", ankenDetailEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeikyuAnkenEntity> getSeikyuAnkenList(SeikyuAnkenListParameter seikyuAnkenListParameter) {
		return (List<SeikyuAnkenEntity>) getSqlMapClientTemplate().queryForList("getSeikyuAnkenList",
				seikyuAnkenListParameter);
	}


}
