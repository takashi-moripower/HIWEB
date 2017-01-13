package logistics.system.project.common.service.Impl;

import java.util.Date;
import java.util.List;

import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.ContactRRKEntity;
import logistics.system.project.common.Entity.NohinEntity;
import logistics.system.project.common.Entity.SyukaEntity;
import logistics.system.project.common.Entity.TruckEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.dao.ContactRRKDao;
import logistics.system.project.common.dao.NohinDao;
import logistics.system.project.common.dao.SyukaDao;
import logistics.system.project.common.dao.TruckDao;
import logistics.system.project.common.dao.TruckOpDao;
import logistics.system.project.common.form.AnkenDetailForm;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.utility.AnkenPicUtils;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ankenDetailService")
public class AnkenDetailServiceImpl implements AnkenDetailService {

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao dao;

	@Autowired
	@Qualifier("truckOpDao")
	private TruckOpDao truckOpDao;

	@Autowired
	@Qualifier("syukaDao")
	private SyukaDao syukaDao;

	@Autowired
	@Qualifier("nohinDao")
	private NohinDao nohinDao;

	@Autowired
	@Qualifier("truckDao")
	private TruckDao truckDao;

	@Override
	public AnkenDetailForm getAnkenDetail(String ankenNo) {
		AnkenDetailForm ankenDetailForm = new AnkenDetailForm();

		AnkenDetailEntity ankenDetail = dao.getAnkenDetail(ankenNo);
		if (ankenDetail != null) {

			if (!StringUtils.isBlank(ankenDetail.getJutuKigen())) {
				ankenDetail.setJutuKigen(ComUtils.editDate(ankenDetail.getJutuKigen(), "yyyyMMdd",
						"yyyy/MM/dd (E)"));
			}
			ankenDetailForm.setAnkenDetail(ankenDetail);

			String ankenId = StringUtils.substring(ankenNo, 0, 15);
			String truckNo = StringUtils.substring(ankenNo, 15, 16);
			TruckEntity truck = truckDao.getTruckByPK(ankenId, truckNo);
			truck.setOpList(ComUtils.editOpList(truck.getOpList(), Constants.getTruckOpList()));
			ankenDetailForm.setTruck(truck);


			List<SyukaEntity> syukaList = syukaDao.getSyukaByAnkenId(ankenId);
			List<NohinEntity> nohinList = nohinDao.getNohinByAnkenId(ankenId);

			for (SyukaEntity syuka : syukaList){
				syuka.setSyukaDay(ComUtils.editDate(syuka.getSyukaDay(), "yyyyMMdd",
						"yyyy/MM/dd (E)"));
				if (!StringUtils.isBlank(syuka.getSyukaTime())) {
					String str = syuka.getSyukaTime();
					syuka.setSyukaTime(StringUtils.left(str, 2) + ":" + StringUtils.right(str, 2));
				}
				if (!StringUtils.isBlank(syuka.getPostCode())) {
					String str = syuka.getPostCode();
					syuka.setPostCode(StringUtils.left(str, 3) + "-" + StringUtils.right(str, 4));
				}
			}

			for (NohinEntity nohin : nohinList){
				nohin.setNohinDay(ComUtils.editDate(nohin.getNohinDay(), "yyyyMMdd",
						"yyyy/MM/dd (E)"));
				if (!StringUtils.isBlank(nohin.getNohinTime())) {
					String str = nohin.getNohinTime();
					nohin.setNohinTime(StringUtils.left(str, 2) + ":" + StringUtils.right(str, 2));
				}
				if (!StringUtils.isBlank(nohin.getPostCode())) {
					String str = nohin.getPostCode();
					nohin.setPostCode(StringUtils.left(str, 3) + "-" + StringUtils.right(str, 4));
				}
			}

			ankenDetailForm.setSyukaList(syukaList);
			ankenDetailForm.setNohinList(nohinList);

			if (StringUtils.isNotBlank(ankenDetail.getPicName1())) {
				String tmpName = new Date().getTime() + "1.jpg";
				AnkenPicUtils.copyPicToTmp(ankenDetail.getAnkenId(), tmpName, 1);
				ankenDetail.setPicTmpNm1(tmpName);
			}

			if (StringUtils.isNotBlank(ankenDetail.getPicName2())) {
				String tmpName = new Date().getTime() + "2.jpg";
				AnkenPicUtils.copyPicToTmp(ankenDetail.getAnkenId(), tmpName, 2);
				ankenDetail.setPicTmpNm2(tmpName);
			}

			if (StringUtils.isNotBlank(ankenDetail.getPicName3())) {
				String tmpName = new Date().getTime() + "3.jpg";
				AnkenPicUtils.copyPicToTmp(ankenDetail.getAnkenId(), tmpName, 3);
				ankenDetail.setPicTmpNm3(tmpName);
			}
		}
		return ankenDetailForm;
	}

	@Override
	public String getMaxSeqNo(String startSeq) {
		return (String) dao.getMaxSeqNo(startSeq);
	}

	@Override
	public boolean updateAnkenStatus(AnkenDetailEntity ankenDetailEntity) {

		boolean success = false;
//		if (ankenDetailEntity.getShihraiKsCd() != null) {
//			ankenDetailEntity.setAnkenId(StringUtils.substring(ankenDetailEntity.getAnkenNo(), 0, 15));
//			success =dao.updateAnkenShiHrai(ankenDetailEntity) == 1;
//		}

		success = dao.updateAnkenStatus(ankenDetailEntity) == 1;

		return success;
	}

	@Override
	public boolean updateSyaban(AnkenDetailEntity ankenDetailEntity, UserEntity userEntity) {

		String current = ComUtils.getSysDateTime();
		ContactRRKEntity conRRKEntity = new ContactRRKEntity();
		conRRKEntity.setCompanyCd(userEntity.getCompanyCd());
		conRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_03);
		conRRKEntity.setCompanyNm(ankenDetailEntity.getSyabanKsNm());
		conRRKEntity.setTantoNm(ankenDetailEntity.getUntensyaName());
		conRRKEntity.setTantoTel(ankenDetailEntity.getRenrakuTel());
		conRRKEntity.setCreateId(userEntity.getUserId());
		conRRKEntity.setCreateDt(current);
		conRRKEntity.setUpdateId(userEntity.getUserId());
		conRRKEntity.setUpdateDt(current);

		List<ContactRRKEntity> contactResultList = contactRRKDao.isExistOrNotInConRRK(conRRKEntity);
		if(contactResultList.size() == 0) {
			contactRRKDao.insertContactRRK(conRRKEntity);
		}

		ankenDetailEntity.setUpdateId(userEntity.getUserId());
		return dao.updateSyaban(ankenDetailEntity) == 1;
	}

	@Override
	public AnkenDetailEntity getAnkenDetailByNo(String ankenNo) {

		return dao.getAnkenDetail(ankenNo);
	}

	@Override
	public TruckEntity getTruckByPK(String ankenId, String truckNo) {
		return truckDao.getTruckByPK(ankenId, truckNo);
	}

	@Autowired
	@Qualifier("contactRRKDao")
	private ContactRRKDao contactRRKDao;
}
