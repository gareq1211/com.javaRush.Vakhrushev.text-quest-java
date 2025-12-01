<!-- src/main/webapp/WEB-INF/jsp/lose.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>💥 Поражение</title>
    <style>
        body { font-family: Arial; text-align: center; margin: 40px; }
        .btn { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #dc3545; color: white; text-decoration: none; border-radius: 5px; }
    </style>
</head>
<body>
<h1>💥 Миссия провалена</h1>
<p><strong>${message}</strong></p>
<a href="${pageContext.request.contextPath}/quest?restart=true" class="btn">Новая попытка</a>
</body>
</html>