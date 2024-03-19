<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${book.bookTitle}</title>
    <link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body>
    <h1 id="book-title">${book.bookTitle}</h1> 
    <h3>- ${book.bookAuthor} 지음</h3>

    <table border="1">
        <tr>
            <th>번호</th>
            <td>${book.bookNo}</td>
        </tr>

        <tr>
            <th>등록 날짜</th>
            <td>${book.regDate}</td>
        </tr>

        <tr>
            <th>내용</th>
            <td class="book-content">${book.bookContent}</td>
        </tr>
    </table>
    <br>
    <button id="goList">목록</button>
    <button id="updateBtn" data-book-no="${book.bookNo}">수정</button>
    <button id="deleteBtn" data-book-no="${book.bookNo}">삭제</button>


    <c:if test="${not empty message}" >
    <script>
        alert("${message}");
    </script>

    <c:remove var="message" />
    </c:if>
    <script src="/resources/js/detail.js"></script>
    
</body>
</html>