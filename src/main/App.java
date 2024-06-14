package main;

import main.game.Menu;
import main.game.Tabuleiro;

public class App {
    public static void main(String[] args) {
        //Menu.mostrarMenu();
        Tabuleiro tabuleiro1 = new Tabuleiro();
        tabuleiro1.gerarTabuleiroAleatorio();
        tabuleiro1.mostrarTabuleiro();
    }
}