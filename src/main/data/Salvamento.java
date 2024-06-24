package main.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import main.game.Partida;

public class Salvamento {

    private static final String URL = "jdbc:mysql://localhost:3306/puzzle_quest";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    
    static String arquivoSql = "D:/Programação/projetos/Java/puzzle-quest/src/main/data/commands.sql";

    public static void executarArquivoSql(Connection conn, String arquivoSql) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoSql));
             Statement stmt = conn.createStatement()) {

            String line;
            StringBuilder sql = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sql.append(line);

                if (line.trim().endsWith(";")) {
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }
        }
    }

    public static void adicionarPartidasNaListaDeComandos() {
        File file = new File(arquivoSql);
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("[ ERRO!!! ] [ ERRO!!! ] [ ERRO!!! ] [ ERRO!!! ]");
        }
        try (FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw)) {

            for (Partida partida : main.game.Partida.listaDePartidas) {
                String content = "INSERT INTO partidas_salvas (nome, situacaoTabuleiro, vidaAtualJogador1, vidaAtualJogador2, vidaMaximaJogador1, vidaMaximaJogador2, ouroAtualJogador1, ouroAtualJogador2, xpAtualJogador1, xpAtualJogador2, multiplicadorJogador1, multiplicadorJogador2) VALUES (" +
                    "'" + partida.toString() + "', " +
                    "'" + partida.tabuleiroOrdenado() + "', " +
                    "'" + partida.jogador1.getVidaAtual() + "', " +
                    "'" + partida.jogador2.getVidaAtual() + "', " +
                    "'" + partida.jogador1.getVidaMaxima() + "', " +
                    "'" + partida.jogador2.getVidaMaxima() + "', " +
                    "'" + partida.jogador1.getQuantidadeOuro() + "', " +
                    "'" + partida.jogador2.getQuantidadeOuro() + "', " +
                    "'" + partida.jogador1.getQuantidadeExperiencia() + "', " +
                    "'" + partida.jogador2.getQuantidadeExperiencia() + "', " +
                    "'" + partida.jogador1.multiplicadorDeDano + "', " +
                    "'" + partida.jogador2.multiplicadorDeDano + "'" +
                    ");" + "\n\n";
                bw.write(content);
            }

            System.out.println("Conteúdo adicionado ao arquivo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void salvarNoBancoDeDados() {
        if (!Partida.listaDePartidas.isEmpty()) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                adicionarPartidasNaListaDeComandos();
                executarArquivoSql(conn, arquivoSql);
                System.out.println("Comandos SQL executados com sucesso.");
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}