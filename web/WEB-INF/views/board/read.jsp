<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%  String id= (String) session.getAttribute("id");
    String boardUserId= (String)session.getAttribute("boardUserId");
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./js/jquery-1.8.3.min.js">
    <link rel="stylesheet" href="./css/creative.min.css">
    <link rel="stylesheet" href="./css/creative.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>게시글 작성</title>
    <script type="text/javascript">
        $(document).ready(function(){

            $("#btnList").click(function(){
                console.log("btnList");
                if(confirm("리스트로 이동하시겠습니까?")){
                location.href="/board/list.do?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}";
                }
            });

            $("#btnDelete").click(function(){
                console.log("btnDelete");
                if(confirm("삭제하시겠습니까?")){
                    document.form1.action = "/board/delete.do";
                    document.form1.submit();
                }
            });

            $("#btnUpdate").click(function(){
                console.log("btnUpdate");
                var title = $("#title").val();
                var content = $("#content").val();
                if(title == ""){
                    alert("제목을 입력하세요");
                    document.form1.title.focus();
                    return;
                }
                if(content == ""){
                    alert("내용을 입력하세요");
                    document.form1.content.focus();
                    return;
                }
                document.form1.action="/board/update.do"
                document.form1.submit();

            });

            $("#btnReply").click(function(){
                console.log("btnReply");
                replyJson(); // json 형식으로 입력
            });

            listReplyRest("1"); // rest방식

            e.preventDefault();
        });

        // 1_2. 댓글 입력 함수(json방식)
        function replyJson(){
            var replytext=$("#replytext").val();
            var boardnumber="${post.boardnumber}"
            // 비밀댓글 체크여부
            var secretreply = "n";
            // 태그.is(":속성") 체크여부 true/false
            if( $("#secretreply").is(":checked") ){
                secretreply = "y";
            }
            $.ajax({
                type: "post",
                url: "/reply/insert.do",
                headers: {
                    "Content-Type" : "application/json"
                },
                dateType: "text",
                data: JSON.stringify({
                    boardnumber : boardnumber,
                    replytext : replytext,
                    secretreply : secretreply
                }),
                success: function(){
                    alert("댓글이 등록되었습니다.");
                    listReplyRest("1"); // Rest 방식
                }
            });
        }


        // 2_1. 댓글 목록 - 전통적인 Controller방식
        function listReply(num){
            $.ajax({
                type: "get",
                url: "/reply/list.do?boardnumber=${post.boardnumber}&curPage="+num,
                success: function(result){
                    // responseText가 result에 저장됨.
                    $("#listReply").html(result);
                }
            });
        }

        function listReplyRest(num){
            console.log("listReplyRest");
            $.ajax({
                type: "get",
                url: "/reply/list/${post.boardnumber}/"+num,
                success: function(result){
                    // responseText가 result에 저장됨.
                    $("#listReply").html(result);
                }
            });
        }

        function showReplyModify(rno){
            $.ajax({
                type: "get",
                url: "/reply/detail/"+rno,
                success: function(result){
                    $("#modifyReply").html(result);
                    // 태그.css("속성", "값")
                    $("#modifyReply").css("visibility", "visible");
                }
            })
        }

    </script>
    <style>
        #modifyReply {
            width: 600px;
            height: 130px;
            background-color: greenyellow;
            padding: 10px;
            z-index: 10;
            visibility: hidden;
        }
        #fileDrop {
            width: 600px;
            height: 80px;
            border: 1px solid gray;
            background-color: gray;
        }

    </style>
</head>
<header role="banner">
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<h2>게시글 보기</h2>
<div id="header">
<form name="form1" method="post" >
    <c:choose>
    <c:when test="${post.show == 'y'}">
    <div>
        조회수 : ${post.views}
    </div>
    <div>
        제목
        <input name="title" id="title" size="80" value="${post.title}" placeholder="제목을 입력해주세요">
    </div>
    <div>
        내용
    </div>
        <div>
        <textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요">${post.content}</textarea>
    </div>
    <div style="width:650px; text-align: center;">
        <input type="hidden" name="boardnumber" value="${post.boardnumber}">
       <!-- <input type="text" name="boardnumber" value="${post.boardnumber}">-->

        <!-- 본인이 쓴 게시물만 수정, 삭제가 가능하도록 처리 -->
        <c:if test="${id ==boardUserId}">
            <button type="button" id="btnUpdate" >수정</button>
            <button type="button" id="btnDelete">삭제</button>
        </c:if>
        <!-- **상세보기 화면에서 게시글 목록화면으로 이동 -->
        <button type="button" id="btnList">목록</button>
    </div>
</form>
</div>
<div style="width:650px; color: green; text-align: center;">
    <br>
    <br>
    <!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
    <c:if test="${id != null}">
        <textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요"></textarea>
        <br>
        <!-- 비밀댓글 체크박스 -->
        <input type="checkbox" id="secretreply">비밀 댓글
        <button type="button" id="btnReply">댓글 작성</button>
    </c:if>
    </div>
    </c:when>
<c:otherwise>
    <!-- show가 n이면 -->
    삭제된 게시글 입니다.
</c:otherwise>
</c:choose>
<div id="listReply"></div>
</body>
</header>
</html>