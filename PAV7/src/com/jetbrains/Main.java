package com.jetbrains;

import compulsory.Game;

/**
 * Creeaza o instanta a jocului si o porneste cu metoda start
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game("P1","P2",10,3);
        game.start();
    }
}
