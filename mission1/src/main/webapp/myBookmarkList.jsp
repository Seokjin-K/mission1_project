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
<title>Insert title here</title>
</head>
<body>
	<div class="main-header">
		<jsp:include page="mainHeader.jsp" />
	</div>
	
	<table class="main-table">
		<thead>
			<tr>
				<th class="column-name">ID</th>
				<th class="column-name">북마크 이름</th>
				<th class="column-name">와이파이명</th>
				<th class="column-name">등록 일자</th>
				<th class="column-name">비고</th>
			</tr>
		</thead>
		<tbody id="my-bookmark_list-table-body">
			<c:forEach var="myBookmark" items="${myBookmarkList}">
                <tr id="row${myBookmark.myBookmarkId}">
                	<td>${myBookmark.myBookmarkId}</td>
                    <td>${myBookmark.myBookmarkName}</td>
                    <td>${myBookmark.wifiName}</td>
                    <td>${myBookmark.registrationDate}</td>
                    <td>
                    	<a href="myBookmarkDelete.jsp?myBookmarkId=${myBookmark.myBookmarkId}&myBookmarkName=${myBookmark.myBookmarkName}
                    	&wifiName=${myBookmark.wifiName}&registrationDate=${myBookmark.registrationDate}">
                    		삭제
                    	</a>
                    </td>
                </tr>
            </c:forEach>
		</tbody>
	</table>
</body>
</html>