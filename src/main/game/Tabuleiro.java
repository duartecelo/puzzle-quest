package main.game;
import java.util.Random;

public class Tabuleiro {
    Tipo[][] posicaoEmTipos = new Tipo[8][8];
    char[][] posicaoEmChar = new char[8][8];

    public void gerarTabuleiroAleatorio() {
        for (int i = 0; i < posicaoEmTipos.length; i++) {
            for (int j = 0; j < posicaoEmTipos.length; j++) {
                posicaoEmTipos[i][j] = gerarEsferaAleatoria();
            }
        }
        while (isTabuleiroPossivel() == false) {
            for (int i = 0; i < posicaoEmChar.length; i++) {
                for (int j = 0; j < posicaoEmChar.length; j++) {
                    while (isProibida(i, j)) {
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

    public boolean isProibida(int i, int j) {
        
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

        return false;
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < posicaoEmChar.length; i++) {
            for (int j = 0; j < posicaoEmChar.length; j++) {
                switch (posicaoEmTipos[i][j]) {
                    case Caveira:
                        posicaoEmChar[i][j] = 'C';
                        break;
                    case Fogo:
                        posicaoEmChar[i][j] = 'F';
                        break;
                    case Gelo:
                        posicaoEmChar[i][j] = 'G';
                        break;
                    case Raio:
                        posicaoEmChar[i][j] = 'R';
                        break;
                    case Natureza:
                        posicaoEmChar[i][j] = 'N';
                        break;
                    case Ouro:
                        posicaoEmChar[i][j] = 'O';
                        break;
                    case Experiencia:
                        posicaoEmChar[i][j] = 'E';
                        break;
                    default:
                        posicaoEmChar[i][j] = ' ';
                        break;
                }
                System.out.printf(" %c",posicaoEmChar[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isTabuleiroPossivel() {
        for (int i = 0; i < posicaoEmChar.length; i++) {
            for (int j = 0; j < posicaoEmChar.length; j++) {
                if (isProibida(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}