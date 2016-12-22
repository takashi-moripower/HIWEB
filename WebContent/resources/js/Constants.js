var Constants = (function() {

	var Temp = {};

	Temp.ADDR_TITLE = {
		"addrNm" : "住所名称",
		"postCode" : "郵便番号",
		"prefNm" : "都道府県",
		"cityNm" : "市区町村",
		"addrOther" : "番地等",
		"deleteFlg" : "削除"
	};

	Temp.PICKUP_ADDR_HISTORY = "#pickup_addr_history";

	Temp.PICKUP_CONTR_HISTORY = "#pickup_contr_history";

	Temp.RECIPIENT_ADDR_HISTORY = "#recipient_addr_history";

	Temp.RECIPIENT_CONTR_HISTORY = "#recipient_contr_history";

	Temp.SHOW_ARR_ELEM_ID = [ "addrNm", "postCode", "prefNm", "cityNm",
			"addrOther" ];

	Temp.CONTRA_TITLE = {
			"companyNm" : "会社名",
			"tantoNm" : "担当者名",
			"tantoTel" : "担当者電話番号",
			"deleteFlg" : "削除"
		};

	Temp.SHOW_CONTRA_ELEM_ID = [ "tantoNm", "tantoTel"];

	Temp.SHOW_SYABAN_CONTRA_ELEM_ID = ["companyNm", "tantoNm", "tantoTel"];

	Temp.ADDRESS_KBN_01 = {
			"addressKbn" : "01"
	};

	Temp.ADDRESS_KBN_02 = {
			"addressKbn" : "02"
	};

	Temp.ADDRESS_KBN_03 = {
			"addressKbn" : "03"
	};

	Temp.AJAX_ADDRESS_URL = "common/addressrrk";

	Temp.AJAX_CONTACT_URL = "common/contactrrk";

	Temp.AJAX_PREFLIST_URL = "common/prefList";

	Temp.AJAX_NISUGATELIST_URL = "common/nisugateList";

	Temp.AJAX_NISYULIST_URL = "common/nisyuList";

	Temp.OPTION_FIRST_NULL = true;

	Temp.RIREKI_TITLE = "履歴から選択";

	Temp.ADD_SYUKA = "集荷先を追加します。よろしいでしょうか？";

	Temp.DELETE_SYUKA = "集荷先を削除します。よろしいでしょうか？";

	Temp.ADD_NOUHIN = "納品先を追加します。よろしいでしょうか？";

	Temp.DELETE_NOUHIN = "納品先を削除します。よろしいでしょうか？";

	Temp.MORE_THAN_THREE_SYUKA_NOUHIN = "集荷先と納品先は三か所まで指定できます。";

	Temp.MORE_THAN_ONE_TRUCK = "複数種類のトラックが存在する場合、集荷先または納品先を追加することはできません。";

	Temp.MORE_THAN_ONE_SYUKA_NOUHIN = "複数の集荷先または納品先が有る場合、トラック種類を追加することはできません";

	Temp.LESS_THAN_ONE = "集荷先、納品先とトラックは最低一つ指定しなければいけません。";

	Temp.ADD_TRUCK = "トラックの種類を追加します。よろしいでしょうか？";

	Temp.DELETE_TRUCK = "トラックを削除します。よろしいでしょうか？";

	Temp.MORE_THAN_THREE_TRUCK = "トラックは三種類まで指定できます。";

	Temp.TIME_FORMAT_LENGTH = "入力型式が正しく有りません（hhmm又はhh:mm）";

	Temp.TIME_FORMAT_TYPE = "数値を入力してください。";

	Temp.MONEY_FORMAT_TYPE = "数値を入力してください。";

	Temp.NUMBER_FORMAT_TYPE = "数値を入力してください。";

	Temp.POST_CODE_FORMAT_TYPE = "数値を入力してください。";

	Temp.TEL_FORMAT_TYPE = "半角数字と-で入力してください。";

	Temp.EMAIL_FORMAT_TYPE = "E-mailの形式が正しくありません。";

	Temp.POST_CODE_FORMAT_LENGTH = "郵便番号を入力してください。";

	Temp.MAX_SYUKA_NOUHIN = 3;

	Temp.MAX_TRUCK = 3;

	Temp.GYOMU_SB_NINUSHI = "0";

	Temp.GYOMU_SB_UNSO = "1";

	Temp.GYOMU_SB_TRAIL = "9";

	return Temp;
})();