
package main.game;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("resource")
public class Partida {
    
    /*
    * Lista que armazena todas as partidas que foram criadas.
    */
    public static List<Partida> listaDePartidas = new ArrayList<>();

    public Tabuleiro tabuleiro;
    public Jogador jogador;
    public Jogador jogador1;
    public Jogador jogador2;
    public Jogador jogadorAtual;
    public String escolha;
    public String erro = "";
    public int rodadas = 1;
    public boolean combo = false;
    public boolean precisaSalvar = true;

    /*
    * Construtor da classe Partida que adiciona a partida à lista de partidas.
    */
    public Partida() {
        listaDePartidas.add(this);
    }
    
    /*
    * Método que retorna a representação em string da partida.
    * @return String contendo o nome dos jogadores que estão competindo.
    */
    @Override
    public String toString() {
        String partida = jogador1.getNome() + "_vs_" + jogador2.getNome();
        return partida;
    }
    
    /*
    * Método responsável por iniciar uma partida PvP.
    * Cria os jogadores, tabuleiro, e inicia o loop de rodadas até que um jogador fique sem vida.
    */
    public void iniciarPartidaPvP() {
        Scanner scanner = new Scanner(System.in);
        tabuleiro = new Tabuleiro();
        tabuleiro.gerarTabuleiroAleatorio();

        System.out.print("Nome do primeiro jogador: ");
        String nomeDoJogador1 = scanner.nextLine();
        System.out.print("Nome do segundo jogador: ");
        String nomeDoJogador2 = scanner.nextLine();
        if (nomeDoJogador2.toUpperCase().equals(nomeDoJogador1.toUpperCase())) {
            nomeDoJogador2 += "(2)"; 
            nomeDoJogador1 += "(1)";
        }
        jogador1 = new Jogador(nomeDoJogador1);
        jogador2 = new Jogador(nomeDoJogador2);
        jogadorAtual = jogador1;
        limparConsole();

        while (jogador1.getVidaAtual() > 0 && jogador2.getVidaAtual() > 0) {
            limparConsole();
            imprimirRodada();
            solicitarEscolhaDaJogada(scanner);
            if (oJogadorJogou()) {
                rodadas++;
                if (temCombo()) combo = true;
                realizarEfeitos();
                jogadorAtual.multiplicadorDeDano = 1;
                tabuleiro.descerEsferas();
                tabuleiro.gerarNovasEsferas();
                while (tabuleiro.isTabuleiroPossivel() == false) {
                    tabuleiro.contarEApagarTudo();
                    realizarEfeitos();
                    jogadorAtual.multiplicadorDeDano = 1;
                    tabuleiro.descerEsferas();
                    tabuleiro.gerarNovasEsferas();
                }
                if (combo == false) {
                    jogadorAtual = jogadorAtual == jogador1 ? jogador2 : jogador1;
                } else {
                    combo = false;
                }
            }
            
        }
        imprimirRodada();
        excluirSalvamentoPassado();
        main.data.Salvamento.salvarNoBancoDeDados();
        listaDePartidas.clear();
        finalDeJogo();
    }

    /**
     * Método responsável por imprimir informações da rodada atual, incluindo o tabuleiro e o jogador atual.
    */
    public void imprimirRodada() {
        tabuleiro.mostrarTabuleiro();
        System.out.println(jogador1);
        System.out.print(jogador2);
        System.out.printf("=== Vez de %s ===\n\n", jogadorAtual.getNome().toUpperCase());
    }
    
    /**
    * Solicita ao jogador uma escolha de movimento ou ação através do console.
    *
    * @param scanner Scanner utilizado para capturar a entrada do jogador
    */
    public void solicitarEscolhaDaJogada(Scanner scanner) {
        System.out.println("* Digite `W`, `A`, `S` ou `D` + Enter para\n  se movimentar dentro do tabuleiro.");
        System.out.println("* Clique Enter para confirmar a posição.");
        System.out.println("* Digite SAIR para salvar e voltar ao menu\n  principal.");
        System.out.print(erro);
        erro = "";
        escolha = scanner.nextLine().toUpperCase();
        switch (escolha) {
            case "W":
                tabuleiro.movimentarParaCima();
                break;
            case "A":
                tabuleiro.movimentarParaEsquerda();
                break;
            case "S":
                tabuleiro.movimentarParaBaixo();
                break;
            case "D":
                tabuleiro.movimentarParaDireita();
                break;
            case "":
                escolherDirecao();
                break;
            case "SAIR":
                excluirSalvamentoPassado();
                main.data.Salvamento.salvarNoBancoDeDados();
                listaDePartidas.clear();
                Menu.mostrarMenu();
                break;
            default:
                erro = "\u001B[31m" + "[ \"" + escolha + "\"" + " NÃO É UMA OPÇÃO VÁLIDA ]\n" + "\u001B[0m";
                break;
        }
    }
    
    /**
    * Imprime as informações finais do jogo, incluindo o vencedor e estatísticas da partida.
    * Aguarda a entrada do jogador para continuar.
    */
    public void finalDeJogo() {
        Scanner scanner = new Scanner(System.in);
        limparConsole();
        Jogador vencedor = jogador1.getVidaAtual() > 0 ? jogador1 : jogador2;

        System.out.println("\n====================|  F I M    D E    J O G O  |===================");
        System.out.printf("- %s vs %s\n", jogador1.getNome().toUpperCase(), jogador2.getNome().toUpperCase());
        System.out.printf("- %s ganhou a partida com %d de vida restante(s)\n", vencedor.getNome().toUpperCase(), vencedor.getVidaAtual());
        System.out.printf("A partida durou %d rodadas, que jogo!\n====================================================================\n", rodadas);
        System.out.println("Pressione Enter para voltar ao menu principal\n\n");
        scanner.nextLine();
        Menu.mostrarMenu();
        
    }
    
    /**
    * Solicita ao jogador uma escolha de direção para movimento dentro do tabuleiro.
    * Utiliza um Scanner para capturar a entrada do jogador.
    */
    public void escolherDirecao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("Você quer mover para qual direção (W, A, S ou D)?  - ");
        escolha = scanner.nextLine().toUpperCase();
        switch (escolha) {
            case "W":
                tabuleiro.trocarCima();
                break;
            case "A":
                tabuleiro.trocarEsquerda();
                break;
            case "S":
                tabuleiro.trocarBaixo();
                break;
            case "D":
                tabuleiro.trocarDireita();
                break;
            case "":
                escolherDirecao();
                break;
            default:
                erro = "\u001B[31m" + "[ \"" + escolha + "\"" + " NÃO É UMA OPÇÃO VÁLIDA ]\n" + "\u001B[0m";
                break;
        }
    }
   
    /**
    * Limpa o console, removendo todos os dados presentes.
    * Utiliza códigos ANSI para realizar a limpeza.
     */
    public final static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
    * Aplica os efeitos das ações realizadas durante a rodada atual.
    * Realiza diferentes ações baseadas nas condições do tabuleiro.
    */
    public void realizarEfeitos() {
        if (tabuleiro.gelosApagados >= 3) {
            for (int i = 0; i < tabuleiro.posicaoEmTipos.length; i++) {
                for (int j = 0; j < tabuleiro.posicaoEmTipos.length; j++) {
                    if (tabuleiro.posicaoEmTipos[i][j] == Tipo.Fogo) {
                        tabuleiro.posicaoEmTipos[i][j] = Tipo.Caveira;
                    }
                }
            }
            tabuleiro.contarEApagarFogos();
            tabuleiro.gelosApagados = 0;
        }

        if (tabuleiro.naturezasApagadas >= 3) {
            for (int i = 0; i < tabuleiro.posicaoEmTipos.length; i++) {
                for (int j = 0; j < tabuleiro.posicaoEmTipos.length; j++) {
                    if (tabuleiro.posicaoEmTipos[i][j] == Tipo.Caveira) {
                        tabuleiro.posicaoEmTipos[i][j] = Tipo.Fogo;
                    }
                }
            }
            tabuleiro.contarEApagarCaveiras();
            tabuleiro.naturezasApagadas = 0;
        }

        for (int i = 0; i < tabuleiro.caveirasApagadas; i++) {
            if (jogadorAtual == jogador1) jogador2.setVidaAtual(jogador2.getVidaAtual() - 1 * jogador1.multiplicadorDeDano);
            else jogador1.setVidaAtual(jogador1.getVidaAtual() - 1 * jogador2.multiplicadorDeDano);
        }
        tabuleiro.caveirasApagadas = 0;

        for (int i = 0; i < tabuleiro.fogosApagados; i++) {
            if (jogadorAtual == jogador1 && jogador1.getVidaAtual() < jogador1.getVidaMaxima()) {
                jogador1.setVidaAtual(jogador2.getVidaAtual() + 1);
            } else if (jogadorAtual == jogador2 && jogador2.getVidaAtual() < jogador2.getVidaMaxima()){
                jogador2.setVidaAtual(jogador2.getVidaAtual() + 1);
            }
        }
        tabuleiro.fogosApagados = 0;

        if (tabuleiro.raiosApagados >= 3) {
            if (jogadorAtual == jogador1) jogador2.setQuantidadeOuro(0);
            else jogador1.setQuantidadeOuro(0);
        }
        tabuleiro.raiosApagados = 0;
        
        for (int i = 0; i < tabuleiro.ourosApagados; i++) {
            if (jogadorAtual.getQuantidadeOuro() == 9) {
                jogadorAtual.setQuantidadeOuro(0);
                jogadorAtual.multiplicadorDeDano = 2;
            } else {
                jogadorAtual.setQuantidadeOuro(jogadorAtual.getQuantidadeOuro() + 1);
            }
        }
        tabuleiro.ourosApagados = 0;

        for (int i = 0; i < tabuleiro.experienciasApagadas; i++) {
            if (jogadorAtual.getQuantidadeExperiencia() == 9) {
                jogadorAtual.setQuantidadeExperiencia(0);
                if (jogadorAtual == jogador1) {
                    jogador2.setVidaMaxima(jogador2.getVidaMaxima() - 10);
                } else {
                    jogador1.setVidaMaxima(jogador2.getVidaMaxima() - 10);
                }
            } else {
                jogadorAtual.setQuantidadeExperiencia(jogadorAtual.getQuantidadeExperiencia() + 1);
            }
        }
        tabuleiro.experienciasApagadas = 0;
    }
    
    /**
    * Verifica se há algum combo no tabuleiro, onde um combo é definido como 4 ou mais elementos iguais apagados.
    *
    * @return true se houver um combo, false caso contrário
    */
    public boolean temCombo() {
        if (tabuleiro.experienciasApagadas >= 4) return true;
        if (tabuleiro.caveirasApagadas >= 4) return true;
        if (tabuleiro.fogosApagados >= 4) return true;
        if (tabuleiro.gelosApagados >= 4) return true;
        if (tabuleiro.naturezasApagadas >= 4) return true;
        if (tabuleiro.ourosApagados >= 4) return true;
        if (tabuleiro.raiosApagados >= 4) return true;
        return false;
    }
    
    /**
    * Reseta todos os contadores de elementos apagados do tabuleiro para zero.
    */
    public void resetarApagados() {
        tabuleiro.experienciasApagadas = 0;
        tabuleiro.caveirasApagadas = 0;
        tabuleiro.fogosApagados = 0;
        tabuleiro.gelosApagados = 0;
        tabuleiro.naturezasApagadas = 0;
        tabuleiro.ourosApagados = 0;
        tabuleiro.raiosApagados = 0;
    }
    
    /**
    * Verifica se o jogador realizou alguma ação durante sua rodada atual.
    *
    * @return true se o jogador jogou (realizou alguma ação), false caso contrário
    */
    public boolean oJogadorJogou() {
        if (tabuleiro.experienciasApagadas >= 1) return true;
        if (tabuleiro.caveirasApagadas >= 1) return true;
        if (tabuleiro.fogosApagados >= 1) return true;
        if (tabuleiro.gelosApagados >= 1) return true;
        if (tabuleiro.naturezasApagadas >= 1) return true;
        if (tabuleiro.ourosApagados >= 1) return true;
        if (tabuleiro.raiosApagados >= 1) return true;
        return false;
    }
    
    /**
    * Retorna uma representação ordenada do tabuleiro, onde cada elemento é representado por uma letra correspondente ao tipo.
    *
    *@return String representando o tabuleiro ordenado por tipos
    */
    public String tabuleiroOrdenado() {
        String conjunto = "";
        for (int i = 0; i < tabuleiro.posicaoEmTipos.length; i++) {
            for (int j = 0; j < tabuleiro.posicaoEmTipos.length; j++) {
                switch (tabuleiro.posicaoEmTipos[i][j]) {
                    case Caveira:
                        conjunto += "C";
                        break;
                    case Fogo:
                        conjunto += "F";
                        break;
                    case Gelo:
                        conjunto += "G";
                        break;
                    case Raio:
                        conjunto += "R";
                        break;
                    case Natureza:
                        conjunto += "N";
                        break;
                    case Ouro:
                        conjunto += "O";
                        break;
                    case Experiencia:
                        conjunto += "E";
                        break;
                    default:
                        break;
                }
            }
        }
        return conjunto;
    }
    /**
    * Exclui o salvamento anterior do jogo, deletando-o do banco de dados.
    */
    public void excluirSalvamentoPassado() {
        main.data.Carregamento.deletarAntigoSalvamento();
    }
}