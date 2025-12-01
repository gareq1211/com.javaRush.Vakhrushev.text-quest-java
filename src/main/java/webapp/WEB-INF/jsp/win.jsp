<!-- src/main/webapp/WEB-INF/jsp/win.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>🏆 Победа!</title>
    <style>
        body { font-family: Arial; text-align: center; margin: 40px; }
        .btn { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; }
    </style>
</head>
<body>
<h1>🏆 Миссия выполнена!</h1>
<p><strong>${message}</strong></p>
<a href="${pageContext.request.contextPath}/quest?restart=true" class="btn">Новая игра</a>
</body>
</html>