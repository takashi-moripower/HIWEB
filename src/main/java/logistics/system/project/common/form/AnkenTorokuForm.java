package logistics.system.project.common.form;

import java.util.List;

import javax.validation.Valid;

import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.ninushi.form.NohinForm;
import logistics.system.project.ninushi.form.SyukaForm;
import logistics.system.project.ninushi.form.TruckForm;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.WebUtils;
import logistics.system.project.utility.annotation.Date;
import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.NotEmpty;

public class AnkenTorokuForm {

	public String getAnkenId() {
		return ankenId;
	}

	public void setAnkenId(String ankenId) {
		this.ankenId = ankenId;
	}

	public String getSeikyuKsCd() {
		return seikyuKsCd;
	}

	public void setSeikyuKsCd(String seikyuKsCd) {
		this.seikyuKsCd = seikyuKsCd;
	}

	public String getShihraiKsCd() {
		return shihraiKsCd;
	}

	public void setShihraiKsCd(String shihraiKsCd) {
		this.shihraiKsCd = shihraiKsCd;
	}

	public String getHokenMn() {
		return hokenMn;
	}

	public void setHokenMn(String hokenMn) {
		this.hokenMn = hokenMn;
	}

	public String getTyuiJk() {
		return tyuiJk;
	}

	public void setTyuiJk(String tyuiJk) {
		this.tyuiJk = tyuiJk;
	}

	public String getPicNm1() {
		return picNm1;
	}

	public void setPicNm1(String picNm1) {
		this.picNm1 = picNm1;
	}

	public String getPicNm2() {
		return picNm2;
	}

	public void setPicNm2(String picNm2) {
		this.picNm2 = picNm2;
	}

	public String getPicNm3() {
		return picNm3;
	}

	public void setPicNm3(String picNm3) {
		this.picNm3 = picNm3;
	}

	public String getAnkenNo() {
		return ankenNo;
	}

	public void setAnkenNo(String ankenNo) {
		this.ankenNo = ankenNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getAnkenStatus() {
		return ankenStatus;
	}

	public void setAnkenStatus(String ankenStatus) {
		this.ankenStatus = ankenStatus;
	}

	public String getAnkenStatusNm() {
		return ankenStatusNm;
	}

	public void setAnkenStatusNm(String ankenStatusNm) {
		this.ankenStatusNm = ankenStatusNm;
	}

	public List<SyukaForm> getSyukaList() {
		return syukaList;
	}

	public void setSyukaList(List<SyukaForm> syukaList) {
		this.syukaList = syukaList;
	}

	public List<NohinForm> getNohinList() {
		return nohinList;
	}

	public void setNohinList(List<NohinForm> nohinList) {
		this.nohinList = nohinList;
	}

	public List<TruckForm> getTruckList() {
		return truckList;
	}

	public void setTruckList(List<TruckForm> truckList) {
		this.truckList = truckList;
	}

	public SyukaForm getSyukaForm() {
		return syukaForm;
	}

	public void setSyukaForm(SyukaForm syukaForm) {
		this.syukaForm = syukaForm;
	}

	public NohinForm getNohinForm() {
		return nohinForm;
	}

	public void setNohinForm(NohinForm nohinForm) {
		this.nohinForm = nohinForm;
	}

	public TruckForm getTruckForm() {
		return truckForm;
	}

	public void setTruckForm(TruckForm truckForm) {
		this.truckForm = truckForm;
	}

//	public List<AddressRRKEntity> getAddressRRKList() {
//		return addressRRKList;
//	}
//
//	public void setAddressRRKList(List<AddressRRKEntity> addressRRKList) {
//		this.addressRRKList = addressRRKList;
//	}

	public List<PrefEntity> getPrefList() {
		return prefList;
	}

	public void setPrefList(List<PrefEntity> prefList) {
		this.prefList = prefList;
	}

	public List<NisyuEntity> getNisyuList() {
		return nisyuList;
	}

	public void setNisyuList(List<NisyuEntity> nisyuList) {
		this.nisyuList = nisyuList;
	}

	public List<NisugateEntity> getNisugateList() {
		return nisugateList;
	}

	public void setNisugateList(List<NisugateEntity> nisugateList) {
		this.nisugateList = nisugateList;
	}

//	public List<ContactRRKEntity> getContactRRKList() {
//		return contactRRKList;
//	}
//
//	public void setContactRRKList(List<ContactRRKEntity> contactRRKList) {
//		this.contactRRKList = contactRRKList;
//	}

	public List<SyasyuEntity> getSyasyuList() {
		return syasyuList;
	}

	public void setSyasyuList(List<SyasyuEntity> syasyuList) {
		this.syasyuList = syasyuList;
	}

	public List<TruckOpEntity> getTruckOpList() {
		return truckOpList;
	}

	public void setTruckOpList(List<TruckOpEntity> truckOpList) {
		this.truckOpList = truckOpList;
	}

	public String getSeikyuKsNm() {
		return seikyuKsNm;
	}

	public void setSeikyuKsNm(String seikyuKsNm) {
		this.seikyuKsNm = seikyuKsNm;
	}

	public String getSeikyuKsAddr() {
		return seikyuKsAddr;
	}

	public void setSeikyuKsAddr(String seikyuKsAddr) {
		this.seikyuKsAddr = seikyuKsAddr;
	}

	public String getShihraiKsNm() {
		return shihraiKsNm;
	}

	public void setShihraiKsNm(String shihraiKsNm) {
		this.shihraiKsNm = shihraiKsNm;
	}

	public String getShihraiKsAddr() {
		return shihraiKsAddr;
	}

	public void setShihraiKsAddr(String shihraiKsAddr) {
		this.shihraiKsAddr = shihraiKsAddr;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}

	public String getPicChg1() {
		return picChg1;
	}

	public void setPicChg1(String picChg1) {
		this.picChg1 = picChg1;
	}

	public String getPicChg2() {
		return picChg2;
	}

	public void setPicChg2(String picChg2) {
		this.picChg2 = picChg2;
	}

	public String getPicChg3() {
		return picChg3;
	}

	public void setPicChg3(String picChg3) {
		this.picChg3 = picChg3;
	}


	public String getPicTmpNm1() {
		return picTmpNm1;
	}

	public void setPicTmpNm1(String picTmpNm1) {
		this.picTmpNm1 = picTmpNm1;
	}

	public String getPicTmpNm2() {
		return picTmpNm2;
	}

	public void setPicTmpNm2(String picTmpNm2) {
		this.picTmpNm2 = picTmpNm2;
	}

	public String getPicTmpNm3() {
		return picTmpNm3;
	}

	public void setPicTmpNm3(String picTmpNm3) {
		this.picTmpNm3 = picTmpNm3;
	}

	// 案件番号
	private String ankenNo;

	// 受注期限
	@NotEmpty(field = "受注期限", message = "{field.not.empty}")
	@Date(field = "受注期限", param="jp", message = "{data.format.incorrect}")
	@Length(field = "受注期限", max = "8", types={Length.Type.DATE_TYPE})
//	@SingleByte(field = "受注期限")
	private String orderDate;

	// 案件ステータス
	private String ankenStatus;
	
	// 案件ステータス名
	private String ankenStatusNm;

	// 積地（集荷）List
	@Valid
	private List<SyukaForm> syukaList;

	// 納品（届先）List
	@Valid
	private List<NohinForm> nohinList;

	// トラック List
	@Valid
	private List<TruckForm> truckList;

	// 積地（集荷）
	private SyukaForm syukaForm;

	// 納品（届先）
	private NohinForm nohinForm;

	// トラック
	private TruckForm truckForm;

	// 集荷先 or 納品先
//	private List<AddressRRKEntity> addressRRKList;

	// 都道府県
	private List<PrefEntity> prefList;

	// 荷種
	private List<NisyuEntity> nisyuList;

	// 荷姿
	private List<NisugateEntity> nisugateList;
	
	// 車種
	private List<SyasyuEntity> syasyuList;
	
	// オプション
	private List<TruckOpEntity> truckOpList;

	// 担当者名
//	private List<ContactRRKEntity> contactRRKList;
	
	// 保険金額
	@Length(field = "", max = "10", types={Length.Type.MONEY_TYPE})
	private String hokenMn;
	
	// 注意事項
	@Length(field = "注意事項", max = "200")
	private String tyuiJk;
	
	// 画像ファイル名1
	private String picNm1; 
	
	// 画像ファイル名2
	private String picNm2;
	
	// 画像ファイル名3
	private String picNm3;
	
	// 画像ファイル
	private String picChg1; 
	
	// 画像ファイル
	private String picChg2;
	
	// 画像ファイル
	private String picChg3;
	
	// 画像ファイル名1
	private String picTmpNm1 = Constants.DEFAULT_PIC_NAME; 
	
	// 画像ファイル名2
	private String picTmpNm2 = Constants.DEFAULT_PIC_NAME; 
	
	// 画像ファイル名3
	private String picTmpNm3 = Constants.DEFAULT_PIC_NAME; 
	
	// 請求先荷主コード
	private String seikyuKsCd;
	
	// 支払先会社コード
	private String shihraiKsCd;
	
	// 案件番号
	private String ankenId;
	
	// 請求先荷主名称
	private String seikyuKsNm;
	
	// 請求先荷主住所
	private String seikyuKsAddr;
	
	// 支払先会社
	private String shihraiKsNm;
	
	// 支払先会社住所
	private String shihraiKsAddr;
	
	// 登録者ＩＤ
	private String createId;
	
	// 登録者日時
	private String createDt;
	
	// 更新者ID
	private String updateId;
	
	// 更新者日時
	private String updateDt;
	
}
