<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>독서 기록</title>
    <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
    <div id="header">
        <h1 id="header-text"><a>책은 힘이다🤸‍♀️</a></h1>
    </div>
    <h3>작성한 독서 기록 : ${fn:length(bookReport)}개</h3>

    <table border="1">
        <thead>
            <th>번호</th>
            <th id="title">책 제목</th>
            <th id="author">작가</th>
            <th id="date">작성일</th>
        </thead>

        <tbody>
            <c:forEach items="${bookReport}" var="book">
                <tr>
                    <td>${book.bookNo}</td>
                    <td>
                        <a href="/book/detail?bookNo=${book.bookNo}">${book.bookTitle}</a>
                    </td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.regDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <button id="addBook">독서 기록하기</button>

    <script src="/resources/js/main.js"></script>

    <c:if test="${not empty message}">
        <script>
            alert("${message}")
        </script>

        <c:remove var="message"></c:remove>
    </c:if>

</body>
</html>