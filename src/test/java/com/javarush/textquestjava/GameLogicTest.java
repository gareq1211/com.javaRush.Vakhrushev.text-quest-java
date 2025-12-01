// src/test/java/com/example/quest/GameLogicTest.java

package com.javarush.textquestjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void testWinCaptainPath() {
        GameLogic.GameState g = new GameLogic.GameState("Alex");
        g.handleAnswer("dock");
        g.handleAnswer("follow");
        g.handleAnswer("yes");
        g.handleAnswer("attack");
        assertTrue(g.isWin());
        assertEquals("Ты стал капитаном станции! Экипаж уважает тебя.", g.getResultMessage());
    }

    @Test
    void testWinSpyPath() {
        GameLogic.GameState g = new GameLogic.GameState("Eva");
        g.handleAnswer("dock");
        g.handleAnswer("sneak");
        g.handleAnswer("truth");
        assertTrue(g.isWin());
        assertEquals("Ты — секретный агент! Твоя миссия выполнена.", g.getResultMessage());
    }

    @Test
    void testLosePanic() {
        GameLogic.GameState g = new GameLogic.GameState("Panic");
        g.handleAnswer("run");
        assertFalse(g.isWin());
        assertEquals("Ты не справился со стрессом. Катастрофа.", g.getResultMessage());
    }

    @Test
    void testLoseBetrayal() {
        GameLogic.GameState g = new GameLogic.GameState("Liar");
        g.handleAnswer("dock");
        g.handleAnswer("follow");
        g.handleAnswer("no");
        assertFalse(g.isWin());
        assertEquals("Экипаж узнал, что ты шпион. Тебя казнили.", g.getResultMessage());
    }

    @Test
    void testEscapeJumpWin() {
        GameLogic.GameState g = new GameLogic.GameState("Runner");
        g.handleAnswer("dock");
        g.handleAnswer("hide");
        g.handleAnswer("wait");
        g.handleAnswer("jump");
        assertTrue(g.isWin());
    }
}