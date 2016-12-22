/**function ankenTorokuAjaxSubmit(context, idList) {
	self = this;

	var ankenTorokuForm = {};

//	var syukaList = [];

	var nohinList = [];

	var truckList = [];

	ankenTorokuForm.caseNo = $("fieldset").find("#caseNoHidden").val();
	ankenTorokuForm.ankenId = $("fieldset").find("#ankenIdHidden").val();
	ankenTorokuForm.orderDate = $("fieldset").find("#orderDate").val();
	ankenTorokuForm.caseStatus = $("fieldset").find("#caseStatusHidden").val();
	
	ankenTorokuForm.hokenMn = $("fieldset").find("#hokenMn").val();
	ankenTorokuForm.tyuiJk = $("fieldset").find("#tyuiJk").val();
	ankenTorokuForm.picNm1 = $("fieldset").find("#picNm1").attr("src");
	ankenTorokuForm.picNm2 = $("fieldset").find("#picNm2").attr("src");
	ankenTorokuForm.picNm3 = $("fieldset").find("#picNm3").attr("src");
	

	$.each(idList, function(index, id) {

		if (id == 'syuka-panel') {
			$syukaObjs = $("div#" + id);

			$.each($syukaObjs, function(index, syuka) {
				var syukaForm = {};
				syukaForm.syukaDay = $(syuka).find("input[id=syuka_date]").val();
				syukaForm.syukaTime = $(syuka).find("input[id=syuka_time]").val();
				syukaForm.addrNm = $(syuka).find("input[id=addrNm]").val();
				syukaForm.postCode = $(syuka).find("input[id=postCode]").val();
				syukaForm.prefNm = $(syuka).find("select[id=prefNm]").find("option:selected").val();
				syukaForm.cityNm = $(syuka).find("input[id=cityNm]").val();
				syukaForm.addrOther = $(syuka).find("input[id=addrOther]").val();
				syukaForm.tantoNm = $(syuka).find("input[id=tantoNm]").val();
				syukaForm.tantoTel = $(syuka).find("input[id=tantoTel]").val();
				syukaForm.amount = $(syuka).find("input[name='syukaForm.amount']")
						.val();
				syukaForm.weight = $(syuka).find("input[id=weight]").val();
				syukaForm.remarks = $(syuka).find("textarea[id=remarks]").val();
				syukaForm.nisyuCd = $(syuka).find("select[id=nisyuCd]").find(
						"option:selected").val();
				syukaForm.nisyuNm = $(syuka).find("select[id=nisyuCd]").find(
						"option:selected").text();
				syukaForm.nisugataCd = $(syuka).find("select[id=nisugataCd]").find(
						"option:selected").val();
				syukaForm.nisugataNm = $(syuka).find("select[id=nisugataCd]").find(
						"option:selected").text();
				syukaList.push(syukaForm);
			});
		}
		if (id == 'nouhin-panel') {

			$nohinObjs = $("div#" + id);

			$.each($nohinObjs, function(index, nohin) {
				var nohinForm = {};
				nohinForm.nohinDay = $(nohin).find("input[id=nouhin_date]").val();
				nohinForm.nohinTime = $(nohin).find("input[id=nohin_time]").val();
				nohinForm.addrNm = $(nohin).find("input[id=addrNm]").val();
				nohinForm.postCode = $(nohin).find("input[id=postCode]").val();
				nohinForm.prefNm = $(nohin).find("select[id=prefNm]").find("option:selected").val();
				nohinForm.cityNm = $(nohin).find("input[id=cityNm]").val();
				nohinForm.addrOther = $(nohin).find("input[id=addrOther]").val();
				nohinForm.tantoNm = $(nohin).find("input[id=tantoNm]").val();
				nohinForm.tantoTel = $(nohin).find("input[id=tantoTel]").val();
				nohinForm.amount = $(nohin).find("input[name='nohinForm.amount']")
						.val();
				nohinForm.weight = $(nohin).find("input[id=weight]").val();
				nohinForm.remarks = $(nohin).find("textarea[id=remarks]").val();
				nohinForm.nisyuCd = $(nohin).find("select[id=nisyuCd]").find(
						"option:selected").val();
				nohinForm.nisyuNm = $(nohin).find("select[id=nisyuCd]").find(
						"option:selected").text();
				nohinForm.nisugataCd = $(nohin).find("select[id=nisugataCd]").find(
						"option:selected").val();
				nohinForm.nisugataNm = $(nohin).find("select[id=nisugataCd]").find(
						"option:selected").text();
				nohinList.push(nohinForm);
			});

		}
		if (id == 'truck-panel') {

			$truckObjs = $("div#" + id);

			$.each($truckObjs, function(index, truck) {
				var truckForm = {};
				truckForm.yosanMn = $(truck).find("input[id=yosanMn]").val();
				truckForm.kosokuKbn = $(truck).find("select[id=kosokuKbn]").find("option:selected").val();
				truckForm.kosokuKbnNm = $(truck).find("select[id=kosokuKbn]").find("option:selected").text();
				truckForm.nenryoscKbn = $(truck).find("select[id=nenryoscKbn]").find("option:selected").val();
				truckForm.nenryoscKbnNm = $(truck).find("select[id=nenryoscKbn]").find("option:selected").text();
				truckForm.nenryoscMn = $(truck).find("input[id=nenryoscMn]").val();
				truckForm.syasyuCd = $(truck).find("select[id=syasyuCd]").find("option:selected").val();
				truckForm.syasyuNm = $(truck).find("select[id=syasyuCd]").find("option:selected").text();
				truckForm.daisu = $(truck).find("select[id=daisu]").find("option:selected").text();
				var opTempList = $(truck).find("input[type='checkbox']");
				var opCd = "";
				var opNm = "";
				$.each(opTempList, function(index, op) {
					if($(op).is(':checked')) {
						opCd = opCd + $(op).val() + ",";
						opNm = opNm + $(op).parent("label").text().trim() + ",";
					}
				});
		
				truckForm.opList = opCd;
				truckForm.opNmList = opNm;
				truckForm.orderMn = $(truck).find("input[id=orderMn]").val();
				truckList.push(truckForm);
			});
		}

	});

	if (syukaList.length > 0) {
		ankenTorokuForm.syukaList = syukaList;
	}
	if (nohinList.length > 0) {
		ankenTorokuForm.nohinList = nohinList;
	}
	if (truckList.length > 0) {
		ankenTorokuForm.truckList = truckList;
	}

	$.ajax({
		contentType : "application/json",
		url : context + "/ninushi/ankenToroku",
		type : "post",
		dataType : "json",
		data : JSON.stringify(ankenTorokuForm),
		
		
		complete: function(XMLHttpRequest, textStatus) {
			location.reload(true);
			location.url=context +"/ninushi/ankenToroku";
			var formindex = XMLHttpRequest.responseText.indexOf('<form');
			var formlast = XMLHttpRequest.responseText.lastIndexOf('form>');
			$("form").html(XMLHttpRequest.responseText.substring(formindex, formlast+5));
        }
	});
	
};**/
var startTime;

function ankenTorokuPostSubmit(context, idList) {
	
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
	
	self = this;
	
	var form = $("form");
	
	$.each(idList, function(index, id) {
		if (id == 'syuka-panel') {
			var $syukaObjs = $("div#" + id);
			
			$.each($syukaObjs, function(inIndex, syuka) {
				var textarea = $("<input name='' type='hidden' value=''/>");
				$.each($(syuka).find('input[name]'), function(innerIndex, nameElem) {
					var input = $("<input name='' type='hidden'/>");
					var nameValue = $(nameElem).attr("name").substring($(nameElem).attr("name").lastIndexOf('.')+1);
					input.attr("name", "syukaList["+inIndex+"]."+nameValue);
					input.appendTo($(syuka)); 
				});
				var nameValue = $(syuka).find("textarea").attr("name").substring($(syuka).find("textarea").attr("name").lastIndexOf('.')+1);
				textarea.attr("name", "syukaList["+inIndex+"]."+nameValue);
				textarea.appendTo($(syuka));
				
				$(syuka).find("input[type='hidden'][name*='syukaDay']").val($(syuka).find("input[id=syuka_date]").val());
				$(syuka).find("input[type='hidden'][name*='syukaTime']").val($(syuka).find("input[id=syuka_time]").val());
				$(syuka).find("input[type='hidden'][name*='addrNm']").val($(syuka).find("input[id=addrNm]").val());
				$(syuka).find("input[type='hidden'][name*='postCode']").val($(syuka).find("input[id=postCode]").val());
				$(syuka).find("input[type='hidden'][name*='prefNm']").val($(syuka).find("select[id=prefNm]").find("option:selected").val());
				$(syuka).find("input[type='hidden'][name*='cityNm']").val($(syuka).find("input[id=cityNm]").val());
				$(syuka).find("input[type='hidden'][name*='addrOther']").val($(syuka).find("input[id=addrOther]").val());
				$(syuka).find("input[type='hidden'][name*='tantoNm']").val($(syuka).find("input[id=tantoNm]").val());
				$(syuka).find("input[type='hidden'][name*='tantoTel']").val($(syuka).find("input[id=tantoTel]").val());
				$(syuka).find("input[type='hidden'][name*='amount']").val($(syuka).find("input[name='syukaForm.amount']").val());
				$(syuka).find("input[type='hidden'][name*='weight']").val($(syuka).find("input[id=weight]").val());
				$(syuka).find("input[type='hidden'][name*='remarks']").val($(syuka).find("textarea[id=remarks]").val());
				$(syuka).find("input[type='hidden'][name*='nisyuCd']").val($(syuka).find("select[id=nisyuCd]").find(
						"option:selected").val());
				$(syuka).find("input[type='hidden'][name*='nisyuNm']").val($(syuka).find("select[id=nisyuCd]").find(
						"option:selected").text());
				$(syuka).find("input[type='hidden'][name*='nisugataCd']").val($(syuka).find("select[id=nisugataCd]").find(
						"option:selected").val());
				$(syuka).find("input[type='hidden'][name*='nisugataNm']").val($(syuka).find("select[id=nisugataCd]").find(
						"option:selected").text());
				
			});	
		}
		
		if (id == 'nouhin-panel') {
			var $nohinObjs = $("div#" + id);
			
			$.each($nohinObjs, function(inIndex, nohin) {
				var textarea = $("<input name='' type='hidden' value=''/>");
				$.each($(nohin).find('input[name]'), function(innerIndex, nameElem) {
					var input = $("<input name='' type='hidden'/>");
					var nameValue = $(nameElem).attr("name").substring($(nameElem).attr("name").lastIndexOf('.')+1);
					input.attr("name", "nohinList["+inIndex+"]."+nameValue);
					input.appendTo($(nohin)); 
				});
				var nameValue = $(nohin).find("textarea").attr("name").substring($(nohin).find("textarea").attr("name").lastIndexOf('.')+1);
				textarea.attr("name", "nohinList["+inIndex+"]."+nameValue);
				textarea.appendTo($(nohin));
				
				$(nohin).find("input[type='hidden'][name*='nohinDay']").val($(nohin).find("input[id=nouhin_date]").val());
				$(nohin).find("input[type='hidden'][name*='nohinTime']").val($(nohin).find("input[id=nohin_time]").val());
				$(nohin).find("input[type='hidden'][name*='addrNm']").val($(nohin).find("input[id=addrNm]").val());
				$(nohin).find("input[type='hidden'][name*='postCode']").val($(nohin).find("input[id=postCode]").val());
				$(nohin).find("input[type='hidden'][name*='prefNm']").val($(nohin).find("select[id=prefNm]").find("option:selected").val());
				$(nohin).find("input[type='hidden'][name*='cityNm']").val($(nohin).find("input[id=cityNm]").val());
				$(nohin).find("input[type='hidden'][name*='addrOther']").val($(nohin).find("input[id=addrOther]").val());
				$(nohin).find("input[type='hidden'][name*='tantoNm']").val($(nohin).find("input[id=tantoNm]").val());
				$(nohin).find("input[type='hidden'][name*='tantoTel']").val($(nohin).find("input[id=tantoTel]").val());
				$(nohin).find("input[type='hidden'][name*='amount']").val($(nohin).find("input[name='nohinForm.amount']").val());
				$(nohin).find("input[type='hidden'][name*='weight']").val($(nohin).find("input[id=weight]").val());
				$(nohin).find("input[type='hidden'][name*='remarks']").val($(nohin).find("textarea[id=remarks]").val());
				$(nohin).find("input[type='hidden'][name*='nisyuCd']").val($(nohin).find("select[id=nisyuCd]").find(
						"option:selected").val());
				$(nohin).find("input[type='hidden'][name*='nisyuNm']").val($(nohin).find("select[id=nisyuCd]").find(
						"option:selected").text());
				$(nohin).find("input[type='hidden'][name*='nisugataCd']").val($(nohin).find("select[id=nisugataCd]").find(
						"option:selected").val());
				$(nohin).find("input[type='hidden'][name*='nisugataNm']").val($(nohin).find("select[id=nisugataCd]").find(
						"option:selected").text());
				
			});	
		}
		
		if (id == 'truck-panel') {
			var $truckObjs = $("div#" + id);
			
			$.each($truckObjs, function(inIndex, truck) {
				
				$.each($(truck).find('input[name]'), function(innerIndex, nameElem) {
					var input = $("<input name='' type='hidden'/>");
					var nameValue = $(nameElem).attr("name").substring($(nameElem).attr("name").lastIndexOf('.')+1);
					input.attr("name", "truckList["+inIndex+"]."+nameValue);
					input.appendTo($(truck)); 
				}); 		
				
			
				$(truck).find("input[type='hidden'][name*='yosanMn']").val($(truck).find("input[id=yosanMn]").val());
				$(truck).find("input[type='hidden'][name*='kosokuKbn']").val($(truck).find("select[id=kosokuKbn]").find("option:selected").val());
				$(truck).find("input[type='hidden'][name*='kosokuKbnNm']").val($(truck).find("select[id=kosokuKbn]").find("option:selected").text());
				$(truck).find("input[type='hidden'][name*='nenryoscKbn']").val($(truck).find("select[id=nenryoscKbn]").find("option:selected").val());
				$(truck).find("input[type='hidden'][name*='nenryoscKbnNm']").val($(truck).find("select[id=nenryoscKbn]").find("option:selected").text());
				$(truck).find("input[type='hidden'][name*='nenryoscMn']").val($(truck).find("input[id=nenryoscMn]").val());				
				$(truck).find("input[type='hidden'][name*='syasyuCd']").val($(truck).find("select[id=syasyuCd]").find("option:selected").val());
				$(truck).find("input[type='hidden'][name*='syasyuNm']").val($(truck).find("select[id=syasyuCd]").find("option:selected").text());
				$(truck).find("input[type='hidden'][name*='daisu']").val($(truck).find("select[id=daisu]").find("option:selected").text());
								
				var opTempList = $(truck).find("input[type='checkbox']");
				var opCd = "";
				var opNm = "";
				$.each(opTempList, function(index, op) {
					if($(op).is(':checked')) {
						opCd = opCd + $(op).val() + ",";
						opNm = opNm + $(op).parent("label").text().trim() + ",";
					}
				});
				
				$(truck).find("input[type='hidden'][name*='opList']").val(opCd);
				$(truck).find("input[type='hidden'][name*='opNmList']").val(opNm);
				$(truck).find("input[type='hidden'][name*='orderMn']").val($(truck).find("input[id=orderMn]").val());
			});	
		}
	});
	
	
	$(form).attr("enctype", "application/x-www-form-urlencoded");
	$(form).attr("action", "ankenToroku");
	$(form).attr("target", "_self");
	$(form).submit();
}