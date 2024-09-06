<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL -->
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
div.title {
    font-weight: bold;
    font-size: 30px;
    margin-top: 15px;
    margin-bottom: 15px;
}

div.category {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

div.category-home{
	margin-right: 5px;
}

div.category-title {
	margin-right: 5px;
	margin-left: 5px;
}
</style>

<meta charset="UTF-8">
<title>Main Header</title>
</head>
<body>
	<div class="title">${pageTitle}</div>
		<div class="category">
			<div class="category-home">
				<a href="home.jsp">홈</a>
			</div>
			|
			<div class="category-title">
				<a href="historyList">위치 히스토리 목록</a>
			</div>
			|
			<div class="category-title">                        
				<a href="loadWifi">Open API 와이파이 정보 가져오기</a>
			</div>
			|
			<div class="category-title">
				<a href="myBookmarkList">즐겨 찾기 보기</a>
			</div>
			|
			<div class="category-title">
				<a href="bookmarkGroup">즐겨 찾기 그룹 관리</a>
			</div>
	</div>
</body>
</html>