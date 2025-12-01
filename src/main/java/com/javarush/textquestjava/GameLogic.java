// src/main/java/com/example/quest/GameLogic.java

package com.javarush.textquestjava;

public class GameLogic {

    public enum State {
        START,
        DOCK_CHOICE,
        FOLLOW_CREW,
        TRUST_CAPTAIN,
        LIE_TO_CREW,
        FIGHT_CHOICE,
        ESCAPE_CHOICE,
        WIN_CAPTAIN,
        WIN_SPY,
        LOSE_BATTLE,
        LOSE_BETRAYAL,
        LOSE_PANIC
    }

    public static class GameState {
        private State state;
        private String playerName;
        private int gamesPlayed;
        private String lastResult;

        public GameState(String playerName) {
            this.playerName = playerName;
            this.gamesPlayed = 0;
            this.state = State.START;
            this.lastResult = "";
        }

        public void handleAnswer(String answer) {
            gamesPlayed++;

            switch (state) {
                case START:
                    if ("dock".equals(answer)) {
                        state = State.DOCK_CHOICE;
                    } else if ("run".equals(answer)) {
                        state = State.LOSE_PANIC; // Бежать = паника
                    }
                    break;

                case DOCK_CHOICE:
                    if ("follow".equals(answer)) {
                        state = State.FOLLOW_CREW;
                    } else if ("sneak".equals(answer)) {
                        state = State.TRUST_CAPTAIN;
                    } else if ("hide".equals(answer)) {
                        state = State.LIE_TO_CREW;
                    }
                    break;

                case FOLLOW_CREW:
                    if ("yes".equals(answer)) {
                        state = State.FIGHT_CHOICE;
                    } else {
                        state = State.LOSE_BETRAYAL;
                    }
                    break;

                case TRUST_CAPTAIN:
                    if ("truth".equals(answer)) {
                        state = State.WIN_SPY;
                    } else {
                        state = State.LOSE_BATTLE;
                    }
                    break;

                case LIE_TO_CREW:
                    state = State.ESCAPE_CHOICE;
                    break;

                case FIGHT_CHOICE:
                    if ("attack".equals(answer)) {
                        state = State.WIN_CAPTAIN;
                    } else {
                        state = State.LOSE_BATTLE;
                    }
                    break;

                case ESCAPE_CHOICE:
                    if ("jump".equals(answer)) {
                        state = State.WIN_CAPTAIN;
                    } else {
                        state = State.LOSE_PANIC;
                    }
                    break;

                default:
                    break;
            }

            // Устанавливаем результат
            if (isGameOver()) {
                if (state == State.WIN_CAPTAIN || state == State.WIN_SPY) {
                    lastResult = "Победа";
                } else {
                    lastResult = "Поражение";
                }
            }
        }

        public String getCurrentQuestion() {
            switch (state) {
                case START:
                    return "Ты приземлился на вражеской станции. Перед тобой док. Что делать?";
                case DOCK_CHOICE:
                    return "Ты вошёл в док. Слышишь голоса экипажа. Как действовать?";
                case FOLLOW_CREW:
                    return "Экипаж замечает тебя. Они предлагают присоединиться. Согласиться?";
                case TRUST_CAPTAIN:
                    return "Ты попал в кабинет капитана. Он спрашивает: «Кто ты?»";
                case LIE_TO_CREW:
                    return "Ты сказал ложь, и тебя заперли в карцере. У тебя есть шанс сбежать!";
                case FIGHT_CHOICE:
                    return "Внезапно раздаётся тревога! На станцию напали. Что делать?";
                case ESCAPE_CHOICE:
                    return "Ты сбежал на катере. Перед тобой пропасть в космосе. Прыгнуть?";
                default:
                    return "";
            }
        }

        public String getResultMessage() {
            switch (state) {
                case WIN_CAPTAIN:
                    return "Ты стал капитаном станции! Экипаж уважает тебя.";
                case WIN_SPY:
                    return "Ты — секретный агент! Твоя миссия выполнена.";
                case LOSE_BATTLE:
                    return "Ты проиграл в бою. Станция уничтожена.";
                case LOSE_BETRAYAL:
                    return "Экипаж узнал, что ты шпион. Тебя казнили.";
                case LOSE_PANIC:
                    return "Ты не справился со стрессом. Катастрофа.";
                default:
                    return "";
            }
        }

        public boolean isGameOver() {
            return state == State.WIN_CAPTAIN ||
                    state == State.WIN_SPY ||
                    state == State.LOSE_BATTLE ||
                    state == State.LOSE_BETRAYAL ||
                    state == State.LOSE_PANIC;
        }

        public boolean isWin() {
            return state == State.WIN_CAPTAIN || state == State.WIN_SPY;
        }

        // Getters
        public State getState() { return state; }
        public String getPlayerName() { return playerName; }
        public int getGamesPlayed() { return gamesPlayed; }
        public String getLastResult() { return lastResult; }
    }
}