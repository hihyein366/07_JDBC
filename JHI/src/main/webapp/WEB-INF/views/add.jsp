<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>독서 기록 남기기</title>
    <link rel="stylesheet" href="/resources/css/add.css">
</head>
<body>
    <div id="header">
        <h1 id="header-text">책은 힘이다🤸‍♀️</h1>
    </div>
    <form action="/book/add" method="POST">
        <hr>
        <h2>독서 기록 남기기</h2>

        <div>
            제목 : <input type="text" name="bookTitle">
        </div>
        <br>
        <div>
            작가 : <input type="text" name="bookAuthor">
        </div>
        <br>
        <div>
            <textarea name="bookContent" 
            cols="50" rows="20" placeholder="책에 대해 기록하고 싶은 내용을 작성하세요"></textarea>
        </div>
        <button>추가</button>
    </form>
    <script src="/resources/js/add.js"></script>
</body>
</html>