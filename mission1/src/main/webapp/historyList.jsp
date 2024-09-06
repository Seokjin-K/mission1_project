<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "위치 히스토리 목록");
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
<title>위치 히스토리 목록</title>
</head>
<body>
	<div class="main-header">
		<jsp:include page="mainHeader.jsp"/>
	</div>
	
	<table class="main-table">
		<thead>
			<tr>
				<th class="column-name">ID</th>
				<th class="column-name">X 좌표</th>
				<th class="column-name">Y 좌표</th>
				<th class="column-name">조회 일자</th>
				<th class="column-name">비고</th>
			</tr>
		</thead>
		<tbody id="history-tableB=body">
			<c:forEach var="history" items="${historyList}">
                <tr id="row${history.historyId}">
                    <td>${history.historyId}</td>
                    <td>${history.xCoord}</td>
                    <td>${history.yCoord}</td>
                    <td>${history.checkDate}</td>
                    <td>
                    	<button onclick="deleteHistory(${history.historyId})">삭제</button>
                    </td>
                </tr>
            </c:forEach>
		</tbody>
	</table>
	
	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		// ajax를 이용하여 서버에 비동기 요청
		function deleteHistory(historyId) {
	        if (confirm("해당 기록을 삭제하시겠습니까?")) {
	            $.ajax({
	                url: 'historyList',
	                type: 'POST',
	                data: { deleteId: historyId },
	                success: function(response) {
	                    $('#row' + historyId).remove();
	                },
	                error: function(xhr, status, error) {
	                    alert("Error: 삭제 실패");
	                }
	            });
	        }
		}
		
	</script>
	
</body>
</html>