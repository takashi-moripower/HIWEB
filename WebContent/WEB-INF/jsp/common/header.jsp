
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title><c:choose>
		<c:when test="${not empty tabtitle}">
			${tabtitle}
		</c:when>
		<c:otherwise>
			配送マッチング-ログイン画面
		</c:otherwise>
	</c:choose></title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-customize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/maincontent.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/locales/bootstrap-datepicker.ja.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/customize.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Constants.js"></script>


<script type="text/javascript">

</script>
