	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
	   function log_out(){
			if (confirm('ログアウトします。よろしいですか？')) {
				window.location.href="<%= request.getContextPath() %>/logout";
			}
			return false;
	   }
	</script>

	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
		<nav class="navbar navbar-default ">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="<%= request.getContextPath() %>/index" data-toggle="tooltip" title="メニュー画面に戻ります">
				<c:choose>
					<c:when test="${not empty pageTitle}">
						${pageTitle}
					</c:when>
					<c:otherwise>
						配送マッチングシステム
					</c:otherwise>
				</c:choose>
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${not empty sessionScope.user.username}">
							<li><a href="#" onClick="log_out();" data-toggle="tooltip" title="ログアウトします"><span class="glyphicon glyphicon-user"></span>
									${sessionScope.user.username} 　様</a></li>
						</c:when>
						<c:otherwise>
							<c:if test="${not empty ownerName}">
								<li><a href="#" onClick="log_out();" data-toggle="tooltip" title="ログアウトします"><span class="glyphicon glyphicon-user"></span>
										${ownerName} 　様</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
	<!-- <c:if test="${showErrorMessFlag}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<form:errors path="*"></form:errors>
		<div>${errormessage}</div>
	</div>
	</c:if> -->

	<c:if test="${message != null and message != ''}">
		<div class="alert alert-info alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<div>${message}</div>
		</div>
	</c:if>