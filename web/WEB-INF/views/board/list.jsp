<%--
  Created by IntelliJ IDEA.
  User: daou
  Date: 2017-09-07
  Time: 오후 4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.spring.Board.dto.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String id= (String) session.getAttribute("id");%>
<!DOCTYPE html>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#btnWrite").click(function () {
                location.href="/board/write.do";
            })

            function list(page) {
                location.href="board/list.do?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
            }
        })
    </script>
</head>
<body>
<h2>게시글 목록</h2>
<form name="form1" method="post" action="/board/list.do">
    <select name="searchOption">
        <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
        <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >제목+아이디+제목</option>
        <option value="userid" <c:out value="${map.searchOption == 'username'?'selected':''}"/> >아이디</option>
        <option value="content" <c:out value="${map.searchOption == 'content'?'selected':''}"/> >내용</option>
        <option value="title" <c:out value="${map.searchOption == 'title'?'selected':''}"/> >제목</option>
    </select>
    <input name="keyword" value="${map.keyword}">
    <input type="submit" value="조회">
    <!-- 로그인한 사용자만 글쓰기 버튼을 활성화 -->
    <c:if test="${id!=null}">
        <button type="button" id="btnWrite">글쓰기</button>
    </c:if>

</form>
${map.count}개의 게시물이 있습니다.
<table border="1" width="600px">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>아이디</th>
        <th>조회수</th>
    </tr>
    <c:forEach var="list" items="${map.list}" varStatus="status">
        <c:choose>
        <c:when test="${list.show == 'y'}">
        <tr>
            <td>${list.boardnumber}</td>
            <!-- ** 게시글 상세보기 페이지로 이동시 게시글 목록페이지에 있는 검색조건, 키워드, 현재페이지 값을 유지하기 위해 -->
            <td><a href="${path}/board/read.do?boardnumber=${list.boardnumber}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">${list.title}</a></td>
            <td>${list.boardnumber}</td>
            <td>${list.views}</td>
        </tr>
        </c:when>
            <c:otherwise>
                <!-- show 컬럼이 n일때(삭제된 글) -->
                <tr>
                    <td>${list.boardnumber}</td>
                </tr>
                <tr>
                    <td colspan="5" align="left">
                        <c:if test="${list.recnt > 0}">
                            <a href="${path}/board/read.do?bno=${list.boardnumber}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">삭제된 게시물입니다.
                                <!-- ** 댓글이 있으면 게시글 이름 옆에 출력하기 -->
                                <c:if test="${list.recnt > 0}">
						<span style="color: red;">(${list.recnt})
						</span>
                                </c:if>
						</span>
                            </a>
                        </c:if>
                        <c:if test="${list.recnt == 0 }">
                            삭제된 게시물입니다.
                        </c:if>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <tr>
        <td colspan="5">
            <!-- **처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
            <c:if test="${map.boardPager.curBlock > 1}">
                <a href="javascript:list('1')">[처음]</a>
            </c:if>

            <!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
            <c:if test="${map.boardPager.curBlock > 1}">
                <a href="javascript:list('${map.boardPager.prevPage}')">[이전]</a>
            </c:if>

            <!-- **하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
            <c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
                <!-- **현재페이지이면 하이퍼링크 제거 -->
                <c:choose>
                    <c:when test="${num == map.boardPager.curPage}">
                        <span style="color: red">${num}</span>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:list('${num}')">${num}</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- **다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
            <c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
                <a href="javascript:list('${map.boardPager.nextPage}')">[다음]</a>
            </c:if>

            <!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
            <c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
                <a href="javascript:list('${map.boardPager.totPage}')">[끝]</a>
            </c:if>
        </td>
    </tr>
</table>
</body>
</body>
</html>

