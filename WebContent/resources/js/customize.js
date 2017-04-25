$(function() {

	$('.input-group.date').click(
		function(event) {
			var target = this;
			$(target).datepicker({
				format : 'yyyy/mm/dd (D)',
				startDate : "2015/5/19",
				language : 'ja',
				orientation : 'top left',
				autoclose : true
			});
			event.stopPropagation();
			event.preventDefault();
		}
	);

	$(document).keydown(function(event){

		var keyCode = event.keyCode || event.which;
		var elem = event.srcElement || event.target;
		var type = elem.type || elem.getAttribute('type');

		//バックスペースキー制御
		if (keyCode == 8) {
			TARGET_TYPES = ["password" , "text" , "textarea" , "tel" , "email" ];
			if ( $.inArray( type , TARGET_TYPES )) {
				var vReadOnly = elem.getAttribute('readonly');
				var vEnabled = elem.getAttribute('enabled');
				vReadOnly = (vReadOnly == null) ? false : vReadOnly;
				vEnabled = (vEnabled == null) ? true : vEnabled;

				if (vReadOnly!=true && vEnabled==true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		if (keyCode == 116) {
			return false;
		}
	});


	document.oncontextmenu = function(e){
    	return false;
	};

	var startTime;

	$("a.btn, button").click(function (event) {
		if(startTime) {
			var interval = new Date().getTime() - startTime;
			if(interval < 1000) {
				return;
			}
			startTime = undefined;
		}
		else {
			startTime = new Date().getTime();
		}
	});

	$('label[required]').css('color', 'Red');

	$("#error_message").hide();

	$(document).on('click', '#btn_confirm', function() {
		if ($("#syuka_date").val() == "") {
			$("#error_message").show();
			$('body, html').animate({
				scrollTop : 0
			}, 200);
			return false;
		}
	});

	$('select[name=select_nenryo]').change(
		function() {
			var target = this;
			if ($(target).val() == 1) {
				$(target).parent().next().find("#nenryo_val").show();
			} else {
				$(target).parent().next().find("#nenryo_val").hide();
			}
		}
	);

	$("#nenryo_val").hide();
	if($('select[name=select_nenryo]').val()==1) {
		$("#nenryo_val").show();
	}
	if($('select[name=select_nenryo]').val()==0) {
		$("#nenryo_val").hide();
	}

	$("input[type=file]").change(function() {
		self = this;
		if($("img[id=picNm1]").attr("src") == "") {
			$("img[id=picNm1]").attr("src", self.value) ;
		}
	});

	$("input[type=tel].time").change(function() {
		var target = this;
		var timeVal = $(target).val().replace(/\:/g, "");

		if(isNaN(timeVal)){
			alert(Constants.TIME_FORMAT_TYPE);
			$(target).val("");
			return false;
		}

		if(timeVal.length!=4){
			alert(Constants.TIME_FORMAT_LENGTH);
			$(target).val("");
			return false;
		}

		var formatVal = timeVal.substr(0, 2) + ":" + timeVal.substr(2, 2);
		$(target).val(formatVal);
	});


	$("input[type=tel].email").change(function() {
		var target = this;
		var emailVal = $(target).val().replace(" ", "");

		if (emailVal.length ==0)
			return true;

		var rs = emailVal.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
		if(rs == null){
			alert(Constants.EMAIL_FORMAT_TYPE);
			$(target).val("");
			$(target).focus();
			return false;
		}
	});

	$("input[type=tel].tel").change(function() {
		var target = this;
		var telVal = $(target).val().replace(/\-/g, "");

		if(isNaN(telVal)){
			alert(Constants.TEL_FORMAT_TYPE);
			$(target).val("");
			$(target).focus();
			return false;
		}
	});

	$("input[type=tel].number").change(function() {
		var target = this;
		var numberVal = $(target).val().replace(/\,/g, "");

		if(isNaN(numberVal)){
			alert(Constants.NUMBER_FORMAT_TYPE);
			$(target).val("");
			$(target).focus();
			return false;
		}
	});

	$("input[type=tel].money").change(function() {
		var target = this;
		var moneyVal = $(target).val().replace(/\,/g, "");

		if(isNaN(moneyVal)){
			alert(Constants.MONEY_FORMAT_TYPE);
			$(target).val("");
			$(target).focus();
			return false;
		}

		var moneyFormat = formatMoney(moneyVal, 0);
		$(target).val(moneyFormat);
	});

	function formatMoney(s, type) {
	    if (/[^0-9\.]/.test(s))
	        return "0";
	    if (s == null || s == "")
	        return "0";
	    s = s.toString().replace(/^(\d*)$/, "$1.");
	    s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
	    s = s.replace(".", ",");
	    var re = /(\d)(\d{3},)/;
	    while (re.test(s))
	        s = s.replace(re, "$1,$2");
	    s = s.replace(/,(\d\d)$/, ".$1");
	    if (type == 0) {
	        var a = s.split(".");
	        if (a[1] == "00") {
	            s = a[0];
	        }
	    }
	    return s;
	}

	$("input[type=tel].postCode").change(function() {
		var target = this;
		var postCode = $(target).val().replace(/\-/g, "");

		if(isNaN(postCode)){
			alert(Constants.POST_CODE_FORMAT_TYPE);
			$(target).val("");
			$(target).focus();
			return false;
		}

		if(postCode.length!=7){
			alert(Constants.POST_CODE_FORMAT_LENGTH);
			$(target).val("");
			$(target).focus();
			return false;
		}

		var formatVal = postCode.substr(0, 3) + "-" + postCode.substr(3, 4);
		$(target).val(formatVal);
	});

});