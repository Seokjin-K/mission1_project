<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "위치 히스토리 목록");
%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

div.container {
	display: flex;
    align-items: center;
    margin-bottom: 20px;
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
<title>즐겨찾기 그룹 관리</title>
</head>
<body>

	<div class="main-header">
		<jsp:include page="mainHeader.jsp"/>
	</div>
	
	<div class="container">
		<button type="button" onclick="moveToPage()">북마크 그룹 이름 추가</button>
	</div>
	
	<table class="main-table">
		<thead>
			<tr>
				<th class="column-name">ID</th>
				<th class="column-name">북마크 이름</th>
				<th class="column-name">순서</th>
				<th class="column-name">등록 일자</th>
				<th class="column-name">수정 일자</th>
				<th class="column-name">비고</th>
			</tr>
		</thead>
		<tbody id="bookmark-group-table-body">
			<c:forEach var="bookmark" items="${bookmarkList}">
                <tr id="row${bookmark.bookmarkId}">
                	<td>${bookmark.bookmarkId}</td>
                    <td>${bookmark.bookmarkName}</td>
                    <td>${bookmark.sequence}</td>
                    <td>${bookmark.registrationDate}</td>
                    <td>${bookmark.modifyDate}</td>
                    <td>
	                    <div>
	                    	<a href="bookmarkModify.jsp?bookmarkId=${bookmark.bookmarkId}&bookmarkName=${bookmark.bookmarkName}&sequence=${bookmark.sequence}">수정 </a>
	                    	<!-- 아래에 아이디, 이름, 순서 전송-->
	                    	<a href="bookmarkGroupDelete.jsp?bookmarkId=${bookmark.bookmarkId}&bookmarkName=${bookmark.bookmarkName}
	                    	&sequence=${bookmark.sequence}">삭제</a>
	                    </div>
                    </td>
                </tr>
            </c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
	    function moveToPage() {
	        window.location.href = 'bookmarkGroupAdd.jsp';
	    }
</script>
</body>
</html>