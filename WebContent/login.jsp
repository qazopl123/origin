<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
<jsp:forward page="main.jsp" />
<%} %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 Page</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
   function login_check() {
      if($('#id').val().length == 0){
          alert("아이디를 입력하세요.");
          $('#id').focus();
          return;
       }
      if($('#pw').val().length == 0){
          alert("비밀번호를 입력하세요.");
          $('#pw').focus();
          return;
       }
      submit_Ok();   
   }
   
   function submit_Ok() {
       var queryString = $("#reg_frm").serialize();
         $.ajax({
             url: '/Jsp25_4_Ajax/loginOk.do',
             type: 'POST',
             data: queryString,
             dataType: 'text',
             success: function(json)
             {
               //console.log(json);
                 var result = JSON.parse(json);
                 if(result.code == "success") {
                     alert(result.desc)
                     window.location.replace("main.jsp");
                 } else if (resule.code == "loginFail") {
                	 alert(result.desc);
                 } else if (result.code == "fail"){
                	 alert(result.desc);
                 }
             }
         });
   }
</script>
</head>
<body>
    <form name="reg_frm" id="reg_frm">
        아이디 : <input type="text" name="id" id="id"
                                value="<% if(session.getAttribute("id") != null )
                                	out.println(session.getAttribute("id")); %>"><br>
        비밀번호 : <input type="password" name="pw" id="pw"><br><p>
        <input type="button" value="로그인" onclick="login_check()"> &nbsp;&nbsp;&nbsp;
        <input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
    </form>
</body>
</html>