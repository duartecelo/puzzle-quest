package main.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Partida {
    static String erro = "";
    public static  void iniciarNovaPartida() {
        mostrarMenuNovaPartida();
    }
    
    public static void continuarPartida() {
        
    }
    public static  void abrirConfiguracoes() {
        
    }

    public static  void mostrarMenuNovaPartida() {
        try {
            limparConsole();
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n" +
                "+------------------------------+ \n" +
                "| Nova partida                 | \n" +
                "+------------------------------+ \n" +
                "|                              | \n" +
                "| [1] Contra o computador      | \n" +
                "|                              | \n" +
                "| [2] Player Vs Player (PvP)   | \n" +
                "|                              | \n" +
                "| [3] Voltar ao menu principal | \n" +
                "|                              | \n" +
                "+------------------------------+ \n"
            );
            System.out.print(erro);
            System.out.print("+--- Digite uma opção: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    Menu.mostrarMenu();
                    break;
                default:
                    erro = "| Você escolheu uma opção inválida, digite\n| o número de alguma opção válida (1 - 3)\n";
                    mostrarMenuNovaPartida();
                    break;
            }
            scanner.close();
        } catch (InputMismatchException e) {
            erro = "| Você não digitou um número \n| inteiro, tente novamente.\n";
            mostrarMenuNovaPartida();
        }
    }

    public final static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
