<!-- src/main/webapp/WEB-INF/jsp/question.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Квест</title>
    <style>
        body { font-family: Arial, margin: 40px; max-width: 600px; }
        .option { margin: 10px 0; }
        button { margin-top: 15px; padding: 8px 16px; }
    </style>
</head>
<body>
<h2>Вопрос:</h2>
<p>${question}</p>

<form action="${pageContext.request.contextPath}/quest" method="post">
    <c:choose>
        <c:when test="${sessionScope.gameState.state == 'START'}">
            <div class="option"><input type="radio" name="answer" value="dock" required> Войти в док</div>
            <div class="option"><input type="radio" name="answer" value="run"> Бежать к кораблю</div>
        </c:when>
        <c:when test="${sessionScope.gameState.state == 'DOCK_CHOICE'}">
            <div class="option"><input type="radio" name="answer" value="follow" required> Следовать за экипажем</div>
            <div class="option"><input type="radio" name="answer" value="sneak"> Проникнуть к капитану</div>
            <div class="option"><input type="radio" name="answer" value="hide"> Спрятаться и врать</div>
        </c:when>
        <c:when test="${sessionScope.gameState.state == 'FOLLOW_CREW'}">
            <div class="option"><input type="radio" name="answer" value="yes" required> Присоединиться к ним</div>
            <div class="option"><input type="radio" name="answer" value="no"> Отказаться</div>
        </c:when>
        <c:when test="${sessionScope.gameState.state == 'TRUST_CAPTAIN'}">
            <div class="option"><input type="radio" name="answer" value="truth" required> Рассказать правду</div>
            <div class="option"><input type="radio" name="answer" value="lie"> Солгать</div>
        </c:when>
        <c:when test="${sessionScope.gameState.state == 'LIE_TO_CREW'}">
            <div class="option"><input type="radio" name="answer" value="wait" required> Ждать</div>
            <!-- wait → переход в ESCAPE_CHOICE -->
        </c:when>
        <c:when test="${sessionScope.gameState.state == 'FIGHT_CHOICE'}">
            <div class="option"><input type="radio" name="answer" value="attack" required> Атаковать врагов</div>
            <div class="option"><input type="radio" name="answer" value="hide"> Спрятаться</div>
        </c:when>
        <c:when test="${sessionScope.gameState.state == 'ESCAPE_CHOICE'}">
            <div class="option"><input type="radio" name="answer" value="jump" required> Прыгнуть в катер</div>
            <div class="option"><input type="radio" name="answer" value="stay"> Остаться</div>
        </c:when>
    </c:choose>

    <button type="submit">Выбрать</button>
</form>

<div style="margin-top: 30px; font-size: 0.9em; color: #555;">
    Игрок: ${sessionScope.gameState.playerName}<br>
    Сыграно игр: ${sessionScope.gameState.gamesPlayed}
</div>
</body>
</html>