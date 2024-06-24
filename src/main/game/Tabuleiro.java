package main.game;
import java.util.Random;

public class Tabuleiro {
    Tipo[][] posicaoEmTipos = new Tipo[8][8];
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

    public void gerarTabuleiroAleatorio() {
        for (int i = 0; i < posicaoEmTipos.length; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                posicaoEmTipos[i][j] = gerarEsferaAleatoria();
            }
        }
        while (isTabuleiroPossivel() == false) {
            for (int i = 0; i < posicaoEmString.length; i++) {
                for (int j = 0; j < posicaoEmString.length; j++) {
                    while (isFormada(i, j)) {
                        posicaoEmTipos[i][j] = gerarEsferaAleatoria();
                    }
                }
            }
        }
    }

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

    public void mostrarTabuleiro() {
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

    public void movimentarParaCima() {
        if (posicaoSelecionada[0] > 0) posicaoSelecionada[0]--;
    }

    public void movimentarParaEsquerda() {
        if (posicaoSelecionada[1] > 0) posicaoSelecionada[1]--;
    }

    public void movimentarParaBaixo() {
        if (posicaoSelecionada[0] < 7) posicaoSelecionada[0]++;
    }

    public void movimentarParaDireita() {
        if (posicaoSelecionada[1] < 7) posicaoSelecionada[1]++;
    }

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
    
    public void contarEApagarTudo() {
        contarEApagarGelos();
        contarEApagarNaturezas();
        contarEApagarCaveiras();
        contarEApagarExperiencias();
        contarEApagarFogos();
        contarEApagarOuros();
        contarEApagarRaios();
    }

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

    public void gerarNovasEsferas() {
        for (int i = 0; i < posicaoEmTipos.length; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                if (posicaoEmTipos[i][j] == null) {
                    posicaoEmTipos[i][j] = gerarEsferaAleatoria();
                }
            }
        }
    }

    public boolean precisaReiniciar() {




        return false;
    }
}