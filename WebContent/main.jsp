<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("ValidMem") == null ) {
%>
<jsp:forward page="login.jsp" />
<% 
}

String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
%>
<script>
function logout() {
	if(confirm("로그아웃 하시겠습니까?") == true){
		   location.href="logout.jsp";
	} else {
		return false;
	}
}
</script>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

    <h1><%= name %> 님 안녕하세요?</h1>
        <button onclick="javascript:logout()">로그아웃</button>&nbsp;&nbsp;&nbsp;
        <button onclick="javascript:window.location='modify.jsp'">정보수정</button>
</body>
</html>