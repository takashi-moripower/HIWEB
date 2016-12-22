package logistics.system.project.utility;

import java.util.List;

import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;

import org.springframework.web.context.WebApplicationContext;

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

	public static List<NisyuEntity> MAST_NISYU_LIST = null;
	public static List<NisugateEntity> MAST_NISUGATE_LIST = null;
	public static List<PrefEntity> MAST_PREF_LIST = null;
	public static List<SyasyuEntity> MAST_SYASYU_LIST = null;
	public static List<TruckOpEntity> MAST_TRUCKOP_LIST = null;

	public static final String CONNECT_COMMA = ",";

	public static final String FTL_PACKAGE = "/logistics/system/project/flt";

	public static final String FTL_MAIL_FILE_NAME = "mail.ftl";

	// メール送信回数
	public static final int MAX_MAIL_SEND_KS = 3;

	// 0:送信待ち
	public static final String MAIL_SEND_SATUS_0 = "0";
	// 1:送信済み
	public static final String MAIL_SEND_SATUS_1 = "1";
	// 9:送信エラー
	public static final String MAIL_SEND_SATUS_9 = "9";

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

}
