package logistics.system.project.utility;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import logistics.system.project.common.Entity.AreaEntity;
import logistics.system.project.common.Entity.CityEntity;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.service.AnkenListSearchService;
import logistics.system.project.common.service.ShukaAreaService;

public class Constants {


	public static final String TAB_TITLE = "tabtitle";

	public static final String PAGE_TITLE = "pageTitle";

	public static final String OWNER_NAME = "ownerName";

	public static final String SHOW_LOGOUT_FLAG = "showLogoutFlag";

	public static final String ERROR_MESSAGE = "errormessage";

	public static final String USER_NOT_EXIST = "ユーザIDまたはパスワードが正しくありません。";

	public static final String SHOW_ERROR_MESS_FLAG = "showErrorMessFlag";

	public static final String GYOMU_SB_NINUSHI = "0";

	public static final String GYOMU_SB_UNSO = "1";

	public static final String GYOMU_SB_TRAIL = "9";

	public static final String NINUSHI_INDEX_TABTITLE = "配送マッチング-ログイン画面";

	public static final String NINUSHI_INDEX_PAGETITLE = "配送マッチングシステム";

	public static final String NINUSHI_LOGIN_TABTITLE = "配送マッチング-荷主様用トップ画面";

	public static final String NINUSHI_LOGIN_PAGETITLE = "配送マッチングシステム（荷主様用）";

	public static final String NINUSHI_ANKEN_TOROKU_TABTITLE = "配送マッチング-案件情報登録";

	public static final String NINUSHI_ANKEN_TOROKU_PAGETITLE = "案件情報登録(入力)";

	public static final String NINUSHI_ANKEN_TOROKU_CONFIRM_TABTITLE = "配送マッチング-案件情報登録";

	public static final String NINUSHI_ANKEN_TOROKU_CONFIRM_PAGETITLE = "案件情報登録(確認)";

	public static final String NINUSHI_ANKEN_TOROKU_COMPLETE_TABTITLE = "配送マッチング-案件情報登録";

	public static final String NINUSHI_ANKEN_TOROKU_COMPLETE_PAGETITLE = "案件情報登録(完了)";

	public static final String UNSO_INDEX_TABTITLE = "配送マッチング-運送様用トップ画面";

	public static final String UNSO_INDEX_PAGETITLE = "配送マッチングシステム（運送様用）";

	public static final String UNSO_TODOHUEN_SENTAKU_TABTITLE = "配送マッチング-都道府県選択";

	public static final String UNSO_TODOHUEN_SENTAKU_PAGETITLE = "都道府県選択";

	public static final String UNSO_SIKUTYOSON_SENTAKU_TABTITLE = "配送マッチング-市区町村選択";

	public static final String UNSO_SIKUTYOSON_SENTAKU_PAGETITLE = "市区町村選択";

	public static final String MEMBER_TOROKU_TABTITLE = "配送マッチング-企業情報登録";

	public static final String MEMBER_TOROKU_PAGETITLE = "企業情報登録";

	public static final String ANKEN_SEARCH_TABTITLE = "配送マッチング-案件検索";

	public static final String ANKEN_SEARCH_PAGETITLE = "案件検索";

	public static final String ANKEN_DETAIL_TABTITLE = "配送マッチング-案件詳細";

	public static final String ANKEN_DETAIL_PAGETITLE = "案件詳細";

	public static final String ANKEN_LIST_TABTITLE = "配送マッチング-案件一覧";

	public static final String ANKEN_LIST_PAGETITLE = "案件一覧";

	public static final String TRAIL_INDEX_TABTITLE = "配送マッチング-管理者用トップ画面";

	public static final String TRAIL_INDEX_PAGETITLE = "配送マッチングシステム（管理者用）";

	public static final String TRAIL_ANKEN_TOROKU_TABTITLE = "配送マッチング-案件情報登録";

	public static final String TRAIL_ANKEN_TOROKU_PAGETITLE = "案件情報登録(入力)";

	public static final String TRAIL_ANKEN_SEARCH_TABTITLE = "配送マッチング-案件一覧";

	public static final String TRAIL_ANKEN_SEARCH_PAGETITLE = "詳細検索";

	public static final String TRAIL_ANKEN_LIST_TABTITLE = "配送マッチング-案件一覧";

	public static final String TRAIL_ANKEN_LIST_PAGETITLE = "案件一覧";

	public static final String TRAIL_SEIKYU_ANKEN_LIST_TABTITLE = "配送マッチング-請求管理";

	public static final String TRAIL_SEIKYU_ANKEN_LIST_PAGETITLE = "請求管理";

	public static final String TRAIL_MEMBER_TOROKU_TABTITLE = "配送マッチング-企業情報登録";

	public static final String TRAIL_MEMBER_TOROKU_PAGETITLE = "企業情報";

	public static final String TRAIL_MEMBER_SEARCH_TABTITLE = "配送マッチング-企業情報検索";

	public static final String TRAIL_MEMBER_SEARCH_PAGETITLE = "会員情報検索";

	public static final String TRAIL_MEMBER_PASSWORD_TABTITLE = "パスワード変更";

	public static final String TRAIL_MEMBER_PASSWORD_PAGETITLE = "パスワード変更";

	public static final String SEIKYU_KANRI_TABTITLE = "配送マッチング-請求管理";

	public static final String SEIKYU_KANRI_PAGETITLE = "請求管理";

	public static final String TUCHI_ADD_TITLE = "通知条件　新規作成";
	public static final String TUCHI_EDIT_TITLE = "通知条件　編集";
	public static final String TUCHI_LIST_TITLE = "通知条件　一覧";

	public static final String AREA_ALL = "全域";

	// 00:　案件削除
	public static final String ANKEN_STATUS_00 = "00";

	// 10:  登録済
	public static final String ANKEN_STATUS_10 = "10";

	// 20:　確定
	public static final String ANKEN_STATUS_20 = "20";

	// 30:　車番登録済み
	public static final String ANKEN_STATUS_30 = "30";

	// 40:　集荷完了
	public static final String ANKEN_STATUS_40 = "40";

	// 90:　キャンセル
	public static final String ANKEN_STATUS_90 = "90";

	public static final String CONNECT_DASH = "-";

	public static WebApplicationContext WEB_APP_CONTEXT = null;

	protected static List<NisyuEntity> MAST_NISYU_LIST = null;
	protected static List<NisugateEntity> MAST_NISUGATE_LIST = null;
	protected static List<PrefEntity> MAST_PREF_LIST = null;
	protected static List<CityEntity> MAST_CITY_LIST = null;
	protected static List<AreaEntity> MAST_AREA_LIST = null;

	protected static List<SyasyuEntity> MAST_SYASYU_LIST = null;
	protected static List<TruckOpEntity> MAST_TRUCKOP_LIST = null;

	public static final String CONNECT_COMMA = ",";

	public static final String FTL_PACKAGE = "/logistics/system/project/flt";

	public static final String FTL_MAIL_FILE_NAME = "mail.ftl";

	// メール送信回数
	public static final int MAX_MAIL_SEND_KS = 3;

	// 0:送信待ち
	public static final String MAIL_SEND_STATUS_WAITING = "0";
	// 1:送信済み
	public static final String MAIL_SEND_STATUS_SEND = "1";
	// 9:送信エラー
	public static final String MAIL_SEND_STATUS_ERROR = "9";

	public static final int MAIL_SEND_MODE_NOFILTER = 0;

	public static final int MAIL_SEND_MODE_FILTER = 1;

	public static final String TEPLATE_WAYBILL = "waybill.html";

	// 高速使用料区分 0:込み
	public static final String KOSOKU_KBN_0 = "0";
	// 高速使用料区分 1:別料金
	public static final String KOSOKU_KBN_1 = "1";

	// 燃料サーチャージ区分 0:込み
	public static final String NENRYOSC_KBN_0 = "0";
	// 燃料サーチャージ区分 1:別料金
	public static final String NENRYOSC_KBN_1 = "1";

	// 01: 集荷先　02:　納品先　03:　運送会社
	public static final String CONTACT_ADDRESS_KBN_01 = "01";
	// 01: 集荷先　02:　納品先　03:　運送会社
	public static final String CONTACT_ADDRESS_KBN_02 = "02";
	// 01: 集荷先　02:　納品先　03:　運送会社
	public static final String CONTACT_ADDRESS_KBN_03 = "03";

	public static final String DEFAULT_PIC_TMP_DIR_ROOT = "resources/tmp";

	public static final String DEFAULT_PIC_NAME = "noPicture.jpg";

//	public static final String TEPLATE_SEIKYU_ANKEN_LIST = "seikyuAnkenList.xml";
	public static final String TEPLATE_SEIKYU_ANKEN_LIST = "logistics/system/project/flt/SeikyuTemplate.xls";

	public static final int MAX_TUCHI_MAIL = 25;

	public static final int TUCHI_QUEUE_STATE_FILTERED = -1;
	public static final int TUCHI_QUEUE_STATE_UNSEND = 0;
	public static final int TUCHI_QUEUE_STATE_FAILED01 = 1;
	public static final int TUCHI_QUEUE_STATE_FAILED02 = 2;

	public static final String TUCHI_EMAIL_SUBJECT = "TRAIL配送マッチング案件通知メール";


	public static final String DATE_PATTERN_FORM = "yyyy/MM/dd";
	public static final SimpleDateFormat DATE_FORMAT_FORM = new SimpleDateFormat(DATE_PATTERN_FORM);


	public static String getPrefName( String prefCd ){
		if( prefCd == null || prefCd.equals("") || prefCd.equals("00")){
			return "全国";
		}

		for(PrefEntity item: getPrefList()){
			if(item.getPrefCd().equals(prefCd)){
				return item.getPrefName();
			}
		}

		return null;
	}

	public static String getCityName( String cityCd ){
		if( cityCd == null || cityCd.equals("") || cityCd.equals("00")){
			return null;
		}

		for( CityEntity item : getCityList()){
			if( item.getCityCd().equals(cityCd)){
				return item.getCityName();
			}
		}
		return null;
	}

	public static void initMasterList(ServletContextEvent event){
		Constants.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

	/**
	 *	各種マスターデータの取得、保持
	 * @param event
	 */
	public static List<NisyuEntity> getNisyuList(){
		if( MAST_NISYU_LIST == null ){
			initAreaData();
		}
		return MAST_NISYU_LIST;
	}

	public static List<NisugateEntity> getNisutagaeList(){
		if( MAST_NISUGATE_LIST == null ){
			initAreaData();
		}
		return MAST_NISUGATE_LIST;
	}

	public static List<AreaEntity> getAreaList(){
		if( MAST_AREA_LIST == null ){
			initAreaData();
		}
		return MAST_AREA_LIST;
	}

	public static List<PrefEntity> getPrefList(){
		if( MAST_PREF_LIST == null ){
			initAreaData();
		}
		return MAST_PREF_LIST;
	}

	public static List<CityEntity> getCityList(){
		if( MAST_CITY_LIST == null ){
			initAreaData();
		}
		return MAST_CITY_LIST;
	}

	protected static void initAreaData(){
		ShukaAreaService shukaAreaService = (ShukaAreaService) Constants.WEB_APP_CONTEXT.getBean("areaService");
		MAST_NISYU_LIST = shukaAreaService.getAllNisyuList();
		MAST_NISUGATE_LIST = shukaAreaService.getAllNisugateList();
		MAST_PREF_LIST = shukaAreaService.getAllPrefList();
		MAST_CITY_LIST = shukaAreaService.getAllCities();
		MAST_AREA_LIST = shukaAreaService.getAllArea();
	}

	public static List<SyasyuEntity> getSyasyuList(){
		if( MAST_SYASYU_LIST == null ){
			initAnkenOpData();
		}
		return MAST_SYASYU_LIST;
	}

	public static List<TruckOpEntity> getTruckOpList(){
		if( MAST_TRUCKOP_LIST == null ){
			initAnkenOpData();
		}
		return MAST_TRUCKOP_LIST;
	}

	protected static void initAnkenOpData(){
		AnkenListSearchService ankenListSearchService = (AnkenListSearchService) Constants.WEB_APP_CONTEXT.getBean("ankenListSearchService");

		MAST_SYASYU_LIST = ankenListSearchService.getSyasyuList();
		MAST_TRUCKOP_LIST = ankenListSearchService.getTruckOpList();
	}
}
