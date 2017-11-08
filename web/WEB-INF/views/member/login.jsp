
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./js/jquery-1.8.3.min.js">
    <link rel="stylesheet" href="./css/creative.min.css">
    <link rel="stylesheet" href="./css/creative.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="./css/creative.css"></script>
    <script src="./css/creative.min.css"></script>
    <title>로그인 페이지</title>
</head>
<body>
<h2 align="center"><p class="bg-danger">로그인</p></h2>
<div class="container">
<form method="post" action="/login/loginCheck.do">
    <table class="table" border="1" width="400px">
        <tbody>
        <tr>
            <td>아이디</td>
            <td><input name="userid" id="userid"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="userpw" id="userpw"></td>
        </tr>
        </tbody>
        <tr>
            <td colspan="2" align="center">
                 <input type="submit" value="로그인"/>
                <c:if test="${msg == 'failure'}">
                    <div style="color: red">
                        아이디 또는 비밀번호가 일치하지 않습니다.
                    </div>
                </c:if>
                <c:if test="${msg == 'logout'}">
                    <div style="color: red">
                        로그아웃되었습니다.
                    </div>
                </c:if>
            </td>
        </tr>
    </table>
</form>
</div>
<div class="container" align="center">
    <input type="button" value="회원가입" onclick="location.href='/member/signupForm.do'">
</div>
</body>
</html>
