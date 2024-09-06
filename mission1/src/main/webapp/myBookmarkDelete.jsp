<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setAttribute("pageTitle", "위치 히스토리 목록");
	
	String myBookmarkId = request.getParameter("myBookmarkId");
	String myBookmarkName = request.getParameter("myBookmarkName");
	String wifiName = request.getParameter("wifiName");
	String registrationDate = request.getParameter("registrationDate");
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
}

tr:nth-child(even) { /* 짝수 줄 */
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f1f1f1;
}

div.actions {
	weight: 100%;
	height: 40px;
	border-left: 1px solid #ddd;
	border-right: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	border-collapse: collapse;
	text-align: center;
	display: flex;
	justify-content: center;
	align-items: center;
}

div.container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
}

a {
	margin-right: 5px;
}

button {
	margin-left: 5px;
}

</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main-header">
		<jsp:include page="mainHeader.jsp" />
	</div>

	<div>북마크를 삭제하시겠습니까?</div>

	<table class="main-table">
		<tr>
			<th>북마크 이름</th>
			<td><%= myBookmarkName %></td>
		</tr>
		<tr>
			<th>와이파이명</th>
			<td><%= wifiName %></td>
		</tr>
		<tr>
			<th>등록 일자</th>
			<td><%= registrationDate %></td>
		</tr>
	</table>
	<div class="actions">
		<div class="container">
			<form>
				<a href="myBookmarkList">돌아가기</a>
				|
				<button type="submit" onclick="handleFormSubmit(event)">삭제</button>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
	    function handleFormSubmit(event) {
	        event.preventDefault();
	
	        const myBookmarkId = <%= myBookmarkId %>;
	        
	        fetch('myBookmarkDelete', {
	        	method: 'post',
	        	headers: {
	        		'Content-Type': 'application/x-www-form-urlencoded'
	        	},
	        	body: new URLSearchParams({
	        		myBookmarkId : myBookmarkId
	        	})
	        })
	        .then(response => response.text()
	        		.then(data => ({
	        			status: response.status,
	        			body: data
	        		})))
	        		.then(result =>{
	        			if(result.status == 200){
	        				alert("북마크 정보를 삭제하였습니다.");
	        				window.location.href = "myBookmarkList";
	        			}else{
	        				alert("북마크 삭제 실패");
	        			}
	        			})
	        			.catch(error => {
	        				alert(data);
	        				})
	        }
    </script>
</body>
</html>