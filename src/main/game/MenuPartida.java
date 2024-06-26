package main.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPartida {
    static String erro = "";

    /**
     * Inicia uma nova partida exibindo o menu de nova partida.
     */
    public static void iniciarNovaPartida() {
        mostrarMenuNovaPartida();
    }
    
    /**
     * Exibe o menu de nova partida e processa a seleção do usuário.
     * As opções incluem Player Vs Player (PvP) e voltar ao menu principal.
     * Em caso de erro na entrada, uma mensagem de erro é exibida.
     */
    public static void mostrarMenuNovaPartida() {
        try {
            limparConsole();
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n" +
                    "+------------------------------+ \n" +
                    "| Nova partida                 | \n" +
                    "+------------------------------+ \n" +
                    "|                              | \n" +
                    "| [1] Player Vs Player (PvP)   | \n" +
                    "|                              | \n" +
                    "| [2] Voltar ao menu principal | \n" +
                    "|                              | \n" +
                    "+------------------------------+ \n");
            System.out.print(erro);
            System.out.print("+--- Digite uma opção: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    limparConsole();
                    Partida partidaPvP = new Partida();
                    partidaPvP.iniciarPartidaPvP();
                    break;
                case 2:
                    Menu.mostrarMenu();
                    break;
                default:
                    erro = "\u001B[31m" + "| Você escolheu uma opção inválida, digite\n| o número de alguma opção válida (1 - 2)\n" + "\u001B[0m";
                    mostrarMenuNovaPartida();
                    break;
            }
            scanner.close();
        } catch (InputMismatchException e) {
            erro = "\u001B[31m" + "| Você não digitou um número \n| inteiro, tente novamente.\n" + "\u001B[0m";
            mostrarMenuNovaPartida();
        }
    }
    
    /**
     * Limpa o console, simulando a função de "limpar tela" para melhorar a legibilidade.
     */
    public final static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}