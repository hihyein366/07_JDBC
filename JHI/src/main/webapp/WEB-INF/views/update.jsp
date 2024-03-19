<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${book.bookTitle} 수정</title>
    <link rel="stylesheet" href="/resources/css/add.css">
</head>
<body>
    <div id="header">
        <h1 id="header-text"><a>책은 힘이다🤸‍♀️</a></h1>
    </div>
    <hr>
    <h2>"${book.bookTitle}" 수정하기</h2>

    <form action="/book/update" method="POST">
        <div>
            제목 : <input type="text" name="bookTitle" value="${book.bookTitle}">
        </div>
        <br>
        <div>
            작가 : <input type="text" name="bookAuthor" value="${book.bookAuthor}">
        </div>
        <br> <br>
       
       <div>
            <textarea name="bookContent" 
            cols="50" rows="20">${book.bookContent}</textarea>
        </div>

        <input type="hidden" name="bookNo" value="${param.bookNo}">

        <button>수정하기</button>
        <button id="back" onclick="goBack()">뒤로가기</button>
    </form>

    <c:if test="${not empty message}" >
        <script>
            alert("${message}");
        </script>

        <c:remove var="message" />
    </c:if>
    <script src="/resources/js/add.js"></script>
</body>
</html>