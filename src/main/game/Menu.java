package main.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static String erro = "";
    public static void mostrarMenu() {
        limparConsole();
        System.out.print("\n" +
            "+------------------------------+\n" +
            "| Puzzle Quest FE              |\n" +
            "+------------------------------+\n" +
            "|                              |\n" +
            "| [1] Iniciar nova partida     |\n" +
            "|                              |\n" +
            "| [2] Continuar uma partida    |\n" +
            "|                              |\n" +
            "| [3] Configurações            |\n" +
            "|                              |\n" +
            "| [4] Fechar o jogo            |\n" +
            "|                              |\n" +
            "+------------------------------+\n"
        );
        System.out.print(erro);
        erro = "";
        registrarEscolha();
    }

    public static void registrarEscolha() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("+--- Digite uma opção: ");
            int opcao = scanner.nextInt();
            executarOpcao(opcao);
            scanner.close();
        }
        catch (InputMismatchException e) {
            erro = "| Você não digitou um número \n| inteiro, tente novamente.\n";
            mostrarMenu();
        }
    }

    public static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                MenuPartida.iniciarNovaPartida();
                break;
            case 2:
                MenuPartida.continuarPartida();
                break;
            case 3:
                Configuracoes.abrirConfiguracoes();
                break;
            case 4:
                    limparConsole();
                    main.data.Salvamento.salvarNoBancoDeDados();
                    System.out.println("=======================\nEncerrando Puzzle Quest\n=======================\n\n");
                    System.exit(0);
                    break;
            default:
                erro = "| Você escolheu uma opção inválida, digite\n| o número de alguma opção válida (1 - 4)\n";
                mostrarMenu();
        }
    }
    
    public final static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}