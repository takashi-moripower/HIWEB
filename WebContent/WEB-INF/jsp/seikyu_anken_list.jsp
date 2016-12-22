<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="common/header.jsp"%>
	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
	th,td{
		text-align:center;
	}  
</style>
<script type="text/javascript">
	function ryokinToroku() {
		$.ajax({
            type: "POST",
            url: "ryokinToroku",
            data:$('form').serialize(),
		    dataType:'json',
            success: function(data) {
            	if(data.success == true ){
            		var size = '${seikyuAnkenListForm.seikyuAnkenList.size()}' || 0;
            		for (var i = 0; i < size; i++) {
            			if ($("#updateFlag" + i).val() == 'true'){
            				$("#updateFlag" + i).val("false");
            				$("#updateDt" + i).val(data.updateDt);
            			}
            		}
		            alert("料金登録が成功しました。");
		        } else {
		            alert("料金登録が失敗しました。");
		        }
            }
        });
	}
	
	function setUpdateFlag(objName){
		$("#"+objName).val("true");
	}
	
	function seikyuAnkenDwl() {
		var form = document.forms['seikyuAnkenListForm'];
		form.action = "seikyuAnkenDwl";
		form.submit();
	}
	
	function search() {
		var form = document.forms['seikyuAnkenListForm'];
		form.action = "searchSeikyuAnkenList";
		form.submit();
	}
	
    $(function() {
      
      $('.input-group.date').datepicker({
          format : 'yyyy/mm/dd (D)',
          startDate: "2015/5/19",
          language: 'ja',
          orientation: 'top left',
          autoclose: true
      });      

      $('label[required]').css('color', 'Red');
	  
    });
  </script>
</head>
<body>
	<div class="container">
		<%@ include file="common/nav.jsp"%>
	
	  <!-- フォーム　▼ -->
	  <form:form class="form-horizontal" commandName="seikyuAnkenListForm" action="searchSeikyuAnkenList" method="POST">
	    <fieldset>
	    <input id="kigyoTxt" name="kigyo" type="hidden" value="${seikyuAnkenListForm.kigyo}" />
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
			<div class="panel-heading">
			  <h4 class="panel-title">	
					  請求対象検索
			  </h4>
			</div>
			<!-- 検索条件(body) 　▼ -->
	      <div id="search_detail" class="panel-body">
			<c:if test="${user.gyomuSb eq '9'}">
		        <div class="form-group  form-inline">
					<label class="col-md-2 control-label" required>企業</label>
					<div id="kigyo" class="col-md-2  control-label">${seikyuAnkenListForm.kigyo}</div>					
					<div class="col-md-3  control-label"><a href="javascript:void(0);" class="btn btn-info btn-sm" onclick="selectNinushi()">選択</a></div>
					<label class="col-md-2 control-label">企業名称</label>
					<div id="kigyoNm" class="col-md-4  control-label">${seikyuAnkenListForm.kigyoNm}</div>
	    			<input id="kigyoNmTxt" name="kigyoNm" type="hidden" value="${seikyuAnkenListForm.kigyoNm}" />
					<label class="col-md-2 control-label">企業住所</label>
					<div id="kigyoJusho" class="col-md-8  control-label">${seikyuAnkenListForm.kigyoJusho}</div>
	    			<input id="kigyoJushoTxt" name="kigyoJusho" type="hidden" value="${seikyuAnkenListForm.kigyoJusho}" />	
		        </div>
			</c:if>
	        <div class="form-group  form-inline">
				<label class="col-md-2 control-label">料率</label>
				<div id="ryoritu" class="col-md-5 control-label">${seikyuAnkenListForm.ryoritu}%</div>
				<input id="ryorituTxt" name="ryoritu" type="hidden" value="${seikyuAnkenListForm.ryoritu}">
				<label class="col-md-2 control-label">締日種別</label>
				<div id="simbSb" class="col-md-4 control-label">${seikyuAnkenListForm.simbSb}</div>
				<input id="simbSbTxt" name="simbSb" type="hidden" value="${seikyuAnkenListForm.simbSb}">
				<label class="col-md-2 control-label">支払サイト</label>
				<div id="sihrSt" class="col-md-3 control-label">${seikyuAnkenListForm.sihrSt}</div>
				<input id="sihrStTxt" name="sihrSt" type="hidden" value="${seikyuAnkenListForm.sihrSt}">
	        </div>
			<div class="form-group form-inline">
	          <label class="col-md-2 control-label" required>対象期間</label>
	          <div class="col-md-11 control-label">
			    <div class="input-group date">
	              <input name="syukaDayFr" type="text" class="form-control" value="${seikyuAnkenListForm.syukaDayFr}" size="12">
	              <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span> 
			    </div>
				<p class="form-control-static">～</p>
			    <div class="input-group date">
	              <input name="syukaDayTo" type="text" class="form-control" value="${seikyuAnkenListForm.syukaDayTo}" size="12">
	              <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span> 
			    </div>
			  </div>
	
	          <label class="col-md-2 control-label" for="selectbasic">案件状態</label>
	          <div class="col-md-6 control-label">
	            <label class="checkbox-inline" for="status_kakutei">
	            <input type="checkbox" name="status" id="status_kakutei" <c:if test="${seikyuAnkenListForm.kakutei eq '20'}">checked</c:if> value="20">
	            確定 </label>
	            <label class="checkbox-inline" for="status_cancel">
	            <input type="checkbox" name="status" id="status_cancel" <c:if test="${seikyuAnkenListForm.cancel eq '90'}">checked</c:if> value="90">
	            キャンセル </label>
	          </div>
	
			</div>
	
		</div>
	  </div>
	
	    <!-- ボタン 　▼-->
	    <div class="row" style="padding:5px 1px 1px 1px;clear:left;">
	      <button class="btn btn-info col-md-offset-17" onclick="search()">検　索</button>
	      <a href="index" class="btn btn-default col-md-offset-1"> 戻る </a>
	    </div>
	    	<c:if test="${user.gyomuSb == '9'}">
			<!-- memberlist popup 　▼-->
				<div id="memListDialog" style="z-index:19891020;overflow:visible;position:fixed;pointer-events:auto;display:none">
					<iframe id="memIframe" name="memIframe" scrolling="auto" allowtransparency="true" frameborder="0" src="" style="width:100%; display:block"></iframe>
				</div>
				<script type="text/javascript">
					var medialog = {}, win = $(window), timer;
					medialog.elem = $('#memListDialog');
					medialog.style = function(options) {
						var dialogo = medialog.elem;
						var titHeight = dialogo.find(".dialog-title").outerHeight() || 0;
						var btnHeight = dialogo.find(".dialog-btn-nav").outerHeight() || 0;
						dialogo.css(options);
						dialogo.find('iframe').css({
							height: parseFloat(options.height) - titHeight - btnHeight
						});
					};
					
					function closeMemListDialog() {
						medialog.elem.hide();
						window.frames["memIframe"].document.write("");
					
						if (timer) {
							clearInterval(timer);
						}
					}
					function selectNinushi() {
						var opt = {
							url: '<%=request.getContextPath()%>/memberList?selType=0'
						};
						
						$("#main").empty();
						showDialog(opt);
					}
					
					function showDialog(opt) {
						if (timer) {
							clearInterval(timer);
						}
						var dialogo = medialog.elem;
						dialogo.find('iframe').attr("src", "");
						dialogo.find('iframe').attr("src", opt.url);
				
						timer = self.setInterval(function(){
							medialog.style({
								top: 0,
								left: 0,
								width: win.width(),
								height: win.height()
							});
						}, 100);
				
						dialogo.show();
					}
					
					function setNinushi(companyCd, customCd, compayNm, officeNm, prefNm, cityNm) {
						$("#kigyo").html(companyCd);
						$("#kigyoTxt").val(companyCd);
						$("#kigyoNm").html(compayNm + " " + officeNm);
						$("#kigyoNmTxt").val(compayNm + " " + officeNm);
						$("#kigyoJusho").html(prefNm + cityNm);
						$("#kigyoJushoTxt").val(prefNm + cityNm);
		
						if (companyCd != "") {
							$.ajax( {
							    url:'getMember',
							    data:{
							             'companyCd' : companyCd
							    },
							    type:'GET',
							    dataType:'json',
							    success:function(data) {
							    	$("#ryoritu").html(data.ryoritu + "%");
									$("#ryorituTxt").val(data.ryoritu);
									$("#simbSb").html(data.simbSb);
									$("#simbSbTxt").val(data.simbSb);
									$("#sihrSt").html(data.sihrSt);
									$("#sihrStTxt").val(data.sihrSt);
							     }/* ,
							     error:function() {
							    	 alert(ankenNo + "は更新されたため、受注できません。再度検索してください。");
							     } */
							});
						} else {
							$("#ryoritu").html("");
							$("#ryorituTxt").val("");
							$("#simbSb").html("");
							$("#simbSbTxt").val("");
							$("#sihrSt").html("");
							$("#sihrStTxt").val("");
						}
						
						closeMemListDialog()
					}
			
				</script>
			<!-- memberlist popup 　▲-->
			</c:if>
		<div id="main">
			<c:if test="${seikyuAnkenListForm.seikyuAnkenList != null && seikyuAnkenListForm.seikyuAnkenList.size() == 0}">
				<div style="color: red">検索した件数が０です。</div>
			</c:if>
			<c:if test="${seikyuAnkenListForm.seikyuAnkenList.size() > 0}">
				<hr>
				<h5>件数：${seikyuAnkenListForm.seikyuAnkenList.size()}件</h5>
		
				<table width="988" align="center" class="table table-bordered table-striped" >
				  <tr>
				    <th width="149" scope="col">案件番号</th>
				    <th width="125" scope="col">集荷日付<br>納品日付</th>
				    <th width="146" scope="col">集荷先名<br>納品先名</th>
				    <th width="201" scope="col">集荷先住所<br>納品先住所</th>
				    <th width="67" scope="col">車種<br>荷姿</th>
				    <th width="78" scope="col">高速<br>料金（円）</th>
				    <th width="85" scope="col">サーチャージ<br> 料金（円）</th>
				    <th width="92" scope="col">キャンセル<br> 料金（円）</th>
				    <th width="114" scope="col">予算（円）<br>手数料（円）</th>
				  </tr>
					<c:forEach items="${seikyuAnkenListForm.seikyuAnkenList}" var="seikyuAnken" varStatus="status"> 
						<tr>
						<input id="updateDt${status.index}" name="seikyuAnkenList[${status.index}].updateDt" type="hidden" value="${seikyuAnken.updateDt}" />
						<input id="updateFlag${status.index}" name="seikyuAnkenList[${status.index}].updateFlag" type="hidden" value="false" />
					    <td>${seikyuAnken.ankenNo}<input name="seikyuAnkenList[${status.index}].ankenNo" type="hidden" value="${seikyuAnken.ankenNo}" /></td>
					    <td>${seikyuAnken.syukaDay}<br>${seikyuAnken.nohinDay}</td>
					    <td>
					    	<div align="left">${seikyuAnken.syukaNm}<br>${seikyuAnken.nohinNm}</div>
					    </td>
					    <td >
					    	<div align="left">${seikyuAnken.syukaBasho}<br>${seikyuAnken.nohinBasho}
					        </div>
					    </td>
					    <td>${seikyuAnken.syasyuNm}<br>${seikyuAnken.syukaNisugata}</td>
					    <td>
						    <c:choose>
								<c:when test="${seikyuAnken.kosokuKbn eq '0'}">
									込み<br>
								</c:when>
								<c:otherwise>
									別料金<br>
									<input name="seikyuAnkenList[${status.index}].kosokuMn" type="tel" class="form-control number" onchange="setUpdateFlag('updateFlag${status.index}')" size="5" maxlength="6" value="${seikyuAnken.kosokuMn}" />
								</c:otherwise>
							</c:choose>
					    </td>
					    <td>
						    <c:choose>
								<c:when test="${seikyuAnken.nenryoscKbn eq '0'}">
									込み<br>
								</c:when>
								<c:otherwise>
									別料金  <br><fmt:formatNumber value="${seikyuAnken.nenryoscMn}" pattern="#,#00"/>
								</c:otherwise>
							</c:choose>
						</td>
					    <td>
						    <c:choose>
								<c:when test="${seikyuAnken.status eq '90' and user.gyomuSb eq '9'}">
									キャンセル<br>
									<input name="seikyuAnkenList[${status.index}].cancelMn" type="tel" class="form-control number" onchange="setUpdateFlag('updateFlag${status.index}')" size="5" maxlength="6" value="${seikyuAnken.cancelMn}" />
								</c:when>
								<c:otherwise>
									-<br><fmt:formatNumber value="${seikyuAnken.cancelMn}" pattern="#,#00"/>
								</c:otherwise>
							</c:choose>	
						</td>
					    <td>
					    	<div align="right"><fmt:formatNumber value="${seikyuAnken.yosan}" pattern="#,#00"/><br>-<fmt:formatNumber value="${seikyuAnken.tesuryo}" pattern="#,##0"/></div>
					    </td>
					    </tr>
				  	</c:forEach>
				</table>
				  	
			    <!-- 担当者情報 ▲ -->
			
			    <!-- ボタン 　▼-->
				<div class="row" >
			       <div class="col-md-4"><a href="index" style="margin:5px;" class="btn btn-default col-md-offset-18"> ホームに戻る </a></div>
			       <div class="col-md-offset-10 col-md-4">
				       	<c:if test="${seikyuAnkenListForm.seikyuAnkenList.size() > 0}">
				       		<a href="javascript:void(0);" onclick="ryokinToroku()" style="margin:5px;" class="btn btn-primary col-md-offset-18"> 料金登録 </a>
				       	</c:if>
			       </div>
			       <div class="col-md-4">
				       	<c:if test="${seikyuAnkenListForm.seikyuAnkenList.size() > 0}">
				       		<a href="javascript:void(0);" onclick="seikyuAnkenDwl()" style="margin:5px;" class="btn btn-info col-md-offset-18"> 請求書ダウンロード </a>
				       	</c:if>
			       		
			       </div>
			
				</div>
				<!-- ボタン 　▲-->  

		</c:if>
	</div>
		</fieldset>
	  </form:form>
	  <!-- フォーム　▲-->
	
	</div>
</body>
</html>
