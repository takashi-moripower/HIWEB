package logistics.system.project.common.Entity;

import logistics.system.project.utility.annotation.Length;
import logistics.system.project.utility.annotation.Patterns;

public class AnkenDetailEntity {
	// 案件情報ＩＤ
	private String ankenId;
	// 案件番号
	private String ankenNo;
	// 受注期限
	private String jutuKigen;
	// 案件ステータス
	private String status;
	// 案件ステータス名称
	private String statusNm;
	// 荷主（コード）
	private String ninushiCd;
	// 荷主名称
	private String ninushiMeisho;
	// 荷主住所
	private String ninushiJusho;
	// 荷主住所
	private String unsoCd;
	// 運送名称
	private String unsoMeisho;
	// 運送住所
	private String unsoJusho;
	// 保険金額
	private String hokenKG;
	// 注意事項
	private String tyuiJK;
	// 画像情報１
	private String picName1;
	// 画像情報２
	private String picName2;
	// 画像情報３
	private String picName3;
	// 画像情報１
	private String picTmpNm1;
	// 画像情報２
	private String picTmpNm2;
	// 画像情報３
	private String picTmpNm3;
	// 車番会社名
	private String syabanKsNm;
	// 車両番号
	private String syaryoNo;
	// 運転者名
	private String untensyaName;
	// 連絡先電話
//	@Patterns(regexp = "^$|^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message="{tel.format.incorrect}", field="連絡先電話")
	@Patterns(regexp = "^$|^[0-9-]*$", message="{tel.format.incorrect}", field="連絡先電話")
	@Length(field = "連絡先電話", max = "20", types={Length.Type.MINUS_TYPE})
	private String renrakuTel;
	// 緊急連絡先
	private String kinkyuCorp;
	// 緊急連絡先担当者
	private String kinkyuTant;
	// 緊急連絡先電話
//	@Patterns(regexp = "^$|^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message="{tel.format.incorrect}", field="緊急連絡先電話")
	@Patterns(regexp = "^$|^[0-9-]*$", message="{tel.format.incorrect}", field="緊急連絡先電話")
	@Length(field = "緊急連絡先電話", max = "20", types={Length.Type.MINUS_TYPE})
	private String kinkyuTel;
	// 請求先荷主コード
	private String seikyuKsCd;
	// 支払先会社コード
	private String shihraiKsCd;
	// 登録者ＩＤ
	private String createId;
	// 登録者日時
	private String createDt;
	// 更新者ＩＤ
	private String updateId;
	// 更新者日時
	private String updateDt;
	
	// 受注登録者ＩＤ
	private String ocreateId;
	// 受注登録者日時
	private String ocreateDt;
	// 受注日時
	private String orderDt;
	// トラックNO
	private String truckNo;
	// 高速使用料金
	private String kosokuMn;

	public String getAnkenId() {
		return ankenId;
	}

	public void setAnkenId(String ankenId) {
		this.ankenId = ankenId;
	}

	public String getAnkenNo() {
		return ankenNo;
	}

	public void setAnkenNo(String ankenNo) {
		this.ankenNo = ankenNo;
	}

	public String getJutuKigen() {
		return jutuKigen;
	}

	public void setJutuKigen(String jutuKigen) {
		this.jutuKigen = jutuKigen;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusNm() {
		return statusNm;
	}

	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}

	public String getNinushiCd() {
		return ninushiCd;
	}

	public void setNinushiCd(String ninushiCd) {
		this.ninushiCd = ninushiCd;
	}

	public String getNinushiMeisho() {
		return ninushiMeisho;
	}

	public void setNinushiMeisho(String ninushiMeisho) {
		this.ninushiMeisho = ninushiMeisho;
	}

	public String getNinushiJusho() {
		return ninushiJusho;
	}

	public void setNinushiJusho(String ninushiJusho) {
		this.ninushiJusho = ninushiJusho;
	}

	public String getUnsoCd() {
		return unsoCd;
	}

	public void setUnsoCd(String unsoCd) {
		this.unsoCd = unsoCd;
	}

	public String getUnsoMeisho() {
		return unsoMeisho;
	}

	public void setUnsoMeisho(String unsoMeisho) {
		this.unsoMeisho = unsoMeisho;
	}

	public String getUnsoJusho() {
		return unsoJusho;
	}

	public void setUnsoJusho(String unsoJusho) {
		this.unsoJusho = unsoJusho;
	}

	public String getHokenKG() {
		return hokenKG;
	}

	public void setHokenKG(String hokenKG) {
		this.hokenKG = hokenKG;
	}

	public String getTyuiJK() {
		return tyuiJK;
	}

	public void setTyuiJK(String tyuiJK) {
		this.tyuiJK = tyuiJK;
	}

	public String getPicName1() {
		return picName1;
	}

	public void setPicName1(String picName1) {
		this.picName1 = picName1;
	}

	public String getPicName2() {
		return picName2;
	}

	public void setPicName2(String picName2) {
		this.picName2 = picName2;
	}

	public String getPicName3() {
		return picName3;
	}

	public void setPicName3(String picName3) {
		this.picName3 = picName3;
	}

	public String getSyaryoNo() {
		return syaryoNo;
	}

	public void setSyaryoNo(String syaryoNo) {
		this.syaryoNo = syaryoNo;
	}

	public String getSyabanKsNm() {
		return syabanKsNm;
	}

	public void setSyabanKsNm(String syabanKsNm) {
		this.syabanKsNm = syabanKsNm;
	}

	public String getUntensyaName() {
		return untensyaName;
	}

	public void setUntensyaName(String untensyaName) {
		this.untensyaName = untensyaName;
	}

	public String getRenrakuTel() {
		return renrakuTel;
	}

	public void setRenrakuTel(String renrakuTel) {
		this.renrakuTel = renrakuTel;
	}

	public String getKinkyuCorp() {
		return kinkyuCorp;
	}

	public void setKinkyuCorp(String kinkyuCorp) {
		this.kinkyuCorp = kinkyuCorp;
	}

	public String getKinkyuTant() {
		return kinkyuTant;
	}

	public void setKinkyuTant(String kinkyuTant) {
		this.kinkyuTant = kinkyuTant;
	}

	public String getKinkyuTel() {
		return kinkyuTel;
	}

	public void setKinkyuTel(String kinkyuTel) {
		this.kinkyuTel = kinkyuTel;
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

	public String getTruckNo() {
		return truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public String getKosokuMn() {
		return kosokuMn;
	}

	public void setKosokuMn(String kosokuMn) {
		this.kosokuMn = kosokuMn;
	}

	public String getOcreateId() {
		return ocreateId;
	}

	public void setOcreateId(String ocreateId) {
		this.ocreateId = ocreateId;
	}

	public String getOcreateDt() {
		return ocreateDt;
	}

	public void setOcreateDt(String ocreateDt) {
		this.ocreateDt = ocreateDt;
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

	public String getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(String orderDt) {
		this.orderDt = orderDt;
	}

}
