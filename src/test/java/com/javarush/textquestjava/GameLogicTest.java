// src/test/java/com/example/quest/GameLogicTest.java

package com.javarush.textquestjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void testWinCaptainPath() {
        GameLogic.GameState gameState = new GameLogic.GameState("Alex");
        gameState.handleAnswer("dock");
        gameState.handleAnswer("follow");
        gameState.handleAnswer("yes");
        gameState.handleAnswer("attack");
        assertTrue(gameState.isWin());
        assertEquals("Ты стал капитаном станции! Экипаж уважает тебя.", gameState.getResultMessage());
    }

    @Test
    void testWinSpyPath() {
        GameLogic.GameState gameState = new GameLogic.GameState("Eva");
        gameState.handleAnswer("dock");
        gameState.handleAnswer("sneak");
        gameState.handleAnswer("truth");
        assertTrue(gameState.isWin());
        assertEquals("Ты — секретный агент! Твоя миссия выполнена.", gameState.getResultMessage());
    }

    @Test
    void testLosePanic() {
        GameLogic.GameState gameState = new GameLogic.GameState("Panic");
        gameState.handleAnswer("run");
        assertFalse(gameState.isWin());
        assertEquals("Ты не справился со стрессом. Катастрофа.", gameState.getResultMessage());
    }

    @Test
    void testLoseBetrayal() {
        GameLogic.GameState gameState = new GameLogic.GameState("Liar");
        gameState.handleAnswer("dock");
        gameState.handleAnswer("follow");
        gameState.handleAnswer("no");
        assertFalse(gameState.isWin());
        assertEquals("Экипаж узнал, что ты шпион. Тебя казнили.", gameState.getResultMessage());
    }

    @Test
    void testEscapeJumpWin() {
        GameLogic.GameState gameState = new GameLogic.GameState("Runner");
        gameState.handleAnswer("dock");
        gameState.handleAnswer("hide");
        gameState.handleAnswer("wait");
        gameState.handleAnswer("jump");
        assertTrue(gameState.isWin());
    }
}