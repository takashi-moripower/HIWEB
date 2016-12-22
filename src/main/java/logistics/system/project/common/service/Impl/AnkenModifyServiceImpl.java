package logistics.system.project.common.service.Impl;

import java.util.ArrayList;
import java.util.List;

import logistics.system.project.common.Entity.AddressRRKEntity;
import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.AnkenOrderEntity;
import logistics.system.project.common.Entity.ContactRRKEntity;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.NohinEntity;
import logistics.system.project.common.Entity.SyukaEntity;
import logistics.system.project.common.Entity.TruckEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.dao.AddressRRKDao;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.dao.AnkenOrderDao;
import logistics.system.project.common.dao.ContactRRKDao;
import logistics.system.project.common.dao.MemberDao;
import logistics.system.project.common.dao.NohinDao;
import logistics.system.project.common.dao.SyukaDao;
import logistics.system.project.common.dao.TruckDao;
import logistics.system.project.common.dao.TruckOpDao;
import logistics.system.project.common.exception.CustomException;
import logistics.system.project.common.form.AnkenTorokuForm;
import logistics.system.project.common.service.AnkenModifyService;
import logistics.system.project.ninushi.form.NohinForm;
import logistics.system.project.ninushi.form.SyukaForm;
import logistics.system.project.ninushi.form.TruckForm;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("ankenModifyService")
public class AnkenModifyServiceImpl implements AnkenModifyService {

	@Value("#{configProperties['update_anken_zero']}")
	private String update_anken_zero;

	@Value("#{configProperties['anken_not_editable']}")
	private String anken_not_editable;

	@Value("#{configProperties['delete_failed']}")
	private String delete_failed;

	@Override
	public List<AnkenOrderEntity> insertAnken(AnkenTorokuForm ankenTorokuForm, UserEntity user) {

		AnkenDetailEntity ankenEntity = new AnkenDetailEntity();
		ankenEntity.setAnkenId(ankenTorokuForm.getAnkenId());
		ankenEntity.setNinushiCd(user.getCompanyCd());

		ankenEntity.setJutuKigen(StringUtils.trimToEmpty(ankenTorokuForm.getOrderDate()).replace("/", "").substring(0,8));
		ankenEntity.setHokenKG(StringUtils.trimToEmpty(ankenTorokuForm.getHokenMn()).replaceAll(Constants.CONNECT_COMMA, ""));
		ankenEntity.setTyuiJK(ankenTorokuForm.getTyuiJk());
		ankenEntity.setPicName1(ankenTorokuForm.getPicNm1());
		ankenEntity.setPicName2(ankenTorokuForm.getPicNm2());
		ankenEntity.setPicName3(ankenTorokuForm.getPicNm3());
		if (Constants.GYOMU_SB_TRAIL.equals(user.getGyomuSb())) {
			ankenEntity.setSeikyuKsCd(StringUtils.trimToEmpty(ankenTorokuForm.getSeikyuKsCd()).replaceAll(Constants.CONNECT_DASH, ""));
		} else {
			ankenEntity.setSeikyuKsCd(user.getCompanyCd());
		}
		ankenEntity.setShihraiKsCd(StringUtils.trimToEmpty(ankenTorokuForm.getShihraiKsCd()).replaceAll(Constants.CONNECT_DASH, ""));
		ankenEntity.setCreateId(ankenTorokuForm.getCreateId());
		ankenEntity.setCreateDt(ankenTorokuForm.getCreateDt());
		ankenEntity.setUpdateId(ankenTorokuForm.getUpdateId());
		ankenEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());


		ankenDao.insertAnken(ankenEntity);

		List<SyukaEntity> syukaEntiList = new ArrayList<SyukaEntity>();

		List<ContactRRKEntity> insertConRRKEntityList = new ArrayList<ContactRRKEntity>();
		List<AddressRRKEntity> insertAddRRKEntityList = new ArrayList<AddressRRKEntity>();

		ContactRRKEntity conRRKEntity = null;
		AddressRRKEntity addRRKEntity = null;

		int syukaIndex = 1;
		for(SyukaForm syukaForm : ankenTorokuForm.getSyukaList()) {

			SyukaEntity syukaEntity = new SyukaEntity();
			syukaEntity.setAnkenId(ankenTorokuForm.getAnkenId());
			syukaEntity.setSyukaSeq(String.valueOf(syukaIndex));
			syukaEntity.setSyukaDay(StringUtils.trimToEmpty(syukaForm.getSyukaDay()).replace("/", "").substring(0,8));
			syukaEntity.setSyukaTime(StringUtils.trimToEmpty(syukaForm.getSyukaTime()).replace(":", ""));
			syukaEntity.setAddrNm(syukaForm.getAddrNm());
			syukaEntity.setPostCode(StringUtils.trimToEmpty(syukaForm.getPostCode()).replaceAll("-", ""));
			syukaEntity.setPrefNm(syukaForm.getPrefNm());
			syukaEntity.setCityNm(syukaForm.getCityNm());
			syukaEntity.setAddrOther(syukaForm.getAddrOther());
			syukaEntity.setTantoNm(syukaForm.getTantoNm());
			syukaEntity.setTantoTel(syukaForm.getTantoTel());
			syukaEntity.setNisyuCd(syukaForm.getNisyuCd());
			syukaEntity.setNisugataCd(syukaForm.getNisugataCd());
			syukaEntity.setKosu(syukaForm.getAmount());
			syukaEntity.setZyuryo(syukaForm.getWeight());
			syukaEntity.setBiko(syukaForm.getRemarks());

			conRRKEntity = new ContactRRKEntity();
			conRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			conRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_01);
			conRRKEntity.setCompanyNm(syukaForm.getAddrNm());
			conRRKEntity.setTantoNm(syukaForm.getTantoNm());
			conRRKEntity.setTantoTel(syukaForm.getTantoTel());
			conRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			conRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			conRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			conRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<ContactRRKEntity> contactResultList = contactRRKDao.isExistOrNotInConRRK(conRRKEntity);
			if(contactResultList.size() == 0) {
				insertConRRKEntityList.add(conRRKEntity);
			}

			addRRKEntity = new AddressRRKEntity();
			addRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			addRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_01);
			addRRKEntity.setAddrNm(syukaForm.getAddrNm());
			addRRKEntity.setPostCode(syukaForm.getPostCode().replaceAll("-", ""));
			addRRKEntity.setPrefNm(syukaForm.getPrefNm());
			addRRKEntity.setCityNm(syukaForm.getCityNm());
			addRRKEntity.setAddrOther(syukaForm.getAddrOther());
			addRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			addRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			addRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			addRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<AddressRRKEntity> addressResultList = addressRRKDao.isExistOrNotInAddRRK(addRRKEntity);
			if(addressResultList.size() == 0) {
				insertAddRRKEntityList.add(addRRKEntity);
			}

			syukaIndex++;
			syukaEntiList.add(syukaEntity);
		}

		syukaDao.insertSyuka(syukaEntiList);

		List<NohinEntity> nohinEntiList = new ArrayList<NohinEntity>();

		int nohinIndex = 1;
		for(NohinForm nohinForm : ankenTorokuForm.getNohinList()) {

			NohinEntity nohinEntity = new NohinEntity();
			nohinEntity.setAnkenId(ankenTorokuForm.getAnkenId());
			nohinEntity.setNohinSeq(String.valueOf(nohinIndex));
			nohinEntity.setNohinDay(StringUtils.trimToEmpty(nohinForm.getNohinDay()).replace("/", "").substring(0,8));
			nohinEntity.setNohinTime(StringUtils.trimToEmpty(nohinForm.getNohinTime()).replace(":", ""));
			nohinEntity.setAddrNm(nohinForm.getAddrNm());
			nohinEntity.setPostCode(StringUtils.trimToEmpty(nohinForm.getPostCode()).replaceAll("-", ""));
			nohinEntity.setPrefNm(nohinForm.getPrefNm());
			nohinEntity.setCityNm(nohinForm.getCityNm());
			nohinEntity.setAddrOther(nohinForm.getAddrOther());
			nohinEntity.setTantoNm(nohinForm.getTantoNm());
			nohinEntity.setTantoTel(nohinForm.getTantoTel());
			nohinEntity.setNisyuCd(nohinForm.getNisyuCd());
			nohinEntity.setNisugataCd(nohinForm.getNisugataCd());
			nohinEntity.setKosu(nohinForm.getAmount());
			nohinEntity.setZyuryo(nohinForm.getWeight());
			nohinEntity.setBiko(nohinForm.getRemarks());

			conRRKEntity = new ContactRRKEntity();
			conRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			conRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_02);
			conRRKEntity.setCompanyNm(nohinEntity.getAddrNm());
			conRRKEntity.setTantoNm(nohinEntity.getTantoNm());
			conRRKEntity.setTantoTel(nohinEntity.getTantoTel());
			conRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			conRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			conRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			conRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<ContactRRKEntity> contactResultList = contactRRKDao.isExistOrNotInConRRK(conRRKEntity);
			if(contactResultList.size() == 0) {
				insertConRRKEntityList.add(conRRKEntity);
			}

			addRRKEntity = new AddressRRKEntity();
			addRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			addRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_02);
			addRRKEntity.setAddrNm(nohinEntity.getAddrNm());
			addRRKEntity.setPostCode(nohinEntity.getPostCode().replaceAll("-", ""));
			addRRKEntity.setPrefNm(nohinEntity.getPrefNm());
			addRRKEntity.setCityNm(nohinEntity.getCityNm());
			addRRKEntity.setAddrOther(nohinEntity.getAddrOther());
			addRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			addRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			addRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			addRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<AddressRRKEntity> addressResultList = addressRRKDao.isExistOrNotInAddRRK(addRRKEntity);
			if(addressResultList.size() == 0) {
				insertAddRRKEntityList.add(addRRKEntity);
			}

			nohinIndex++;
			nohinEntiList.add(nohinEntity);
		}

		nohinDao.insertNohin(nohinEntiList);

		List<TruckEntity> truckEntityList = new ArrayList<TruckEntity>();

		List<TruckForm> truckFormList = ankenTorokuForm.getTruckList();

		int truckIndex = 1;

		for(TruckForm truckForm : truckFormList) {
			TruckEntity truckEntity = new TruckEntity();
			truckEntity.setAnkenId(ankenTorokuForm.getAnkenId());
			truckEntity.setTruckNo("" + truckIndex);
			truckEntity.setYosanMn(StringUtils.trimToNull(StringUtils.trimToEmpty(truckForm.getYosanMn()).replaceAll(Constants.CONNECT_COMMA, "")));
			truckEntity.setOrderMn(StringUtils.trimToNull(StringUtils.trimToEmpty(truckForm.getOrderMn()).replaceAll(Constants.CONNECT_COMMA, "")));
			if(StringUtils.isEmpty(truckEntity.getOrderMn())){
				truckEntity.setOrderMn(truckEntity.getYosanMn());
			}
			truckEntity.setKosokuKbn(truckForm.getKosokuKbn());
			truckEntity.setNenryoscKbn(truckForm.getNenryoscKbn());
			truckEntity.setNenryoscMn(StringUtils.trimToNull(StringUtils.trimToEmpty(truckForm.getNenryoscMn()).replaceAll(Constants.CONNECT_COMMA, "")));
			truckEntity.setSyasyuCd(truckForm.getSyasyuCd());
			truckEntity.setDaisu(truckForm.getDaisu());
			truckEntity.setOpList(truckForm.getOpList());
			truckIndex++;
			truckEntityList.add(truckEntity);
		}
		truckDao.insertCaseTruck(truckEntityList);

		List<AnkenOrderEntity> ankenOrderList = new ArrayList<AnkenOrderEntity>();

		int truckNo = truckFormList.size();

		AnkenOrderEntity ankenOder = null;

		for(int i = 1; i <= truckNo; i++) {
			int truckDaisu = Integer.parseInt(truckFormList.get(i-1).getDaisu());

			for(int index = 1; index <= truckDaisu; index++) {
				ankenOder = new AnkenOrderEntity();
				ankenOder.setAnkenId(ankenTorokuForm.getAnkenId());
				ankenOder.setTruckNo("" + i);
				String renban = String.format("%02d", index);
				ankenOder.setRenban(renban);
				ankenOder.setAnkenNo(ankenTorokuForm.getAnkenId() + i + renban);
				if (Constants.GYOMU_SB_TRAIL.equals(user.getGyomuSb())) {
					ankenOder.setUnsoksCd(user.getCompanyCd());
				} else {
					ankenOder.setUnsoksCd(StringUtils.trimToEmpty(ankenTorokuForm.getShihraiKsCd()).replace(Constants.CONNECT_DASH, ""));
				}

				//案件ステータス（運送を指定して登録時は登録済みにする
				if(StringUtils.isEmpty(ankenTorokuForm.getShihraiKsCd())){
					ankenOder.setAnkenStatus(Constants.ANKEN_STATUS_10);
				} else {
					ankenOder.setAnkenStatus(Constants.ANKEN_STATUS_20);
				}

				ankenOder.setShihraiKsCd(StringUtils.trimToEmpty(ankenTorokuForm.getShihraiKsCd()).replace(Constants.CONNECT_DASH, ""));
				ankenOder.setCreateId(ankenTorokuForm.getCreateId());
				ankenOder.setCreateDt(ankenTorokuForm.getCreateDt());
				ankenOder.setUpdateId(ankenTorokuForm.getUpdateId());
				ankenOder.setUpdateDt(ankenTorokuForm.getUpdateDt());
				ankenOrderList.add(ankenOder);
			}

		}
		ankenOrderDao.insertAnkenOrder(ankenOrderList);

		for(ContactRRKEntity conEntity : insertConRRKEntityList) {
			contactRRKDao.insertContactRRK(conEntity);
		}

		for(AddressRRKEntity addEntity : insertAddRRKEntityList) {
			addressRRKDao.insertAddressRRK(addEntity);
		}

		return ankenOrderList;
	}

	@Override
	public List<AnkenOrderEntity> updateAnken(AnkenTorokuForm ankenTorokuForm, UserEntity user) throws CustomException, Exception{

		AnkenDetailEntity ankenEntity = new AnkenDetailEntity();
		ankenEntity.setAnkenId(ankenTorokuForm.getAnkenId());
		ankenEntity.setAnkenNo(ankenTorokuForm.getAnkenNo().replaceAll(Constants.CONNECT_DASH, ""));
		ankenEntity.setNinushiCd(user.getCompanyCd());

		ankenEntity.setJutuKigen(StringUtils.trimToEmpty(ankenTorokuForm.getOrderDate()).replace("/", "").substring(0,8));
		ankenEntity.setHokenKG(StringUtils.trimToEmpty(ankenTorokuForm.getHokenMn()).replaceAll(Constants.CONNECT_COMMA, ""));
		ankenEntity.setTyuiJK(ankenTorokuForm.getTyuiJk());
		ankenEntity.setPicName1(ankenTorokuForm.getPicNm1());
		ankenEntity.setPicName2(ankenTorokuForm.getPicNm2());
		ankenEntity.setPicName3(ankenTorokuForm.getPicNm3());
		if (Constants.GYOMU_SB_TRAIL.equals(user.getGyomuSb())) {
			ankenEntity.setSeikyuKsCd(StringUtils.trimToEmpty(ankenTorokuForm.getSeikyuKsCd()).replaceAll(Constants.CONNECT_DASH, ""));
		} else {
			ankenEntity.setSeikyuKsCd(user.getCompanyCd());
		}
		ankenEntity.setShihraiKsCd(StringUtils.trimToEmpty(ankenTorokuForm.getShihraiKsCd()).replaceAll(Constants.CONNECT_DASH, ""));
		ankenEntity.setCreateId(ankenTorokuForm.getCreateId());
		ankenEntity.setCreateDt(ankenTorokuForm.getCreateDt());
		ankenEntity.setUpdateId(ankenTorokuForm.getUpdateId());
		ankenEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

		int count = ankenDao.updateAnken(ankenEntity);
		if(count == 0) {
			throw new CustomException(this.update_anken_zero);
		}

		int statusCheckCount = getCountNot10(ankenTorokuForm.getAnkenId(), ankenTorokuForm.getUpdateDt());
		if(statusCheckCount == 1) {
			try {
				deleteAnkenOther(ankenTorokuForm.getAnkenId(), ankenTorokuForm.getUpdateDt());
			}
			catch (Exception e) {
				throw new CustomException(this.delete_failed);
			}
		}
		else {
			throw new CustomException(this.anken_not_editable);
		}

		List<AnkenOrderEntity> ankenOrderList = insertAnkenOther(ankenTorokuForm, user);
		return ankenOrderList;
	}

	@Override
	public AnkenTorokuForm getAnkenEditable(String ankenNo) {
		AnkenTorokuForm ankenTorokuForm = new AnkenTorokuForm();
		AnkenDetailEntity ankenDetail = ankenDao.getAnkenDetail(ankenNo);
		if (ankenDetail != null) {

			if (!StringUtils.isEmpty(ankenDetail.getJutuKigen())) {
				ankenTorokuForm.setOrderDate(ComUtils.editDate(ankenDetail.getJutuKigen(), "yyyyMMdd",
						"yyyy/MM/dd (E)"));
			}

			String ankenId = StringUtils.substring(ankenNo, 0, 15);

			ankenTorokuForm.setAnkenNo(ComUtils.formatAnkenNo(ankenNo));
			ankenTorokuForm.setAnkenId(ankenId);
			ankenTorokuForm.setOrderDate(ComUtils.editDate(ankenDetail.getJutuKigen(), "yyyyMMdd",	"yyyy/MM/dd (E)"));
			ankenTorokuForm.setAnkenStatus(ankenDetail.getStatus());
			ankenTorokuForm.setAnkenStatusNm(ankenDetail.getStatusNm());
			ankenTorokuForm.setHokenMn(ankenDetail.getHokenKG());
			ankenTorokuForm.setTyuiJk(ankenDetail.getTyuiJK());
			ankenTorokuForm.setSeikyuKsCd(ankenDetail.getSeikyuKsCd());
			ankenTorokuForm.setShihraiKsCd(ankenDetail.getShihraiKsCd());
			ankenTorokuForm.setPicNm1(ankenDetail.getPicName1());
			ankenTorokuForm.setPicNm2(ankenDetail.getPicName2());
			ankenTorokuForm.setPicNm3(ankenDetail.getPicName3());
			ankenTorokuForm.setCreateId(ankenDetail.getCreateId());
			ankenTorokuForm.setCreateDt(ankenDetail.getCreateDt());
			ankenTorokuForm.setUpdateId(ankenDetail.getUpdateId());
			ankenTorokuForm.setUpdateDt(ankenDetail.getUpdateDt());

			if (StringUtils.isNotBlank(ankenDetail.getNinushiCd())) {
				MemberEntity seikyuKs = memberDao.getMemberByCd(ankenDetail.getNinushiCd());

				if (seikyuKs != null) {
					ankenTorokuForm.setSeikyuKsCd(ankenDetail.getSeikyuKsCd());
					String seikyuKsComNm = StringUtils.isNotBlank(seikyuKs.getCompanyNm()) ? seikyuKs.getCompanyNm() : "";
					String seikyuKsOfficeNm = StringUtils.isNotBlank(seikyuKs.getOfficeNm()) ? seikyuKs.getOfficeNm() : "";
					ankenTorokuForm.setSeikyuKsNm(seikyuKsComNm + seikyuKsOfficeNm);

					String seikyuKsPrefNm = StringUtils.isNotBlank(seikyuKs.getPrefNm()) ? seikyuKs.getPrefNm() : "";
					String seikyuKsCityNm = StringUtils.isNotBlank(seikyuKs.getCityNm()) ? seikyuKs.getCityNm() : "";
					ankenTorokuForm.setSeikyuKsAddr(seikyuKsPrefNm + seikyuKsCityNm);
				}
			}

			if (StringUtils.isNotBlank(ankenDetail.getUnsoCd())) {
				MemberEntity shihraiKs = memberDao.getMemberByCd(ankenDetail.getUnsoCd());

				if (shihraiKs != null) {
					ankenTorokuForm.setShihraiKsCd(ankenDetail.getUnsoCd());

					String shihraiKsComNm = StringUtils.isNotBlank(shihraiKs.getCompanyNm()) ? shihraiKs.getCompanyNm() : "";
					String shihraiKsOfficeNm = StringUtils.isNotBlank(shihraiKs.getOfficeNm()) ? shihraiKs.getOfficeNm() : "";
					ankenTorokuForm.setShihraiKsNm(shihraiKsComNm + shihraiKsOfficeNm);

					String shihraiKsPrefNm = StringUtils.isNotBlank(shihraiKs.getPrefNm()) ? shihraiKs.getPrefNm() : "";
					String shihraiKsCityNm = StringUtils.isNotBlank(shihraiKs.getCityNm()) ? shihraiKs.getCityNm() : "";
					ankenTorokuForm.setShihraiKsAddr(shihraiKsPrefNm + shihraiKsCityNm);
				}
			}


			List<SyukaEntity> syukaEntityList = syukaDao.getSyukaByAnkenId(ankenId);
			List<SyukaForm> syukaFormList = new ArrayList<SyukaForm>();
			SyukaForm syukaForm = null;
			for(SyukaEntity syukaEntity : syukaEntityList) {
				syukaForm = new SyukaForm();
				syukaForm.setAddrNm(syukaEntity.getAddrNm());
				syukaForm.setAddrOther(syukaEntity.getAddrOther());
				syukaForm.setAmount(syukaEntity.getKosu());
				syukaForm.setCityNm(syukaEntity.getCityNm());
				syukaForm.setNisugataCd(syukaEntity.getNisugataCd());
				syukaForm.setNisugataNm(syukaEntity.getNisugataNm());
				syukaForm.setNisyuCd(syukaEntity.getNisyuCd());
				syukaForm.setNisyuNm(syukaEntity.getNisyuNm());
				syukaForm.setPostCode(syukaEntity.getPostCode());
				syukaForm.setPrefNm(syukaEntity.getPrefNm());
				syukaForm.setRemarks(syukaEntity.getBiko());
				syukaForm.setSyukaDay(ComUtils.editDate(syukaEntity.getSyukaDay(), "yyyyMMdd",	"yyyy/MM/dd (E)"));
				syukaForm.setSyukaTime(ComUtils.editDate(syukaEntity.getSyukaTime(), "HHmm", "HH:mm"));
				syukaForm.setTantoNm(syukaEntity.getTantoNm());
				syukaForm.setTantoTel(syukaEntity.getTantoTel());
				syukaForm.setWeight(syukaEntity.getZyuryo());
				syukaFormList.add(syukaForm);
			}

			List<NohinEntity> nohinEntityList = nohinDao.getNohinByAnkenId(ankenId);
			List<NohinForm> nohinFormList = new ArrayList<NohinForm>();
			NohinForm nohinForm = null;
			for (NohinEntity nohinEntity : nohinEntityList) {
				nohinForm = new NohinForm();
				nohinForm.setAddrNm(nohinEntity.getAddrNm());
				nohinForm.setAddrOther(nohinEntity.getAddrOther());
				nohinForm.setAmount(nohinEntity.getKosu());
				nohinForm.setCityNm(nohinEntity.getCityNm());
				nohinForm.setNisugataCd(nohinEntity.getNisugataCd());
				nohinForm.setNisugataNm(nohinEntity.getNisugataNm());
				nohinForm.setNisyuCd(nohinEntity.getNisyuCd());
				nohinForm.setNisyuNm(nohinEntity.getNisyuNm());
			    nohinForm.setNohinDay(ComUtils.editDate(nohinEntity.getNohinDay(),"yyyyMMdd",	"yyyy/MM/dd (E)"));
				nohinForm.setNohinTime(ComUtils.editDate(nohinEntity.getNohinTime(), "HHmm", "HH:mm"));
				nohinForm.setPostCode(nohinEntity.getPostCode());
				nohinForm.setPrefNm(nohinEntity.getPrefNm());
				nohinForm.setRemarks(nohinEntity.getBiko());
				nohinForm.setTantoNm(nohinEntity.getTantoNm());
				nohinForm.setTantoTel(nohinEntity.getTantoTel());
				nohinForm.setWeight(nohinEntity.getZyuryo());
				nohinFormList.add(nohinForm);
			}

			List<TruckEntity> truckEntityList = truckDao.getTruckById(ankenId);;
			List<TruckForm> truckFormList = new ArrayList<TruckForm>();
			TruckForm truckForm = null;
			for(TruckEntity truckEntity : truckEntityList) {
				truckForm = new TruckForm();
				truckForm.setDaisu(truckEntity.getDaisu());
				truckForm.setKosokuKbn(truckEntity.getKosokuKbn());
				truckForm.setNenryoscKbn(truckEntity.getNenryoscKbn());
				truckForm.setNenryoscMn(truckEntity.getNenryoscMn());
				truckForm.setOpList(ComUtils.editOpList(truckEntity.getOpList(), Constants.MAST_TRUCKOP_LIST));
				truckForm.setOrderMn(truckEntity.getOrderMn());
				truckForm.setSyasyuCd(truckEntity.getSyasyuCd());
				truckForm.setSyasyuNm(truckEntity.getSyasyuNm());
				truckForm.setYosanMn(truckEntity.getYosanMn());
				truckFormList.add(truckForm);
			}

			ankenTorokuForm.setSyukaList(syukaFormList);
			ankenTorokuForm.setNohinList(nohinFormList);
			ankenTorokuForm.setTruckList(truckFormList);

		}

		return ankenTorokuForm;
	}

	@Override
	public int getCountNot10(String ankenId, String updateDt) {
		return ankenOrderDao.getCountNot10(ankenId, updateDt);
	}

	@Override
	public void deleteAnken(String ankenId, String udpateDt) throws Exception {

		int ankenDeleted = ankenDao.deleteAnken(ankenId, udpateDt);
		if (ankenDeleted == 0) {
			throw new Exception();
		}
		int ankenOrderDeleted = ankenOrderDao.deleteAnkenOrder(ankenId, udpateDt);
		if (ankenOrderDeleted == 0) {
			throw new Exception();
		}

		if(syukaDao.deleteSyuka(ankenId) == 0) {
			throw new Exception();
		}

		if(nohinDao.deleteNohin(ankenId) == 0) {
			throw new Exception();
		}

		if(truckDao.deleteTruck(ankenId) == 0) {
			throw new Exception();
		}
	}

	@Override
	public void deleteAnkenOther(String ankenId, String udpateDt) throws Exception {

		int ankenOrderDeleted = ankenOrderDao.deleteAnkenOrder(ankenId, udpateDt);
		if (ankenOrderDeleted == 0) {
			throw new Exception();
		}

		if(syukaDao.deleteSyuka(ankenId) == 0) {
			throw new Exception();
		}

		if(nohinDao.deleteNohin(ankenId) == 0) {
			throw new Exception();
		}

		if(truckDao.deleteTruck(ankenId) == 0) {
			throw new Exception();
		}
	}

	@Override
	public List<AnkenOrderEntity> insertAnkenOther(AnkenTorokuForm ankenTorokuForm, UserEntity user) {

		List<AnkenOrderEntity> ankenOrderList = new ArrayList<AnkenOrderEntity>();

		List<TruckForm> truckFormList = ankenTorokuForm.getTruckList();
		int truckNo = truckFormList.size();

		AnkenOrderEntity ankenOder = null;

		for(int i = 1; i <= truckNo; i++) {
			int truckDaisu = Integer.parseInt(truckFormList.get(i-1).getDaisu());

			for(int index = 1; index <= truckDaisu; index++) {
				ankenOder = new AnkenOrderEntity();
				ankenOder.setAnkenId(ankenTorokuForm.getAnkenId());
				ankenOder.setTruckNo("" + i);
				String renban = String.format("%02d", index);
				ankenOder.setRenban(renban);
				ankenOder.setAnkenNo(ankenTorokuForm.getAnkenId() + i + renban);
				if (Constants.GYOMU_SB_TRAIL.equals(user.getGyomuSb())) {
					ankenOder.setUnsoksCd(user.getCompanyCd());
				} else {
					ankenOder.setUnsoksCd(StringUtils.trimToEmpty(ankenTorokuForm.getShihraiKsCd()).replace(Constants.CONNECT_DASH, ""));
				}

				//案件ステータス（運送を指定して登録時は登録済みにする
				if(StringUtils.isEmpty(ankenTorokuForm.getShihraiKsCd())){
					ankenOder.setAnkenStatus(Constants.ANKEN_STATUS_10);
				} else {
					ankenOder.setAnkenStatus(Constants.ANKEN_STATUS_20);
				}

				ankenOder.setShihraiKsCd(StringUtils.trimToEmpty(ankenTorokuForm.getShihraiKsCd()).replace(Constants.CONNECT_DASH, ""));
				ankenOder.setCreateId(ankenTorokuForm.getUpdateId());
				ankenOder.setCreateDt(ankenTorokuForm.getUpdateDt());
				ankenOder.setUpdateId(ankenTorokuForm.getUpdateId());
				ankenOder.setUpdateDt(ankenTorokuForm.getUpdateDt());
				ankenOrderList.add(ankenOder);
			}

		}
		ankenOrderDao.insertAnkenOrder(ankenOrderList);

		List<SyukaEntity> syukaEntiList = new ArrayList<SyukaEntity>();

		List<ContactRRKEntity> insertConRRKEntityList = new ArrayList<ContactRRKEntity>();
		List<AddressRRKEntity> insertAddRRKEntityList = new ArrayList<AddressRRKEntity>();

		ContactRRKEntity conRRKEntity = null;
		AddressRRKEntity addRRKEntity = null;

		int syukaIndex = 1;
		for(SyukaForm syukaForm : ankenTorokuForm.getSyukaList()) {

			SyukaEntity syukaEntity = new SyukaEntity();
			syukaEntity.setAnkenId(ankenTorokuForm.getAnkenId());
			syukaEntity.setSyukaSeq(String.valueOf(syukaIndex));
			syukaEntity.setSyukaDay(StringUtils.trimToEmpty(syukaForm.getSyukaDay()).replace("/", "").substring(0,8));
			syukaEntity.setSyukaTime(StringUtils.trimToEmpty(syukaForm.getSyukaTime()).replace(":", ""));
			syukaEntity.setAddrNm(syukaForm.getAddrNm());
			syukaEntity.setPostCode(StringUtils.trimToEmpty(syukaForm.getPostCode()).replaceAll("-", ""));
			syukaEntity.setPrefNm(syukaForm.getPrefNm());
			syukaEntity.setCityNm(syukaForm.getCityNm());
			syukaEntity.setAddrOther(syukaForm.getAddrOther());
			syukaEntity.setTantoNm(syukaForm.getTantoNm());
			syukaEntity.setTantoTel(syukaForm.getTantoTel());
			syukaEntity.setNisyuCd(syukaForm.getNisyuCd());
			syukaEntity.setNisugataCd(syukaForm.getNisugataCd());
			syukaEntity.setKosu(syukaForm.getAmount());
			syukaEntity.setZyuryo(syukaForm.getWeight());
			syukaEntity.setBiko(syukaForm.getRemarks());

			conRRKEntity = new ContactRRKEntity();
			conRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			conRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_01);
			conRRKEntity.setCompanyNm(syukaForm.getAddrNm());
			conRRKEntity.setTantoNm(syukaForm.getTantoNm());
			conRRKEntity.setTantoTel(syukaForm.getTantoTel());
			conRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			conRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			conRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			conRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<ContactRRKEntity> contactResultList = contactRRKDao.isExistOrNotInConRRK(conRRKEntity);
			if(contactResultList.size() == 0) {
				insertConRRKEntityList.add(conRRKEntity);
			}

			addRRKEntity = new AddressRRKEntity();
			addRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			addRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_01);
			addRRKEntity.setAddrNm(syukaForm.getAddrNm());
			addRRKEntity.setPostCode(syukaForm.getPostCode().replaceAll("-", ""));
			addRRKEntity.setPrefNm(syukaForm.getPrefNm());
			addRRKEntity.setCityNm(syukaForm.getCityNm());
			addRRKEntity.setAddrOther(syukaForm.getAddrOther());
			addRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			addRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			addRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			addRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<AddressRRKEntity> addressResultList = addressRRKDao.isExistOrNotInAddRRK(addRRKEntity);
			if(addressResultList.size() == 0) {
				insertAddRRKEntityList.add(addRRKEntity);
			}

			syukaIndex++;
			syukaEntiList.add(syukaEntity);
		}

		syukaDao.insertSyuka(syukaEntiList);

		List<NohinEntity> nohinEntiList = new ArrayList<NohinEntity>();

		int nohinIndex = 1;
		for(NohinForm nohinForm : ankenTorokuForm.getNohinList()) {

			NohinEntity nohinEntity = new NohinEntity();
			nohinEntity.setAnkenId(ankenTorokuForm.getAnkenId());
			nohinEntity.setNohinSeq(String.valueOf(nohinIndex));
			nohinEntity.setNohinDay(StringUtils.trimToEmpty(nohinForm.getNohinDay()).replace("/", "").substring(0,8));
			nohinEntity.setNohinTime(StringUtils.trimToEmpty(nohinForm.getNohinTime()).replace(":", ""));
			nohinEntity.setAddrNm(nohinForm.getAddrNm());
			nohinEntity.setPostCode(StringUtils.trimToEmpty(nohinForm.getPostCode()).replaceAll("-", ""));
			nohinEntity.setPrefNm(nohinForm.getPrefNm());
			nohinEntity.setCityNm(nohinForm.getCityNm());
			nohinEntity.setAddrOther(nohinForm.getAddrOther());
			nohinEntity.setTantoNm(nohinForm.getTantoNm());
			nohinEntity.setTantoTel(nohinForm.getTantoTel());
			nohinEntity.setNisyuCd(nohinForm.getNisyuCd());
			nohinEntity.setNisugataCd(nohinForm.getNisugataCd());
			nohinEntity.setKosu(nohinForm.getAmount());
			nohinEntity.setZyuryo(nohinForm.getWeight());
			nohinEntity.setBiko(nohinForm.getRemarks());

			conRRKEntity = new ContactRRKEntity();
			conRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			conRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_02);
			conRRKEntity.setCompanyNm(nohinEntity.getAddrNm());
			conRRKEntity.setTantoNm(nohinEntity.getTantoNm());
			conRRKEntity.setTantoTel(nohinEntity.getTantoTel());
			conRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			conRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			conRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			conRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<ContactRRKEntity> contactResultList = contactRRKDao.isExistOrNotInConRRK(conRRKEntity);
			if(contactResultList.size() == 0) {
				insertConRRKEntityList.add(conRRKEntity);
			}

			addRRKEntity = new AddressRRKEntity();
			addRRKEntity.setCompanyCd(StringUtils.substring(ankenTorokuForm.getAnkenId(), 0,6));
			addRRKEntity.setAddressKbn(Constants.CONTACT_ADDRESS_KBN_02);
			addRRKEntity.setAddrNm(nohinEntity.getAddrNm());
			addRRKEntity.setPostCode(nohinEntity.getPostCode().replaceAll("-", ""));
			addRRKEntity.setPrefNm(nohinEntity.getPrefNm());
			addRRKEntity.setCityNm(nohinEntity.getCityNm());
			addRRKEntity.setAddrOther(nohinEntity.getAddrOther());
			addRRKEntity.setCreateId(ankenTorokuForm.getCreateId());
			addRRKEntity.setCreateDt(ankenTorokuForm.getCreateDt());
			addRRKEntity.setUpdateId(ankenTorokuForm.getUpdateId());
			addRRKEntity.setUpdateDt(ankenTorokuForm.getUpdateDt());

			List<AddressRRKEntity> addressResultList = addressRRKDao.isExistOrNotInAddRRK(addRRKEntity);
			if(addressResultList.size() == 0) {
				insertAddRRKEntityList.add(addRRKEntity);
			}

			nohinIndex++;
			nohinEntiList.add(nohinEntity);
		}

		nohinDao.insertNohin(nohinEntiList);

		List<TruckEntity> truckEntityList = new ArrayList<TruckEntity>();


		int truckIndex = 1;

		for(TruckForm truckForm : truckFormList) {
			TruckEntity truckEntity = new TruckEntity();
			truckEntity.setAnkenId(ankenTorokuForm.getAnkenId());
			truckEntity.setTruckNo("" + truckIndex);
			truckEntity.setYosanMn(StringUtils.trimToNull(StringUtils.trimToEmpty(truckForm.getYosanMn()).replaceAll(Constants.CONNECT_COMMA, "")));
			truckEntity.setOrderMn(StringUtils.trimToNull(StringUtils.trimToEmpty(truckForm.getOrderMn()).replaceAll(Constants.CONNECT_COMMA, "")));
			if(StringUtils.isEmpty(truckEntity.getOrderMn())){
				truckEntity.setOrderMn(truckEntity.getYosanMn());
			}
			truckEntity.setKosokuKbn(truckForm.getKosokuKbn());
			truckEntity.setNenryoscKbn(truckForm.getNenryoscKbn());
			truckEntity.setNenryoscMn(StringUtils.trimToNull(StringUtils.trimToEmpty(truckForm.getNenryoscMn()).replaceAll(Constants.CONNECT_COMMA, "")));
			truckEntity.setSyasyuCd(truckForm.getSyasyuCd());
			truckEntity.setDaisu(truckForm.getDaisu());
			truckEntity.setOpList(truckForm.getOpList());
			truckIndex++;
			truckEntityList.add(truckEntity);
		}
		truckDao.insertCaseTruck(truckEntityList);

		for(ContactRRKEntity conEntity : insertConRRKEntityList) {
			contactRRKDao.insertContactRRK(conEntity);
		}

		for(AddressRRKEntity addEntity : insertAddRRKEntityList) {
			addressRRKDao.insertAddressRRK(addEntity);
		}

		return ankenOrderList;
	}


	@Autowired
	@Qualifier("syukaDao")
	private SyukaDao syukaDao;

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao ankenDao;

	@Autowired
	@Qualifier("nohinDao")
	private NohinDao nohinDao;

	@Autowired
	@Qualifier("truckDao")
	private TruckDao truckDao;

	@Autowired
	@Qualifier("ankenOrderDao")
	private AnkenOrderDao ankenOrderDao;

	@Autowired
	@Qualifier("truckOpDao")
	private TruckOpDao truckOpDao;

	@Autowired
	@Qualifier("memberDao")
	private MemberDao memberDao;

	@Autowired
	@Qualifier("contactRRKDao")
	private ContactRRKDao contactRRKDao;

	@Autowired
	@Qualifier("addressRRKDao")
	private AddressRRKDao addressRRKDao;
}
