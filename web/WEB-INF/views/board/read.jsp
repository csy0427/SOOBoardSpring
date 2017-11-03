<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%String id= (String) session.getAttribute("id");%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>게시글 작성</title>
    <script>
        $(document).ready(function(){
            // ** 목록 버튼 클릭 이벤트 : 버튼 클릭시 상세보기화면에 있던 페이지, 검색옵션, 키워드 값을 가지로 목록으로 이동
            $("#btnList").click(function(){
                location.href="/board/list.do?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}";
            });

            $("#btnDelete").click(function(){
                if(confirm("삭제하시겠습니까?")){
                    document.form1.action = "/board/delete.do";
                    document.form1.submit();
                }
            });

            $("#btnUpdete").click(function(){
                //var title = document.form1.title.value; ==> name속성으로 처리할 경우
                //var content = document.form1.content.value;
                //var writer = document.form1.writer.value;
                var title = $("#title").val();
                var content = $("#content").val();
                //var writer = $("#writer").val();
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
                /* if(writer == ""){
                    alert("이름을 입력하세요");
                    document.form1.writer.focus();
                    return;
                } */
                document.form1.action="/board/update.do"
                // 폼에 입력한 데이터를 서버로 전송
                document.form1.submit();

            });
        });
    </script>
</head>
<h2>게시글 보기</h2>
<form name="form1" method="post">
    <div>
        조회수 : ${post.views}
    </div>
    <div>
        제목
        <input name="title" id="title" size="80" value="${post.title}" placeholder="제목을 입력해주세요">
    </div>
    <div>
        내용
        <textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요">${post.content}</textarea>
    </div>
    <div style="width:650px; text-align: center;">
        <!-- 게시물번호를 hidden으로 처리 -->
        <input type="hidden" name="boardnumber" value="${post.boardnumber}">
        <!-- 본인이 쓴 게시물만 수정, 삭제가 가능하도록 처리 -->
        <c:if test="${id == post.userid}">
        <button type="button" id="btnUpdete">수정</button>
            <button type="button" id="btnDelete">삭제</button>
        </c:if>
        <!-- **상세보기 화면에서 게시글 목록화면으로 이동 -->
        <button type="button" id="btnList">목록</button>
    </div>
</form>


</html>