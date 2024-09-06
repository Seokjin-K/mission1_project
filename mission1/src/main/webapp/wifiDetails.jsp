<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "와이파이 정보 구하기");
%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

table.main-table {
    width: 100%;
    margin-top: 10px;
    border-collapse: collapse;
    text-align: left;
}

th {
	width: 300px;
    height: auto;
    color: white;
    background-color: #32b386;
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

td {
    padding: 10px;
    border: 1px solid #ddd;
    text-style: bold;
}

tr:nth-child(even) { /* 짝수 줄 */
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #f1f1f1;
}

div.container {
    display: flex;
    align-items: center;
    margin: 20px;
}

</style>

    <meta charset="UTF-8">
    <title>와이파이 상세 보기</title>
</head>
<body>
    <div class="main-header">
		<jsp:include page="mainHeader.jsp"/>
	</div>
	
	<div class="container">
		<form onsubmit="handleFormSubmit(event)">
			<select id="select">
				<option value="">북마크 그룹 이름 선택</option>
				<c:forEach var="bookmark" items="${bookmarkList}">
                    <option value="${bookmark.bookmarkName}">${bookmark.bookmarkName}</option>
                </c:forEach>
			</select>
			<button type="submit">즐겨찾기 추가하기</button>
		</form>
	</div>
	
    <table class="main-table">
        <tr>
            <th>거리 (km)</th>
            <td>${distance}</td>
        </tr>
        <tr>
            <th>관리 번호</th>
            <td>${mgrNo}</td>
        </tr>
        <tr>
            <th>자치구</th>
            <td>${borough}</td>
        </tr>
        <tr>
            <th><a>와이파이명</a></th>
            <td>${ssid}</td>
        </tr>
        <tr>
            <th>도로명 주소</th>
            <td>${address}</td>
        </tr>
        <tr>
            <th>상세 주소</th>
            <td>${detailAddress}</td>
        </tr>
        <tr>
            <th>설치 위치(층)</th>
            <td>${installFloor}</td>
        </tr>
        <tr>
            <th>설치 유형</th>
            <td>${installType}</td>
        </tr>
        <tr>
            <th>설치 기관</th>
            <td>${installAgency}</td>
        </tr>
        <tr>
            <th>서비스 구분</th>
            <td>${serviceType}</td>
        </tr>
        <tr>
            <th>망 종류</th>
            <td>${networkType}</td>
        </tr>
        <tr>
            <th>설치 년도</th>
            <td>${installYear}</td>
        </tr>
        <tr>
            <th>실·내외 구분</th>
            <td>${indoorOutdoor}</td>
        </tr>
        <tr>
            <th>WIFI 접속 환경</th>
            <td>${connectionEnv}</td>
        </tr>
        <tr>
            <th>X 좌표</th>
            <td>${xCoord}</td>
        </tr>
        <tr>
            <th>Y 좌표</th>
            <td>${yCoord}</td>
        </tr>
        <tr>
            <th>작업 일자</th>
            <td>${workDate}</td>
        </tr>
    </table>
    
    <script type="text/javascript">
	    function handleFormSubmit(event) {
	        event.preventDefault();
	
	        const bookmarkName = document.getElementById("select").value;
	        const ssid = '${ssid}';
	        
	        if(bookmarkName && ssid) {
	            fetch('myBookmarkAdd', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/x-www-form-urlencoded'
	                },
	                body: new URLSearchParams({
	                    bookmarkName: bookmarkName,
	                    ssid: ssid
	                })
	            })
	            .then(response => response.text().then(message => ({
	                status: response.status,
	                message: message
	            })))
	            .then(result => {
	                if (result.status === 200) {
	                    alert(result.message);
	                    window.location.href = "myBookmarkList";
	                } else {
	                    alert("Error: " + result.message);
	                }
	            })
	            .catch(error => {
	                alert("오류가 발생했습니다: " + error.message);
	            });
	        }
	    }
    </script>
</body>
</html>