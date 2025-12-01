// src/main/java/com/example/quest/QuestServlet.java

package com.javarush.textquestjava;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


public class QuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameLogic.GameState gameState = (GameLogic.GameState) session.getAttribute("gameState");

        if (gameState == null || request.getParameter("restart") != null) {
            // Начать новую игру
            session.removeAttribute("gameState");
            request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
            return;
        }

        if (gameState.isGameOver()) {
            request.setAttribute("message", gameState.getResultMessage());
            request.setAttribute("isWin", gameState.isWin());
            request.getRequestDispatcher(gameState.isWin() ? "/WEB-INF/jsp/win.jsp" : "/WEB-INF/jsp/lose.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("question", gameState.getCurrentQuestion());
        request.getRequestDispatcher("/WEB-INF/jsp/question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameLogic.GameState gameState = (GameLogic.GameState) session.getAttribute("gameState");

        if (gameState == null) {
            String playerName = request.getParameter("playerName");
            if (playerName == null || playerName.trim().isEmpty()) playerName = "Капитан";
            gameState = new GameLogic.GameState(playerName);
            session.setAttribute("gameState", gameState);
        }

        String answer = request.getParameter("answer");
        if (answer != null) {
            gameState.handleAnswer(answer);
        }

        response.sendRedirect(request.getContextPath() + "/quest");
    }
}