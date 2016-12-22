<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.form-group .control-label {
	padding: 0px;
}
</style>

<script src="resources/js/customized-commponent.js"></script>

<script type="text/javascript">
	$(function() {
		$(document).on('click', '#btn_syabanToroku', function(event) {
			if (checkInput() && confirm('この内容で登録します。よろしいでしょうか？')) {
				$(event.target).attr("disabled", "disabled");
				$("form").submit();
			} else {
				return false;
			}
		});

	});

	function checkInput(){
		if($("#companyNm").val()==""){
			alert("会社名を入力してください。");
			$("#companyNm").focus()
			return false;
		}
		if($("#syaryoNo").val()==""){
			alert("車両番号を入力してください。");
			$("#syaryoNo").focus()
			return false;
		}
		if($("#tantoNm").val()==""){
			alert("運転者名を入力してください。");
			$("#tantoNm").focus()
			return false;
		}
		if($("#tantoTel").val()==""){
			alert("連絡先電話を入力してください。");
			$("#tantoTel").focus()
			return false;
		}
		if($("#kinkyuCorp").val()==""){
			alert("緊急連絡先を入力してください。");
			$("#kinkyuCorp").focus()
			return false;
		}
		if($("#kinkyuTant").val()==""){
			alert("緊急連絡先担当者を入力してください。");
			$("#kinkyuTant").focus()
			return false;
		}
		if($("#kinkyuTel").val()==""){
			alert("緊急連絡先電話を入力してください。");
			$("#kinkyuTant").focus()
			return false;
		}

		return true;
	}
	var contraTitle = Constants.CONTRA_TITLE;

	var showContraElemId = Constants.SHOW_SYABAN_CONTRA_ELEM_ID;

	var contactURL = Constants.AJAX_CONTACT_URL;

	var addrKBN03 = Constants.ADDRESS_KBN_03;

	var rirekiTitle = Constants.RIREKI_TITLE;

	function getComponets(event) {
		var etarget = event.srcElement || event.target;
		getItems(contactURL, addrKBN03, rirekiTitle, contraTitle, "syaban_contr_history", showContraElemId, etarget, "id");
	};

</script>
</head>
<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		<!-- フォーム　▼ -->

		<form:form name="ankenDetailForm" commandName="ankenDetailForm" class="form-horizontal" method="POST" action="submitSyaban">
			<input name="ankenDetail.ankenNo" type="hidden" value="${ankenDetailForm.ankenDetail.ankenNo}"/>
			<input name="ankenDetail.updateDt" type="hidden" value="${ankenDetailForm.ankenDetail.updateDt}"/>
			<c:set var="status" value="${ankenDetailForm.ankenDetail.status}"/>
			<fieldset>
				<c:if test="${showErrorMessFlag}">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<form:errors path="*"></form:errors>
						<div>${errormessage}</div>
					</div>
				</c:if>
				<div class="panel panel-default">
					<!-- 基本情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">基本情報</h4>
					</div>
					<div class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-2 control-label" for="textinput">案件番号</label>
							<div class="col-md-6 control-label">${ankenDetailForm.ankenDetail.ankenNo}</div>
							<label class="col-md-3 control-label" for="textinput">受注期限</label>
							<div class="col-md-6 control-label">${ankenDetailForm.ankenDetail.jutuKigen}</div>
							<label class="col-md-3 control-label" for="textinput">案件ステータス</label>
							<div class="col-md-3 control-label">${ankenDetailForm.ankenDetail.statusNm}</div>
						</div>
						<c:if test="${user.gyomuSb == '9'}">
							<div class="form-group  form-inline">
								<label class="col-md-2 control-label" for="textinput">荷主</label>
								<div class="col-md-6  control-label">${ankenDetailForm.ankenDetail.seikyuKsCd}</div>
								<label class="col-md-2 control-label" for="textinput">荷主名称</label>
								<div class="col-md-6  control-label">${ankenDetailForm.ankenDetail.ninushiMeisho}</div>
								<label class="col-md-2 control-label" for="textinput">荷主住所</label>
								<div class="col-md-5  control-label">${ankenDetailForm.ankenDetail.ninushiJusho}</div>
							</div>
							<div class="form-group  form-inline">
								<label class="col-md-2 control-label" for="textinput">運送</label>
								<div class="col-md-6  control-label">${ankenDetailForm.ankenDetail.unsoCd}</div>
								<label class="col-md-2 control-label" for="textinput">運送会社</label>
								<div class="col-md-6  control-label">${ankenDetailForm.ankenDetail.unsoMeisho}</div>
								<label class="col-md-2 control-label" for="textinput">運送住所</label>
								<div class="col-md-5  control-label">${ankenDetailForm.ankenDetail.unsoJusho}</div>
							</div>
						</c:if>
					</div>
					<!-- 基本情報(body) 　▲ -->
				</div>
				<!-- 基本情報 　▲-->

				<div class="row">
					<c:forEach var="syuka" items="${ankenDetailForm.syukaList}">
						<div class="col-md-12" id="syuka-panel">
							<div class="panel panel-default matchHeight">
								<div class="panel-heading clearfix">
									<h4 class="panel-title pull-left">積地（集荷）</h4>
								</div>
								<!-- 運送情報(body) 　▼ -->
								<div class="panel-body">
									<div class="form-group form-inline">
										<!-- 集荷日時 -->
										<label class="col-md-4 control-label" for="textinput">集荷日時</label>
										<div class="col-md-18 control-label">${syuka.syukaDay}</div>
									</div>

									<!-- 集荷名称 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">集荷先</label>
										<div class="col-md-18 control-label">${syuka.addrNm}</div>
									</div>

									<!-- 集荷場所 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">郵便番号</label>
										<div class="col-md-5 control-label">${syuka.postCode}</div>
										<div class="col-md-3"></div>
										<!-- 都道府県 -->
										<label class="col-md-4 control-label" for="textinput">都道府県</label>
										<div class="col-md-8 control-label">${syuka.prefNm}</div>
									</div>
									<div class="form-group form-inline">
										<!-- 市区町村 -->
										<label class="col-md-4 control-label" for="textinput">市区町村</label>
										<div class="col-md-8 control-label">${syuka.cityNm}</div>
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">番地など</label>
										<div class="col-md-8 control-label">${syuka.addrOther}</div>
									</div>
									<!-- 担当者 -->
									<div class="form-group form-inline">
										<!-- 担当者 -->
										<label class="col-md-4 control-label" for="textinput">担当者名</label>
										<div class="col-md-8 control-label">${syuka.tantoNm}</div>
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">担当者TEL</label>
										<div class="col-md-8 control-label">${syuka.tantoTel}</div>
									</div>
									<!--荷姿 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">荷種</label>
										<div class="col-md-8 control-label">${syuka.nisyuNm}</div>
										<!-- 荷姿 -->
										<label class="col-md-4 control-label" for="textinput">荷姿</label>
										<div class="col-md-8 control-label">${syuka.nisugataNm}</div>
									</div>
									<div class="form-group form-inline">
										<!-- 個数 -->
										<label class="col-md-4 control-label" for="textinput">個数</label>
										<div class="col-md-8 control-label">${syuka.kosu}</div>
										<!-- 重量 -->
										<label class="col-md-4 control-label" for="textinput">重量</label>
										<div class="col-md-8  control-label">${syuka.zyuryo}ｔ</div>
									</div>
									<!--備考 -->
									<div class="form-group form-inline">
										<!-- 備考 -->
										<label class="col-md-4 control-label" for="textinput">備考</label>
										<div class="col-md-20  control-label">${syuka.biko}
										</div>
									</div>
								</div>
								<!-- 集荷情報(body) 　▲ -->
							</div>
						</div>
					</c:forEach>
					<c:forEach var="nohin" items="${ankenDetailForm.nohinList}">
						<div class="col-md-12" id="nouhin-panel" id="nouhin-panel1">
							<!-- 納品情報 　▲-->
							<div class="panel panel-info matchHeight">
								<div class="panel-heading clearfix">
									<h4 class="panel-title pull-left">納品（届先）</h4>
								</div>
								<!-- 運送情報(body) 　▼ -->
								<div class="panel-body">
									<div class="form-group form-inline">
										<!-- 集荷日時 -->
										<label class="col-md-4 control-label" for="textinput">納品日時</label>
										<div class="col-md-18  control-label">${nohin.nohinDay}
										</div>
									</div>

									<!-- 納品名称 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">納品先</label>
										<div class="col-md-18  control-label">${nohin.addrNm}</div>
									</div>

									<!-- 納品場所 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">郵便番号</label>
										<div class="col-md-5 control-label">${nohin.postCode}</div>
										<div class="col-md-3"></div>
										<!-- 都道府県 -->
										<label class="col-md-4 control-label" for="textinput">都道府県</label>
										<div class="col-md-8 control-label">${nohin.prefNm}</div>
									</div>
									<div class="form-group form-inline">
										<!-- 市区町村 -->
										<label class="col-md-4 control-label" for="textinput">市区町村</label>
										<div class="col-md-8 control-label">${nohin.cityNm}</div>
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">番地など</label>
										<div class="col-md-8 control-label">${nohin.addrOther}</div>
									</div>
									<!-- 担当者 -->
									<div class="form-group form-inline">
										<!-- 担当者 -->
										<label class="col-md-4 control-label" for="textinput">担当者名</label>
										<div class="col-md-8 control-label">${nohin.tantoNm}</div>
										<!-- 担当者TEL -->
										<label class="col-md-4 control-label" for="textinput">担当者TEL</label>
										<div class="col-md-8 control-label">${nohin.tantoTel}</div>
									</div>
									<!--荷姿 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">荷種</label>
										<div class="col-md-8  control-label">${nohin.nisyuNm}</div>
										<!-- 荷姿 -->
										<label class="col-md-4 control-label" for="textinput">荷姿</label>
										<div class="col-md-8  control-label">${nohin.nisugataNm}</div>
									</div>
									<div class="form-group form-inline">
										<!-- 個数 -->
										<label class="col-md-4 control-label" for="textinput">個数</label>
										<div class="col-md-8 control-label">${nohin.kosu}</div>
										<!-- 重量 -->
										<label class="col-md-4 control-label" for="textinput">重量</label>
										<div class="col-md-8 control-label">
											<div class="input-group  control-label">${nohin.zyuryo}ｔ</div>
										</div>
									</div>
									<!--備考 -->
									<div class="form-group form-inline">
										<!-- 備考 -->
										<label class="col-md-4 control-label" for="textinput">備考</label>
										<div class="col-md-20  control-label">${nohin.biko}
										</div>
									</div>
								</div>
								<!-- 運送情報(body) 　▲ -->
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- トラック情報 ▲ -->
				<div class="row">
					<div id="truck-panel1" class="panel panel-default" style="margin: 2.5px">
						<div class="panel-heading clearfix">
							<h4 class="panel-title pull-left">トラック</h4>
						</div>
						<!-- トラック情報(body) 　▼ -->
						<div class="panel-body">
							<div class="form-group form-inline">
								<label class="col-md-1 control-label" for="textinput" required>予算</label>
								<div class="col-md-4 control-label">
									<span class="text-danger" style="font-size: 18px;"><fmt:formatNumber value="${ankenDetailForm.truck.yosanMn}" pattern="#,#00"/>円
									</span>
								</div>
								<label class="col-md-3 control-label" for="radios">高速使用料</label>
								<div class="col-md-4 control-label">${ankenDetailForm.truck.kosokuKbn}</div>
								<label class="col-md-3 control-label" for="radios">燃料サーチャージ</label>
								<div class="col-md-4 control-label">${ankenDetailForm.truck.nenryoscKbn}</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-md-1 control-label" for="selectbasic" required>車種</label>
								<div class="col-md-2 control-label">
									<span class="text-danger" style="font-size: 18px;">${ankenDetailForm.truck.syasyuNm}</span>
								</div>
								<label class="col-md-1 control-label " for="selectbasic" required>台数</label>
								<div class="col-md-2 control-label  text-danger">
									<span class="text-danger" style="font-size: 18px;">1 台</span>
								</div>
								<label class="col-md-2 control-label col-md-offset-1"
									for="checkboxes">オプション</label>
								<div class="col-md-10">
									<c:forEach var="option" items="${ankenDetailForm.truck.opList}">
										<span class="label label-warning">${option}</span>
									</c:forEach>
								</div>
							</div>
						</div>
						<!-- トラック情報(body) 　▲ -->
					</div>
				</div>

				<!-- トラック情報 ▲ -->

				<!-- 補足情報(body)-->
				<div class="panel panel-default">
					<!-- 補足情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">補足情報</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-10">
								<div class="form-group form-inline">
									<label class="col-md-4 control-label" for="textinput">保険金額</label>
									<div class="col-md-10"><fmt:formatNumber value="${ankenDetailForm.ankenDetail.hokenKG}" pattern="#,#00"/>円</div>
								</div>
								<!--注意事項 ▼-->
								<div class="form-group form-inline">
									<!-- 注意事項-->
									<label class="col-md-4 control-label" for="textinput">注意事項</label>
									<div class="col-md-10">${ankenDetailForm.ankenDetail.tyuiJK}</div>
								</div>
								<!--注意事項 ▲-->
							</div>
<!--
							<div class="col-md-14">
								<div class="form-group form-inline">
									<label class="col-md-5 control-label" for="radios">画像情報</label>
								</div>
								<div class="form-group form-inline">
									<div class="col-md-7 col-md-offset-3">
									<c:choose>
										<c:when test="${ankenDetailForm.ankenDetail.picName1 != null && ankenDetailForm.ankenDetail.picName1 != ''}">
											<img id="picImg1" src="${sessionScope.anken_pic_url_tmp}/${ankenDetailForm.ankenDetail.picTmpNm1}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg1" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
									</div>
									<div class="col-md-7">
									<c:choose>
										<c:when test="${ankenDetailForm.ankenDetail.picName2 != null && ankenDetailForm.ankenDetail.picName2 != ''}">
											<img id="picImg2" src="${sessionScope.anken_pic_url_tmp}/${ankenDetailForm.ankenDetail.picTmpNm2}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg2" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
									</div>
									<div class="col-md-7">
									<c:choose>
										<c:when test="${ankenDetailForm.ankenDetail.picName3 != null && ankenDetailForm.ankenDetail.picName3 != ''}">
											<img id="picImg3" src="${sessionScope.anken_pic_url_tmp}/${ankenDetailForm.ankenDetail.picTmpNm3}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg3" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
									</div>
								</div>
 							</div>
 -->
						</div>
					</div>
					<!-- 補足情報(body) 　▲ -->
				</div>
				<!-- 補足情報 　▲-->
				<!-- 車番情報-->
				<div class="panel panel-warning">
					<!-- 車番情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">車番入力（受注事項入力）</h4>
					</div>
					<div class="panel-body">
						<div class="form-group form-inline">
							<label class="col-md-2 control-label" for="syabanKsNm" required>会社名</label>
							<div class="col-md-5 control-label">
								<input id="companyNm" name="ankenDetail.syabanKsNm"class="form-control input-md" type="text" maxlength="60" size="19" value="${ankenDetailForm.ankenDetail.syabanKsNm}"/>
								<a class="btn btn-info btn-sm" id="hisory-syuka-0"
												data-toggle="modal" data-target="#pickup_contr_history"
												onclick="getComponets(event);">履歴</a>
							</div>
							<label class="col-md-3 control-label" for="syaryoNo" required>車両番号</label>
							<div class="col-md-5 control-label">
								<input id="syaryoNo" name="ankenDetail.syaryoNo" class="form-control input-md" type="text" maxlength="45" size="25" value="${ankenDetailForm.ankenDetail.syaryoNo}"/>
							</div>
						</div>
						<div class="form-group form-inline">
							<label class="col-md-2 control-label" for="untensyaName" required>運転者名</label>
							<div class="col-md-5 control-label">
								<input id="tantoNm" name="ankenDetail.untensyaName" class="form-control input-md" type="text" maxlength="60" size="25" value="${ankenDetailForm.ankenDetail.untensyaName}"/>
							</div>
							<label class="col-md-3 control-label" for="renrakuTel" required>連絡先電話</label>
							<div class="col-md-5 control-label">
								<input id="tantoTel" name="ankenDetail.renrakuTel" class="form-control input-md tel" type="tel" maxlength="20" size="20" value="${ankenDetailForm.ankenDetail.renrakuTel}"/>
							</div>

						</div>
						<!--注意事項 ▼-->
						<div class="form-group form-inline">
							<label class="col-md-2 control-label" for="kinkyuCorp" required>緊急連絡先</label>
							<div class="col-md-5 control-label">
								<input id="kinkyuCorp" name="ankenDetail.kinkyuCorp" class="form-control input-md" type="text" maxlength="60" size="25" value="${ankenDetailForm.ankenDetail.kinkyuCorp}"/>
							</div>
							<label class="col-md-3 control-label" for="kinkyuTant" required>緊急連絡先担当者</label>
							<div class="col-md-5 control-label">
								<input id="kinkyuTant" name="ankenDetail.kinkyuTant" class="form-control input-md" type="text" maxlength="60" size="25" value="${ankenDetailForm.ankenDetail.kinkyuTant}"/>
							</div>
							<label class="col-md-3 control-label" for="kinkyuTel" required>緊急連絡先電話</label>
							<div class="col-md-5 control-label">
								<input id="kinkyuTel" name="ankenDetail.kinkyuTel" class="form-control input-md tel" type="tel" maxlength="20" size="20" value="${ankenDetailForm.ankenDetail.kinkyuTel}"/>
							</div>
						</div>
						<!--注意事項 ▲-->
					</div>
					<!-- 車番情報body) 　▲ -->
				</div>
				<!-- 車番情報 　▲-->
				<!-- 基本情報 　▲-->

				<!-- ボタン 　▼-->
				<div class="row" style="padding: 15px 15px 15px 15px; clear: left;">
      				<button id="btn_syabanToroku" class="btn btn-primary col-md-offset-15" >登録</button>
					<a href="ankenList?pageNo=${pageNo}" class="btn btn-default col-md-offset-1"> 一覧に戻る </a>
				</div>
				<!-- ボタン 　▲-->
			</fieldset>
		</form:form>
		<!-- フォーム　▲-->

	</div>
</body>
<script src="<%=request.getContextPath()%>/resources/js/jquery.matchHeight.js"></script>
<script type="text/javascript">
   	$(".matchHeight").matchHeight();
</script>
</html>
