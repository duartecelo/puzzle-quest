package main.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.game.Partida;
import main.game.Tabuleiro;
import main.game.Tipo;
import main.game.Jogador;

public class Carregamento {
    static Scanner scanner = new Scanner(System.in);
    
    private static final String URL = "jdbc:mysql://localhost:3306/puzzle_quest";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    static List<String> listaDeNomesDasPartidas = new ArrayList<>();
    
    public static void continuarPartida() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        
            stmt = conn.createStatement();
        
            String sqlPt1 = "SELECT * FROM partidas_salvas;";
            rs = stmt.executeQuery(sqlPt1);
        
            while (rs.next()) {
                listaDeNomesDasPartidas.add(rs.getString("nome") + "[" + rs.getString("id") + "]");
            }
            limparConsole();
            System.out.println("+----------------------------------------------");
            System.out.println("| Digite o número (o que está entre colchetes)\n            da partida que você quer carregar:");
            System.out.println("|");
            for (String nomeDaPartida : listaDeNomesDasPartidas) {
                System.out.printf("+- %s\n|\n", nomeDaPartida);
            }
            System.out.print("+- - - - | ");
            String opcao = "";
            do {
                opcao = scanner.nextLine();
                if (listaDeNomesDasPartidas.contains(opcao)) {
                    String sqlPt2 = String.format("SELECT * FROM partidas_salvas WHERE id = '%c'", opcao.charAt(opcao.length() - 2));

                    rs = stmt.executeQuery(sqlPt2);

                    if (rs.next()) {
                        carregarPartida(rs);
                    }
                }
            } while (!listaDeNomesDasPartidas.contains(opcao));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void carregarPartida(ResultSet rs) {
        try {
            Partida partida = new Partida();
            partida.tabuleiro = new Tabuleiro();
            partida.erro = "";
            partida.rodadas = 1;
            partida.combo = false;

            partida.tabuleiro.posicaoEmTipos = converterTextoParaTabuleiro(rs.getString("situacaoTabuleiro"));
            partida.jogador1 = new Jogador((rs.getString("nome").split("_vs_")[0]));
            partida.jogador2 = new Jogador((rs.getString("nome").split("_vs_")[1]));
            partida.jogadorAtual = partida.jogador1;
            partida.jogador1.setVidaMaxima(rs.getInt("vidaMaximaJogador1"));
            partida.jogador2.setVidaMaxima(rs.getInt("vidaMaximaJogador2"));
            partida.jogador1.setVidaAtual(rs.getInt("vidaAtualJogador1"));
            partida.jogador2.setVidaAtual(rs.getInt("vidaAtualJogador2"));
            partida.jogador1.setQuantidadeOuro(rs.getInt("ouroAtualJogador1"));
            partida.jogador2.setQuantidadeOuro(rs.getInt("ouroAtualJogador2"));
            partida.jogador1.setQuantidadeExperiencia(rs.getInt("xpAtualJogador1"));
            partida.jogador2.setQuantidadeExperiencia(rs.getInt("xpAtualJogador2"));
            partida.jogador1.multiplicadorDeDano = rs.getInt("multiplicadorJogador1");
            partida.jogador2.multiplicadorDeDano = rs.getInt("multiplicadorJogador2");

            while (partida.jogador1.getVidaAtual() > 0 && partida.jogador2.getVidaAtual() > 0) {
                limparConsole();
                partida.imprimirRodada();
                partida.solicitarEscolhaDaJogada(scanner);
                if (partida.oJogadorJogou()) {
                    partida.rodadas++;
                    if (partida.temCombo()) partida.combo = true;
                    partida.realizarEfeitos();
                    partida.jogadorAtual.multiplicadorDeDano = 1;
                    partida.tabuleiro.descerEsferas();
                    partida.tabuleiro.gerarNovasEsferas();
                    while (partida.tabuleiro.isTabuleiroPossivel() == false) {
                        partida.tabuleiro.contarEApagarTudo();
                        partida.realizarEfeitos();
                        partida.jogadorAtual.multiplicadorDeDano = 1;
                        partida.tabuleiro.descerEsferas();
                        partida.tabuleiro.gerarNovasEsferas();
                    }
                    if (partida.combo == false) {
                        partida.jogadorAtual = partida.jogadorAtual == partida.jogador1 ? partida.jogador2 : partida.jogador1;
                    } else {
                        partida.combo = false;
                    }
                }
                
            }
            partida.imprimirRodada();
            partida.finalDeJogo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static main.game.Tipo[][] converterTextoParaTabuleiro(String tabuleiroEmTexto) {
        Tipo[][] tabuleiroEmTipos = new Tipo[8][8];
        int contadorX = 0;
        int contadorY = 0;
        for (char caractere : tabuleiroEmTexto.toCharArray()) {
            switch (caractere) {
                case 'C':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Caveira;
                    break;
                case 'F':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Fogo;
                    break;
                case 'G':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Gelo;
                    break;
                case 'N':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Natureza;
                    break;
                case 'R':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Raio;
                    break;
                case 'O':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Ouro;
                    break;
                case 'E':
                    tabuleiroEmTipos[contadorY][contadorX] = Tipo.Experiencia;
                    break;
                default:
                    System.out.println("\n\n\n\n\n");
                    break;
            }
            if (contadorX == 7) {
                contadorX = 0;
                contadorY++;
            }
            else contadorX++;
        }
        return tabuleiroEmTipos;
    }

    public final static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}