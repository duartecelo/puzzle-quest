package main.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static String erro = "";
    
    /**
     * Exibe o menu principal do jogo e processa a seleção do usuário.
     * As opções incluem iniciar uma nova partida, continuar uma partida, abrir configurações e fechar o jogo.
     * Em caso de erro na entrada, uma mensagem de erro é exibida.
     */
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

    /**
     * Lê a escolha do usuário no menu e chama o método apropriado para executar a opção selecionada.
     * Em caso de erro na entrada, uma mensagem de erro é exibida e o menu é mostrado novamente.
     */
    public static void registrarEscolha() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("+--- Digite uma opção: ");
            int opcao = scanner.nextInt();
            executarOpcao(opcao);
            scanner.close();
        }
        catch (InputMismatchException e) {
            erro = "\u001B[31m" + "| Você não digitou um número \n| inteiro, tente novamente.\n" + "\u001B[0m";
            mostrarMenu();
        }
    }
    
    /**
     * Executa a opção selecionada pelo usuário no menu.
     * 
     * @param opcao a opção escolhida pelo usuário.
     * As opções válidas são:
     * 1 - Iniciar nova partida.
     * 2 - Continuar uma partida.
     * 3 - Abrir configurações.
     * 4 - Fechar o jogo.
     * Se a opção for inválida, uma mensagem de erro é exibida e o menu é mostrado novamente.
     */
    public static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                MenuPartida.iniciarNovaPartida();
                break;
            case 2:
                main.data.Carregamento.continuarPartida();
                break;
            case 3:
                Configuracoes.abrirConfiguracoes();
                break;
            case 4:
                    limparConsole();
                    System.out.println("=======================\nEncerrando Puzzle Quest\n=======================\n\n");
                    System.exit(0);
                    break;
            default:
                erro = "\u001B[31m" + "| Você escolheu uma opção inválida, digite\n| o número de alguma opção válida (1 - 4)\n" + "\u001B[0m";
                mostrarMenu();
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