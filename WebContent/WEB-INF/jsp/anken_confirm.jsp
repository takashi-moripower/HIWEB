<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>

<script src="resources/js/Constants.js"></script>
<script src="resources/js/customized-commponent.js"></script>


<script type="text/javascript">
	$(function() {

		var updateFlag = $("[id='updateFlag']").val();

		var status = updateFlag ? "更新" : "登録";

		var running = 0;

		$(document).on('click', '#btn_toroku', function(event) {
			if (running == 1){
				return false;
			}
			running = 1;
			if (confirm('この内容で'+status+'します。よろしいでしょうか？')) {
				return true;
			} else {
				return false;
			}
		});

	});
</script>
</head>

<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
		<!-- フォーム　▼ -->

		<!-- フォーム　▼ -->

		<form:form class="form-horizontal" id="ankenTorokuForm" commandName="ankenTorokuForm"
			action="ankenTorokuConfirm">
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

			<fieldset>

			<input type="hidden" id="updateFlag" name="updateFlag" value="${requestScope.updateFlag}"/>
			<form:hidden path="" name="createId" value="${ankenTorokuForm.createId}"/>
			<form:hidden path="" name="createDt" value="${ankenTorokuForm.createDt}"/>
			<form:hidden path="" name="updateId" value="${ankenTorokuForm.updateId}"/>
			<form:hidden path="" name="updateDt" value="${ankenTorokuForm.updateDt}"/>

				<div class="panel panel-default">
					<!-- 基本情報(body) 　▼ -->
					<div class="panel-heading clearfix">
						<h4 class="panel-title pull-left">基本情報</h4>
					</div>
					<div class="panel-body">
						<div class="form-group  form-inline">
							<label class="col-md-2 control-label" for="textinput">案件番号</label>
							<div class="col-md-5 control-label">${ankenTorokuForm.ankenNo}</div>
							<form:hidden path="" name="ankenNo"
								value="${ankenTorokuForm.ankenNo}" />
							<form:hidden path="" name="ankenId"
								value="${ankenTorokuForm.ankenId}" />
							<label class="col-md-2 control-label" for="textinput">受注期限</label>
							<div class="col-md-6 control-label">${ankenTorokuForm.orderDate}</div>
							<form:hidden path="" name="orderDate"
								value="${ankenTorokuForm.orderDate}" />
							<label class="col-md-3 control-label" for="textinput">案件ステータス</label>
							<div class="col-md-3 control-label">${ankenTorokuForm.ankenStatusNm}</div>
							<form:hidden path="" name="ankenStatus"
								value="${ankenTorokuForm.ankenStatus}" />
							<form:hidden path="" name="ankenStatusNm"
								value="${ankenTorokuForm.ankenStatusNm}" />
						</div>
						<c:if test="${user.gyomuSb == '9'}">
						<div class="form-group  form-inline">
			    			<label class="col-md-2 control-label">荷主</label>
			    			<div class="col-md-5  control-label">
			    				<form:label id="ninushiCdLabel" class="col-md-7 control-label" path="seikyuKsCd">${ankenTorokuForm.seikyuKsCd}</form:label>
			    				<a href="javascript:;" class="btn btn-info btn-sm" id="" onclick="selectNinushi()">選択</a>
			    			</div>
			    			<form:hidden path="seikyuKsCd" id="seikyuKsCdHidden" name="seikyuKsCd"/>
			    			<form:hidden path="seikyuKsNm" id="seikyuKsNmHidden" name="seikyuKsNm"/>
			    			<form:hidden path="seikyuKsAddr" id="seikyuKsAddrHidden" name="seikyuKsAddr"/>
			    			<label class="col-md-2 control-label">荷主名称</label>
			    			<form:label id="ninushiNmLabel" class="col-md-6 control-label" path="seikyuKsNm">${ankenTorokuForm.seikyuKsNm}</form:label>
			    			<label class="col-md-2 control-label">荷主住所</label>
			    			<form:label id="ninushiAddrLabel" class="col-md-5 control-label" path="seikyuKsAddr">${ankenTorokuForm.seikyuKsAddr}</form:label>
			         	</div>
			       	 	<div class="form-group  form-inline">
			    			<label class="col-md-2 control-label" for="textinput">運送</label>
			    			<div class="col-md-5  control-label">
			    				<form:label id="unsoCdLabel" class="col-md-7 control-label" path="shihraiKsCd">${ankenTorokuForm.shihraiKsCd}</form:label>
			    				<a href="javascript:;" class="btn btn-info btn-sm" id="" onclick="selectUnso()">選択</a>
			    			</div>
			    			<form:hidden path="shihraiKsCd" id="shihraiKsCdHidden" name="shihraiKsCd"/>
			    			<form:hidden path="shihraiKsNm" id="shihraiKsNmHidden" name="shihraiKsNm"/>
			    			<form:hidden path="shihraiKsAddr" id="shihraiKsAddrHidden" name="shihraiKsAddr"/>
			    			<label class="col-md-2 control-label">運送会社</label>
			    			<form:label id="unsoNmLabel" class="col-md-6 control-label" path="shihraiKsNm">${ankenTorokuForm.shihraiKsNm}</form:label>
			    			<label class="col-md-2 control-label">運送住所</label>
			    			<form:label id="unsoAddrLabel" class="col-md-5 control-label" path="shihraiKsAddr">${ankenTorokuForm.shihraiKsAddr}</form:label>
			        	</div>
			        	</c:if>
					</div>
					<!-- 基本情報(body) 　▲ -->
				</div>
				<!-- 基本情報 　▲-->

				<div class="row">

					<c:forEach var="item" items="${ankenTorokuForm.syukaList}"
						varStatus="status">

						<div class="col-md-12" id="syuka-panel">
							<div class="panel panel-default matchHeight">
								<div class="panel-heading clearfix">
									<h4 class="panel-title pull-left">積地（集荷）</h4>
								</div>
								<!-- 運送情報(body) 　▼ -->
								<div class="panel-body">
									<div class="form-group form-inline">
										<!-- 集荷日時 -->
										<label class="col-md-4 control-label" for="textinput" required>集荷日時</label>
										<div class="col-md-18 control-label">${item.syukaDay}
											${item.syukaTime}</div>
										<form:hidden path=""
											name="syukaList[${status.index}].syukaDay"
											value="${item.syukaDay}" />
										<form:hidden path=""
											name="syukaList[${status.index}].syukaTime"
											value="${item.syukaTime}" />
									</div>

									<!-- 集荷名称 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput" required>集荷先</label>
										<div class="col-md-18 control-label">${item.addrNm}</div>
										<form:hidden path="" name="syukaList[${status.index}].addrNm"
											value="${item.addrNm}" />
									</div>

									<!-- 集荷場所 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">郵便番号</label>
										<div class="col-md-5 control-label">${item.postCode}</div>
										<form:hidden path=""
											name="syukaList[${status.index}].postCode"
											value="${item.postCode}" />
										<div class="col-md-3"></div>
										<!-- 都道府県 -->
										<label class="col-md-4 control-label" for="textinput" required>都道府県</label>
										<div class="col-md-8 control-label">${item.prefNm}</div>
										<form:hidden path="" name="syukaList[${status.index}].prefNm"
											value="${item.prefNm}" />
									</div>
									<div class="form-group form-inline">
										<!-- 市区町村 -->
										<label class="col-md-4 control-label" for="textinput" required>市区町村</label>
										<div class="col-md-8 control-label">${item.cityNm}</div>
										<form:hidden path="" name="syukaList[${status.index}].cityNm"
											value="${item.cityNm}" />
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">番地など</label>
										<div class="col-md-8 control-label">${item.addrOther}
											<form:hidden path=""
												name="syukaList[${status.index}].addrOther"
												value="${item.addrOther}" />
										</div>
									</div>
									<!-- 担当者 -->
									<div class="form-group form-inline">
										<!-- 担当者 -->
										<label class="col-md-4 control-label" for="textinput">担当者名</label>
										<div class="col-md-8 control-label">${item.tantoNm}</div>
										<form:hidden path="" name="syukaList[${status.index}].tantoNm"
											value="${item.tantoNm}" />
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">担当者TEL</label>
										<div class="col-md-8 control-label">${item.tantoTel}</div>
										<form:hidden path=""
											name="syukaList[${status.index}].tantoTel"
											value="${item.tantoTel}" />
									</div>
									<!--荷姿 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">荷種</label>
										<div class="col-md-8 control-label">${item.nisyuNm}</div>
										<form:hidden path="" name="syukaList[${status.index}].nisyuCd"
											value="${item.nisyuCd}" />
										<form:hidden path="" name="syukaList[${status.index}].nisyuNm"
											value="${item.nisyuNm}" />
										<!-- 荷姿 -->
										<label class="col-md-4 control-label" for="textinput" required>荷姿</label>
										<div class="col-md-8 control-label">${item.nisugataNm}</div>
										<form:hidden path=""
											name="syukaList[${status.index}].nisugataCd"
											value="${item.nisugataCd}" />
										<form:hidden path=""
											name="syukaList[${status.index}].nisugataNm"
											value="${item.nisugataNm}" />
									</div>
									<div class="form-group form-inline">
										<!-- 個数 -->
										<label class="col-md-4 control-label" for="textinput">個数</label>
										<div class="col-md-8 control-label">${item.amount}</div>
										<form:hidden path="" name="syukaList[${status.index}].amount"
											value="${item.amount}" />
										<!-- 重量 -->
										<label class="col-md-4 control-label" for="textinput">重量</label>
										<div class="col-md-8  control-label">${item.weight}ｔ</div>
										<form:hidden path="" name="syukaList[${status.index}].weight"
											value="${item.weight}" />
									</div>
									<!--備考 -->
									<div class="form-group form-inline">
										<!-- 備考 -->
										<label class="col-md-4 control-label" for="textinput">備考</label>
										<div class="col-md-20  control-label" style="word-wrap: break-word;">${item.remarks}</div>
										<form:hidden path="" name="syukaList[${status.index}].remarks"
											value="${item.remarks}" />
									</div>
								</div>
								<!-- 集荷情報(body) 　▲ -->
							</div>
						</div>
					</c:forEach>


					<c:forEach var="item" items="${ankenTorokuForm.nohinList}"
						varStatus="status">
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
										<label class="col-md-4 control-label" for="textinput" required>納品日時</label>
										<div class="col-md-18  control-label">${item.nohinDay}
											${item.nohinTime}</div>
										<form:hidden path=""
											name="nohinList[${status.index}].nohinDay"
											value="${item.nohinDay}" />
										<form:hidden path=""
											name="nohinList[${status.index}].nohinTime"
											value="${item.nohinTime}" />
									</div>

									<!-- 納品名称 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput" required>納品先</label>
										<div class="col-md-18  control-label">${item.addrNm}</div>
										<form:hidden path="" name="nohinList[${status.index}].addrNm"
											value="${item.addrNm}" />
									</div>

									<!-- 納品場所 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">郵便番号</label>
										<div class="col-md-5 control-label">${item.postCode}</div>
										<form:hidden path=""
											name="nohinList[${status.index}].postCode"
											value="${item.postCode}" />
										<div class="col-md-3"></div>
										<!-- 都道府県 -->
										<label class="col-md-4 control-label" for="textinput" required>都道府県</label>
										<div class="col-md-8 control-label">${item.prefNm}</div>
										<form:hidden path="" name="nohinList[${status.index}].prefNm"
											value="${item.prefNm}" />
									</div>
									<div class="form-group form-inline">
										<!-- 市区町村 -->
										<label class="col-md-4 control-label" for="textinput" required>市区町村</label>
										<div class="col-md-8 control-label">${item.cityNm}</div>
										<form:hidden path="" name="nohinList[${status.index}].cityNm"
											value="${item.cityNm}" />
										<!-- 番地 -->
										<label class="col-md-4 control-label" for="textinput">番地など</label>
										<div class="col-md-8 control-label">${item.addrOther}
											<form:hidden path=""
												name="nohinList[${status.index}].addrOther"
												value="${item.addrOther}" />
										</div>
									</div>
									<!-- 担当者 -->
									<div class="form-group form-inline">
										<!-- 担当者 -->
										<label class="col-md-4 control-label" for="textinput">担当者名</label>
										<div class="col-md-8 control-label">${item.tantoNm}</div>
										<form:hidden path="" name="nohinList[${status.index}].tantoNm"
											value="${item.tantoNm}" />
										<!-- 担当者TEL -->
										<label class="col-md-4 control-label" for="textinput">担当者TEL</label>
										<div class="col-md-8 control-label">${item.tantoTel}</div>
										<form:hidden path=""
											name="nohinList[${status.index}].tantoTel"
											value="${item.tantoTel}" />
									</div>
									<!--荷姿 -->
									<div class="form-group form-inline">
										<label class="col-md-4 control-label" for="textinput">荷種</label>
										<div class="col-md-8  control-label">${item.nisyuNm}</div>
										<form:hidden path="" name="nohinList[${status.index}].nisyuCd"
											value="${item.nisyuCd}" />
										<form:hidden path="" name="nohinList[${status.index}].nisyuNm"
											value="${item.nisyuNm}" />
										<!-- 荷姿 -->
										<label class="col-md-4 control-label" for="textinput" required>荷姿</label>
										<div class="col-md-8  control-label">${item.nisugataNm}</div>
										<form:hidden path=""
											name="nohinList[${status.index}].nisugataCd"
											value="${item.nisugataCd}" />
										<form:hidden path=""
											name="nohinList[${status.index}].nisugataNm"
											value="${item.nisugataNm}" />
									</div>
									<div class="form-group form-inline">
										<!-- 個数 -->
										<label class="col-md-4 control-label" for="textinput">個数</label>
										<div class="col-md-8 control-label">${item.amount}</div>
										<form:hidden path="" name="nohinList[${status.index}].amount"
											value="${item.amount}" />
										<!-- 重量 -->
										<label class="col-md-4 control-label" for="textinput">重量</label>
										<div class="col-md-8 control-label">
											<div class="input-group  control-label">${item.weight}ｔ</div>
											<form:hidden path="" name="nohinList[${status.index}].weight"
												value="${item.weight}" />
										</div>
									</div>
									<!--備考 -->
									<div class="form-group form-inline">
										<!-- 備考 -->
										<label class="col-md-4 control-label" for="textinput">備考</label>
										<div class="col-md-20  control-label" style="word-wrap: break-word;">${item.remarks}</div>
										<form:hidden path="" name="nohinList[${status.index}].remarks"
											value="${item.remarks}" />
									</div>
								</div>
								<!-- 運送情報(body) 　▲ -->
							</div>
						</div>
					</c:forEach>

				</div>

				<c:forEach var="item" items="${ankenTorokuForm.truckList}"
					varStatus="status">
					<div id="truck-panel1" class="panel panel-default">
						<div class="panel-heading clearfix">
							<h4 class="panel-title pull-left">トラック</h4>
						</div>
						<!-- トラック情報(body) 　▼ -->
						<div class="panel-body">
							<div class="form-group form-inline">
								<label class="col-md-1 control-label" for="textinput" required>予算</label>
								<div class="col-md-4 control-label">
									<span class="text-danger" style="font-size: 18px;">${item.yosanMn}
									</span>
									<form:hidden path="" name="truckList[${status.index}].yosanMn"
										value="${item.yosanMn}" />
								</div>
								<label class="col-md-3 control-label" for="radios">高速使用料</label>
								<div class="col-md-4 control-label">${item.kosokuKbnNm}</div>
								<form:hidden path="" name="truckList[${status.index}].kosokuKbn"
									value="${item.kosokuKbn}" />
								<label class="col-md-3 control-label" for="radios">燃料サーチャージ</label>
								<div class="col-md-4 control-label">${item.nenryoscKbnNm}</div>
								<form:hidden path=""
									name="truckList[${status.index}].nenryoscKbn"
									value="${item.nenryoscKbn}" />
								<form:hidden path=""
									name="truckList[${status.index}].nenryoscKbnNm"
									value="${item.nenryoscKbnNm}" />
								<div class="col-md-4">
									<c:if test="${item.nenryoscKbn=='1'}">
										<div class="input-group">
											<span class="text-danger" style="font-size: 18px;">${item.nenryoscMn}円
											</span>
											<form:hidden path=""
												name="truckList[${status.index}].nenryoscMn"
												value="${item.nenryoscMn}" />
										</div>
									</c:if>
								</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-md-1 control-label" for="selectbasic" required>車種</label>
								<div class="col-md-2 control-label">
									<span class="text-danger" style="font-size: 18px;">${item.syasyuNm}</span>
									<form:hidden path="" name="truckList[${status.index}].syasyuCd"
										value="${item.syasyuCd}" />
								</div>
								<label class="col-md-1 control-label " for="selectbasic"
									required>台数</label>
								<div class="col-md-2 control-label  text-danger">
									<span class="text-danger" style="font-size: 18px;">${item.daisu}台</span>
									<form:hidden path="" name="truckList[${status.index}].daisu"
										value="${item.daisu}" />
								</div>
								<label class="col-md-2 control-label col-md-offset-1">オプション</label>
								<div class="col-md-10">
									<form:hidden path="" name="truckList[${status.index}].opList"
										value="${item.opList}" />
									<form:hidden path="" name="truckList[${status.index}].opNmList"
										value="${item.opNmList}" />

									<c:set var="theString" value="${item.opNmList}" />
									<c:if test="${fn:contains(theString, 'ゲート')}">
										<span class="label label-warning">ゲート</span>
									</c:if>
									<c:if test="${fn:contains(theString, 'ワイド')}">
										<span class="label label-warning">ワイド</span>
									</c:if>
									<c:if test="${fn:contains(theString, 'エア')}">
										<span class="label label-warning">エア</span>
									</c:if>
									<c:if test="${fn:contains(theString, '箱')}">
										<span class="label label-warning">箱</span>
									</c:if>
									<c:if test="${fn:contains(theString, '平')}">
										<span class="label label-warning">平</span>
									</c:if>
									<c:if test="${fn:contains(theString, '冷凍')}">
										<span class="label label-warning">冷凍</span>
									</c:if>
									<c:if test="${fn:contains(theString, '冷蔵')}">
										<span class="label label-warning">冷蔵 </span>
									</c:if>
									<c:if test="${fn:contains(theString, 'ユニック')}">
										<span class="label label-warning">ユニック </span>
									</c:if>
								</div>
							</div>
							<c:if test="${user.gyomuSb != '0'}">
							<div class="form-group form-inline">
								<label class="col-md-1 control-label" for="textinput" required>受注</label>
								<div class="col-md-4 control-label">
									<span class="text-danger" style="font-size: 18px;">${item.orderMn}円
									</span>
									<form:hidden path="" name="truckList[${status.index}].orderMn"
										value="${item.orderMn}" />
								</div>
							</div>
							</c:if>
						</div>
						<!-- トラック情報(body) 　▲ -->

					</div>
				</c:forEach>



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
									<div class="col-md-10">${ankenTorokuForm.hokenMn}</div>
									<form:hidden path="" name="hokenMn"
										value="${ankenTorokuForm.hokenMn}" />
								</div>
								<!--注意事項 ▼-->
								<div class="form-group form-inline">
									<!-- 注意事項-->
									<label class="col-md-4 control-label" for="textinput">注意事項</label>
									<div class="col-md-10" style="word-wrap: break-word;">${ankenTorokuForm.tyuiJk}</div>
									<form:hidden path="" name="tyuiJk"
										value="${ankenTorokuForm.tyuiJk}" />
								</div>
								<!--注意事項 ▲-->
							</div>
							<div class="col-md-14">
								<div class="form-group form-inline">
									<label class="col-md-5 control-label" for="radios">画像情報</label>
								</div>
								<form:hidden path="" id="picNm1" name="picNm1" value="${ankenTorokuForm.picNm1}"/>
								<form:hidden path="" id="picTmpNm1" name="picTmpNm1" value="${ankenTorokuForm.picTmpNm1}"/>
								<form:hidden path="" id="picChg1" name="picChg1" value="${ankenTorokuForm.picChg1}"/>
								<form:hidden path="" id="picNm2" name="picNm2" value="${ankenTorokuForm.picNm2}"/>
								<form:hidden path="" id="picTmpNm2" name="picTmpNm2" value="${ankenTorokuForm.picTmpNm2}"/>
								<form:hidden path="" id="picChg2" name="picChg2" value="${ankenTorokuForm.picChg2}"/>
								<form:hidden path="" id="picNm3" name="picNm3" value="${ankenTorokuForm.picNm3}"/>
								<form:hidden path="" id="picTmpNm3" name="picTmpNm3" value="${ankenTorokuForm.picTmpNm3}"/>
								<form:hidden path="" id="picChg3" name="picChg3" value="${ankenTorokuForm.picChg3}"/>
								<div class="form-group form-inline">
									<div class="col-md-7 col-md-offset-3">
									<c:choose>
										<c:when test="${ankenTorokuForm.picNm1 != null && ankenTorokuForm.picNm1 != ''}">
											<img id="picImg1" src="${sessionScope.anken_pic_url_tmp}/${ankenTorokuForm.picTmpNm1}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg1" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
									</div>
									<div class="col-md-7">
									<c:choose>
										<c:when test="${ankenTorokuForm.picNm2 != null && ankenTorokuForm.picNm2 != ''}">
											<img id="picImg2" src="${sessionScope.anken_pic_url_tmp}/${ankenTorokuForm.picTmpNm2}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg2" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
									</div>
									<div class="col-md-7">
									<c:choose>
										<c:when test="${ankenTorokuForm.picNm3 != null && ankenTorokuForm.picNm3 != ''}">
											<img id="picImg3" src="${sessionScope.anken_pic_url_tmp}/${ankenTorokuForm.picTmpNm3}" width="142" height="110" style="border:solid 1px gray"/>
										</c:when>
										<c:otherwise>
											<img id="picImg3" src="${sessionScope.anken_pic_url_tmp}/noPicture.jpg" width="142" height="110" style="border:solid 1px gray"/>
										</c:otherwise>
									</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 補足情報(body) 　▲ -->
				</div>

				<!-- ボタン 　▼-->
				<div class="row" style="padding: 15px 15px 15px 15px; clear: left;">
					<!-- <a href="anken_toroku_complete.html"
						class="btn btn-primary col-md-offset-17" id="btn_toroku">登録 </a>  -->

					<button type="submit" id="btn_toroku"
						class="btn btn-primary col-md-offset-11">登録</button>

					<a href="javascript:void(0)" onclick="goBack()"
						class="btn btn-default col-md-offset-1"> 戻る </a>
				</div>
				<!-- ボタン 　▲-->

			</fieldset>
		</form:form>
		<!-- フォーム　▲-->

	</div>

	<script src="<%=request.getContextPath()%>/resources/js/jquery.matchHeight.js"></script>
	<script type="text/javascript">
		$(function(){
		   	$(".matchHeight").matchHeight();
		});

		function goBack() {
			var form = document.forms['ankenTorokuForm'];
			form.action = 'ankenback';
			form.submit();
		}
	</script>
</body>
</html>
