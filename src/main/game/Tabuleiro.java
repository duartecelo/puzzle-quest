package main.game;
import java.util.Random;

/**
 * Classe que representa o tabuleiro do jogo.
 */
public class Tabuleiro {
    public Tipo[][] posicaoEmTipos = new Tipo[8][8];
    String[][] posicaoEmString = new String[8][8];
    int[] posicaoSelecionada = {0, 0};
    int caveirasApagadas = 0;
    int fogosApagados = 0;
    int gelosApagados = 0;
    int raiosApagados = 0;
    int naturezasApagadas = 0;
    int ourosApagados = 0;
    int experienciasApagadas = 0;
    final String ANSI_RESET = "\u001B[0m";

    /**
     * Gera um tabuleiro aleatório, garantindo que ele seja possível de jogar.
     */
    public void gerarTabuleiroAleatorio() {
        for (int i = 0; i < posicaoEmTipos.length; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                posicaoEmTipos[i][j] = gerarEsferaAleatoria();
            }
        }
        while (isTabuleiroPossivel() == false) {
            for (int i = 0; i < posicaoEmTipos.length; i++) {
                for (int j = 0; j < posicaoEmTipos.length; j++) {
                    while (isFormada(i, j)) {
                        posicaoEmTipos[i][j] = gerarEsferaAleatoria();
                    }
                }
            }
        }
    }
    /**
     * Gera uma esfera aleatória de um tipo específico.
     * @return Um tipo de esfera aleatório.
     */
    public Tipo gerarEsferaAleatoria() {
        Random random = new Random();
        int aleatorio = random.nextInt(1, 8);
        switch (aleatorio) {
            case 1:
                return Tipo.Caveira;
            case 2:
                return Tipo.Fogo;
            case 3:
                return Tipo.Gelo;
            case 4:
                return Tipo.Raio;
            case 5:
                return Tipo.Natureza;
            case 6:
                return Tipo.Ouro;
            case 7:
                return Tipo.Experiencia;
            default:
                return Tipo.ERRO;
        }
    }

    /**
     * Verifica se uma sequência de esferas foi formada em uma posição específica.
     * @param i Índice da linha.
     * @param j Índice da coluna.
     * @return true se uma sequência foi formada, false caso contrário.
     */
    public boolean isFormada(int i, int j) {
        if (posicaoEmTipos[i][j] != null) {
            if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                return true;
            }
            if (j <= 5 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j + 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j + 2]) {
                return true;
            }
            if (j >= 1 && j <= 6 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j + 1]) {
                return true;
            }

            if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                return true;
            }
            if (i <= 5 && posicaoEmTipos[i][j] == posicaoEmTipos[i + 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i + 2][j]) {
                return true;
            }
            if (i >= 1 && i <= 6 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i + 1][j]) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Mostra o tabuleiro atual no console.
     */
    public void mostrarTabuleiro() {
        reiniciarSeNecessario();
        for (int i = 0; i < posicaoEmString.length; i++) {
            System.out.print("      ");
            for (int j = 0; j < posicaoEmString.length; j++) {
                switch (posicaoEmTipos[i][j]) {
                    case Caveira:
                        posicaoEmString[i][j] = "\u001B[1m" + "C" + ANSI_RESET;
                        break;
                    case Fogo:
                        posicaoEmString[i][j] = "\u001B[31m" + "F" + ANSI_RESET;
                        break;
                    case Gelo:
                        posicaoEmString[i][j] = "\u001B[34m" + "G" + ANSI_RESET;
                        break;
                    case Raio:
                        posicaoEmString[i][j] = "\u001B[33m" + "R" + ANSI_RESET;
                        break;
                    case Natureza:
                        posicaoEmString[i][j] = "\u001B[32m" + "N" + ANSI_RESET;
                        break;
                    case Ouro:
                        posicaoEmString[i][j] = "\u001B[4m" + "\u001B[93m" + "O" + ANSI_RESET;
                        break;
                    case Experiencia:
                        posicaoEmString[i][j] = "\u001B[35m" + "E" + ANSI_RESET;
                        break;
                    case null:
                        posicaoEmString[i][j] = " " + ANSI_RESET;
                        break;
                    default:
                        posicaoEmString[i][j] = " " + ANSI_RESET;
                        break;
                }
                if (posicaoSelecionada[0] == i && posicaoSelecionada[1] == j) {
                    System.out.printf("%c%s%c",Configuracoes.destaque[0],posicaoEmString[i][j],Configuracoes.destaque[1]);
                } else {
                    System.out.printf(" %s ",posicaoEmString[i][j]);
                }
                
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Verifica se o tabuleiro gerado é possível de jogar (não possui combinações formadas).
     * @return true se o tabuleiro é possível, false caso contrário.
     */
    public boolean isTabuleiroPossivel() {
        for (int i = 0; i < posicaoEmString.length; i++) {
            for (int j = 0; j < posicaoEmString.length; j++) {
                if (isFormada(i, j)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * Move a posição selecionada para cima.
     */
    public void movimentarParaCima() {
        if (posicaoSelecionada[0] > 0) posicaoSelecionada[0]--;
    }
    
    /**
     * Move a posição selecionada para a esquerda.
     */
    public void movimentarParaEsquerda() {
        if (posicaoSelecionada[1] > 0) posicaoSelecionada[1]--;
    }
    
    /**
     * Move a posição selecionada para baixo.
     */
    public void movimentarParaBaixo() {
        if (posicaoSelecionada[0] < 7) posicaoSelecionada[0]++;
    }
    
    /**
     * Move a posição selecionada para a direita.
     */
    public void movimentarParaDireita() {
        if (posicaoSelecionada[1] < 7) posicaoSelecionada[1]++;
    }

    /**
     * Troca a posição selecionada com a posição acima.
     */
    public void trocarCima() {
        try {
            Tipo trocador;
            trocador = posicaoEmTipos[posicaoSelecionada[0] - 1][posicaoSelecionada[1]];
            posicaoEmTipos[posicaoSelecionada[0] - 1][posicaoSelecionada[1]] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
            posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            if (isTabuleiroPossivel()) {
                trocador = posicaoEmTipos[posicaoSelecionada[0] - 1][posicaoSelecionada[1]];
                posicaoEmTipos[posicaoSelecionada[0] - 1][posicaoSelecionada[1]] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
                posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            } else {
                contarEApagarTudo();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }
    
    /**
     * Troca a posição selecionada com a posição à esquerda.
     */
    public void trocarEsquerda() {
        try {
            Tipo trocador;
            trocador = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] - 1];
            posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] - 1] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
            posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            if (isTabuleiroPossivel()) {
                trocador = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] - 1];
                posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] - 1] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
                posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            } else {
                contarEApagarTudo();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }
    
    /**
     * Método para trocar um elemento para baixo no tabuleiro, se possível.
     * Se a troca for válida, verifica se forma combinações; caso contrário, desfaz a troca.
     */
    public void trocarBaixo() {
        try {
            Tipo trocador;
            trocador = posicaoEmTipos[posicaoSelecionada[0] + 1][posicaoSelecionada[1]];
            posicaoEmTipos[posicaoSelecionada[0] + 1][posicaoSelecionada[1]] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
            posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            if (isTabuleiroPossivel()) {
                trocador = posicaoEmTipos[posicaoSelecionada[0] + 1][posicaoSelecionada[1]];
                posicaoEmTipos[posicaoSelecionada[0] + 1][posicaoSelecionada[1]] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
                posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            } else {
                contarEApagarTudo();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }

    /**
     * Método para trocar um elemento para a direita no tabuleiro, se possível.
     * Se a troca for válida, verifica se forma combinações; caso contrário, desfaz a troca.
     */
    public void trocarDireita() {
        try {
            Tipo trocador;
            trocador = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] + 1];
            posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] + 1] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
            posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            if (isTabuleiroPossivel()) {
                trocador = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] + 1];
                posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1] + 1] = posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]];
                posicaoEmTipos[posicaoSelecionada[0]][posicaoSelecionada[1]] = trocador;
            } else {
                contarEApagarTudo();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            
        }
    }
    
    /**
     * Método para contar e apagar combinações de caveiras no tabuleiro.
     * As caveiras formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de caveiras apagadas no jogo.
     */
    public void contarEApagarCaveiras() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Caveira) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    } else if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        caveirasApagadas = quantidadeApagadas;
    }

    /**
     * Método para contar e apagar combinações de fogos no tabuleiro.
     * Os fogos formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de fogos apagados no jogo.
     */
    public void contarEApagarFogos() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Fogo) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    }
                    if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        fogosApagados = quantidadeApagadas;
    }

    /**
     * Método para contar e apagar combinações de gelos no tabuleiro.
     * Os gelos formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de gelos apagados no jogo.
     */
    public void contarEApagarGelos() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Gelo) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    }
                    if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        gelosApagados = quantidadeApagadas;
    }
    
    /**
     * Método para contar e apagar combinações de raios no tabuleiro.
     * Os raios formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de raios apagados no jogo.
     */
    public void contarEApagarRaios() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Raio) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    }
                    if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        raiosApagados = quantidadeApagadas;
    }
    
    /**
     * Método para contar e apagar combinações de naturezas no tabuleiro.
     * As naturezas formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de naturezas apagadas no jogo.
     */
    public void contarEApagarNaturezas() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Natureza) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    }
                    if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        naturezasApagadas = quantidadeApagadas;
    }

    /**
     * Método para contar e apagar combinações de ouros no tabuleiro.
     * Os ouros formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de ouros apagados no jogo.
     */
    public void contarEApagarOuros() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Ouro) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    }
                    if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        ourosApagados = quantidadeApagadas;
    }
    
    /**
     * Método para contar e apagar combinações de experiências no tabuleiro.
     * As experiências formam combinações se houver 3 ou mais em linha (horizontal ou vertical).
     * Atualiza o número de experiências apagadas no jogo.
     */
    public void contarEApagarExperiencias() {
        boolean[][] apagadas = new boolean[8][8];
        int quantidadeApagadas = 0;
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (posicaoEmTipos[i][j] == Tipo.Experiencia) {
                    if (j >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 1] && posicaoEmTipos[i][j] == posicaoEmTipos[i][j - 2]) {
                        apagadas[i][j] = true;
                        apagadas[i][j - 1] = true;
                        apagadas[i][j - 2] = true;
                    }
                    if (i >= 2 && posicaoEmTipos[i][j] == posicaoEmTipos[i - 1][j] && posicaoEmTipos[i][j] == posicaoEmTipos[i - 2][j]) {
                        apagadas[i][j] = true;
                        apagadas[i - 1][j] = true;
                        apagadas[i - 2][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < apagadas.length; i++) {
            for (int j = 0; j < apagadas.length; j++) {
                if (apagadas[i][j] == true) {
                    quantidadeApagadas++;
                    posicaoEmTipos[i][j] = null;
                }
            }
        }
        experienciasApagadas = quantidadeApagadas;
    }
    
    /**
     * Método para contar e apagar todas as combinações possíveis no tabuleiro.
     * Chama métodos individuais para cada tipo de elemento para contar e apagar as combinações.
     */
    public void contarEApagarTudo() {
        contarEApagarGelos();
        contarEApagarNaturezas();
        contarEApagarCaveiras();
        contarEApagarExperiencias();
        contarEApagarFogos();
        contarEApagarOuros();
        contarEApagarRaios();
    }
    
    /**
     * Método para descer as esferas no tabuleiro, movendo cada esfera para a posição vazia imediatamente abaixo dela.
     * Esferas que estiverem na base do tabuleiro não podem descer mais.
     */
    public void descerEsferas() {
        while (temEsferasADescer()) {
            for (int i = 0; i < posicaoEmTipos.length - 1; i++) {
                for (int j = 0; j < posicaoEmTipos.length; j++) {
                    if (posicaoEmTipos[i][j] != null && posicaoEmTipos[i + 1][j] == null) {
                        posicaoEmTipos[i + 1][j] = posicaoEmTipos[i][j];
                        posicaoEmTipos[i][j] = null;
                    }
                }
            }
        }
    }
    
    /**
     * Verifica se há esferas que podem ser descidas no tabuleiro.
     *
     * @return true se há pelo menos uma esfera que pode ser movida para baixo, false caso contrário.
     */
    public boolean temEsferasADescer() {
        for (int i = 0; i < posicaoEmTipos.length - 1; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                if (posicaoEmTipos[i][j] != null && posicaoEmTipos[i + 1][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Método para gerar novas esferas nas posições vazias do tabuleiro.
     * Esferas são geradas aleatoriamente.
     */
    public void gerarNovasEsferas() {
        for (int i = 0; i < posicaoEmTipos.length; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                if (posicaoEmTipos[i][j] == null) {
                    posicaoEmTipos[i][j] = gerarEsferaAleatoria();
                }
            }
        }
    }
    
    /**
     * Método para reiniciar o tabuleiro se não houver mais movimentos válidos.
     * Testa todas as trocas possíveis e, se nenhuma for válida, gera um novo tabuleiro aleatório.
     */
    public void reiniciarSeNecessario() {
        Tipo controle;
        boolean necessario = true;
        for (int i = 0; i < posicaoEmTipos.length; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                if (i > 0 && i < posicaoEmTipos.length - 1 && j > 0 && j < posicaoEmTipos.length - 1) {

                    // Testando a troca pra baixo
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i + 1][j];
                    posicaoEmTipos[i + 1][j] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i + 1][j];
                    posicaoEmTipos[i + 1][j] = controle;

                    // Testando a troca pra cima
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i - 1][j];
                    posicaoEmTipos[i - 1][j] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i - 1][j];
                    posicaoEmTipos[i - 1][j] = controle;

                    // Testando a troca pra direita
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j + 1];
                    posicaoEmTipos[i][j + 1] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j + 1];
                    posicaoEmTipos[i][j + 1] = controle;

                    // Testando a troca pra esquerda
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j - 1];
                    posicaoEmTipos[i][j - 1] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j - 1];
                    posicaoEmTipos[i][j - 1] = controle;

                } if (i > 0 && i < posicaoEmTipos.length - 1) {
                    
                    // Testando a troca pra baixo
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i + 1][j];
                    posicaoEmTipos[i + 1][j] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i + 1][j];
                    posicaoEmTipos[i + 1][j] = controle;

                    // Testando a troca pra cima
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i - 1][j];
                    posicaoEmTipos[i - 1][j] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i - 1][j];
                    posicaoEmTipos[i - 1][j] = controle;
                    
                } if (j > 0 && j < posicaoEmTipos.length - 1) {

                    // Testando a troca pra direita
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j + 1];
                    posicaoEmTipos[i][j + 1] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j + 1];
                    posicaoEmTipos[i][j + 1] = controle;

                    // Testando a troca pra esquerda
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j - 1];
                    posicaoEmTipos[i][j - 1] = controle;
                    if (isTabuleiroPossivel() == false) {
                        necessario = false;
                    }
                    controle = posicaoEmTipos[i][j];
                    posicaoEmTipos[i][j] = posicaoEmTipos[i][j - 1];
                    posicaoEmTipos[i][j - 1] = controle;
                    
                }
            }
        }
        if (necessario) {
            gerarTabuleiroAleatorio();
        }
    }
}