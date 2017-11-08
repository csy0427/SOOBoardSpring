<%@ page import="com.spring.Board.dto.BoardVO" %>
<%@ page isELIgnored="false" %>

<%--
  Created by IntelliJ IDEA.
  User: daou
  Date: 2017-09-08
  Time: 오후 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- <script type="text/javascript" src="../js/common.js"></script>
    <title>게시글 작성</title>
    <style>
        /* 첨부파일을 드래그할 영역의 스타일 */
        .fileDrop {
            width: 600px;
            height: 70px;
            border: 2px dotted gray;
            background-color: gray;
        }
    </style>

    <script>
        function fileSubmit() {
            var formData = new FormData($("#fileForm")[0]);
            $.ajax({
                type : 'post',
                url : '/board/add.do',
                data : formData,
                processData : false,
                contentType : false,
                success : function() {
                    alert("파일 업로드하였습니다.");
                },
                error : function(error) {
                    alert("파일 업로드에 실패하였습니다.");
                    console.log(error);
                    console.log(error.status);
                }
            });
        }
    </script>
</head>
-->
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<h2>게시글 작성</h2>
<form name="add" method="post" action="/board/add.do">
    <div>
        제목
        <input name="title" id="title" size="80" placeholder="제목을 입력해주세요">
    </div>
    <div>
        내용
        <textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요"></textarea>
    </div>
    <div>
        아이디
        ${id}
    </div>
    <div style="width:650px; text-align: center;">
        <input type="submit" value="제출">
        <button type="reset">취소</button>
    </div>
   <!-- <input id="imageUpload" type="file" name="file"  multiple="">-->
    <br>
    </div>
</form>


</body>
