<!DOCTYPE html>
<html lang="en">
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

	<%@ include file="common/header.jsp"%>

<style>
  p{padding:0px;margin:0px;}
  p.no_wrap{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	-o-text-overflow: ellipsis; /* Opera9,10対応 */
  }
  hr.spacer{margin:5px; border:0px;}
  .hoverDiv {padding:10px; background: #fff; border-bottom:1px solid #666;}
  .hoverDiv:hover {background-color:  #F0F0F0; cursor: pointer;}

  </style>
<script type="text/javascript">
	function getSysDate(){
		// yyyy/MM/dd (N)
		var date = new Date();
		var daysShort = ["日","月","火","水","木","金","土"];
		return date.getFullYear() + "/"+ ((date.getMonth() + 1) >= 10 ? (date.getMonth() + 1) : "0"
            + (date.getMonth() + 1)) + "/" +  (date.getDate() < 10 ? "0" + date.getDate() : date
                    .getDate()) + " (" + daysShort[date.getDay()] +")";
	}

	function init(clear) {
		$("#todohuenLink").html("全国");
		$("#todohuen").val("");
		$("#sikutyosonLink").html("");
		$("#sikutyoson").val("");
		$("input[name='syasyuList']").val([]);
		if (clear) {
			$("#syukaDayFr").val("");
		} else {
			$("#syukaDayFr").val(getSysDate());
		}
		$("#syukaDayTo").val("");
		$("input[name='opList']").val([]);
		$("#ankenNo").val("");
		if (clear) {
			$("input[name='ankenStatusList']").val([]);
		} else {
			$("input[name='ankenStatusList']").val(["10"]);
		}
		$("#selectbasic").val("0");

		$("#ninushiCdLabel").html('');
		$("#seikyuKsCdHidden").val('');
		$("#seikyuKsNmHidden").val('');
		$("#seikyuKsAddrHidden").val('');
		$("#ninushiNmLabel").html('');
		$("#ninushiAddrLabel").html('');

		$("#unsoCdLabel").html('');
		$("#shihraiKsCdHidden").val('');
		$("#shihraiKsNmHidden").val('');
		$("#shihraiKsAddrHidden").val('');
		$("#unsoNmLabel").html('');
		$("#unsoAddrLabel").html('');
	}

    $(function() {
		var ankenSearchForm = eval(${ankenSearchForm.json});
    	if(ankenSearchForm) {
    		$("#todohuenLink").html(ankenSearchForm.prefNameList.length == 0 ? "全国" : ankenSearchForm.prefNameList.join(","));
    		$("#todohuen").val(ankenSearchForm.todohuen);
    		$("#sikutyosonLink").html(ankenSearchForm.cityNameList.join(","));
    		$("#sikutyoson").val(ankenSearchForm.sikutyoson);
    		$("input[name='syasyuList']").val(ankenSearchForm.syasyuList);
    		$("#syukaDayFr").val(ankenSearchForm.syukaDayFr);
    		$("#syukaDayTo").val(ankenSearchForm.syukaDayTo);
    		$("input[name='opList']").val(ankenSearchForm.opList);
    		$("#ankenNo").val(ankenSearchForm.ankenNo);
    		$("input[name='ankenStatusList']").val(ankenSearchForm.ankenStatusList);
    		$("#selectbasic").val(ankenSearchForm.sortId);

			$("#ninushiCdLabel").html(ankenSearchForm.seikyuKsCd);
			$("#seikyuKsCdHidden").val(ankenSearchForm.seikyuKsCd);
			$("#seikyuKsNmHidden").val(ankenSearchForm.seikyuKsNm);
			$("#seikyuKsAddrHidden").val(ankenSearchForm.seikyuKsAddr);
			$("#ninushiNmLabel").html(ankenSearchForm.seikyuKsNm);
			$("#ninushiAddrLabel").html(ankenSearchForm.seikyuKsAddr);

			$("#unsoCdLabel").html(ankenSearchForm.shihraiKsCd);
			$("#shihraiKsCdHidden").val(ankenSearchForm.shihraiKsCd);
			$("#shihraiKsNmHidden").val(ankenSearchForm.shihraiKsNm);
			$("#shihraiKsAddrHidden").val(ankenSearchForm.shihraiKsAddr);
			$("#unsoNmLabel").html(ankenSearchForm.shihraiKsNm);
			$("#unsoAddrLabel").html(ankenSearchForm.shihraiKsAddr);
    	} else {
    		init();
    	}

		$('.input-group.date').datepicker({
		  format : 'yyyy/mm/dd (D)',
		  startDate: "2015/5/19",
		  language: 'ja',
		  orientation: 'top left',
		  autoclose: true
		});
		$('label[required]').css('color', 'Red');

		setInterval(function(){
			$('.label-warning1').fadeOut(500,function(){$(this).fadeIn(500)});
		},2000);

		$(document).on('click', '.hoverDiv',function(){
			location.href='anken_detail_unso.html';
		});

		$('.collapse').on('shown.bs.collapse', function(){
			$(this).parent().find(".glyphicon-chevron-down").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
		}).on('hidden.bs.collapse', function(){
			$(this).parent().find(".glyphicon-chevron-up").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		});

		$(document).on('click', '#clear',function(event){
			init(true);
		});

		$(document).on('click', '#search',function(event){
			var syukaDayFr = $("#syukaDayFr").val();
			var syukaDayTo = $("#syukaDayTo").val();

			if (syukaDayFr.length !=0 && syukaDayTo.length != 0) {
				syukaDayFr = syukaDayFr.substr(0, 10).replace(/\//g, "");
				syukaDayTo = syukaDayTo.substr(0, 10).replace(/\//g, "");
				if (syukaDayFr > syukaDayTo) {
					alert("集荷日付終了より、集荷日付開始が大きいです。");
					return;
				}
			}
			$("form").submit();
		});

		$(document).on('click', '#todohuenLink',function(event){
			$("form").attr("action", "submit_todohuen");
			$("form").submit();
		});

		$(document).on('click', '#sikutyosonLink',function(event){
			if (!$("#sikutyosonLink").disabled) {
				$("form").attr("action", "submit_sikutyoson");
				$("#prefName").val($("#todohuen").val());
				$("form").submit();
			}
		});

		if ($("#todohuen").val() == "" || $("#todohuen").val().indexOf(",") != -1) {
			$("#sikutyosonLink").html("");
			$("#sikutyoson").val("");
		} else {
			if (ankenSearchForm.cityNameList.length == 0) {
				$("#sikutyosonLink").html("全て");
				$("#sikutyoson").val("");
			}
		}
    });

  </script>
</head>
<body>
<div class="container">
	<%@ include file="common/nav.jsp"%>

 <!-- フォーム　▼ -->
  <form:form class="form-horizontal" name="ankenSearchForm" commandName="ankenSearchForm" action="ankenSearch" method="POST">
    <fieldset>
   	<c:if test="${showErrorMessFlag}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div>${errormessage}</div>
				</div>
	</c:if>
	<input name="func" type="hidden" value="ankenSearch">
	<input id="prefName" name="prefName" type="hidden" value="">
  <!-- 検索条件　▼ -->
	<div class="panel panel-default">
      <!-- 検索条件(heading) 　▼ -->
		<div class="panel-heading">
		  <h4 class="panel-title">
				  詳細検索
		  </h4>
		</div>
      <!-- 検索条件(body) 　▼ -->
      <div id="search_detail" class="panel-body">
	      <c:if test="${user.gyomuSb == '9'}">
			<div class="form-group  form-inline">
	 			<label class="col-md-2 control-label">荷主</label>
	 			<div class="col-md-5  control-label">
	 				<label id="ninushiCdLabel" class="col-md-7 control-label"></label>
					<a href="javascript:;" class="btn btn-info btn-sm" id="" onclick="selectNinushi()">選択</a>
				</div>
				<input id="seikyuKsCdHidden" type="hidden" name="seikyuKsCd">
				<input id="seikyuKsNmHidden" type="hidden" name="seikyuKsNm">
				<input id="seikyuKsAddrHidden" type="hidden" name="seikyuKsAddr">
				<label class="col-md-2 control-label">荷主名称</label>
				<label id="ninushiNmLabel" class="col-md-6 control-label"></label>
				<label class="col-md-2 control-label">荷主住所</label>
				<label id="ninushiAddrLabel" class="col-md-5 control-label"></label>
			</div>
			<div class="form-group  form-inline">
				<label class="col-md-2 control-label" for="textinput">運送</label>
				<div class="col-md-5  control-label">
					<label id="unsoCdLabel" class="col-md-7 control-label"></label>
					<a href="javascript:;" class="btn btn-info btn-sm" id="" onclick="selectUnso()">選択</a>
				</div>
				<input id="shihraiKsCdHidden" type="hidden" name="shihraiKsCd">
				<input id="shihraiKsNmHidden" type="hidden" name="shihraiKsNm">
				<input id="shihraiKsAddrHidden" type="hidden" name="shihraiKsAddr">
				<label class="col-md-2 control-label">運送会社</label>
				<label id="unsoNmLabel" class="col-md-6 control-label"></label>
				<label class="col-md-2 control-label">運送住所</label>
				<label id="unsoAddrLabel" class="col-md-5 control-label"></label>
			</div>
			<!-- <div class="form-group  form-inline">
				<label class="col-md-3 control-label" for="textinput">荷主</label>
				<div class="col-md-4  control-label">A001-01&nbsp;&nbsp;&nbsp;<a href="member_search.html" class="btn btn-info btn-sm" id="hisory-syuka-0" >選択</a></div>
				<label class="col-md-2 control-label" for="textinput">荷主名称</label>
				<div class="col-md-5  control-label">荷主株式会社 東京本社</div>
				<label class="col-md-3 control-label" for="textinput">荷主住所</label>
				<div class="col-md-5  control-label">東京都中央区日本橋</div>
	        </div>
			<div class="form-group  form-inline">
				<label class="col-md-3 control-label" for="textinput">運送会社</label>
				<div class="col-md-4  control-label">B001-01&nbsp;&nbsp;&nbsp;<a href="member_search.html" class="btn btn-info btn-sm" id="hisory-syuka-0" >選択</a></div>
				<label class="col-md-2 control-label" for="textinput">運送会社</label>
				<div class="col-md-5  control-label">運送株式会社</div>
				<label class="col-md-3 control-label" for="textinput">運送会社住所</label>
				<div class="col-md-5  control-label">神奈川県厚木市</div>
	        </div> -->
	      </c:if>
		<div class="form-group form-inline">
          <label class="col-md-3 control-label" >都道府県</label>
          <label class="col-md-4 control-label">
		  <a id="todohuenLink">全国</a>
		  <input id="todohuen" name="todohuen" type="hidden" value=""/>
		  </label>
          <label class="col-md-3 control-label" >市区町村</label>
          <label class="col-md-4 control-label">
		  <a id="sikutyosonLink">全て</a>
		  <input id="sikutyoson" name="sikutyoson" type="hidden" value=""/>
		  </label>
		</div>
		<div class="form-group form-inline">
          <label class="col-md-3 control-label" >車種</label>
          <div class="col-md-21">
          	<c:forEach var="syasyu" items="${syasyuList}">
	          	<label class="checkbox-inline" for="syasyu-${syasyu.syasyuCd}">
	            <input type="checkbox" name="syasyuList" id="syasyu-${syasyu.syasyuCd}" value="${syasyu.syasyuCd}">
	            ${syasyu.syasyuName} </label>
          	</c:forEach>
          </div>
        </div>
		<div class="form-group form-inline">
          <label class="col-md-3 control-label">集荷日付</label>
          <div class="col-md-21">
		    <div class="input-group date">
              <input id="syukaDayFr" name="syukaDayFr" type="text" class="form-control" value="" size="12">
              <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
		    </div>
			<p class="form-control-static">～</p>
		    <div class="input-group date">
              <input id="syukaDayTo" name="syukaDayTo" type="text" class="form-control" value="" size="12">
              <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
		    </div>
		  </div>
		</div>
		<div class="form-group form-inline">
 		  <label class="col-md-3 control-label">オプション</label>
          <div class="col-md-21">
     	    <c:forEach var="option" items="${truckOpList}">
	          	<label class="checkbox-inline" for="option-${option.opCd}">
	            <input type="checkbox" name="opList" id="option-${option.opCd}" value="${option.opCd}">
	            ${option.opName} </label>
          	</c:forEach>
          </div>
		</div>
        <div class="form-group form-inline" >
          <label class="col-md-3 control-label" for="textinput">案件番号</label>
          <div class="col-md-6">
            <input id="ankenNo" name="ankenNo" type="text" maxlength="23" class="form-control input-md" size="23" value="">
          </div>
          <label class="col-md-3 control-label">案件状態</label>
          <div class="col-md-12">
            <label class="checkbox-inline" for="status-0">
            <input type="checkbox" name="ankenStatusList" id="status-0" value="10">
            登録済 </label>
            <label class="checkbox-inline" for="status-1">
            <input type="checkbox" name="ankenStatusList" id="status-1" value="20">
            確定 </label>
            <label class="checkbox-inline" for="status-2">
            <input type="checkbox" name="ankenStatusList" id="status-2" value="30">
            車番登録済み </label>
            <label class="checkbox-inline" for="status-3">
            <input type="checkbox" name="ankenStatusList" id="status-3" value="40">
            集荷完了 </label>
            <label class="checkbox-inline" for="status-4">
            <input type="checkbox" name="ankenStatusList" id="status-4" value="90">
            キャンセル </label>
          </div>
        </div>
        <div class="form-group form-inline" >
          <label class="col-md-3 control-label" for="selectbasic">並べ替え</label>
  		  <div class="col-md-3">
            <select id="selectbasic" name="sortId" class="form-control ">
              <option value="0" selected>予算が高い順</option>
              <option value="1">集荷日時が近い順</option>
              <option value="2">車種順</option>
            </select>
		  </div>
        </div>
		<div class="form-group form-inline">
		  <a id="search" class="btn btn-info col-md-offset-12" style="width:120px;">検　索</a>
		  <a href="index" class="btn btn-default col-md-offset-1"> 前画面に戻る </a>
		  <a id="clear" class="btn btn-default col-md-offset-1"> 条件をクリア </a>
		</div>

      </div>
      <!-- 基本情報(body) 　▲ -->
    </div>
  <!-- 検索条件　▲-->

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
					url: '<%=request.getContextPath()%>/memberList?selType=2'
				};
				showDialog(opt);
			}

			function setNinushi(companyCd, customCd, compayNm, officeNm, prefNm, cityNm) {
				$("#ninushiCdLabel").html(companyCd);
				$("#seikyuKsCdHidden").val(companyCd);
				$("#seikyuKsNmHidden").val(compayNm + " " + officeNm);
				$("#seikyuKsAddrHidden").val(prefNm + cityNm);
				$("#ninushiNmLabel").html(compayNm + " " + officeNm);
				$("#ninushiAddrLabel").html(prefNm + cityNm);

				closeMemListDialog()
			}

			function selectUnso() {
				var opt = {
					url: '<%=request.getContextPath()%>/memberList?selType=1'
				};
				showDialog(opt);
			}

			function setUnso(companyCd, customCd, compayNm, officeNm, prefNm, cityNm) {
				$("#unsoCdLabel").html(companyCd);
				$("#shihraiKsCdHidden").val(companyCd);
				$("#shihraiKsNmHidden").val(compayNm + " " + officeNm);
				$("#shihraiKsAddrHidden").val(prefNm + cityNm);
				$("#unsoNmLabel").html(compayNm + " " + officeNm);
				$("#unsoAddrLabel").html(prefNm + cityNm);

				closeMemListDialog()
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

		</script>
	<!-- memberlist popup 　▲-->
	</c:if>

	</fieldset>
  </form:form>
  <!-- フォーム　▲-->


</div>
</body>
</html>
