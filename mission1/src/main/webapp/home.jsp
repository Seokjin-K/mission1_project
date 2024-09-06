<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "와이파이 정보 구하기");
%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

div.location-title {
    margin-right: 5px;
}

input.number-box {
    width: 150px;
    border-radius: 3px;
    padding: 5px;
}

div.location-container {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

button.location-button {
    margin-left: 5px;
    padding: 5px 10px;
    border-radius: 3px;
    background-color: #32b386;
    color: white;
    border: none;
    cursor: pointer;
}

button.location-button:hover {
    background-color: #28a075;
}

table.main-table {
    width: 100%;
    margin-top: 10px;
    border-collapse: collapse;
    text-align: left;
}

th.column-name {
    width: auto;
    height: 30px;
    color: white;
    background-color: #32b386;
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
    text-style: bold;
}

tr:nth-child(even) { /* 짝수 줄 */
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #f1f1f1;
}

</style>

<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<div class="main-header">
		<jsp:include page="mainHeader.jsp"/>
	</div>
	
	<div class="location-container">
		<div class="location-title">LAT:</div>
		<input id="lat" class="number-box" type="number" min="-90" max="90">
		
		<div class="location-title">, LNT:</div>
		<input id="lnt" class="number-box" type="number" min="-180" max="180">
		
		<button class="location-button" onclick="getLocation()">내 위치 가져오기</button>
		<button class="location-button" onclick="getWifiInfo()">근처 WIFI 정보 보기</button>
	</div>
	
	<table class="main-table">
		<thead>
			<tr>
				<th class="column-name">거리 (km)</th>
				<th class="column-name">관리번호</th>
				<th class="column-name">자치구</th>
				<th class="column-name">와이파이명</th>
				<th class="column-name">도로명 주소</th>
				<th class="column-name">상세 주소</th>
				<th class="column-name">설치 위치 (층)</th>
				<th class="column-name">설치 유형</th>
				<th class="column-name">설치 기관</th>
				<th class="column-name">서비스 구분</th>
				<th class="column-name">망 종류</th>
				<th class="column-name">설치 년도</th>
				<th class="column-name">실·내외 구분</th>
				<th class="column-name">WIFI 접속 환경</th>
				<th class="column-name">X 좌표</th>
				<th class="column-name">Y 좌표</th>
				<th class="column-name">작업 일자</th>
			</tr>
		</thead>
		<tbody id="wifi-table-body">
		</tbody>
	</table>
	
	<script type="text/javascript">
        function getLocation() {
            if (navigator.geolocation) { // 브라우저에서 위치 기반 서비스 제공 API
            	// 현재 위치를 가져오기 성공하면 showPosition() 호출, 실패하면 showError() 호출
                navigator.geolocation.getCurrentPosition(setLocation, showError);
            } else {
                alert("브라우저가 위치 정보 조회를 지원하지 않습니다.");
            }
        }

        function setLocation(position) {
            var lat = position.coords.latitude; // 위도
            var lnt = position.coords.longitude; // 경도
            
            // 위도와 경도를 input 요소에 설정
            document.getElementById('lat').value = lat;
            document.getElementById('lnt').value = lnt;
        }

        function showError(error) {
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    alert("브라우저에서 위치 요청 거부");
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("위치 정보 사용할 수 없음");
                    break;
                case error.TIMEOUT:
                    alert("위치 정보 요청 시간 초과");
                    break;
                case error.UNKNOWN_ERROR:
                    alert("알 수 없는 오류 발생");
                    break;
            }
        }
        
        function getWifiInfo(){
            var lat = document.getElementById('lat').value;
            var lnt = document.getElementById('lnt').value;

            if (!lat || !lnt) {
                alert("위도와 경도를 입력하세요.");
                return;
            }

            fetch('/mission1/wifi?lat=' + lat + '&lnt=' + lnt)
            .then(response => {
                if (!response.ok) {
                    return response.text().then(error => {throw new error.message});
                }
                return response.json();
            })
            .then(data => {
                var wifiTableBody = document.getElementById('wifi-table-body');
                wifiTableBody.innerHTML = '';
                
                if (data.length === 0) {
                    // 데이터가 없을 경우, 메시지를 출력
                    wifiTableBody.innerHTML = '<tr class="no-wifi-info"><td colspan="17">검색된 Wi-Fi 정보가 없습니다.</td></tr>';
                } else {
                    let rows = '';
                    data.forEach(function(wifi) {
                        var wifiJson = encodeURIComponent(JSON.stringify(wifi));
                        
                        rows += '<tr>' +
                        '<td>' + wifi.distance + '</td>' +
                        '<td>' + wifi.mgrNo + '</td>' +
                        '<td>' + wifi.borough + '</td>' +
                        '<td><a href="/mission1/wifiDetails?wifi=' + wifiJson + '">' + wifi.ssid + '</a></td>' +
                        '<td>' + wifi.address + '</td>' +
                        '<td>' + wifi.detailAddress + '</td>' +
                        '<td>' + wifi.installFloor + '</td>' +
                        '<td>' + wifi.installType + '</td>' +
                        '<td>' + wifi.installAgency + '</td>' +
                        '<td>' + wifi.serviceType + '</td>' +
                        '<td>' + wifi.networkType + '</td>' +
                        '<td>' + wifi.installYear + '</td>' +
                        '<td>' + wifi.indoorOutdoor + '</td>' +
                        '<td>' + wifi.connectionEnv + '</td>' +
                        '<td>' + wifi.xCoord + '</td>' +
                        '<td>' + wifi.yCoord + '</td>' +
                        '<td>' + wifi.workDate + '</td>' +
                    '</tr>';
                    });
                    wifiTableBody.innerHTML = rows;
                }
            })
            .catch(error => {
                alert('error: ' + error.message);
            });
        }
    </script>
</body>
</html>