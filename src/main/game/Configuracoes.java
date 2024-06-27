package main.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Configuracoes {
    public static char[] destaque = {'(', ')'};
    public static int vidaMaximaPadrao = 35;

    static String erro = "";

    public static void abrirConfiguracoes() {
        Scanner scanner = new Scanner(System.in);
        limparConsole();
        System.out.print("\n" +
                "+------------------------------+ \n" +
                "| Configurações                | \n" +
                "+------------------------------+ \n" +
                "|                              | \n" +
                "| [1] Símbolos de destaque da  | \n" +
                "|     peça atual do tabuleiro  | \n" +
                "|                              | \n" +
                "| [2] Vida máxima dos jogado-  | \n" +
                "|     res                      | \n" +
                "|                              | \n" +
                "| [3] Voltar ao menu principal | \n" +
                "|                              | \n" +
                "+------------------------------+ \n"
            );
        try {
            System.out.print(erro);
            erro = "";
            System.out.print("+--- Digite uma opção: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    alternarSimbolo();
                    break;
                case 2:
                    alternarVidaMaxima();
                    break;
                case 3:
                    Menu.mostrarMenu();
                    break;
                default:
                    erro = "\u001B[31m" + "| Você escolheu uma opção inválida, digite\n| o número de alguma opção válida (1 - 3)\n" + "\u001B[0m";
                    abrirConfiguracoes();
                    break;
            }
            scanner.close();
            
        } catch (InputMismatchException e) {
            erro = "\u001B[31m" + "| Você não digitou um número \n| inteiro, tente novamente.\n" + "\u001B[0m";
            abrirConfiguracoes();
        }
    }

    public static void alternarSimbolo() {
        Scanner scanner = new Scanner(System.in);
        limparConsole();
        System.out.print("\n" +
            "+------------------------------+ \n" +
            "| Alternar símbolo de destaque | \n" +
            "+------------------------------+ \n"
        );
        System.out.print("+--- Digite o símbolo da esquerda desejado (atual: ` " + Configuracoes.destaque[0] + " `): ");
        Configuracoes.destaque[0] = scanner.next().toCharArray()[0];
        
        System.out.print("+--- Digite o símbolo da direita desejado (atual: ` " + Configuracoes.destaque[1] + " `): ");
        Configuracoes.destaque[1] = scanner.next().toCharArray()[0];

        abrirConfiguracoes();
        scanner.close();
    }

    @SuppressWarnings("resource")
    public static void alternarVidaMaxima() {
        Scanner scanner = new Scanner(System.in);
        limparConsole();
        System.out.print("\n" +
            "+------------------------------+ \n" +
            "| Alternar vida máx. dos jgds. | \n" +
            "+------------------------------+ \n"
        );
        System.out.print(erro);
        erro = "";
        System.out.print("+--- Digite a vida máxima desejada (atual: `" + Configuracoes.vidaMaximaPadrao + "`): ");
        try {
            int vida = scanner.nextInt();
            if (vida <= 0) {
                erro = "\u001B[31m" + "| Você precisa digitar um número\n| inteiro válido (1 - 2147483647).\n" + "\u001B[0m";
                alternarVidaMaxima();
            } else {
                Configuracoes.vidaMaximaPadrao = vida;
            }
        } catch (InputMismatchException e) {
            erro = "\u001B[31m" + "| Você precisa digitar um número inteiro.\n" + "\u001B[0m";
            alternarVidaMaxima();
        }
        abrirConfiguracoes();
    }
    public final static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}