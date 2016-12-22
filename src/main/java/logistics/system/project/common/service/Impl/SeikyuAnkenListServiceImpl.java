package logistics.system.project.common.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import logistics.system.project.common.Entity.AnkenOrderEntity;
import logistics.system.project.common.Entity.SeikyuAnkenEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.dao.AnkenOrderDao;
import logistics.system.project.common.parameterClass.SeikyuAnkenListParameter;
import logistics.system.project.common.service.SeikyuAnkenListService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("seikyuAnkenListService")
public class SeikyuAnkenListServiceImpl implements SeikyuAnkenListService {

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao dao;

	@Autowired
	@Qualifier("ankenOrderDao")
	private AnkenOrderDao ankenOrderDao;

	@Override
	public List<SeikyuAnkenEntity> getSeikyuAnkenList(
			SeikyuAnkenListParameter seikyuAnkenListParameter, UserEntity user) {
		
		List<SeikyuAnkenEntity> seikyuAnkenList = dao.getSeikyuAnkenList(seikyuAnkenListParameter);
		this.editseikyuAnkenList(seikyuAnkenList, seikyuAnkenListParameter.getRyoritu(), user);
		
		return seikyuAnkenList;
	}
	
	private void editseikyuAnkenList (List<SeikyuAnkenEntity> seikyuAnkenList, String ryoritu, UserEntity user) {
		
		for (SeikyuAnkenEntity seikyuAnken : seikyuAnkenList) {
			seikyuAnken.setAnkenNo(ComUtils.formatAnkenNo(seikyuAnken.getAnkenNo()));
			seikyuAnken.setSyukaDay(ComUtils.getJapanDate(seikyuAnken.getSyukaDay()));
			seikyuAnken.setNohinDay(ComUtils.getJapanDate(seikyuAnken.getNohinDay()));
			
			BigDecimal yosan = new BigDecimal(StringUtils.isBlank(seikyuAnken.getYosan())? "0" : seikyuAnken.getYosan());

			BigDecimal tesuryo = BigDecimal.ZERO;
			if (Constants.GYOMU_SB_TRAIL.equals(user.getGyomuSb())) {
				if (!user.getCompanyCd().equals(seikyuAnken.getNinushiCd())) {
					tesuryo = yosan.multiply(new BigDecimal(ryoritu).divide(new BigDecimal("100")));
				}
			} else {
				if (StringUtils.isBlank((seikyuAnken.getShihraiKsCd()))) {
					tesuryo = yosan.multiply(new BigDecimal(ryoritu).divide(new BigDecimal("100")));
				}
			}
			seikyuAnken.setTesuryo(tesuryo.toString());
		}
	}

	@Override
	public boolean updateRyokin(List<SeikyuAnkenEntity> seikyuAnkenList, String sysDatetime, UserEntity user) throws Exception {
		
		for (SeikyuAnkenEntity seikyuAnken : seikyuAnkenList) {
			if (seikyuAnken.isUpdateFlag()) {
				AnkenOrderEntity ankenOrderEntity = new AnkenOrderEntity();
				ankenOrderEntity.setAnkenNo(seikyuAnken.getAnkenNo().replaceAll("-", ""));
				if (StringUtils.isBlank(seikyuAnken.getKosokuMn())) {
					ankenOrderEntity.setKosokuMn(null);
				} else {
					ankenOrderEntity.setKosokuMn(seikyuAnken.getKosokuMn());
				}
				if (StringUtils.isBlank(seikyuAnken.getCancelMn())) {
					ankenOrderEntity.setCancelMn(null);
				} else {
					ankenOrderEntity.setCancelMn(seikyuAnken.getCancelMn());
				}
				ankenOrderEntity.setUpdateId(user.getCompanyCd());
				ankenOrderEntity.setUpdateDt(seikyuAnken.getUpdateDt());
				ankenOrderEntity.setUpdateDt1(sysDatetime);
				
				if (ankenOrderDao.ryokinToroku(ankenOrderEntity) == 0) {
					throw new Exception();
				};
			}
			
		}
		return true;
	}

}
