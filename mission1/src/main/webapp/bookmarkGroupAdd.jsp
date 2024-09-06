<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

</style>

<meta charset="UTF-8">
<title>북마크 그룹 추가</title>
</head>
<body>
	<div class="main-header">
		<jsp:include page="mainHeader.jsp" />
	</div>

	<form onsubmit="handleSubmit(event)">
		<table class="main-table">
			<tr>
				<th>북마크 이름</th>
				<td>
					<input type="text" id="bookmarkName" name="bookmarkName">
				</td>
			</tr>
			<tr>
				<th class="name">순서</th>
				<td>
					<input type="text" id="sequence" name="sequence">
				</td>
			</tr>

		</table>

		<div class="actions">
			<button type="submit">추가</button>
		</div>
	</form>
	
	<script type="text/javascript">
	 
	function handleSubmit(event) {
        event.preventDefault();
        
        const bookmarkName = document.getElementById("bookmarkName").value;
        const sequence = document.getElementById("sequence").value;

        if (bookmarkName && sequence) {
            fetch('bookmarkAdd', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    bookmarkName: bookmarkName,
                    sequence: sequence
                })
            })
            .then(response => response.text()
            		.then(data => ({ 
            	status: response.status, 
            	body: data 
            	}))).then(result => {
                if (result.status === 200) {
                    alert("북마크 그룹 정보를 추가하였습니다.");
                    window.location.href = "bookmarkGroup";
                } else {
                    alert(result.body);
                }
            })
            .catch(error => {
                alert( error);
            });
        } else {
            alert("모든 필드를 입력하세요.");
        }
    }
	
	</script>
</body>
</html>