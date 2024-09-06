<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style>

div.container {
	text-align: center;
}

h1.count {
	font-size: 35px;
	font-weight: bold;
}

</style>

<meta charset="UTF-8">
<title>Open API 와이파이 정보 저장하기</title>
</head>
<body>
	<div class="container">
		<h1 id="wifiCount" class="count"><%= request.getAttribute("totalWifiCount") %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
		<a href="home.jsp">홈으로 가기</a>
	</div>
</body>
</html>