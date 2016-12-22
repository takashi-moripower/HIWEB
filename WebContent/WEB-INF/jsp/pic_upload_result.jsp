<!DOCTYPE html>
<html lang="en">
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=yes" />

<%@ include file="common/header.jsp"%>

</head>

<body>


<script type="text/javascript">
	var data = {};
	data.resCd = "${resCd}";
	data.idx = "${currPic}";
	data.picNm = "${picNm}";
	data.picTmpNm = "${picTmpNm}";
	data.picUrl = "${picPath}";
	parent.setImg(data);
</script>
</body>
</html>
