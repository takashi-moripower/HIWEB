var syukaIndex=nouhinIndex=truckIndex = 0;

$(function() {
	syukaIndex = $('[id=syuka-panel]').length-1;
	nouhinIndex=$('[id=nouhin-panel').length-1;
	truckIndex=$('[id=truck-panel').length-1;
});

var maxSyukaNouhin = Constants.MAX_SYUKA_NOUHIN;

var maxTruck = Constants.MAX_TRUCK;


$(document).on('click', 'a[id^=add-syuka]', function() {
	if(truckIndex >= 1) {
		alert(Constants.MORE_THAN_ONE_TRUCK);
		return false;
	}
	if(syukaIndex == maxSyukaNouhin-1) {
		alert(Constants.MORE_THAN_THREE_SYUKA_NOUHIN);
		return false;
	}

	if(confirm(Constants.ADD_SYUKA)) {
		var $obj = $(this).parents("div[id^=syuka-panel]").clone(false);

		$obj.find('.input-group.date').bind("click", showCalendar);
		$obj.find('input[type=tel].time').bind("change", timeChange);
		$obj.find('input[type=tel].tel').bind("change", telChange);
		$obj.find('input[type=tel].number').bind("change", numberChange);
		$obj.find('input[type=tel].money').bind("change", moneyChange);
		$obj.find('input[type=tel].postCode').bind("change", postCodeChange);


		$obj.find("input").each(function(){
			$(this).val("");
		});

		$obj.find("select option:selected").each(function(){
			$(this).attr("selected", false);
		});

		$obj.insertAfter("div[id^=syuka-panel]:last");

		syukaIndex++;
		$('html,body').animate({
			scrollTop : $obj.offset().top
		}, 500);
	}
});

$(document).on('click', 'a[id^=delete-syuka]', function() {
	if(syukaIndex == 0) {
		alert(Constants.LESS_THAN_ONE);
		return false;
	}
	if(confirm(Constants.DELETE_SYUKA)) {
		$(this).parents("div[id^=syuka-panel]").remove();
		syukaIndex--;
	}
});

$(document).on('click', 'a[id^=add-nouhin]', function() {
	if(truckIndex >= 1) {
		alert(Constants.MORE_THAN_ONE_TRUCK);
		return false;
	}
	if(nouhinIndex == maxSyukaNouhin-1) {
		alert(Constants.MORE_THAN_THREE_SYUKA_NOUHIN);
		return false;
	}

	if(confirm(Constants.ADD_NOUHIN)) {
		var $obj = $(this).parents("div[id^=nouhin-panel]").clone(false);

		$obj.find('.input-group.date').bind("click", showCalendar);
		$obj.find('input[type=tel].time').bind("change", timeChange);
		$obj.find('input[type=tel].tel').bind("change", telChange);
		$obj.find('input[type=tel].number').bind("change", numberChange);
		$obj.find('input[type=tel].money').bind("change", moneyChange);
		$obj.find('input[type=tel].postCode').bind("change", postCodeChange);

		$obj.find("input").each(function(){
			$(this).val("");
		});

		$obj.find("select option:selected").each(function(){
			$(this).attr("selected", false);
		});

		$obj.insertAfter("div[id^=nouhin-panel]:last");

		nouhinIndex++;
		$('html,body').animate({
			scrollTop : $obj.offset().top
		}, 500);
	}
});

$(document).on('click', 'a[id^=delete-nouhin]', function() {
	if(nouhinIndex == 0) {
		alert(Constants.LESS_THAN_ONE);
		return false;
	}
	if(confirm(Constants.DELETE_NOUHIN)) {
		$(this).parents("div[id^=nouhin-panel]").remove();
		nouhinIndex--;
	}
});

$(document).on('click', 'a[id^=add-truck]', function() {
	if(syukaIndex > 0 || nouhinIndex > 0) {
		alert(Constants.MORE_THAN_ONE_SYUKA_NOUHIN);
		return false;
	}
	if(truckIndex == maxTruck-1) {
		alert(Constants.MORE_THAN_THREE_TRUCK);
		return false;
	}
	if(confirm(Constants.ADD_TRUCK)) {
		var $obj = $(this).parents("div[id^=truck-panel]").clone(true);
		$obj.insertAfter("div[id^=truck-panel]:last");

		truckIndex++;

		$('html,body').animate({
			scrollTop : $obj.offset().top
		}, 500);
	}
});

$(document).on('click', 'a[id^=delete-truck]', function() {
	if(truckIndex == 0) {
		alert(Constants.LESS_THAN_ONE);
		return false;
	}
	if(confirm(Constants.DELETE_TRUCK)) {
		$(this).parents("div[id^=truck-panel]").remove();

		truckIndex--;
	}
});

/*$(document).on('click', 'a.btn[data-toggle="modal"]', function() {

	var target = this;
	var $syukaObj = $(target).parents("div[id^=syuka-panel]");
	var $nouhinObj = $(target).parents("div[id^=nouhin-panel]");

	var targetObj = "";;
	var modelId = "";
	if($syukaObj[0] != undefined) {
		targetObj = $syukaObj[0];
		modelId = target.attributes["data-target"].value;
	}
	else if($nouhinObj[0] != undefined){
		targetObj = $nouhinObj[0];
		modelId = target.attributes["data-target"].value;
	}

	if(modelId == Constants.PICKUP_ADDR_HISTORY || modelId == Constants.RECIPIENT_ADDR_HISTORY) {
		showSelectedItem(modelId, Constants.SHOW_ARR_ELEM_ID, $(targetObj));
	}
	if(modelId == Constants.PICKUP_CONTR_HISTORY || modelId == Constants.RECIPIENT_CONTR_HISTORY) {
		showSelectedItem(modelId, Constants.SHOW_CONTRA_ELEM_ID, $(targetObj));
	}


});*/

function showCalendar(event) {
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



function timeChange() {
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
}

function telChange() {
	var target = this;
	var telVal = $(target).val().replace(/\-/g, "");

	if(isNaN(telVal)){
		alert(Constants.TEL_FORMAT_TYPE);
		$(target).val("");
		$(target).focus();
		return false;
	}
}

function numberChange() {
	var target = this;
	var numberVal = $(target).val().replace(/\,/g, "");

	if(isNaN(numberVal)){
		alert(Constants.NUMBER_FORMAT_TYPE);
		$(target).val("");
		$(target).focus();
		return false;
	}
}

function moneyChange() {
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
}

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

function postCodeChange() {
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
}

function showSelectedItem(presentId, showElemIdList, that) {

	$('.close[data-dismiss="modal"]').click(
			function() {
				$("#"+ presentId).modal('hide');
				$("#"+ presentId + " tbody tr").off("click");
				$("#"+ presentId).remove();

	});

	$("#"+ presentId + " tbody tr").click(
			function(event) {

				var target = this;
				$.each(showElemIdList, function(index, showElemId) {
					var $inputText = that.find(".panel-body").find(
							"input[id=" + showElemId + "]");
					var $selectText = that.find(".panel-body").find(
							"select[id=" + showElemId + "]");
					var selectItem = $(target).find("#" + showElemId).text();
					if ($inputText[0] != undefined) {
						$inputText.val(selectItem);
					}
					if ($selectText[0] != undefined) {
						$selectText.find("[value='" + selectItem + "']").attr(
								"selected", true);
					}
				});

				$("#"+ presentId).modal('hide');
				$("#"+ presentId + " tbody tr").off("click");
				$("#"+ presentId).remove();

				event.stopPropagation();
				event.preventDefault();
			});

	$(".glyphicon.glyphicon-trash").click(
			function(event) {
				var self = this;
				var outerDiv = $(self).parents("[role='dialog']");
				var outerId = outerDiv.attr("id");

				var rrkId = $(self).parents("tr").find("[id='id']").text();

				if(outerId.indexOf("addr_history") > 0) {
					if(confirm('住所を削除します。よろしいですか？')) {
						//
						$.ajax( {
						    url:'deleteAddrById',
						    data:{
						             'rrkId' : rrkId
						    },
						    type:'post',
						    dataType:'json',
						    success:function(data) {
						        if(data == 1){
						            alert("住所履歴を削除しました。");
						        } else {
						            alert("住所履歴の削除が失敗しました。");
						        }
						     }
						});
					}
				}

				if(outerId.indexOf("contr_history") > 0) {
					if(confirm('担当者を削除します。よろしいですか？')) {
						//
						$.ajax( {
						    url:'deleteContactById',
						    data:{
						             'rrkId' : rrkId
						    },
						    type:'post',
						    dataType:'json',
						    success:function(data) {
						        if(data == 1 ){
						            alert("担当者履歴を削除しました。");
						        } else {
						            alert("担当者履歴の削除が失敗しました。");
						        }
						     }
						});
					}
				}

				outerDiv.modal('hide');
				outerDiv.remove();

				event.stopPropagation();
				event.preventDefault();
			}
		);
}


/*function createSelectList(firstnull, data, id, showElemIdList) {
	$.each(showElemIdList, function(index, key) {

		var optionValue = "";
		if(!firstnull) {
			optionValue = optionValue +  '<option value=""></option>';
		}

		$.each(data, function(index, element){
			if (element[key] != undefined) {
				optionValue = optionValue + '<option value="'+element[key]+'">'+element[key]+'</option>';
			} else {
				optionValue = optionValue + '<option value=""></option>';
			}
		});
		$("select[id=" + id + "]").append(optionValue);
	});
}*/


function createComponent(title, header, itemsList, presentId, showElemIdList, target, hiddenId) {

	var panelObj = $(target).parents("div.panel");

	var targetObj = panelObj[0];


	var selectLayout = '<div id="' + presentId
			+ '" class="modal" role="dialog">';
	selectLayout = selectLayout
			+ '<div class="modal-dialog" style="width: 600px;">';
	selectLayout = selectLayout
			+ '<div class="modal-content" style="height: 550px">';
	selectLayout = selectLayout + '<div class="modal-header">';
	selectLayout = selectLayout
			+ '<button type="button" class="close" data-dismiss="modal">&times;</button>';
	selectLayout = selectLayout + '<h4 class="modal-title">' + title
			+ '</h4></div>';
	selectLayout = selectLayout + '<div class="modal-body"';
	selectLayout = selectLayout + 'style="height: 480px; overflow-y: auto;">';
	selectLayout = selectLayout
			+ '<table class="table table-hover" style="font-size: 14px;">';
	selectLayout = selectLayout + '<thead><tr>';

	for ( var headerKey in header) {
		selectLayout = selectLayout + '<td>' + header[headerKey] + '</td>';
	}

	selectLayout = selectLayout + '</tr></thead><tbody>';

	$.each(itemsList, function(index, element) {
		selectLayout = selectLayout + '<tr class="selectItem">';
		for ( var key in header) {
			if (key == "deleteFlg") {
				selectLayout = selectLayout
						+ '<td><span class="glyphicon glyphicon-trash"></span></td>';
			} else {
				if (element[key] != undefined) {
					selectLayout = selectLayout + '<td id='
							+ key + '>' + element[key]
							+ '</td>';
				} else {
					selectLayout = selectLayout + '<td> </td>';
				}
			}
		}
		selectLayout = selectLayout + '<td id='+ hiddenId + ' style="visibility: hidden;">' + element[hiddenId]+ '</td>';
		selectLayout = selectLayout + '</tr>';
	});

	selectLayout = selectLayout + '</tbody></table></div></div></div></div>';

	$(target).parent().after(selectLayout);
	//$("[data-target=#" + presentId + "]").parent().after(selectLayout);
	$("#"+presentId).modal('show');

	showSelectedItem(presentId, showElemIdList, $(targetObj));
}


/**
 * create component and bind it into page
 *
 * @param itemUrl
 * @param filter
 * @param title
 * @param header
 * @param id
 * @param showElemIdList
 */
function getItems(itemUrl, filter, title, header, id, showElemIdList, etarget, hiddenId) {

	$.ajax({

		type : "post",
		url : itemUrl,
		data : filter,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",

		success : function(data) {
			createComponent(title, header, data, id, showElemIdList, $(etarget), hiddenId);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}
